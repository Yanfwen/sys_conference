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
 * (SysUser)实体类
 *
 * @author makejava
 * @since 2021-12-12 21:50:29
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ApiModel("SysUser实体类")
public class SysUser implements Serializable {
    private static final long serialVersionUID = -51393563573304044L;
    /**
     * 用户id
     */
    @ApiModelProperty("用户id")
        private String id;
    /**
     * 用户名
     */
    @ApiModelProperty("用户名")
        private String username;
    /**
     * 密码
     */
    @ApiModelProperty("密码")
        private String password;
    /**
     * 性别（1：男 2：女）
     */
    @ApiModelProperty("性别（1：男 2：女）")
        private Integer sex;
    /**
     * 	出生日期
     */
    @ApiModelProperty("	出生日期")
        private Date birthday;
    /**
     * 联系电话
     */
    @ApiModelProperty("联系电话")
        private String phone;
    /**
     * 头像
     */
    @ApiModelProperty("头像")
        private String photo;
    /**
     * 电子邮件
     */
    @ApiModelProperty("电子邮件")
        private String email;
    /**
     * 城市
     */
    @ApiModelProperty("城市")
        private String city;
    /**
     * 状态  (1：正常  2：冻结 ）
     */
    @ApiModelProperty("状态  (1：正常  2：冻结 ）")
        private Integer status;
    /**
     * 身份（0 普通成员 1 上级）
     */
    @ApiModelProperty("身份（0 普通成员 1 上级）")
        private Integer identity;
    /**
     * 注册时间
     */
    @ApiModelProperty("注册时间")
        private Date createTime;
    /**
     * 第三方登录的唯一标识
     */
    @ApiModelProperty("第三方登录的唯一标识")
        private String thirdId;
    /**
     * 第三方登录类型（github/github，wechat_enterprise/企业微信，dingtalk/钉钉）
     */
    @ApiModelProperty("第三方登录类型（github/github，wechat_enterprise/企业微信，dingtalk/钉钉）")
        private String thirdType;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getIdentity() {
        return identity;
    }

    public void setIdentity(Integer identity) {
        this.identity = identity;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getThirdId() {
        return thirdId;
    }

    public void setThirdId(String thirdId) {
        this.thirdId = thirdId;
    }

    public String getThirdType() {
        return thirdType;
    }

    public void setThirdType(String thirdType) {
        this.thirdType = thirdType;
    }

}

