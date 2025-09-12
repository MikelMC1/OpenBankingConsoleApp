package service;

import dto.Loan;
import excpetion.LoanNotFoundException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class LoanStatus {

    private boolean loanStatus;
    private long loanId;
    private Date nextPaymentDate;
    private int remainingMonths;



    private List<LoanStatus> loanStatusList = new ArrayList<>();




    public LoanStatus(Loan loan) {
        this.loanId = loan.getId();
        this.nextPaymentDate = loan.getNextPaymentDate();
        this.remainingMonths = (int) Math.ceil(loan.getRemainingLoanAmount() / loan.getMonthlyLoanAmount());
        this.loanStatus = loan.getRemainingLoanAmount() > 0;
    }

    // Add a LoanStatus to the list
    public void addLoanStatus(LoanStatus status) {
        loanStatusList.add(status);
    }



    public List<LoanStatus> getLoanStatusList() {
        return loanStatusList;
    }

    public boolean isLoanStatus() {
        return loanStatus;
    }

    public long getLoanId() {
        return loanId;
    }



    public Date getNextPaymentDate() {
        return nextPaymentDate;
    }

    public int getRemainingMonths() {
        return remainingMonths;
    }



    public void setLoanStatus(boolean loanStatus) {
        this.loanStatus = loanStatus;
    }

    public void setLoanId(long loanId) {
        this.loanId = loanId;
    }




    public void setNextPaymentDate(Date nextPaymentDate) {
        this.nextPaymentDate = nextPaymentDate;
    }

    public void setRemainingMonths(int remainingMonths) {
        this.remainingMonths = remainingMonths;
    }

    //TODO
    public void printLoanStatusByLoanId(Long loanId) throws LoanNotFoundException{
        LoanStatus status = loanStatusList.stream()
                .filter(b -> Objects.equals(b.getLoanId(), loanId))
                .findAny()
                .orElseThrow(() -> new LoanNotFoundException("Loan with the ID :'" + loanId + "' does not exist"));

        System.out.println(status.isLoanStatus());
    }

    //TODO
    public void deleteAllInactiveLoansFromLoansList(List<Loan> loans) {
        boolean removed = loans.removeIf(loan -> !loan.getStatus().isLoanStatus());

        if (!removed) {
            System.out.println("No inactive loans found in loans list.");
        } else {
            System.out.println("All inactive loans have been deleted from loans list.");
        }
    }









}
