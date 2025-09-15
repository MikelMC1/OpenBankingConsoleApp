import dto.Balance;
import dto.Loan;
import enums.UserRole;
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

        loanService.takeLoan(new Loan(2L,2L,new Date(),5020,510,5020));
        System.out.println((loanService.getAllLoans()));

        loanService.makePayment(1,200,balanceService);

        loanService.printRemainingLoanByUserId(1L);



    }

}