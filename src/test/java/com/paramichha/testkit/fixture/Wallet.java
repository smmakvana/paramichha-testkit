package com.paramichha.testkit.fixture;

import java.math.BigDecimal;

/** A plain class (not a record) rebuilt through its field-matching public constructor. */
public class Wallet {
    private final String owner;
    private final int coins;
    private final BigDecimal balance;

    public Wallet(String owner, int coins, BigDecimal balance) {
        this.owner = owner;
        this.coins = coins;
        this.balance = balance;
    }
}
