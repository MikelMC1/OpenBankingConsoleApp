package service;
import dto.Transaction;
import excpetion.TransactionNotFoundException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.List;



public class StatisticsService {


    private List<Transaction> transactions = new ArrayList<>();

    private TransactionsService transactionsService;

    public StatisticsService(TransactionsService transactionsService) {
        this.transactionsService = transactionsService;
    }

    public List<Transaction> getTransactionsTranscript(long userId) throws TransactionNotFoundException {
        List<Transaction> result = transactionsService.getTransactions().stream()
                .filter(t -> t.getFromUserId() == userId)
                .toList();

        if (result.isEmpty()) {
            throw new TransactionNotFoundException("No transactions found for user " + userId);
        }

        return result;
    }


    //TODO
    public Map<Long, Integer> getMostActiveUsers() throws TransactionNotFoundException {
        List<Transaction> transactions = transactionsService.getTransactions();

        if (transactions.isEmpty()) {
            throw new TransactionNotFoundException("No transactions found in the system.");
        }

        Map<Long, Integer> activity = new HashMap<>();
        for (Transaction transaction : transactions) {
            activity.put(transaction.getFromUserId(),activity.getOrDefault(transaction.getFromUserId(),0)+1);

        }



        return activity.entrySet()
                .stream()
                .sorted((a, b) -> b.getValue().compareTo(a.getValue()))
                .limit(10)
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));
    }










    }




















