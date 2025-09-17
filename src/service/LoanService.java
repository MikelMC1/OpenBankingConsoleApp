package service;


import dto.Loan;
import excpetion.AuthenticationException;
import excpetion.InsufficientBalanceException;
import excpetion.LoanNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class LoanService {

    private List<Loan> loans = new ArrayList<>(List.of(
            new Loan(1L, 1L, new Date(),addOneMonth(new Date()), 5000,500,5000)

    ));


    public  Date addOneMonth(Date date) {
        java.util.Calendar cal = java.util.Calendar.getInstance();
        cal.setTime(date);
        cal.add(java.util.Calendar.MONTH, 1);
        return cal.getTime();
    }


    public List<Loan> getAllLoans()  {


        return loans;
    }


    public void  takeLoan(Loan loan) {
        loans.add(loan);
    }



    public void printRemainingLoanByLoanId(Long loanId) throws LoanNotFoundException {
        Loan loan = loans.stream()
                .filter(b -> Objects.equals(b.getUserId(), loanId))
                .findAny()
                .orElseThrow(() -> new LoanNotFoundException("User '" + loanId + "' does not exist"));

        System.out.println("User " + loan.getUserId() + " remaining to pay : " + loan.getRemainingLoanAmount());
    }


    public void makePayment(long loanId,double amount,BalanceService balanceService) throws LoanNotFoundException, InsufficientBalanceException, AuthenticationException {
        Loan loan = loans.stream()
                .filter(b -> Objects.equals(b.getUserId(), loanId))
                .findAny()
                .orElseThrow(() -> new LoanNotFoundException("Loan '" + loanId + "' does not exist"));

        long userId = loan.getUserId();
        double userBalance = balanceService.getBalanceByUserId(userId);

        if (userBalance < amount) {

            balanceService.removeMoney(userId,amount);
            loan.setRemainingLoanAmount(loan.getRemainingLoanAmount() - amount);

        }
        else {
            balanceService.removeMoney(userId,amount);
            loan.setRemainingLoanAmount(loan.getRemainingLoanAmount() - amount);
            loan.setNextPaymentDate(addOneMonth(loan.getNextPaymentDate()));

        }

        loan.getStatus().setLoanStatus(loan.getRemainingLoanAmount() > 0);

    }

    public void deleteLoan(long loanId) throws LoanNotFoundException {
        Loan loan = loans.stream()
                .filter(b -> Objects.equals(b.getId(), loanId))
                .findAny()
                .orElseThrow(() -> new LoanNotFoundException("User '" + loanId + "' does not exist"));

        loans.remove(loan);
        System.out.println("Loan with ID " + loanId + " has been deleted.");

       
    }


    }




