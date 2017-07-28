package yyk.decoratinghouses.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Unique;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by YYK on 2017/7/28.
 */

@Entity
public class ZcOpitions {
    @Id(autoincrement = true)
    private Long id;
    @Unique@NotNull
    private String name;
    private Long d_id;
    @NotNull
    private float number;
    @NotNull
    private String unit;
    @Generated(hash = 160644415)
    public ZcOpitions(Long id, @NotNull String name, Long d_id, float number,
            @NotNull String unit) {
        this.id = id;
        this.name = name;
        this.d_id = d_id;
        this.number = number;
        this.unit = unit;
    }
    @Generated(hash = 880375385)
    public ZcOpitions() {
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
}
