package com.nostratech.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by fani on 3/16/15.
 */
@Entity
public class QuarterlySales {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String item;

    private double amountQ1;
    private double amountQ2;
    private double amountQ3;
    private double amountQ4;

    public QuarterlySales() {
    }

    public QuarterlySales(String item) {
        this.item = item;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public double getAmountQ1() {
        return amountQ1;
    }

    public void setAmountQ1(double amountQ1) {
        this.amountQ1 = amountQ1;
    }

    public double getAmountQ2() {
        return amountQ2;
    }

    public void setAmountQ2(double amountQ2) {
        this.amountQ2 = amountQ2;
    }

    public double getAmountQ3() {
        return amountQ3;
    }

    public void setAmountQ3(double amountQ3) {
        this.amountQ3 = amountQ3;
    }

    public double getAmountQ4() {
        return amountQ4;
    }

    public void setAmountQ4(double amountQ4) {
        this.amountQ4 = amountQ4;
    }

}
