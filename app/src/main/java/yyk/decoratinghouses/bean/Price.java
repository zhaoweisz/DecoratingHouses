package yyk.decoratinghouses.bean;

import android.os.Parcel;
import android.os.Parcelable;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Unique;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by wangyao on 2017/7/30.
 */

@Entity
public class Price implements Parcelable {

    @Id(autoincrement = true)
    private Long id;
    @NotNull@Unique
    private String name;
    private float price;
    @NotNull
    private Long o_id;
    @NotNull
    private String o_name;
    @NotNull
    private Long d_id;
    @NotNull
    private String d_name;
    @NotNull
    private float number;
    @NotNull
    private float total;
    @Generated(hash = 1854784257)
    public Price(Long id, @NotNull String name, float price, @NotNull Long o_id,
            @NotNull String o_name, @NotNull Long d_id, @NotNull String d_name,
            float number, float total) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.o_id = o_id;
        this.o_name = o_name;
        this.d_id = d_id;
        this.d_name = d_name;
        this.number = number;
        this.total = total;
    }
    @Generated(hash = 812905808)
    public Price() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public float getPrice() {
        return this.price;
    }
    public void setPrice(float price) {
        this.price = price;
    }
    public Long getO_id() {
        return this.o_id;
    }
    public void setO_id(Long o_id) {
        this.o_id = o_id;
    }
    public String getO_name() {
        return this.o_name;
    }
    public void setO_name(String o_name) {
        this.o_name = o_name;
    }
    public Long getD_id() {
        return this.d_id;
    }
    public void setD_id(Long d_id) {
        this.d_id = d_id;
    }
    public String getD_name() {
        return this.d_name;
    }
    public void setD_name(String d_name) {
        this.d_name = d_name;
    }

    public float getNumber() {
        return this.number;
    }
    public void setNumber(float number) {
        this.number = number;
    }
    public float getTotal() {
        return this.total;
    }
    public void setTotal(float total) {
        this.total = total;
    }

    public static final Creator<Price> CREATOR = new Creator<Price>() {
        @Override
        public Price createFromParcel(Parcel parcel) {
            return new Price(parcel.readLong(), parcel.readString(),
                    parcel.readFloat(),parcel.readLong(),parcel.readString(),
                    parcel.readLong(),parcel.readString(),
                    parcel.readFloat(),parcel.readFloat());
        }

        @Override
        public Price[] newArray(int i) {
            return new Price[i];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(getId());
        parcel.writeString(getName());
        parcel.writeFloat(getPrice());
        parcel.writeLong(getO_id());
        parcel.writeString(getO_name());
        parcel.writeLong(getD_id());
        parcel.writeString(getD_name());
        parcel.writeFloat(getNumber());
        parcel.writeFloat(getTotal());
    }
}
