package org.conference.system.controller;

import org.conference.system.entity.SysCheckinLog;
import org.conference.system.service.ISysCheckinLogService;
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
 * (SysCheckinLog)表控制层
 *
 * @author makejava
 * @since 2021-12-12 23:23:06
 */
@RestController
@RequestMapping("sysCheckinLog")
@Api(tags = "SysCheckinLog表控制层模块")
public class SysCheckinLogController {
    /**
     * 服务对象
     */
    @Resource
    private ISysCheckinLogService sysCheckinLogService;

    /**
     * 分页查询
     *
     * @param sysCheckinLog 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @ApiOperation(value = "SysCheckinLog分页查询", notes = "通过筛选条件分页")
    @ApiImplicitParams({
         @ApiImplicitParam(name="sysCheckinLog", value="筛选条件"),
         @ApiImplicitParam(name="pageRequest",value="分页对象")
    })
    @GetMapping
    public ResponseEntity<Page<SysCheckinLog>> queryByPage(SysCheckinLog sysCheckinLog, PageRequest pageRequest) {
        return ResponseEntity.ok(this.sysCheckinLogService.queryByPage(sysCheckinLog, pageRequest));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @ApiOperation(value = "SysCheckinLog查询单条数据", notes = "通过主键查询单条数据")
    @ApiImplicitParam(name="id",value="主键")
    @GetMapping("{id}")
    public ResponseEntity<SysCheckinLog> queryById(@PathVariable("id") String id) {
        return ResponseEntity.ok(this.sysCheckinLogService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param sysCheckinLog 实体
     * @return 新增结果
     */
    @ApiOperation(value = "SysCheckinLog新增数据")
    @ApiImplicitParam(name="sysCheckinLog",value="SysCheckinLog实体")
    @PostMapping
    public ResponseEntity<SysCheckinLog> add(SysCheckinLog sysCheckinLog) {
        return ResponseEntity.ok(this.sysCheckinLogService.insert(sysCheckinLog));
    }

    /**
     * 编辑数据
     *
     * @param sysCheckinLog 实体
     * @return 编辑结果
     */
    @ApiOperation(value = "SysCheckinLog编辑数据")
    @ApiImplicitParam(name="sysCheckinLog",value="SysCheckinLog实体")
    @PutMapping
    public ResponseEntity<SysCheckinLog> edit(SysCheckinLog sysCheckinLog) {
        return ResponseEntity.ok(this.sysCheckinLogService.update(sysCheckinLog));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @ApiOperation(value = "SysCheckinLog删除数据", notes = "通过主键删除数据")
    @ApiImplicitParam(name="id",value="主键")
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(String id) {
        return ResponseEntity.ok(this.sysCheckinLogService.deleteById(id));
    }

}

