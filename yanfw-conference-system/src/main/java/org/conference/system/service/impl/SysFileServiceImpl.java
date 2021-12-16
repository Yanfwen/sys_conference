package org.conference.system.service.impl;

import org.conference.system.entity.SysFile;
import org.conference.system.mapper.SysFileMapper;
import org.conference.system.service.ISysFileService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import javax.annotation.Resource;

/**
 * (SysFile)表服务实现类
 *
 * @author makejava
 * @since 2021-12-10 17:11:14
 */
@Service("sysFileService")
public class SysFileServiceImpl extends ServiceImpl<SysFileMapper, SysFile> implements  ISysFileService {
    @Resource
    private SysFileMapper sysFileMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public SysFile queryById(String id) {
        return this.sysFileMapper.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param sysFile 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<SysFile> queryByPage(SysFile sysFile, PageRequest pageRequest) {
        long total = this.sysFileMapper.count(sysFile);
        return new PageImpl<>(this.sysFileMapper.queryAllByLimit(sysFile, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param sysFile 实例对象
     * @return 实例对象
     */
    @Override
    public SysFile insert(SysFile sysFile) {
        this.sysFileMapper.insert(sysFile);
        return sysFile;
    }

    /**
     * 修改数据
     *
     * @param sysFile 实例对象
     * @return 实例对象
     */
    @Override
    public SysFile update(SysFile sysFile) {
        this.sysFileMapper.update(sysFile);
        return this.queryById(sysFile.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String id) {
        return this.sysFileMapper.deleteById(id) > 0;
    }
}
