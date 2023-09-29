package com.lesr.k_beer.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.Relation;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;


@ToString
@Entity
public class Beer implements Parcelable{
    @PrimaryKey
    @SerializedName("id")
    public int id;

    @ColumnInfo(name = "name")
    @SerializedName("name")
    public String mName;

    @ColumnInfo(name = "tagline")
    @SerializedName("tagline")@Getter
    public String tagline;

    @ColumnInfo(name = "description")
    @SerializedName("description")@Getter
    public String description;

    @ColumnInfo(name = "image_url")
    @SerializedName("image_url")@Getter
    public String image_url;

    @ColumnInfo(name = "first_brewed")
    @SerializedName("first_brewed")@Getter
   public String first_brewed;

    @ColumnInfo(name = "abv")
    @SerializedName("abv")@Getter
    public Float abv; // % Alcohol

    @ColumnInfo(name = "ibu")
    @SerializedName("ibu")@Getter
    public Float ibu; // Amargor lúpliups utilizado

    @ColumnInfo(name = "attenuation_level")
    @SerializedName("attenuation_level")@Getter
    public Float attenuation_level; // Nievel de azucares mas bajo más dulce

    @ColumnInfo(name = "srm")
    @SerializedName("srm")@Getter
    public Float srm;

    @ColumnInfo(name = "ph")
    @SerializedName("ph")@Getter
    public Float ph;


    @SerializedName("food_pairing")@Getter // Recomendaciones de alimnetos
    public String[] food_pairing;

    @ColumnInfo(name = "brewers_tip")
    @SerializedName("brewers_tips")@Getter //Consejos de consumo
    public String brewers_tips;

    @ColumnInfo(name = "contributed_by")
    @SerializedName("contributed_by")@Getter
    public String contributed_by;


    @SerializedName("ingredients")
    @Ignore
    public Ingredients ingredients;

    public Beer(){}

    public String getName() {
        return mName;
    }

    public Ingredients getIngredients() {
        return ingredients;
    }

    protected Beer(Parcel in) {
        // Leer los datos del Parcel y asignarlos a las variables miembro
        id = in.readInt();
        mName = in.readString();
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
        dest.writeString(mName);
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
