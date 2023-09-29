package com.lesr.k_beer.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.Relation;
import androidx.room.TypeConverters;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Data;

@Data
@Entity
public class Ingredients implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    public int id_ingredient;


    @ColumnInfo(name = "idBeer")
    public int idBeer;

    @Ignore
    @SerializedName("malt")
    public List<Malt> malt;

    @SerializedName("hops")
    @Ignore
    public List<Hops> hops;

    @ColumnInfo(name = "yeast")
    @SerializedName("yeast")
    public String yeast;

    public Ingredients(){

    }

    protected Ingredients(Parcel in) {
        malt = in.createTypedArrayList(Malt.CREATOR);
        hops = in.createTypedArrayList(Hops.CREATOR);
        yeast = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(malt);
        dest.writeTypedList(hops);
        dest.writeString(yeast);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Ingredients> CREATOR = new Creator<Ingredients>() {
        @Override
        public Ingredients createFromParcel(Parcel in) {
            return new Ingredients(in);
        }

        @Override
        public Ingredients[] newArray(int size) {
            return new Ingredients[size];
        }
    };
}
