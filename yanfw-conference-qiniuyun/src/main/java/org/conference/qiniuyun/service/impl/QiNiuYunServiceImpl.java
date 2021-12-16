package org.conference.qiniuyun.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qiniu.storage.model.FileInfo;
import org.conference.qiniuyun.entity.SysQiniuyunFile;
import org.conference.qiniuyun.mapper.QiNiuYunMapper;
import org.conference.qiniuyun.service.IQiNiuYunService;
import org.conference.qiniuyun.utils.QiNiuYunUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @Author: Yanfw
 * @Email: 840947210@qq.com
 * @Time: 2021/12/14
 */
@Service("qiNiuYunService")
public class QiNiuYunServiceImpl extends ServiceImpl<QiNiuYunMapper, SysQiniuyunFile> implements IQiNiuYunService {

    @Override
    public FileInfo getFileInfo(String fileName) {
        return null;
    }

//    @Override
//    public String getFileUrl(String fileName) {
//        return QiNiuYunUtils.getPrivateFile(fileName);
//    }
}
