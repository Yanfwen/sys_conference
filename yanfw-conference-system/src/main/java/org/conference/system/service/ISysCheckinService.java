package org.conference.system.service;

import org.conference.system.entity.SysCheckin;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import com.baomidou.mybatisplus.extension.service.IService;
/**
 * (SysCheckin)表服务接口
 *
 * @author makejava
 * @since 2021-12-10 17:11:14
 */
public interface ISysCheckinService extends IService<SysCheckin>{

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SysCheckin queryById(String id);

    /**
     * 分页查询
     *
     * @param sysCheckin 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<SysCheckin> queryByPage(SysCheckin sysCheckin, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param sysCheckin 实例对象
     * @return 实例对象
     */
    SysCheckin insert(SysCheckin sysCheckin);

    /**
     * 修改数据
     *
     * @param sysCheckin 实例对象
     * @return 实例对象
     */
    SysCheckin update(SysCheckin sysCheckin);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(String id);

}
