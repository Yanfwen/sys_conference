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
 * (SysFile)实体类
 *
 * @author makejava
 * @since 2021-12-12 21:50:29
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ApiModel("SysFile实体类")
public class SysFile implements Serializable {
    private static final long serialVersionUID = 102216747740882827L;
    /**
     * 文件ID
     */
    @ApiModelProperty("文件ID")
        private String id;
    /**
     * 文件名称
     */
    @ApiModelProperty("文件名称")
        private String fileName;
    /**
     * 文件类型(1: 文件夹，2：文件)
     */
    @ApiModelProperty("文件类型(1: 文件夹，2：文件)")
        private Integer fileType;
    /**
     * 文件大小
     */
    @ApiModelProperty("文件大小")
        private String size;
    /**
     * 文件路径
     */
    @ApiModelProperty("文件路径")
        private String path;
    /**
     * 备注
     */
    @ApiModelProperty("备注")
        private String remark;
    /**
     * 创建人ID
     */
    @ApiModelProperty("创建人ID")
        private String userId;
    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
        private Date createDate;
    /**
     * 文件图标
     */
    @ApiModelProperty("文件图标")
        private String icon;
    /**
     * 文件权限（0：可读，1：可写，2：可读可写，3：不可读写）
     */
    @ApiModelProperty("文件权限（0：可读，1：可写，2：可读可写，3：不可读写）")
        private Integer privilege;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Integer getFileType() {
        return fileType;
    }

    public void setFileType(Integer fileType) {
        this.fileType = fileType;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getPrivilege() {
        return privilege;
    }

    public void setPrivilege(Integer privilege) {
        this.privilege = privilege;
    }

}

