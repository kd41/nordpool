package com.nordpool.dao;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class Money implements Serializable {
  private static final long serialVersionUID = 1L;

  private final BigDecimal amount;
  private String currency;

  public Money(String amount, String currency) {
    this.amount = convertToBigDecimal(amount);
    this.currency = currency;
  }

  public Money(double amount, String currency) {
    this.amount = convertToBigDecimal(amount);
    this.currency = currency;
  }

  public Money(String amount) {
    this.amount = BigDecimal.valueOf(Double.valueOf(amount.replaceAll(",", ".")));
    this.currency = "EUR";
  }

  public BigDecimal getAmount() {
    return amount;
  }

  public String getCurrency() {
    return currency;
  }

  public String getStripTrailingZeros() {
    return getAmount().stripTrailingZeros().toPlainString();
  }

  public static BigDecimal convertToBigDecimal(String amount) {
    return BigDecimal.valueOf(Double.valueOf(amount)).setScale(2, RoundingMode.HALF_DOWN);
  }

  public static BigDecimal convertToBigDecimal(double amount) {
    return BigDecimal.valueOf(Double.valueOf(amount)).setScale(2, RoundingMode.HALF_DOWN);
  }

  @Override
  public String toString() {
    return "Money [amount=" + amount + ", currency=" + currency + "]";
  }

}
