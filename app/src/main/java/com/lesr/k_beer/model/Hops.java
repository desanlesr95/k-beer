package com.lesr.k_beer.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class Hops implements Parcelable {

    @SerializedName("name")
    String name;

    @SerializedName("amount")
    Amount amount;

    @SerializedName("add")
    String add;


    @SerializedName("attribute")
    String attribute;


    protected Hops(Parcel in) {
        name = in.readString();
        amount = in.readParcelable(Amount.class.getClassLoader());
        add = in.readString();
        attribute = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeParcelable(amount, flags);
        dest.writeString(add);
        dest.writeString(attribute);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Hops> CREATOR = new Creator<Hops>() {
        @Override
        public Hops createFromParcel(Parcel in) {
            return new Hops(in);
        }

        @Override
        public Hops[] newArray(int size) {
            return new Hops[size];
        }
    };

}
