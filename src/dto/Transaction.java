package dto;

import enums.TransactionStatus;
import enums.TransactionType;

import java.util.Date;

public class Transaction {

    private long transactionId;

    private Date date;

    private TransactionStatus status;

    private TransactionType type;

    private long fromUserId;

    private  Long toUserId;


    private double amount;

    public Transaction(long transactionId, Date date, TransactionStatus status, TransactionType type, long fromUserId, Long toUserId, double amount) {
        this.transactionId = transactionId;
        this.date = date;
        this.status = status;
        this.type = type;
        this.fromUserId = fromUserId;
        this.toUserId = toUserId;
        this.amount = amount;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public long getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(long fromUserId) {
        this.fromUserId = fromUserId;
    }

    public long getToUserId() {
        return toUserId;
    }

    public void setToUserId(long toUserId) {
        this.toUserId = toUserId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(long transactionId) {
        this.transactionId = transactionId;
    }

    public TransactionStatus getStatus() {
        return status;
    }

    public void setStatus(TransactionStatus status) {
        this.status = status;
    }


    @Override
    public String toString() {
        return "Transaction{" +
                "transactionId=" + transactionId +
                ", date=" + date +
                ", status=" + status +
                ", type=" + type +
                ", fromUserId=" + fromUserId +
                ", toUserId=" + toUserId +
                ", amount=" + amount +
                '}';
    }
}
