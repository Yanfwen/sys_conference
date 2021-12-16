package org.conference.system.mapper;

import org.conference.system.entity.SysCheckinLog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * (SysCheckinLog)表数据库访问层
 *
 * @author makejava
 * @since 2021-12-10 17:11:14
 */

public interface SysCheckinLogMapper extends  BaseMapper<SysCheckinLog>{

    /**
     * 通过ID查询单条数据
     *
     * @param mettingId 主键
     * @return 实例对象
     */
    SysCheckinLog queryById(String mettingId);

    /**
     * 查询指定行数据
     *
     * @param sysCheckinLog 查询条件
     * @param pageable         分页对象
     * @return 对象列表
     */
    List<SysCheckinLog> queryAllByLimit(SysCheckinLog sysCheckinLog, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param sysCheckinLog 查询条件
     * @return 总行数
     */
    long count(SysCheckinLog sysCheckinLog);

    /**
     * 新增数据
     *
     * @param sysCheckinLog 实例对象
     * @return 影响行数
     */
    int insert(SysCheckinLog sysCheckinLog);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<SysCheckinLog> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<SysCheckinLog> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<SysCheckinLog> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<SysCheckinLog> entities);

    /**
     * 修改数据
     *
     * @param sysCheckinLog 实例对象
     * @return 影响行数
     */
    int update(SysCheckinLog sysCheckinLog);

    /**
     * 通过主键删除数据
     *
     * @param mettingId 主键
     * @return 影响行数
     */
    int deleteById(String mettingId);

}

