package service;

import dto.Loan;
import dto.Transaction;
import enums.TransactionStatus;
import enums.TransactionType;
import excpetion.AuthenticationException;
import excpetion.InsufficientBalanceException;
import excpetion.LoanNotFoundException;
import excpetion.TransactionNotFoundException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class TransactionsService {

    private List<Transaction> transactions = new ArrayList<>();
    private long transactionCounter = 0L;


    private long getNextTransactionId() {
        return ++transactionCounter;
    }





    private BalanceService balanceService;

    public TransactionsService(BalanceService balanceService) {
        this.balanceService = balanceService;
    }

    public void makeTransfer(long fromUserId, long toUserId, double amount)
            throws AuthenticationException, InsufficientBalanceException {

        balanceService.withdrawMoney(fromUserId, amount);

        balanceService.depositMoney(toUserId, amount);

        long transactionId = getNextTransactionId();


        Transaction transaction = new Transaction(
                transactionId,
                new Date(),
                TransactionStatus.APPROVED,
                TransactionType.TRANSFER,
                fromUserId,
                toUserId,
                amount
        );


        transactions.add(transaction);

        System.out.println("Transferred " + amount + " from User " + fromUserId + " to User " + toUserId);
    }


    public void printTransactionByTransactionId(long transactionId) throws TransactionNotFoundException {
        Transaction transaction = transactions.stream()
                .filter(b -> Objects.equals(b.getTransactionId(), transactionId))
                .findAny()
                .orElseThrow(() -> new TransactionNotFoundException("Transaction with Id : " + transactionId + "' does not exist"));

        System.out.println(transaction);

    }



    //TODO
    public void chargeBack(long transactionId) throws TransactionNotFoundException, AuthenticationException, InsufficientBalanceException {
        Transaction transaction = transactions.stream()
                .filter(b -> Objects.equals(b.getTransactionId(), transactionId))
                .findAny()
                .orElseThrow(() -> new TransactionNotFoundException("Transaction with Id : " + transactionId + "' does not exist"));

        long fromUserId= transaction.getFromUserId();
        long toUserId = transaction.getToUserId();
        double amount = transaction.getAmount();

        balanceService.withdrawMoney(toUserId, amount);

        balanceService.depositMoney(fromUserId, amount);


        long newTransactionId = getNextTransactionId();
        Transaction chargebackTransaction = new Transaction(
                newTransactionId,
                new Date(),
                TransactionStatus.APPROVED,
                TransactionType.CHARGEBACK,
                fromUserId,
                toUserId,
                amount
        );
        transactions.add(chargebackTransaction);






    }

    //TODO
    public void getTransactions() {



    }
}
