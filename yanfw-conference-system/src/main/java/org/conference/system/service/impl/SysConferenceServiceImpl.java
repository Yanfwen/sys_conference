package org.conference.system.service.impl;

import org.conference.system.entity.SysConference;
import org.conference.system.mapper.SysConferenceMapper;
import org.conference.system.service.ISysConferenceService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import javax.annotation.Resource;

/**
 * (SysConference)表服务实现类
 *
 * @author makejava
 * @since 2021-12-10 17:11:14
 */
@Service("sysConferenceService")
public class SysConferenceServiceImpl extends ServiceImpl<SysConferenceMapper, SysConference> implements  ISysConferenceService {
    @Resource
    private SysConferenceMapper sysConferenceMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public SysConference queryById(String id) {
        return this.sysConferenceMapper.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param sysConference 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<SysConference> queryByPage(SysConference sysConference, PageRequest pageRequest) {
        long total = this.sysConferenceMapper.count(sysConference);
        return new PageImpl<>(this.sysConferenceMapper.queryAllByLimit(sysConference, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param sysConference 实例对象
     * @return 实例对象
     */
    @Override
    public SysConference insert(SysConference sysConference) {
        this.sysConferenceMapper.insert(sysConference);
        return sysConference;
    }

    /**
     * 修改数据
     *
     * @param sysConference 实例对象
     * @return 实例对象
     */
    @Override
    public SysConference update(SysConference sysConference) {
        this.sysConferenceMapper.update(sysConference);
        return this.queryById(sysConference.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String id) {
        return this.sysConferenceMapper.deleteById(id) > 0;
    }
}
