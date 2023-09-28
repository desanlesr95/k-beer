package com.lesr.k_beer.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class Malt implements Parcelable {
    @SerializedName("name")
    String name;

    @SerializedName("amount")
    Amount amount;

    protected Malt(Parcel in) {
        name = in.readString();
        amount = in.readParcelable(Amount.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeParcelable(amount, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Malt> CREATOR = new Creator<Malt>() {
        @Override
        public Malt createFromParcel(Parcel in) {
            return new Malt(in);
        }

        @Override
        public Malt[] newArray(int size) {
            return new Malt[size];
        }
    };
}
