package service;


import dto.User;
import enums.UserRole;
import excpetion.AuthenticationException;

import java.util.*;

public class UserService {



    private List<User> users =  new ArrayList<>(List.of(
            new User(1L, "Mikel", "1234", UserRole.BRANCH_MANAGER),
            new User(2L, "John", "123444", UserRole.SIMPLE_USER),
            new User(3L, "Toni", "12344", UserRole.ADMIN)
    ));

    private BalanceService balanceService;

    public UserService(BalanceService balanceService) {
        this.balanceService = balanceService;
    }





    public boolean isUserAuthenticated(String username, String password) {
        return users.stream()
                .anyMatch(user -> user.getUsername().equals(username)
                        && user.getPassword().equals(password));
    }


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

    public void addUser(String username, String password, User userToAdd) throws AuthenticationException {


        User actingUser = users.stream()
                .filter(u -> u.getUsername().equals(username) && u.getPassword().equals(password))
                .findFirst()
                .orElseThrow(() -> new AuthenticationException("User '" + username + "' does not exist or is not authenticated"));

        if (actingUser.getRole() != UserRole.ADMIN) {
            throw new AuthenticationException("User '" + username + "' is not an ADMIN");
        }


        boolean userExists = users.stream()
                .anyMatch(u -> u.getUsername().equals(userToAdd.getUsername()) || Objects.equals(u.getUserId(), userToAdd.getUserId()));

        if (userExists) {
            throw new AuthenticationException("User '" + userToAdd.getUsername() + "' is already registered");
        }


        users.add(userToAdd);

        balanceService.createBalanceForUser(userToAdd.getUserId());
    }

    public void removeUser(String username, String password,long userId) throws AuthenticationException {
        User actingUser = users.stream()
                .filter(u->u.getUsername().equals(username) && u.getPassword().equals(password))
                .findFirst()
                .orElseThrow(() -> new AuthenticationException("User '" + username + "' does not exist"));

        if (actingUser.getRole() != UserRole.ADMIN) {
            throw new AuthenticationException("User '" + username + "' is not an ADMIN");
        }


        User user = users.stream()
                .filter(b -> Objects.equals(b.getUserId(), userId))
                .findAny()
                .orElseThrow(() -> new AuthenticationException("User '" + userId + "' does not exist or has been recently removed"));

        users.remove(user);

    }


}
