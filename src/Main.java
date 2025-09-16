import dto.Loan;
import dto.User;
import enums.UserRole;
import excpetion.AuthenticationException;
import excpetion.InsufficientBalanceException;
import excpetion.LoanNotFoundException;
import service.LoanService;
import service.TransactionsService;
import service.UserService;
import service.BalanceService;
import service.StatisticsService;

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


        //transactionsService.printTransactionByTransactionId(1L);

        transactionsService.chargeBack(1L);

       // transactionsService.printTransactionByTransactionId(2L);

        //balanceService.printBalanceByUserId(1L);
       // balanceService.printBalanceByUserId(2L);



        transactionsService.withdrawMoney(1L,500);
        //balanceService.printBalanceByUserId(1L);
        //transactionsService.printTransactionByTransactionId(3L);


        StatisticsService statisticsService = new StatisticsService(transactionsService);


        //System.out.print(transactionsService.getTransactions());





    System.out.println(statisticsService.getTransactionsTranscript(1L));




    userService.addUser("Toni","12344",new User(15L,"Antonio","12344",UserRole.SIMPLE_USER));

    System.out.println(userService.getAllUsers("Mikel","1234",UserRole.BRANCH_MANAGER));

    userService.removeUser("Toni","12344",15L);

    System.out.println(userService.getAllUsers("Mikel","1234",UserRole.BRANCH_MANAGER));












    }

}