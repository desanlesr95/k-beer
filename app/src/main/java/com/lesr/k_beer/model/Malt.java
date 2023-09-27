package com.lesr.k_beer.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import lombok.Data;

@Data
public class Malt implements Serializable {
    @SerializedName("name")
    String name;

    @SerializedName("amount")
    Amount amount;
}