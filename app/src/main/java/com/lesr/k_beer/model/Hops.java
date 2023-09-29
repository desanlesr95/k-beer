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
public class Hops implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    public int id_hop;
    @ColumnInfo(name = "id_amount")
    public int id_amount;

    @ColumnInfo(name = "ingredientsId")
    public int ingredientsID;

    @ColumnInfo(name = "name")
    @SerializedName("name")
    public String name;

    @SerializedName("amount")@Ignore
    public Amount amount;

    @ColumnInfo(name = "add")
    @SerializedName("add")
    public String add;

    @ColumnInfo(name = "attribute")
    @SerializedName("attribute")
    public String attribute;

    public Hops(){

    }


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
