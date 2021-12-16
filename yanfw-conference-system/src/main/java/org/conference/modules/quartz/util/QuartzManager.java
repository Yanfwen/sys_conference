package org.conference.modules.quartz.util;

import org.conference.modules.quartz.entity.SysQuartzJob;
import org.conference.modules.quartz.entity.vo.SysQuartzJobVo;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @Description:
 * @Author: Yanfw
 * @Email: 840947210@qq.com
 * @Time: 2021/12/13
 */
@Component
public class QuartzManager {
    // 创建一个SchedulerFactory工厂实例
    private SchedulerFactory getSchedulerFactory = new StdSchedulerFactory();
    // 默认的：任务组名
    private String JOB_GROUP_NAME = "DEFAULT_JOB_GROUP_NAME";
    // 默认的：触发器组名
    private String TRIGGER_GROUP_NAME = "DEFAULT_TRIGGER_GROUP_NAME";

    /**
     * 添加一个定时任务，【使用默认:  的任务组名，触发器名，触发器组名】
     *
     * @param jobName 任务名
     * @param cls     任务
     * @param cron    时间设置，参考quartz说明文档
     */
    public boolean addJob(String jobName, Class<? extends Job> cls, String cron) {
        try {
            // 通过SchedulerFactory构建Scheduler对象
            Scheduler sched = getSchedulerFactory.getScheduler();
            // 用于描叙Job实现类及其他的一些静态信息，构建一个作业实例
            JobDetail jobDetail = JobBuilder.newJob(cls)            // 同时提供需要执行的类(例如: cls = XXXX.class)
                    // 设置默认的任务组名
                    .withIdentity(jobName, JOB_GROUP_NAME).build();
            // 创建一个新的TriggerBuilder来规范一个触发器
            CronTrigger trigger = (CronTrigger) TriggerBuilder.newTrigger()
                    // 给触发器起一个名字和组名,自定义任务名，和任务组名
                    .withIdentity(jobName, TRIGGER_GROUP_NAME)
                    // 传入cronExpression表达式
                    .withSchedule(CronScheduleBuilder.cronSchedule(cron))
                    .build();
            // 将触发器添加至Scheduler中
            System.out.println("--------------");
            sched.scheduleJob(jobDetail, trigger);
            if (!sched.isShutdown()) {
                // 启动
                sched.start();
            }
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 添加一个定时任务,【使用指定的的任务组名，触发器名，触发器组名】
     *
     * @param jobName          任务名
     * @param jobGroupName     任务组名
     * @param triggerName      触发器名
     * @param triggerGroupName 触发器组名
     * @param jobClass         任务
     * @param cron             时间设置，参考quartz说明文档
     */
    public boolean addJob(String jobName, String jobGroupName, String triggerName, String triggerGroupName, Class<? extends Job> jobClass, String cron) {
        try {
            Scheduler sched = getSchedulerFactory.getScheduler();
            JobDetail jobDetail = JobBuilder.newJob(jobClass).withIdentity(jobName, jobGroupName).build();
            CronTrigger trigger = (CronTrigger) TriggerBuilder
                    .newTrigger()
                    .withIdentity(triggerName, triggerGroupName)
                    .withSchedule(CronScheduleBuilder.cronSchedule(cron))
                    .build();
            sched.scheduleJob(jobDetail, trigger);
            if (!sched.isShutdown()) {
                sched.start();
            }
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * 修改一个任务的触发时间(使用默认的任务组名，触发器名，触发器组名)
     *
     * @param jobName 任务名
     * @param cron    时间表达式 （如：0/5 * * * * ? ）
     */
    public boolean modifyJobTime(String jobName, String cron) {
        try {
            Scheduler sched = getSchedulerFactory.getScheduler();
            // 通过触发器名和组名获取TriggerKey
            TriggerKey triggerKey = TriggerKey.triggerKey(jobName, TRIGGER_GROUP_NAME);
            // 通过TriggerKey获取CronTrigger
            CronTrigger trigger = (CronTrigger) sched.getTrigger(triggerKey);
            if (trigger == null) {
                return false;
            }
            String oldTime = trigger.getCronExpression();
            if (!oldTime.equalsIgnoreCase(cron)) {
                // 通过任务名和组名获取JobKey
                JobKey jobKey = JobKey.jobKey(jobName, JOB_GROUP_NAME);
                JobDetail jobDetail = sched.getJobDetail(jobKey);
                Class<? extends Job> objJobClass = jobDetail.getJobClass();
                pauseORRemoveORRecoverDefaultJob(1, jobName);
                // 修改新时间
                addJob(jobName, objJobClass, cron);
            }
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 修改一个任务的触发时间(使用指定的任务组名，触发器名，触发器组名)
     *
     * @param triggerName      任务名称
     * @param triggerGroupName 传过来的任务名称
     * @param time             更新后的时间规则
     */
    public boolean modifyJobTime(String triggerName, String triggerGroupName, String time) {
        try {
            Scheduler sched = getSchedulerFactory.getScheduler();
            TriggerKey triggerKey = TriggerKey.triggerKey(triggerName, triggerGroupName);
            CronTrigger trigger = (CronTrigger) sched.getTrigger(triggerKey);
            if (trigger == null)
                return false;
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(trigger.getCronExpression());
            String oldTime = trigger.getCronExpression();
            if (!oldTime.equalsIgnoreCase(time)) {
                trigger = (CronTrigger) trigger.getTriggerBuilder()
                        // 重新构建trigger
                        .withIdentity(triggerKey).withSchedule(scheduleBuilder)
                        // 修改时间
                        .withSchedule(CronScheduleBuilder.cronSchedule(time)).build();
                // 按新的trigger重新设置job执行
                sched.rescheduleJob(triggerKey, trigger);
            }
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取所有计划中的任务列表
     *
     * @return
     * @throws SchedulerException
     */
    public List<SysQuartzJob> getAllJob() {
        System.out.println("===========");
        try {
            Scheduler scheduler = getSchedulerFactory.getScheduler();
            GroupMatcher<JobKey> matcher = GroupMatcher.anyJobGroup();
            Set<JobKey> jobKeys = scheduler.getJobKeys(matcher);
            List<SysQuartzJob> jobList = new ArrayList<SysQuartzJob>();
            for (JobKey jobKey : jobKeys) {
                List<? extends Trigger> triggers = scheduler.getTriggersOfJob(jobKey);
                for (Trigger trigger : triggers) {
                    SysQuartzJob job = new SysQuartzJob();
                    // 获取任务名
                    job.setJobName(jobKey.getName());
                    // 获取组名
                    job.setJobGroup(jobKey.getGroup());
                    job.setDescription("触发器:" + trigger.getKey());
                    Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
                    job.setStatus(Integer.valueOf(triggerState.name()));
                    if (trigger instanceof CronTrigger) {
                        CronTrigger cronTrigger = (CronTrigger) trigger;
                        String cronExpression = cronTrigger.getCronExpression();
                        job.setCronExpression(cronExpression);
                    }
                    jobList.add(job);
                }
            }
            return jobList;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * 所有正在运行的job
     */
    public List<SysQuartzJob> getRunningJob() {
        try {
            Scheduler scheduler = getSchedulerFactory.getScheduler();
            List<JobExecutionContext> executingJobs = scheduler.getCurrentlyExecutingJobs();
            List<SysQuartzJob> jobList = new ArrayList<SysQuartzJob>(executingJobs.size());
            for (JobExecutionContext executingJob : executingJobs) {
                SysQuartzJob job = new SysQuartzJob();
                JobDetail jobDetail = executingJob.getJobDetail();
                JobKey jobKey = jobDetail.getKey();
                Trigger trigger = executingJob.getTrigger();
                job.setJobName(jobKey.getName());
                job.setJobGroup(jobKey.getGroup());
                job.setDescription("触发器:" + trigger.getKey());
                Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
                job.setStatus(Integer.valueOf(triggerState.name()));
                if (trigger instanceof CronTrigger) {
                    CronTrigger cronTrigger = (CronTrigger) trigger;
                    String cronExpression = cronTrigger.getCronExpression();
                    job.setCronExpression(cronExpression);
                }
                jobList.add(job);
            }
            return jobList;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * 暂停、恢复、移除一个【默认任务组名，触发器名，触发器组名】job
     *
     * @param handleType 操作类型
     * @param jobName    jobName
     */
    public boolean pauseORRemoveORRecoverDefaultJob(int handleType, String jobName) {
        return defaultJob(handleType, jobName);
    }


    /**
     * 暂停、恢复、移除一个【默认任务组名，触发器名，触发器组名】job
     *
     * @param handleType 操作类型
     * @param jobName    jobName
     */
    public boolean defaultJob(int handleType, String jobName) {
        try {
            Scheduler scheduler = getSchedulerFactory.getScheduler();
            // 通过触发器名和组名获取TriggerKey
            TriggerKey triggerKey = TriggerKey.triggerKey(jobName, TRIGGER_GROUP_NAME);
            JobKey jobKey = JobKey.jobKey(jobName, JOB_GROUP_NAME);
            jobStatus(handleType, scheduler, jobKey, triggerKey);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    /**
     * 暂停、恢复、移除一个【指定的任务组名，触发器名，触发器组名】job
     *
     * @param handleType     操作类型
     * @param sysQuartzJobVo SysQuartzJobVo
     */
    public boolean pauseORRemoveORRecoverPointJob(int handleType, SysQuartzJobVo sysQuartzJobVo) {
        return pointJob(handleType, sysQuartzJobVo);
    }

    /**
     * 暂停、恢复、移除一个【指定的任务组名，触发器名，触发器组名】job
     *
     * @param handleType     操作类型
     * @param sysQuartzJobVo SysQuartzJobVo
     */
    public boolean pointJob(int handleType, SysQuartzJobVo sysQuartzJobVo) {
        try {
            Scheduler scheduler = getSchedulerFactory.getScheduler();
            JobKey jobKey = JobKey.jobKey(sysQuartzJobVo.getJobName(), sysQuartzJobVo.getJobGroup());
            // 通过触发器名和组名获取TriggerKey
            TriggerKey triggerKey = TriggerKey.triggerKey(sysQuartzJobVo.getTriggerName(), sysQuartzJobVo.getTriggerGroupName());
            jobStatus(handleType, scheduler, jobKey, triggerKey);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public void jobStatus(int handleType, Scheduler scheduler, JobKey jobKey, TriggerKey triggerKey) throws SchedulerException {
        switch (handleType) {
            case 1:// 暂停任务
                scheduler.pauseJob(jobKey);
                break;
            case 2:// 恢复任务
                scheduler.resumeJob(jobKey);
                break;
            case 3:// 删除任务
                // 停止触发器
                scheduler.pauseTrigger(triggerKey);
                // 移除触发器
                scheduler.unscheduleJob(triggerKey);
                scheduler.deleteJob(jobKey);
                break;
            case 4:// 立即执行任务
                scheduler.triggerJob(jobKey);
                break;
        }
    }


    /**
     * 启动所有定时任务
     */
    public boolean startJobs() {
        try {
            Scheduler sched = getSchedulerFactory.getScheduler();
            sched.start();
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 关闭所有定时任务
     */
    public boolean shutdownJobs() {
        try {
            Scheduler sched = getSchedulerFactory.getScheduler();
            if (!sched.isShutdown()) {
                sched.shutdown();
            }
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}