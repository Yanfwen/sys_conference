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
 * (SysMessage)实体类
 *
 * @author makejava
 * @since 2021-12-12 21:50:29
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ApiModel("SysMessage实体类")
public class SysMessage implements Serializable {
    private static final long serialVersionUID = 105943940543383024L;
    /**
     * 信息ID
     */
    @ApiModelProperty("信息ID")
        private String id;
    /**
     * 用户ID
     */
    @ApiModelProperty("用户ID")
        private String userId;
    /**
     * 	类型 0-即时单条 1-定时单条 2-即时群发 3-定时群发
     */
    @ApiModelProperty("	类型 0-即时单条 1-定时单条 2-即时群发 3-定时群发")
        private String type;
    /**
     * 发送时间
     */
    @ApiModelProperty("发送时间")
        private Date createTime;
    /**
     * 信息内容
     */
    @ApiModelProperty("信息内容")
        private String text;


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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}

