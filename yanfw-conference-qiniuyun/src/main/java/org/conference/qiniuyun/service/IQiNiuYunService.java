package org.conference.qiniuyun.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qiniu.storage.model.FileInfo;
import org.conference.qiniuyun.entity.SysQiniuyunFile;

/**
 * @Description:
 * @Author: Yanfw
 * @Email: 840947210@qq.com
 * @Time: 2021/12/14
 */
public interface IQiNiuYunService extends IService<SysQiniuyunFile> {
//    String getFileUrl(String fileName);

     FileInfo getFileInfo(String fileName);
//     Boolean getFileInfo(String fileName,Integer days);
}
