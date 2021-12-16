package org.conference.system.entity;

import java.util.Date;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * (SysCheckinLog)实体类
 *
 * @author makejava
 * @since 2021-12-12 21:50:29
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ApiModel("SysCheckinLog实体类")
public class SysCheckinLog implements Serializable {
    private static final long serialVersionUID = -11863364281161763L;
    /**
     * 签到ID
     */
    @ApiModelProperty("签到ID")
    private String id;
    /**
     * 用户ID
     */
    @ApiModelProperty("用户ID")
    private String userId;
    /**
     * 会议ID
     */
    @ApiModelProperty("会议ID")
    private String mettingId;
    /**
     * 签到时间
     */
    @ApiModelProperty("签到时间")
    private Date time;
    /**
     * 经度
     */
    @ApiModelProperty("经度")
    private Object longitude;
    /**
     * 纬度
     */
    @ApiModelProperty("纬度")
    private Object latitude;
    /**
     * 签到位置
     */
    @ApiModelProperty("签到位置")
    private String address;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMettingId() {
        return mettingId;
    }

    public void setMettingId(String mettingId) {
        this.mettingId = mettingId;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Object getLongitude() {
        return longitude;
    }

    public void setLongitude(Object longitude) {
        this.longitude = longitude;
    }

    public Object getLatitude() {
        return latitude;
    }

    public void setLatitude(Object latitude) {
        this.latitude = latitude;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}

