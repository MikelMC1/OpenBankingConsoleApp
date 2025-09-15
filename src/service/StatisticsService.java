package service;

import dto.Balance;
import dto.Transaction;
import dto.User;
import excpetion.AuthenticationException;
import excpetion.TransactionNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


public class StatisticsService {


    private List<Transaction> transactions = new ArrayList<>();

    private TransactionsService transactionsService;

    public StatisticsService(TransactionsService transactionsService) {
        this.transactionsService = transactionsService;
    }

    public List<Transaction> getTransactionsTranscript(long userId) throws TransactionNotFoundException {
        List<Transaction> result = transactionsService.getTransactions().stream()
                .filter(t -> t.getFromUserId() == userId) // only sender
                .toList();

        if (result.isEmpty()) {
            throw new TransactionNotFoundException("No transactions found for user " + userId);
        }

        return result;
    }















}
