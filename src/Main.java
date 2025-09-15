import dto.Loan;
import excpetion.AuthenticationException;
import excpetion.InsufficientBalanceException;
import excpetion.LoanNotFoundException;
import service.LoanService;
import service.TransactionsService;
import service.UserService;
import service.BalanceService;

import java.util.Date;

public class Main {

    public static void main(String[] args) throws AuthenticationException, InsufficientBalanceException, LoanNotFoundException {
        UserService userService = new UserService();


        LoanService loanService = new LoanService();
        System.out.println((loanService.getAllLoans()));

        BalanceService balanceService = new BalanceService();

        TransactionsService transactionsService = new TransactionsService(balanceService);

        transactionsService.makeTransfer(1L,2L,300);

        balanceService.printBalanceByUserId(1L);
        balanceService.printBalanceByUserId(2L);


        transactionsService.printTransactionByTransactionId(1L);

        transactionsService.chargeBack(1L);

        transactionsService.printTransactionByTransactionId(2L);

        balanceService.printBalanceByUserId(1L);
        balanceService.printBalanceByUserId(2L);











    }

}