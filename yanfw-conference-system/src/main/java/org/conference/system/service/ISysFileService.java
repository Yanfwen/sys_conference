package org.conference.system.service;

import org.conference.system.entity.SysFile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import com.baomidou.mybatisplus.extension.service.IService;
/**
 * (SysFile)表服务接口
 *
 * @author makejava
 * @since 2021-12-10 17:11:14
 */
public interface ISysFileService extends IService<SysFile>{

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SysFile queryById(String id);

    /**
     * 分页查询
     *
     * @param sysFile 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<SysFile> queryByPage(SysFile sysFile, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param sysFile 实例对象
     * @return 实例对象
     */
    SysFile insert(SysFile sysFile);

    /**
     * 修改数据
     *
     * @param sysFile 实例对象
     * @return 实例对象
     */
    SysFile update(SysFile sysFile);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(String id);

}
