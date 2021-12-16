package org.conference.system.controller;

import org.conference.system.entity.SysTransations;
import org.conference.system.service.ISysTransationsService;
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
 * (SysTransations)表控制层
 *
 * @author makejava
 * @since 2021-12-12 23:23:06
 */
@RestController
@RequestMapping("sysTransations")
@Api(tags = "SysTransations表控制层模块")
public class SysTransationsController {
    /**
     * 服务对象
     */
    @Resource
    private ISysTransationsService sysTransationsService;

    /**
     * 分页查询
     *
     * @param sysTransations 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @ApiOperation(value = "SysTransations分页查询", notes = "通过筛选条件分页")
    @ApiImplicitParams({
         @ApiImplicitParam(name="sysTransations", value="筛选条件"),
         @ApiImplicitParam(name="pageRequest",value="分页对象")
    })
    @GetMapping
    public ResponseEntity<Page<SysTransations>> queryByPage(SysTransations sysTransations, PageRequest pageRequest) {
        return ResponseEntity.ok(this.sysTransationsService.queryByPage(sysTransations, pageRequest));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @ApiOperation(value = "SysTransations查询单条数据", notes = "通过主键查询单条数据")
    @ApiImplicitParam(name="id",value="主键")
    @GetMapping("{id}")
    public ResponseEntity<SysTransations> queryById(@PathVariable("id") String id) {
        return ResponseEntity.ok(this.sysTransationsService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param sysTransations 实体
     * @return 新增结果
     */
    @ApiOperation(value = "SysTransations新增数据")
    @ApiImplicitParam(name="sysTransations",value="SysTransations实体")
    @PostMapping
    public ResponseEntity<SysTransations> add(SysTransations sysTransations) {
        return ResponseEntity.ok(this.sysTransationsService.insert(sysTransations));
    }

    /**
     * 编辑数据
     *
     * @param sysTransations 实体
     * @return 编辑结果
     */
    @ApiOperation(value = "SysTransations编辑数据")
    @ApiImplicitParam(name="sysTransations",value="SysTransations实体")
    @PutMapping
    public ResponseEntity<SysTransations> edit(SysTransations sysTransations) {
        return ResponseEntity.ok(this.sysTransationsService.update(sysTransations));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @ApiOperation(value = "SysTransations删除数据", notes = "通过主键删除数据")
    @ApiImplicitParam(name="id",value="主键")
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(String id) {
        return ResponseEntity.ok(this.sysTransationsService.deleteById(id));
    }

}

