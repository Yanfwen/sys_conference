package org.conference.system.controller;

import org.conference.system.entity.SysCheckin;
import org.conference.system.service.ISysCheckinService;
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
 * (SysCheckin)表控制层
 *
 * @author makejava
 * @since 2021-12-12 23:23:06
 */
@RestController
@RequestMapping("sysCheckin")
@Api(tags = "SysCheckin表控制层模块")
public class SysCheckinController {
    /**
     * 服务对象
     */
    @Resource
    private ISysCheckinService sysCheckinService;

    /**
     * 分页查询
     *
     * @param sysCheckin 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @ApiOperation(value = "SysCheckin分页查询", notes = "通过筛选条件分页")
    @ApiImplicitParams({
         @ApiImplicitParam(name="sysCheckin", value="筛选条件"),
         @ApiImplicitParam(name="pageRequest",value="分页对象")
    })
    @GetMapping
    public ResponseEntity<Page<SysCheckin>> queryByPage(SysCheckin sysCheckin, PageRequest pageRequest) {
        return ResponseEntity.ok(this.sysCheckinService.queryByPage(sysCheckin, pageRequest));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @ApiOperation(value = "SysCheckin查询单条数据", notes = "通过主键查询单条数据")
    @ApiImplicitParam(name="id",value="主键")
    @GetMapping("{id}")
    public ResponseEntity<SysCheckin> queryById(@PathVariable("id") String id) {
        return ResponseEntity.ok(this.sysCheckinService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param sysCheckin 实体
     * @return 新增结果
     */
    @ApiOperation(value = "SysCheckin新增数据")
    @ApiImplicitParam(name="sysCheckin",value="SysCheckin实体")
    @PostMapping
    public ResponseEntity<SysCheckin> add(SysCheckin sysCheckin) {
        return ResponseEntity.ok(this.sysCheckinService.insert(sysCheckin));
    }

    /**
     * 编辑数据
     *
     * @param sysCheckin 实体
     * @return 编辑结果
     */
    @ApiOperation(value = "SysCheckin编辑数据")
    @ApiImplicitParam(name="sysCheckin",value="SysCheckin实体")
    @PutMapping
    public ResponseEntity<SysCheckin> edit(SysCheckin sysCheckin) {
        return ResponseEntity.ok(this.sysCheckinService.update(sysCheckin));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @ApiOperation(value = "SysCheckin删除数据", notes = "通过主键删除数据")
    @ApiImplicitParam(name="id",value="主键")
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(String id) {
        return ResponseEntity.ok(this.sysCheckinService.deleteById(id));
    }

}

