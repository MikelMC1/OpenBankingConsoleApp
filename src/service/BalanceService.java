package service;

import dto.Balance;
import enums.AccountType;
import excpetion.AuthenticationException;
import excpetion.InsufficientBalanceException;
import java.util.*;

public class BalanceService {

    private List<Balance> balances = new ArrayList<>(List.of(
            new Balance(AccountType.DEBIT, 1L, new Date(), 500),
            new Balance(AccountType.CREDIT, 2L, new Date(), 0),
            new Balance(AccountType.DEBIT, 3L, new Date(), 500)
    ));



    public void createBalanceForUser(Long userId) {

        balances.add(new Balance(AccountType.DEBIT, userId, new Date(), 0));
    }


    public void printBalanceByUserId(Long userId) throws AuthenticationException {
        Balance balance = balances.stream()
                .filter(b -> Objects.equals(b.getUserId(), userId))
                .findAny()
                .orElseThrow(() -> new AuthenticationException("User '" + userId + "' does not exist"));

        System.out.println("User " + balance.getUserId() + " balance: " + balance.getAmmount());
    }


    public void removeMoney(long userId, double amount) throws InsufficientBalanceException, AuthenticationException {

        Balance balance = balances.stream()
                .filter(b -> Objects.equals(b.getUserId(), userId))
                .findFirst()
                .orElseThrow(() -> new AuthenticationException("User '" + userId + "' does not exist"));

        if (balance.getAmmount() < amount) {
            throw new InsufficientBalanceException("User '" + userId + "' has insufficient funds");
        }

        balance.setAmmount(balance.getAmmount() - amount);
        System.out.println("Withdrawn " + amount + ". New balance: " + balance.getAmmount());
    }


    public void addMoney(long userId, double amount) throws AuthenticationException {
        Balance balance = balances.stream()
                .filter(b -> Objects.equals(b.getUserId(), userId))
                .findFirst()
                .orElseThrow(() -> new AuthenticationException("User '" + userId + "' does not exist"));


        balance.setAmmount(balance.getAmmount() +    amount);


    }

    public double getBalanceByUserId(long userId) throws InsufficientBalanceException {
        return balances.stream()
                .filter(b -> b.getUserId() == userId)
                .findFirst()
                .map(Balance::getAmmount)
                .orElseThrow(() -> new InsufficientBalanceException("User '" + userId + "' has no balance record"));
    }


}
