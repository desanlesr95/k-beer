package com.lesr.k_beer.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import lombok.Data;
import lombok.ToString;

@Data
public class Beer implements Serializable {
    @SerializedName("id")
    int id;
    @SerializedName("name")
    String name;
    @SerializedName("tagline")
    String tagline;
    @SerializedName("description")
    String description;
    @SerializedName("image_url")
    String image_url;
    @SerializedName("fisrt_brewed")
    String first_brewed;
    @SerializedName("abv")
    Float abv; // % Alcohol
    @SerializedName("ibu")
    Float ibu; // Amargor lúpliups utilizado
    @SerializedName("attenuation_level")
    Float attenuation_level; // Nievel de azucares mas bajo más dulce
    @SerializedName("srm")
    Float srm;
    @SerializedName("ph")
    Float ph;
    @SerializedName("food_pairing") // Recomendaciones de alimnetos
    String[] food_pairing;
    @SerializedName("brewers_tips") //Consejos de consumo
    String brewers_tips;
    @SerializedName("contributed_by")
    String contributed_by;


}
