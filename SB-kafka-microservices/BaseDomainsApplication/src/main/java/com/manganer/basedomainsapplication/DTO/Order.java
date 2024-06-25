package com.manganer.basedomainsapplication.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    private String orderId;
    private String name;
    private int qty;
    private double price;

    public String getCustomerEmail() {
        return "";
    }

    public String getCustomerName() {
        return "";
    }
}
