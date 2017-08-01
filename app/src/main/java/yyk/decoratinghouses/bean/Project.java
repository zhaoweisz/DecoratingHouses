package yyk.decoratinghouses.bean;

import android.os.Parcel;
import android.os.Parcelable;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by YYK on 2017/8/1.
 */

@Entity
public class Project implements Parcelable {

    private Long id;
    private String title;
    private Long p_id;
    private String p_name;
    private float p_total;
    private Long o_id;
    private String o_name;
    private Long d_di;
    private String d_name;
    @Generated(hash = 874993054)
    public Project(Long id, String title, Long p_id, String p_name, float p_total,
            Long o_id, String o_name, Long d_di, String d_name) {
        this.id = id;
        this.title = title;
        this.p_id = p_id;
        this.p_name = p_name;
        this.p_total = p_total;
        this.o_id = o_id;
        this.o_name = o_name;
        this.d_di = d_di;
        this.d_name = d_name;
    }
    @Generated(hash = 1767516619)
    public Project() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public Long getP_id() {
        return this.p_id;
    }
    public void setP_id(Long p_id) {
        this.p_id = p_id;
    }
    public String getP_name() {
        return this.p_name;
    }
    public void setP_name(String p_name) {
        this.p_name = p_name;
    }
    public float getP_total() {
        return this.p_total;
    }
    public void setP_total(float p_total) {
        this.p_total = p_total;
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
    public Long getD_di() {
        return this.d_di;
    }
    public void setD_di(Long d_di) {
        this.d_di = d_di;
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
        dest.writeString(this.title);
        dest.writeValue(this.p_id);
        dest.writeString(this.p_name);
        dest.writeFloat(this.p_total);
        dest.writeValue(this.o_id);
        dest.writeString(this.o_name);
        dest.writeValue(this.d_di);
        dest.writeString(this.d_name);
    }

    protected Project(Parcel in) {
        this.id = (Long) in.readValue(Long.class.getClassLoader());
        this.title = in.readString();
        this.p_id = (Long) in.readValue(Long.class.getClassLoader());
        this.p_name = in.readString();
        this.p_total = in.readFloat();
        this.o_id = (Long) in.readValue(Long.class.getClassLoader());
        this.o_name = in.readString();
        this.d_di = (Long) in.readValue(Long.class.getClassLoader());
        this.d_name = in.readString();
    }

    public static final Parcelable.Creator<Project> CREATOR = new Parcelable.Creator<Project>() {
        @Override
        public Project createFromParcel(Parcel source) {
            return new Project(source);
        }

        @Override
        public Project[] newArray(int size) {
            return new Project[size];
        }
    };
}
