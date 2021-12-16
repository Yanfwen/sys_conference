package org.conference.system.controller;

import org.conference.system.entity.SysMessage;
import org.conference.system.service.ISysMessageService;
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
 * (SysMessage)表控制层
 *
 * @author makejava
 * @since 2021-12-12 23:23:06
 */
@RestController
@RequestMapping("sysMessage")
@Api(tags = "SysMessage表控制层模块")
public class SysMessageController {
    /**
     * 服务对象
     */
    @Resource
    private ISysMessageService sysMessageService;

    /**
     * 分页查询
     *
     * @param sysMessage 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @ApiOperation(value = "SysMessage分页查询", notes = "通过筛选条件分页")
    @ApiImplicitParams({
         @ApiImplicitParam(name="sysMessage", value="筛选条件"),
         @ApiImplicitParam(name="pageRequest",value="分页对象")
    })
    @GetMapping
    public ResponseEntity<Page<SysMessage>> queryByPage(SysMessage sysMessage, PageRequest pageRequest) {
        return ResponseEntity.ok(this.sysMessageService.queryByPage(sysMessage, pageRequest));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @ApiOperation(value = "SysMessage查询单条数据", notes = "通过主键查询单条数据")
    @ApiImplicitParam(name="id",value="主键")
    @GetMapping("{id}")
    public ResponseEntity<SysMessage> queryById(@PathVariable("id") String id) {
        return ResponseEntity.ok(this.sysMessageService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param sysMessage 实体
     * @return 新增结果
     */
    @ApiOperation(value = "SysMessage新增数据")
    @ApiImplicitParam(name="sysMessage",value="SysMessage实体")
    @PostMapping
    public ResponseEntity<SysMessage> add(SysMessage sysMessage) {
        return ResponseEntity.ok(this.sysMessageService.insert(sysMessage));
    }

    /**
     * 编辑数据
     *
     * @param sysMessage 实体
     * @return 编辑结果
     */
    @ApiOperation(value = "SysMessage编辑数据")
    @ApiImplicitParam(name="sysMessage",value="SysMessage实体")
    @PutMapping
    public ResponseEntity<SysMessage> edit(SysMessage sysMessage) {
        return ResponseEntity.ok(this.sysMessageService.update(sysMessage));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @ApiOperation(value = "SysMessage删除数据", notes = "通过主键删除数据")
    @ApiImplicitParam(name="id",value="主键")
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(String id) {
        return ResponseEntity.ok(this.sysMessageService.deleteById(id));
    }

}

