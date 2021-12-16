package org.conference.system.service;

import org.conference.system.entity.SysTransations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import com.baomidou.mybatisplus.extension.service.IService;
/**
 * (SysTransations)表服务接口
 *
 * @author makejava
 * @since 2021-12-10 17:11:14
 */
public interface ISysTransationsService extends IService<SysTransations>{

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SysTransations queryById(String id);

    /**
     * 分页查询
     *
     * @param sysTransations 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<SysTransations> queryByPage(SysTransations sysTransations, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param sysTransations 实例对象
     * @return 实例对象
     */
    SysTransations insert(SysTransations sysTransations);

    /**
     * 修改数据
     *
     * @param sysTransations 实例对象
     * @return 实例对象
     */
    SysTransations update(SysTransations sysTransations);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(String id);

}
