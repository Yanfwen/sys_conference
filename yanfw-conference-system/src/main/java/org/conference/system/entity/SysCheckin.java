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
 * (SysCheckin)实体类
 *
 * @author makejava
 * @since 2021-12-12 21:50:27
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ApiModel("SysCheckin实体类")
public class SysCheckin implements Serializable {
    private static final long serialVersionUID = -97963538964690590L;
    /**
     * 签到ID
     */
    @ApiModelProperty("签到ID")
    private String id;
    /**
     * 会议ID
     */
    @ApiModelProperty("会议ID")
    private String mettingId;
    /**
     * 签到总人数
     */
    @ApiModelProperty("签到总人数")
    private Integer count;
    /**
     * 实签到人数
     */
    @ApiModelProperty("实签到人数")
    private Integer trueCount;
    /**
     * 签到类型（1：签到，2：未到）
     */
    @ApiModelProperty("签到类型（1：签到，2：未到）")
    private Integer type;
    /**
     * 签到开始时间
     */
    @ApiModelProperty("签到开始时间")
    private Date startTime;
    /**
     * 签到结束时间
     */
    @ApiModelProperty("签到结束时间")
    private Date endTime;
    /**
     * 缺勤人数
     */
    @ApiModelProperty("缺勤人数")
    private Integer absenceCount;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMettingId() {
        return mettingId;
    }

    public void setMettingId(String mettingId) {
        this.mettingId = mettingId;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getTrueCount() {
        return trueCount;
    }

    public void setTrueCount(Integer trueCount) {
        this.trueCount = trueCount;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getAbsenceCount() {
        return absenceCount;
    }

    public void setAbsenceCount(Integer absenceCount) {
        this.absenceCount = absenceCount;
    }

}

