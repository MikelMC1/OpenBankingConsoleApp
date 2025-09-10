package service;

import dto.User;
import enums.UserRole;
import excpetion.AuthenticationException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class UserService {

    //refactor to list of users and also refactor the methods affected
    //what map does in stream java
    //how to throw expections with optionals


    private List<User> users = List.of(
            new User(1L, "Mikel", "1234", UserRole.BRANCH_MANAGER),
            new User(2L, "John", "123444", UserRole.SIMPLE_USER)
    );

    // new: map to store balances for users  check


    // constructor to initialize sample balances  check



    //create a method that gives back for a specific user the current amount of money  check

    //1. create a different class called balance and connect it with a user (String accountType, Long userId, Date creationDate)  check
    //2. create the method that retrieves back the balance
    //3. check if the user exists and has balance and is bigger then 0.




    public boolean isUserAuthenticated(String username, String password) {
        return users.stream()
                .anyMatch(user -> user.getUsername().equals(username)
                        && user.getPassword().equals(password));
    }




    // reminder always send user params username password and role as paramaters
    public List<User> getAllUsers(String username,String password,UserRole userRole) throws AuthenticationException {


        boolean userExists = users.stream()
                .anyMatch(user -> user.getUsername().equals(username));

        if (!userExists) {
            throw new AuthenticationException("User '" + username + "' does not exist");
        }
        if (!isUserAuthenticated(username, password)) {
            throw new AuthenticationException(username + "is not authenticated");
        }

        if (userRole != UserRole.BRANCH_MANAGER) {
            throw new AuthenticationException("User '" + username + "' is not a branch manager");
        }


            return users.stream().toList();
    }
}
