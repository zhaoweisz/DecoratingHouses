package yyk.decoratinghouses.bean;

import android.os.Parcel;
import android.os.Parcelable;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Unique;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by wangyao on 2017/7/29.
 */

@Entity
public class Opition implements Parcelable {

    @Id(autoincrement = true)
    private Long id;
    @Unique
    @NotNull
    private String name;
    @NotNull
    private Long d_id;
    @NotNull
    private String d_name;
    @NotNull
    private float number;
    @NotNull
    private String unit;
    @Generated(hash = 1593260062)
    public Opition(Long id, @NotNull String name, @NotNull Long d_id,
            @NotNull String d_name, float number, @NotNull String unit) {
        this.id = id;
        this.name = name;
        this.d_id = d_id;
        this.d_name = d_name;
        this.number = number;
        this.unit = unit;
    }
    @Generated(hash = 1387171030)
    public Opition() {
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
    public Long getD_id() {
        return this.d_id;
    }
    public void setD_id(Long d_id) {
        this.d_id = d_id;
    }
    public float getNumber() {
        return this.number;
    }
    public void setNumber(float number) {
        this.number = number;
    }
    public String getUnit() {
        return this.unit;
    }
    public void setUnit(String unit) {
        this.unit = unit;
    }
    public String getD_name() {
        return this.d_name;
    }
    public void setD_name(String d_name) {
        this.d_name = d_name;
    }

    public static final Creator<Opition> CREATOR = new Creator<Opition>() {
        @Override
        public Opition createFromParcel(Parcel parcel) {
            return new Opition(parcel.readLong(), parcel.readString(),
                    parcel.readLong(),parcel.readString(),
                    parcel.readFloat(),parcel.readString());
        }

        @Override
        public Opition[] newArray(int i) {
            return new Opition[i];
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
        parcel.writeLong(getD_id());
        parcel.writeString(getD_name());
        parcel.writeFloat(getNumber());
        parcel.writeString(getUnit());
    }
}
