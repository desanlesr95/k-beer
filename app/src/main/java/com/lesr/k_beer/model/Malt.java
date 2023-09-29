package com.lesr.k_beer.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
@Entity
public class Malt implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    public int id_malt;
    @ColumnInfo(name = "ingredientsId")
    public int ingredientsId;

    @ColumnInfo(name = "id_amount")
    public int id_amount;
    @SerializedName("name")
    public String name;
    @SerializedName("amount")@Ignore
    public Amount amount;

    public Malt(){

    }

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
