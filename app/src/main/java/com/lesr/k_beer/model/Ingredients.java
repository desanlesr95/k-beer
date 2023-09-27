package com.lesr.k_beer.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import lombok.Data;

@Data
public class Ingredients implements Serializable {
    @SerializedName("malt")
    String malt;

    @SerializedName("hops")
    String hops;

    @SerializedName("yeast")
    String yeast;
}
