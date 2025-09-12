import dto.Loan;
import excpetion.AuthenticationException;
import excpetion.InsufficientBalanceException;
import excpetion.LoanNotFoundException;
import service.LoanService;
import service.UserService;
import service.BalanceService;

import java.util.Date;

public class Main {

    public static void main(String[] args) throws AuthenticationException, InsufficientBalanceException, LoanNotFoundException {
        UserService userService = new UserService();


        LoanService loanService = new LoanService();
        System.out.println((loanService.getAllLoans()));

        BalanceService balanceService = new BalanceService();

        loanService.takeLoan(new Loan(2L,2L,new Date(),6000,2));



        loanService.printRemainingLoanByLoanId(1L);

        System.out.println((loanService.getAllLoans()));

        loanService.deleteLoan(2L);



        System.out.println((loanService.getAllLoans()));






    }

}