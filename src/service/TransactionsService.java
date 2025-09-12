package service;

import excpetion.AuthenticationException;
import excpetion.InsufficientBalanceException;

public class TransactionsService {




    private BalanceService balanceService;

    public TransactionsService(BalanceService balanceService) {
        this.balanceService = balanceService;
    }

    public void makeTransfer(long fromUserId, long toUserId, double amount)
            throws AuthenticationException, InsufficientBalanceException {

        balanceService.withdrawMoney(fromUserId, amount);

        balanceService.depositMoney(toUserId, amount);

        System.out.println("Transferred " + amount + " from User " + fromUserId + " to User " + toUserId);
    }

    //TODO
    public void getTransactions() {



    }
}
