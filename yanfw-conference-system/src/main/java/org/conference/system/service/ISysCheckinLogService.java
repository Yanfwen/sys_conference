package org.conference.system.service;

import org.conference.system.entity.SysCheckinLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import com.baomidou.mybatisplus.extension.service.IService;
/**
 * (SysCheckinLog)表服务接口
 *
 * @author makejava
 * @since 2021-12-10 17:11:14
 */
public interface ISysCheckinLogService extends IService<SysCheckinLog>{

    /**
     * 通过ID查询单条数据
     *
     * @param mettingId 主键
     * @return 实例对象
     */
    SysCheckinLog queryById(String mettingId);

    /**
     * 分页查询
     *
     * @param sysCheckinLog 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<SysCheckinLog> queryByPage(SysCheckinLog sysCheckinLog, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param sysCheckinLog 实例对象
     * @return 实例对象
     */
    SysCheckinLog insert(SysCheckinLog sysCheckinLog);

    /**
     * 修改数据
     *
     * @param sysCheckinLog 实例对象
     * @return 实例对象
     */
    SysCheckinLog update(SysCheckinLog sysCheckinLog);

    /**
     * 通过主键删除数据
     *
     * @param mettingId 主键
     * @return 是否成功
     */
    boolean deleteById(String mettingId);

}
