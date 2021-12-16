package org.conference.system.service.impl;

import org.conference.system.entity.SysMessage;
import org.conference.system.mapper.SysMessageMapper;
import org.conference.system.service.ISysMessageService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import javax.annotation.Resource;

/**
 * (SysMessage)表服务实现类
 *
 * @author makejava
 * @since 2021-12-10 17:11:14
 */
@Service("sysMessageService")
public class SysMessageServiceImpl extends ServiceImpl<SysMessageMapper, SysMessage> implements  ISysMessageService {
    @Resource
    private SysMessageMapper sysMessageMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public SysMessage queryById(String id) {
        return this.sysMessageMapper.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param sysMessage 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<SysMessage> queryByPage(SysMessage sysMessage, PageRequest pageRequest) {
        long total = this.sysMessageMapper.count(sysMessage);
        return new PageImpl<>(this.sysMessageMapper.queryAllByLimit(sysMessage, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param sysMessage 实例对象
     * @return 实例对象
     */
    @Override
    public SysMessage insert(SysMessage sysMessage) {
        this.sysMessageMapper.insert(sysMessage);
        return sysMessage;
    }

    /**
     * 修改数据
     *
     * @param sysMessage 实例对象
     * @return 实例对象
     */
    @Override
    public SysMessage update(SysMessage sysMessage) {
        this.sysMessageMapper.update(sysMessage);
        return this.queryById(sysMessage.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String id) {
        return this.sysMessageMapper.deleteById(id) > 0;
    }
}
