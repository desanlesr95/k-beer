package com.lesr.k_beer.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;
import lombok.Data;


@Data
public class Beer implements Parcelable{
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
    @SerializedName("ingredients")
    Ingredients ingredients;

    protected Beer(Parcel in) {
        // Leer los datos del Parcel y asignarlos a las variables miembro
        id = in.readInt();
        name = in.readString();
        tagline = in.readString();
        description = in.readString();
        // ... y así sucesivamente para las otras variables miembro
    }

    public static final Creator<Beer> CREATOR = new Creator<Beer>() {
        @Override
        public Beer createFromParcel(Parcel in) {
            return new Beer(in);
        }

        @Override
        public Beer[] newArray(int size) {
            return new Beer[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(tagline);
        dest.writeString(description);
        dest.writeString(image_url);
        dest.writeString(first_brewed);
        dest.writeFloat(abv);
        dest.writeFloat(ibu);
        dest.writeFloat(attenuation_level);
        dest.writeFloat(srm);
        dest.writeFloat(ph);
        dest.writeStringArray(food_pairing);
        dest.writeString(brewers_tips);
        dest.writeString(contributed_by);
        dest.writeParcelable(ingredients,flags);
    }





}
