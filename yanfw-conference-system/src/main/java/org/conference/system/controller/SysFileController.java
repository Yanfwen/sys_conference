package org.conference.system.controller;

import org.conference.system.entity.SysFile;
import org.conference.system.service.ISysFileService;
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
 * (SysFile)表控制层
 *
 * @author makejava
 * @since 2021-12-12 23:23:06
 */
@RestController
@RequestMapping("sysFile")
@Api(tags = "SysFile表控制层模块")
public class SysFileController {
    /**
     * 服务对象
     */
    @Resource
    private ISysFileService sysFileService;

    /**
     * 分页查询
     *
     * @param sysFile 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @ApiOperation(value = "SysFile分页查询", notes = "通过筛选条件分页")
    @ApiImplicitParams({
         @ApiImplicitParam(name="sysFile", value="筛选条件"),
         @ApiImplicitParam(name="pageRequest",value="分页对象")
    })
    @GetMapping
    public ResponseEntity<Page<SysFile>> queryByPage(SysFile sysFile, PageRequest pageRequest) {
        return ResponseEntity.ok(this.sysFileService.queryByPage(sysFile, pageRequest));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @ApiOperation(value = "SysFile查询单条数据", notes = "通过主键查询单条数据")
    @ApiImplicitParam(name="id",value="主键")
    @GetMapping("{id}")
    public ResponseEntity<SysFile> queryById(@PathVariable("id") String id) {
        return ResponseEntity.ok(this.sysFileService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param sysFile 实体
     * @return 新增结果
     */
    @ApiOperation(value = "SysFile新增数据")
    @ApiImplicitParam(name="sysFile",value="SysFile实体")
    @PostMapping
    public ResponseEntity<SysFile> add(SysFile sysFile) {
        return ResponseEntity.ok(this.sysFileService.insert(sysFile));
    }

    /**
     * 编辑数据
     *
     * @param sysFile 实体
     * @return 编辑结果
     */
    @ApiOperation(value = "SysFile编辑数据")
    @ApiImplicitParam(name="sysFile",value="SysFile实体")
    @PutMapping
    public ResponseEntity<SysFile> edit(SysFile sysFile) {
        return ResponseEntity.ok(this.sysFileService.update(sysFile));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @ApiOperation(value = "SysFile删除数据", notes = "通过主键删除数据")
    @ApiImplicitParam(name="id",value="主键")
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(String id) {
        return ResponseEntity.ok(this.sysFileService.deleteById(id));
    }

}

