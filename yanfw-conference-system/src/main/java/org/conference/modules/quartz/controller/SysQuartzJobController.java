package org.conference.modules.quartz.controller;

import org.conference.modules.quartz.entity.SysQuartzJob;
import org.conference.modules.quartz.entity.vo.SysQuartzJobVo;
import org.conference.modules.quartz.job.SampleJob;
import org.conference.modules.quartz.service.ISysQuartzJobService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;

import javax.annotation.Resource;
import java.util.List;

/**
 * (SysQuartzJob)表控制层
 *
 * @author makejava
 * @since 2021-12-13 16:22:02
 */
@RestController
@RequestMapping("sysQuartzJob")
@Api(tags = "SysQuartzJob表控制层模块")
public class SysQuartzJobController {
    /**
     * 服务对象
     */
    @Resource
    private ISysQuartzJobService sysQuartzJobService;


    @GetMapping("/getRunningJob")
    public List<SysQuartzJob> getRunningJob() {
        return sysQuartzJobService.getRunningJob();
    }


    @GetMapping("/addjob")
    public boolean addjob(@PathVariable("jobName") String jobName, @PathVariable("cron") String cron) {
        return sysQuartzJobService.addJob(jobName, SampleJob.class, cron);
    }

    @GetMapping("/modifyJobTime")
    public boolean modifyJobTime(@PathVariable("jobName") String jobName, @PathVariable("cron") String cron) {
        return sysQuartzJobService.modifyJobTime(jobName, cron);
    }

    @GetMapping("/defaultJob")
    public boolean pauseORRemoveORRecoverDefaultJob(@PathVariable("jobName") int handleType,@PathVariable("jobName") String jobName) {
        return sysQuartzJobService.pauseORRemoveORRecoverDefaultJob(handleType, jobName);
    }

    @GetMapping("/pointJob")
    public boolean pauseORRemoveORRecoverPointJob(@PathVariable("jobName") int handleType,@PathVariable("sysQuartzJobVo") SysQuartzJobVo sysQuartzJobVo) {
        return sysQuartzJobService.pauseORRemoveORRecoverPointJob(handleType, sysQuartzJobVo);
    }

    /**
     * 分页查询
     *
     * @param sysQuartzJob 筛选条件
     * @param pageRequest  分页对象
     * @return 查询结果
     */
    @ApiOperation(value = "SysQuartzJob分页查询", notes = "通过筛选条件分页")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "sysQuartzJob", value = "筛选条件"),
            @ApiImplicitParam(name = "pageRequest", value = "分页对象")
    })
    @GetMapping
    public ResponseEntity<Page<SysQuartzJob>> queryByPage(SysQuartzJob sysQuartzJob, PageRequest pageRequest) {
        return ResponseEntity.ok(this.sysQuartzJobService.queryByPage(sysQuartzJob, pageRequest));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @ApiOperation(value = "SysQuartzJob查询单条数据", notes = "通过主键查询单条数据")
    @ApiImplicitParam(name = "id", value = "主键")
    @GetMapping("{id}")
    public ResponseEntity<SysQuartzJob> queryById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(this.sysQuartzJobService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param sysQuartzJob 实体
     * @return 新增结果
     */
    @ApiOperation(value = "SysQuartzJob新增数据")
    @ApiImplicitParam(name = "sysQuartzJob", value = "SysQuartzJob实体")
    @PostMapping
    public ResponseEntity<SysQuartzJob> add(SysQuartzJob sysQuartzJob) {
        return ResponseEntity.ok(this.sysQuartzJobService.insert(sysQuartzJob));
    }

    /**
     * 编辑数据
     *
     * @param sysQuartzJob 实体
     * @return 编辑结果
     */
    @ApiOperation(value = "SysQuartzJob编辑数据")
    @ApiImplicitParam(name = "sysQuartzJob", value = "SysQuartzJob实体")
    @PutMapping
    public ResponseEntity<SysQuartzJob> edit(SysQuartzJob sysQuartzJob) {
        return ResponseEntity.ok(this.sysQuartzJobService.update(sysQuartzJob));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @ApiOperation(value = "SysQuartzJob删除数据", notes = "通过主键删除数据")
    @ApiImplicitParam(name = "id", value = "主键")
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Integer id) {
        return ResponseEntity.ok(this.sysQuartzJobService.deleteById(id));
    }

}

