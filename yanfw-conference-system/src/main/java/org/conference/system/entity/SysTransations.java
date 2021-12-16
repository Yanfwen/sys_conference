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
 * (SysTransations)实体类
 *
 * @author makejava
 * @since 2021-12-12 21:50:29
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ApiModel("SysTransations实体类")
public class SysTransations implements Serializable {
    private static final long serialVersionUID = -99418897122240376L;
    /**
     * 付款id
     */
    @ApiModelProperty("付款id")
        private String id;
    /**
     * 交易完成时间
     */
    @ApiModelProperty("交易完成时间")
        private Date completedTime;
    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
        private Date createdTime;
    /**
     * 订单金额
     */
    @ApiModelProperty("订单金额")
        private Object orderAmount;
    /**
     * 订单号
     */
    @ApiModelProperty("订单号")
        private String orderNo;
    /**
     * 支付方式（1；支付宝，2：微信，3：QQ，4：和包）
     */
    @ApiModelProperty("支付方式（1；支付宝，2：微信，3：QQ，4：和包）")
        private Integer payType;
    /**
     * 用户
     */
    @ApiModelProperty("用户")
        private String userId;
    /**
     * 订单名称
     */
    @ApiModelProperty("订单名称")
        private String subject;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCompletedTime() {
        return completedTime;
    }

    public void setCompletedTime(Date completedTime) {
        this.completedTime = completedTime;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Object getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(Object orderAmount) {
        this.orderAmount = orderAmount;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

}

