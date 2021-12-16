package org.conference.modules.quartz.service;

import org.conference.modules.quartz.entity.SysQuartzJob;
import org.conference.modules.quartz.entity.vo.SysQuartzJobVo;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * (SysQuartzJob)表服务接口
 *
 * @author makejava
 * @since 2021-12-13 16:22:03
 */
public interface ISysQuartzJobService extends IService<SysQuartzJob> {

    /**
     * 添加一个定时任务，【使用默认:  的任务组名，触发器名，触发器组名】
     *
     * @param jobName 任务名
     * @param cls     任务
     * @param cron    时间设置，参考quartz说明文档
     */
    public boolean addJob(String jobName, Class<? extends Job> cls, String cron);

    /**
     * 添加一个定时任务,【使用指定的的任务组名，触发器名，触发器组名】
     *
     * @param jobName          任务名
     * @param jobGroupName     任务组名
     * @param triggerName      触发器名
     * @param triggerGroupName 触发器组名
     * @param jobClass         任务
     * @param cron             时间表达式 （如：0/5 * * * * ? ）
     */
    boolean addJob(String jobName, String jobGroupName, String triggerName, String triggerGroupName, Class<? extends Job> jobClass, String cron);


    /**
     * 修改一个任务的触发时间(使用默认的任务组名，触发器名，触发器组名)
     *
     * @param jobName 任务名
     * @param cron    时间表达式 （如：0/5 * * * * ? ）
     */
    boolean modifyJobTime(String jobName, String cron);

    /**
     * 修改一个任务的触发时间(使用指定的任务组名，触发器名，触发器组名)
     *
     * @param triggerName      任务名称
     * @param triggerGroupName 传过来的任务名称
     * @param cron             时间表达式 （如：0/5 * * * * ? ）
     */
    boolean modifyJobTime(String triggerName, String triggerGroupName, String cron);

    /**
     * 获取所有计划中的任务列表
     */
    List<SysQuartzJob> getAllJob();

    /**
     * 所有正在运行的job
     */
    List<SysQuartzJob> getRunningJob();


    /**
     * 暂停、恢复、移除一个【默认组的】job
     */
    public boolean pauseORRemoveORRecoverDefaultJob(int handleType, String jobName);

    /**
     * 暂停、恢复、移除一个【指定的任务组名，触发器名，触发器组名】job
     *
     * @param handleType     操作类型
     * @param sysQuartzJobVo SysQuartzJobVo
     */
    public boolean pauseORRemoveORRecoverPointJob(int handleType, SysQuartzJobVo sysQuartzJobVo);

    /**
     * 启动所有定时任务
     */
    boolean startJobs();

    /**
     * 关闭所有定时任务
     */
    boolean shutdownJobs();


    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SysQuartzJob queryById(Integer id);

    /**
     * 分页查询
     *
     * @param sysQuartzJob 筛选条件
     * @param pageRequest  分页对象
     * @return 查询结果
     */
    Page<SysQuartzJob> queryByPage(SysQuartzJob sysQuartzJob, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param sysQuartzJob 实例对象
     * @return 实例对象
     */
    SysQuartzJob insert(SysQuartzJob sysQuartzJob);

    /**
     * 修改数据
     *
     * @param sysQuartzJob 实例对象
     * @return 实例对象
     */
    SysQuartzJob update(SysQuartzJob sysQuartzJob);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}
