package org.conference.qiniuyun.entity;

import java.util.Date;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * (SysQiniuyunFile)实体类
 *
 * @author makejava
 * @since 2021-12-14 18:12:37
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ApiModel("SysQiniuyunFile实体类")
public class SysQiniuyunFile implements Serializable {
    private static final long serialVersionUID = -83235514644513106L;
    private String id;
    /**
     * 文件名
     */
    @ApiModelProperty("文件名")
    private String fileName;
    /**
     * 文件类型
     */
    @ApiModelProperty("文件类型")
    private String fileType;
    /**
     * 存储类型(1、标准存储  2、低频存储  3、归档存储)
     */
    @ApiModelProperty("存储类型(1、标准存储  2、低频存储  3、归档存储)")
    private Integer saveType;
    /**
     * 文件大小
     */
    @ApiModelProperty("	文件大小")
    private String fileSize;
    /**
     * 最后更新
     */
    @ApiModelProperty("最后更新")
    private Date updateTime;

}

