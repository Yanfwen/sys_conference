package org.conference;

import org.conference.modules.quartz.entity.SysQuartzJob;
import org.conference.modules.quartz.job.SampleJob;
import org.conference.modules.quartz.util.QuartzManager;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

/**
 * @Description:
 * @Author: Yanfw
 * @Email: 840947210@qq.com
 * @Time: 2021/12/13
 */

@SpringBootTest(classes = ConferenceSystemApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)   // 版本问题junit4用 @RunWith(SpringRunner.class)
public class quartzTest {

    private static QuartzManager quartzManager=new QuartzManager();

    public static void main(String[] args) {
//        addJob(String jobName, Class<? extends Job> cls, String cron)
        System.out.println(quartzManager + "====ssss");
        quartzManager.addJob("yanfw1", SampleJob.class, "*/10 * * * * ?");

    }

    @Test
    public void quartzTest1(){
        QuartzManager quartzManager2=new QuartzManager();
        List<SysQuartzJob> list= quartzManager2.getAllJob();
        System.out.println(list+"----------<<<<<<,");
    }

}
@SpringBootTest(classes = ConferenceSystemApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)   // 版本问题junit4用 @RunWith(SpringRunner.class)
class yanfw{

    @Autowired
    QuartzManager quartzManager;
    private static QuartzManager quartzManager2;

    private yanfw() {
        quartzManager2 = this.quartzManager;
    }


    public static void main(String[] args) throws SchedulerException {
        List<SysQuartzJob> list= quartzManager2.getRunningJob();
        System.out.println(list);
    }
}
