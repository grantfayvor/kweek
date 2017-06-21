package com.kweek.model;

/**
 * Created by Harrison on 2017-06-17.
 */
public class Authority {

    private String accountType;

    public Authority(AccountType accountType) {
        this.accountType = accountType.toString();
    }

    /**
     * Getter for property 'accountType'.
     *
     * @return Value for property 'accountType'.
     */
    public String getAccountType() {
        return accountType;
    }

    @Override
    public String toString() {
        return "Authority{" +
                "accountType='" + accountType + '\'' +
                '}';
    }
}
