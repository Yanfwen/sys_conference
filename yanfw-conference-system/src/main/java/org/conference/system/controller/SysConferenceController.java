package org.conference.system.controller;

import org.conference.system.entity.SysConference;
import org.conference.system.service.ISysConferenceService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;

import javax.annotation.Resource;

/**
 * (SysConference)表控制层
 *
 * @author makejava
 * @since 2021-12-12 23:23:06
 */
@RestController
@RequestMapping("sysConference")
@Api(tags = "SysConference表控制层模块")
public class SysConferenceController {
    /**
     * 服务对象
     */
    @Resource
    private ISysConferenceService sysConferenceService;

    /**
     * 分页查询
     *
     * @param sysConference 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @ApiOperation(value = "SysConference分页查询", notes = "通过筛选条件分页")
    @ApiImplicitParams({
         @ApiImplicitParam(name="sysConference", value="筛选条件"),
         @ApiImplicitParam(name="pageRequest",value="分页对象")
    })
    @GetMapping
    public ResponseEntity<Page<SysConference>> queryByPage(SysConference sysConference, PageRequest pageRequest) {
        return ResponseEntity.ok(this.sysConferenceService.queryByPage(sysConference, pageRequest));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @ApiOperation(value = "SysConference查询单条数据", notes = "通过主键查询单条数据")
    @ApiImplicitParam(name="id",value="主键")
    @GetMapping("{id}")
    public ResponseEntity<SysConference> queryById(@PathVariable("id") String id) {
        return ResponseEntity.ok(this.sysConferenceService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param sysConference 实体
     * @return 新增结果
     */
    @ApiOperation(value = "SysConference新增数据")
    @ApiImplicitParam(name="sysConference",value="SysConference实体")
    @PostMapping
    public ResponseEntity<SysConference> add(SysConference sysConference) {
        return ResponseEntity.ok(this.sysConferenceService.insert(sysConference));
    }

    /**
     * 编辑数据
     *
     * @param sysConference 实体
     * @return 编辑结果
     */
    @ApiOperation(value = "SysConference编辑数据")
    @ApiImplicitParam(name="sysConference",value="SysConference实体")
    @PutMapping
    public ResponseEntity<SysConference> edit(SysConference sysConference) {
        return ResponseEntity.ok(this.sysConferenceService.update(sysConference));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @ApiOperation(value = "SysConference删除数据", notes = "通过主键删除数据")
    @ApiImplicitParam(name="id",value="主键")
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(String id) {
        return ResponseEntity.ok(this.sysConferenceService.deleteById(id));
    }

}

