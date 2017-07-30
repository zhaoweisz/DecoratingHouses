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
public class DecoratingType implements Parcelable {

    @Id(autoincrement = true)
    private Long id;
    @NotNull@Unique
    private String name;
    @Generated(hash = 65511314)
    public DecoratingType(Long id, @NotNull String name) {
        this.id = id;
        this.name = name;
    }
    @Generated(hash = 1245361614)
    public DecoratingType() {
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

    public static final Creator<DecoratingType> CREATOR = new Creator<DecoratingType>() {
        @Override
        public DecoratingType createFromParcel(Parcel parcel) {
            return new DecoratingType(parcel.readLong(), parcel.readString());
        }

        @Override
        public DecoratingType[] newArray(int i) {
            return new DecoratingType[i];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(id);
        parcel.writeString(name);
    }
}
