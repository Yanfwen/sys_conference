package org.conference.modules.quartz.service.impl;

import org.conference.modules.quartz.entity.SysQuartzJob;
import org.conference.modules.quartz.entity.vo.SysQuartzJobVo;
import org.conference.modules.quartz.mapper.SysQuartzJobMapper;
import org.conference.modules.quartz.service.ISysQuartzJobService;
import org.conference.modules.quartz.util.QuartzManager;
import org.quartz.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import javax.annotation.Resource;
import java.util.List;

/**
 * (SysQuartzJob)表服务实现类
 *
 * @author makejava
 * @since 2021-12-13 16:22:03
 */
@Service("sysQuartzJobService")
public class SysQuartzJobServiceImpl extends ServiceImpl<SysQuartzJobMapper, SysQuartzJob> implements ISysQuartzJobService {


    @Resource
    private SysQuartzJobMapper sysQuartzJobMapper;

    @Resource
    private QuartzManager quartzManager;

    @Override
    public boolean addJob(String jobName, Class<? extends Job> cls, String cron) {
        return quartzManager.addJob(jobName, cls, cron);
    }

    @Override
    public boolean addJob(String jobName, String jobGroupName, String triggerName, String triggerGroupName, Class<? extends Job> jobClass, String cron) {
        return quartzManager.addJob(jobName, jobGroupName, triggerName, triggerGroupName, jobClass, cron);
    }

    @Override
    public boolean modifyJobTime(String jobName, String cron) {
        return quartzManager.modifyJobTime(jobName, cron);
    }

    @Override
    public boolean modifyJobTime(String triggerName, String triggerGroupName, String cron) {
        return quartzManager.modifyJobTime(triggerName, triggerGroupName, cron);
    }

    @Override
    public List<SysQuartzJob> getAllJob() {
        return quartzManager.getAllJob();
    }

    @Override
    public List<SysQuartzJob> getRunningJob() {
        return quartzManager.getRunningJob();
    }

    @Override
    public boolean pauseORRemoveORRecoverDefaultJob(int handleType, String jobName) {
        return quartzManager.pauseORRemoveORRecoverDefaultJob(handleType, jobName);
    }

    @Override
    public boolean pauseORRemoveORRecoverPointJob(int handleType, SysQuartzJobVo sysQuartzJobVo) {
        return quartzManager.pauseORRemoveORRecoverPointJob(handleType, sysQuartzJobVo);
    }

    @Override
    public boolean startJobs() {
        return quartzManager.startJobs();
    }

    @Override
    public boolean shutdownJobs() {
        return quartzManager.shutdownJobs();
    }

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public SysQuartzJob queryById(Integer id) {
        return this.sysQuartzJobMapper.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param sysQuartzJob 筛选条件
     * @param pageRequest  分页对象
     * @return 查询结果
     */
    @Override
    public Page<SysQuartzJob> queryByPage(SysQuartzJob sysQuartzJob, PageRequest pageRequest) {
        long total = this.sysQuartzJobMapper.count(sysQuartzJob);
        return new PageImpl<>(this.sysQuartzJobMapper.queryAllByLimit(sysQuartzJob, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param sysQuartzJob 实例对象
     * @return 实例对象
     */
    @Override
    public SysQuartzJob insert(SysQuartzJob sysQuartzJob) {
        this.sysQuartzJobMapper.insert(sysQuartzJob);
        return sysQuartzJob;
    }

    /**
     * 修改数据
     *
     * @param sysQuartzJob 实例对象
     * @return 实例对象
     */
    @Override
    public SysQuartzJob update(SysQuartzJob sysQuartzJob) {
        this.sysQuartzJobMapper.update(sysQuartzJob);
        return this.queryById(sysQuartzJob.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.sysQuartzJobMapper.deleteById(id) > 0;
    }
}
