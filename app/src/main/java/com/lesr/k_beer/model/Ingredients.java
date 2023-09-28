package com.lesr.k_beer.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class Ingredients implements Parcelable {
    @SerializedName("malt")
    Malt[] malt;

    @SerializedName("hops")
    Hops[] hops;

    @SerializedName("yeast")
    String yeast;

    protected Ingredients(Parcel in) {
        malt = in.createTypedArray(Malt.CREATOR);
        hops = in.createTypedArray(Hops.CREATOR);
        yeast = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedArray(malt, flags);
        dest.writeTypedArray(hops, flags);
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
