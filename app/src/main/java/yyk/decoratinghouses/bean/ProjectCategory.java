package yyk.decoratinghouses.bean;

import android.os.Parcel;
import android.os.Parcelable;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by wangyao on 2017/8/2.
 */
@Entity
public class ProjectCategory implements Parcelable {

    @Id
    private Long id;
    @NotNull
    private String name;
    @NotNull
    private Long d_id;
    private String d_name;
    @Generated(hash = 306603594)
    public ProjectCategory(Long id, @NotNull String name, @NotNull Long d_id,
            String d_name) {
        this.id = id;
        this.name = name;
        this.d_id = d_id;
        this.d_name = d_name;
    }
    @Generated(hash = 1653171862)
    public ProjectCategory() {
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
    public String getD_name() {
        return this.d_name;
    }
    public void setD_name(String d_name) {
        this.d_name = d_name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeString(this.name);
        dest.writeValue(this.d_id);
        dest.writeString(this.d_name);
    }

    protected ProjectCategory(Parcel in) {
        this.id = (Long) in.readValue(Long.class.getClassLoader());
        this.name = in.readString();
        this.d_id = (Long) in.readValue(Long.class.getClassLoader());
        this.d_name = in.readString();
    }

    public static final Parcelable.Creator<ProjectCategory> CREATOR = new Parcelable.Creator<ProjectCategory>() {
        @Override
        public ProjectCategory createFromParcel(Parcel source) {
            return new ProjectCategory(source);
        }

        @Override
        public ProjectCategory[] newArray(int size) {
            return new ProjectCategory[size];
        }
    };
}
