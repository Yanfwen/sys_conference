package org.conference.system.service.impl;

import org.conference.system.entity.SysTransations;
import org.conference.system.mapper.SysTransationsMapper;
import org.conference.system.service.ISysTransationsService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import javax.annotation.Resource;

/**
 * (SysTransations)表服务实现类
 *
 * @author makejava
 * @since 2021-12-10 17:11:14
 */
@Service("sysTransationsService")
public class SysTransationsServiceImpl extends ServiceImpl<SysTransationsMapper, SysTransations> implements  ISysTransationsService {
    @Resource
    private SysTransationsMapper sysTransationsMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public SysTransations queryById(String id) {
        return this.sysTransationsMapper.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param sysTransations 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<SysTransations> queryByPage(SysTransations sysTransations, PageRequest pageRequest) {
        long total = this.sysTransationsMapper.count(sysTransations);
        return new PageImpl<>(this.sysTransationsMapper.queryAllByLimit(sysTransations, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param sysTransations 实例对象
     * @return 实例对象
     */
    @Override
    public SysTransations insert(SysTransations sysTransations) {
        this.sysTransationsMapper.insert(sysTransations);
        return sysTransations;
    }

    /**
     * 修改数据
     *
     * @param sysTransations 实例对象
     * @return 实例对象
     */
    @Override
    public SysTransations update(SysTransations sysTransations) {
        this.sysTransationsMapper.update(sysTransations);
        return this.queryById(sysTransations.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String id) {
        return this.sysTransationsMapper.deleteById(id) > 0;
    }
}
