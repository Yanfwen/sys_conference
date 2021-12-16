package org.conference.modules.quartz.aomian;

import org.conference.modules.quartz.job.SampleJob;
import org.conference.modules.quartz.service.ISysQuartzJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Description:
 * @Author: Yanfw
 * @Email: 840947210@qq.com
 * @Time: 2021/12/14
 */
@Component
public class JobSchedule implements CommandLineRunner {
    @Resource
    private ISysQuartzJobService sysQuartzJobService;

    @Override
    public void run(String... strings) {
        System.out.println("==============开始初始化定时任务==============");
//        sysQuartzJobService.addJob("yanfw1", SampleJob.class, "*/5 * * * * ?");
//        sysQuartzJobService.addJob("yanfw2", SampleJob.class, "*/5 * * * * ?");
        System.out.println("==============初始化定时任务结束==============");
    }
}