package org.conference.system.service.impl;

import org.conference.system.entity.SysCheckinLog;
import org.conference.system.mapper.SysCheckinLogMapper;
import org.conference.system.service.ISysCheckinLogService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import javax.annotation.Resource;

/**
 * (SysCheckinLog)表服务实现类
 *
 * @author makejava
 * @since 2021-12-10 17:11:14
 */
@Service("sysCheckinLogService")
public class SysCheckinLogServiceImpl extends ServiceImpl<SysCheckinLogMapper, SysCheckinLog> implements  ISysCheckinLogService {
    @Resource
    private SysCheckinLogMapper sysCheckinLogMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param mettingId 主键
     * @return 实例对象
     */
    @Override
    public SysCheckinLog queryById(String mettingId) {
        return this.sysCheckinLogMapper.queryById(mettingId);
    }

    /**
     * 分页查询
     *
     * @param sysCheckinLog 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<SysCheckinLog> queryByPage(SysCheckinLog sysCheckinLog, PageRequest pageRequest) {
        long total = this.sysCheckinLogMapper.count(sysCheckinLog);
        return new PageImpl<>(this.sysCheckinLogMapper.queryAllByLimit(sysCheckinLog, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param sysCheckinLog 实例对象
     * @return 实例对象
     */
    @Override
    public SysCheckinLog insert(SysCheckinLog sysCheckinLog) {
        this.sysCheckinLogMapper.insert(sysCheckinLog);
        return sysCheckinLog;
    }

    /**
     * 修改数据
     *
     * @param sysCheckinLog 实例对象
     * @return 实例对象
     */
    @Override
    public SysCheckinLog update(SysCheckinLog sysCheckinLog) {
        this.sysCheckinLogMapper.update(sysCheckinLog);
        return this.queryById(sysCheckinLog.getMettingId());
    }

    /**
     * 通过主键删除数据
     *
     * @param mettingId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String mettingId) {
        return this.sysCheckinLogMapper.deleteById(mettingId) > 0;
    }
}
