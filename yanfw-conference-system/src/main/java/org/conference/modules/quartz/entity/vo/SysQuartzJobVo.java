package org.conference.modules.quartz.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description:
 * @Author: Yanfw
 * @Email: 840947210@qq.com
 * @Time: 2021/12/14
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("SysQuartzJobVo模块")
public class SysQuartzJobVo {
    /**
     * 任务类名
     */
    @ApiModelProperty("任务类名")
    private String jobName;
    /**
     * 任务分组
     */
    @ApiModelProperty("任务分组")
    private String jobGroup;

    /**
     * 触发器名
     */
    @ApiModelProperty("触发器名")
    private String triggerName;
    /**
     * 触发器组
     */
    @ApiModelProperty("触发器分组")
    private String triggerGroupName;
}
