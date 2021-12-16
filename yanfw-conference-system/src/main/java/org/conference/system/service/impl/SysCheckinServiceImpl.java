package org.conference.system.service.impl;

import org.conference.system.entity.SysCheckin;
import org.conference.system.mapper.SysCheckinMapper;
import org.conference.system.service.ISysCheckinService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import javax.annotation.Resource;

/**
 * (SysCheckin)表服务实现类
 *
 * @author makejava
 * @since 2021-12-10 17:11:14
 */
@Service("sysCheckinService")
public class SysCheckinServiceImpl extends ServiceImpl<SysCheckinMapper, SysCheckin> implements  ISysCheckinService {
    @Resource
    private SysCheckinMapper sysCheckinMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public SysCheckin queryById(String id) {
        return this.sysCheckinMapper.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param sysCheckin 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<SysCheckin> queryByPage(SysCheckin sysCheckin, PageRequest pageRequest) {
        long total = this.sysCheckinMapper.count(sysCheckin);
        return new PageImpl<>(this.sysCheckinMapper.queryAllByLimit(sysCheckin, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param sysCheckin 实例对象
     * @return 实例对象
     */
    @Override
    public SysCheckin insert(SysCheckin sysCheckin) {
        this.sysCheckinMapper.insert(sysCheckin);
        return sysCheckin;
    }

    /**
     * 修改数据
     *
     * @param sysCheckin 实例对象
     * @return 实例对象
     */
    @Override
    public SysCheckin update(SysCheckin sysCheckin) {
        this.sysCheckinMapper.update(sysCheckin);
        return this.queryById(sysCheckin.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String id) {
        return this.sysCheckinMapper.deleteById(id) > 0;
    }
}
