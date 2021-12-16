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
 * (SysConference)实体类
 *
 * @author makejava
 * @since 2021-12-12 21:50:29
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ApiModel("SysConference实体类")
public class SysConference implements Serializable {
    private static final long serialVersionUID = 739736934042331498L;
    /**
     * 会议室id
     */
    @ApiModelProperty("会议室id")
    private String id;
    /**
     * 名称
     */
    @ApiModelProperty("名称")
    private String meetingName;
    /**
     * 状态（1：在线，2：不在线）
     */
    @ApiModelProperty("状态（1：在线，2：不在线）")
    private Integer meetingStatus;
    /**
     * 开始时间
     */
    @ApiModelProperty("开始时间")
    private Date createDate;
    /**
     * 结束时间
     */
    @ApiModelProperty("结束时间")
    private Date endDate;
    /**
     * 描述
     */
    @ApiModelProperty("描述")
    private String remark;
    /**
     * 最大容纳人数
     */
    @ApiModelProperty("最大容纳人数")
    private Integer maxCount;
    /**
     * 申请人id
     */
    @ApiModelProperty("申请人id")
    private String userId;
    /**
     * 会议地点
     */
    @ApiModelProperty("会议地点")
    private String place;
    /**
     * 会议链接
     */
    @ApiModelProperty("会议链接")
    private String link;
    /**
     * 是否公开(0是，1否)
     */
    @ApiModelProperty("是否公开(0是，1否)")
    private Integer common;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMeetingName() {
        return meetingName;
    }

    public void setMeetingName(String meetingName) {
        this.meetingName = meetingName;
    }

    public Integer getMeetingStatus() {
        return meetingStatus;
    }

    public void setMeetingStatus(Integer meetingStatus) {
        this.meetingStatus = meetingStatus;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getMaxCount() {
        return maxCount;
    }

    public void setMaxCount(Integer maxCount) {
        this.maxCount = maxCount;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Integer getCommon() {
        return common;
    }

    public void setCommon(Integer common) {
        this.common = common;
    }

}

