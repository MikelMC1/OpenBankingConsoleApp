package dto;

import service.LoanStatus;

import java.util.Date;

public class Loan {
    private long id;

    private long userId;

    private Date creationDate;

    private Date nextPaymentDate;

    private double totalLoanAmount;

    private double monthlyLoanAmount;

    private double remainingLoanAmount;

    private int termYears   ;

    private LoanStatus status;


    public Loan(long id, long userId, Date creationDate, double totalLoanAmount, int termYears  ) {
        this.id = id;
        this.userId = userId;
        this.creationDate = creationDate;
        this.nextPaymentDate = addOneMonth(creationDate);
        this.totalLoanAmount = totalLoanAmount;
        this.remainingLoanAmount = totalLoanAmount;
        this.termYears = termYears;
        this.monthlyLoanAmount = totalLoanAmount / (termYears * 12);
        this.status = new LoanStatus(this);
    }


    public double getMonthlyLoanAmount() {
        return totalLoanAmount / (termYears * 12); // convert years to months
    }

    public  Date addOneMonth(Date date) {
        java.util.Calendar cal = java.util.Calendar.getInstance();
        cal.setTime(date);
        cal.add(java.util.Calendar.MONTH, 1);
        return cal.getTime();
    }
    public long getId() {
        return id;
    }

    public LoanStatus getStatus() {
        return status;
    }

    public void setStatus(LoanStatus status) {
        this.status = status;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public double getTotalLoanAmount() {
        return totalLoanAmount;
    }

    public Date getNextPaymentDate() {
        return nextPaymentDate;
    }

    public void setTotalLoanAmount(double totalLoanAmount) {
        this.totalLoanAmount = totalLoanAmount;
    }



    public void setMonthlyLoanAmount(double monthlyLoanAmount) {
        this.monthlyLoanAmount = monthlyLoanAmount;
    }

    public double getRemainingLoanAmount() {
        return remainingLoanAmount;
    }

    public void setRemainingLoanAmount(double remainingLoanAmount) {
        this.remainingLoanAmount = remainingLoanAmount;
    }

    public void setNextPaymentDate(Date nextPaymentDate) {
        this.nextPaymentDate = nextPaymentDate;
    }

    public int getTermYears() { return termYears; }

    public void setTermYears(int termYears) { this.termYears = termYears; }

    @Override
    public String toString() {
        return "Loan{" +
                "id=" + id +
                ", userId=" + userId +
                ", creationDate=" + creationDate +
                ", nextPaymentDate=" + nextPaymentDate +
                ", totalLoanAmount=" + totalLoanAmount +
                ", monthlyLoanAmount=" + getMonthlyLoanAmount() +
                ", remainingLoanAmount=" + remainingLoanAmount +
                ", termYears=" + termYears + // changed
                '}';

    }

}
