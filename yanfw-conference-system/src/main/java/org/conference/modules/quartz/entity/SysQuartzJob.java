package org.conference.modules.quartz.entity;

import java.util.Date;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * (SysQuartzJob)实体类
 *
 * @author makejava
 * @since 2021-12-13 16:22:02
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ApiModel("SysQuartzJob实体类")
public class SysQuartzJob implements Serializable {
    private static final long serialVersionUID = 711496162847620537L;
    private Integer id;
    /**
     * 创建人
     */
    @ApiModelProperty("创建人")
    private String createBy;
    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    private Date createTime;
    /**
     * 修改时间
     */
    @ApiModelProperty("修改时间")
    private Date updateTime;
    /**
     * 删除状态
     */
    @ApiModelProperty("删除状态")
    private String delFlag;
    /**
     * 修改人
     */
    @ApiModelProperty("修改人")
    private Date updateBy;
    /**
     * cron表达式
     */
    @ApiModelProperty("cron表达式")
    private String cronExpression;
    /**
     * 参数
     */
    @ApiModelProperty("参数")
    private String parameter;
    /**
     * 描述
     */
    @ApiModelProperty("描述")
    private String description;
    /**
     * 任务状态 0正常 -1停止
     */
    @ApiModelProperty("任务状态 0正常 -1停止")
    private Integer status;
    /**
     * 任务调用的方法名
     */
    @ApiModelProperty("任务调用的方法名")
    private String methodName;
    /**
     * 任务分组
     */
    @ApiModelProperty("任务分组")
    private String jobGroup;
    /**
     * 任务类名
     */
    @ApiModelProperty("任务类名")
    private String jobName;
    /**
     * 任务执行时调用哪个类的方法 包名+类名
     */
    @ApiModelProperty("任务执行时调用哪个类的方法 包名+类名")
    private String beanClass;

}

