package com.lesr.k_beer.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import lombok.Data;

@Data
public class Amount implements Serializable {
    @SerializedName("value")
    Float value;
    @SerializedName("unit")
    String unit;
}
