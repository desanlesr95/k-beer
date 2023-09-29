package com.lesr.k_beer.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
@Entity
public class Amount implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    public int id_amount;
    @ColumnInfo(name = "value")
    @SerializedName("value")
    public Float value;

    @ColumnInfo(name = "unit")
    @SerializedName("unit")
    public String unit;


    public Amount() {
    }

    protected Amount(Parcel in) {
        value = in.readFloat();
        unit = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeFloat(value);
        dest.writeString(unit);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Amount> CREATOR = new Creator<Amount>() {
        @Override
        public Amount createFromParcel(Parcel in) {
            return new Amount(in);
        }

        @Override
        public Amount[] newArray(int size) {
            return new Amount[size];
        }
    };
}
