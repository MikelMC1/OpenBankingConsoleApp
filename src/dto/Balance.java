package dto;

import enums.AccountType;

import java.util.Date;

public class Balance {
    private AccountType accountType;

    private Long userId;

    private Date creationDate;

    private double ammount;

    public Balance(AccountType accountType, Long userId, Date creationDate, double ammount){
        this.accountType=accountType;
        this.userId=userId;
        this.creationDate = creationDate;
        this.ammount = ammount;

    }

    public AccountType getAccountType() {
        return accountType;
    }

    public Long getUserId() {
        return userId;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public double getAmmount() {
        return ammount;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public void setAmmount(double ammount) {
        this.ammount = ammount;
    }

}
