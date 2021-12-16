package org.conference.qiniuyun.utils;

import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.*;
import com.qiniu.storage.model.FileInfo;
import com.qiniu.storage.persistent.FileRecorder;
import com.qiniu.util.Auth;
import lombok.extern.slf4j.Slf4j;
import org.conference.common.api.Result;
import org.conference.common.constant.DateConstant;
import org.conference.common.utils.DateUtils;
import org.conference.qiniuyun.config.QiNiuYunConfig;
import org.conference.qiniuyun.entity.SysQiniuyunFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ansi.AnsiOutput;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.file.Paths;
import java.sql.SQLOutput;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @Description:
 * @Author: Yanfw
 * @Email: 840947210@qq.com
 * @Time: 2021/12/14
 */
@Component
@Slf4j
public class QiNiuYunUtils {
    @Value("${oss.qiniu.url}")
    private String url;
    @Value("${oss.qiniu.accessKey}")
    private String accessKey;
    @Value("${oss.qiniu.secretKey}")
    private String secretKey;
    @Value("${oss.qiniu.bucketName}")
    private String bucketName;

    /**
     * 机房
     * #华东	Region.region0(), Region.huadong()
     * #华北	Region.region1(), Region.huabei()
     * #华南	Region.region2(), Region.huanan()
     */
    private Configuration cfg;
    //上传的token
    private String token;
    // 七牛认证管理
    private Auth auth;
    private BucketManager bucketManager;
    private UploadManager uploadManager;

    /**
     * 初始化
     */
    public void QiNiuYunUtils() {
        auth = Auth.create(accessKey, secretKey);
        token = auth.uploadToken(bucketName);
        cfg = new Configuration(Region.autoRegion());
        bucketManager = new BucketManager(auth, cfg);
    }


    /***
     * 获取文件信息: fsize、putTime、mimeType、type、status、md5
     * @param fileName
     * @return
     */
    public FileInfo getFileInfo(String fileName) {
        this.QiNiuYunUtils();
        try {
            return bucketManager.stat(bucketName, fileName);
        } catch (QiniuException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 设置文件过期时间
     *
     * @param fileName
     * @param days
     * @return
     */
    public Boolean setFileLifeTime(String fileName, Integer days) {
        this.QiNiuYunUtils();
        try {
            bucketManager.deleteAfterDays(bucketName, fileName, days);
            return true;
        } catch (QiniuException ex) {
            log.error(ex.response.toString());
        }
        return false;
    }

    /**
     * 获取空间文件列表
     */
    public List<SysQiniuyunFile> getFileList() {
        this.QiNiuYunUtils();
        //每次迭代的长度限制，最大1000，推荐值 1000
        int limit = 1000;
        //指定目录分隔符，列出所有公共前缀（模拟列出目录效果）。缺省值为空字符串
        String delimiter = "";
        //文件名前缀
        String prefix = "";
        BucketManager.FileListIterator fileListIterator = bucketManager.createFileListIterator(bucketName, prefix, limit, delimiter);
        List<SysQiniuyunFile> sysQiniuyunFileList = new ArrayList<>();

        while (fileListIterator.hasNext()) {
            //处理获取的file list结果
            FileInfo[] items = fileListIterator.next();
            for (FileInfo item : items) {
                // 文件ID_hash值、文件名、文件类型、文件大小、文件日期
                // 0-13 毫秒
                // 0-10 秒
                String timestamp = String.valueOf(item.putTime).substring(0, 13);
                sysQiniuyunFileList.add(new SysQiniuyunFile(
                        item.hash, item.key,
                        item.mimeType, 1, String.valueOf(item.fsize),
                        DateUtils.localDate2Date(DateUtils.formatStr2LocalDate(DateUtils.TimeStamp2date(Long.valueOf(timestamp)), DateConstant.DATETIME_BAR_SS_FORMAT))));
            }
        }
        return sysQiniuyunFileList;
    }

    /**
     * 上传文件
     **/
    public Boolean uploadFile(MultipartFile file) {
        this.QiNiuYunUtils();
        uploadManager = new UploadManager(cfg);
        try {
            // 获取文件的名称
            String fileName = file.getOriginalFilename();
            System.out.println(fileName + "<<<><><><><>");
            // 获取唯一ID
            UUID uuid = UUID.randomUUID();
            StringBuilder uuidStr = new StringBuilder();
            // 去除UUID的"-"
            uuidStr.append(uuid.toString().replace("-", ""));
            // 获取新文件名
            String newFileName = "conference_" + uuidStr + "_" + fileName;
            System.out.println(newFileName + "------------");
            FileInputStream inputStream = (FileInputStream) file.getInputStream();
            // 上传图片文件
            Response res = uploadManager.put(inputStream, newFileName, token, null, null);
            if (!res.isOK()) {
                throw new RuntimeException("上传七牛出错：" + res.toString());
            }
            return true;
        } catch (QiniuException ex) {
            throw new RuntimeException(ex.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }


    /**
     * 获取私有空间文件
     */
    public String getPrivateFile(String fileName) {
        try {
            Response response = bucketManager.delete("yanfw-conference", fileName);
            return response.statusCode == 200 ? "删除成功" : "删除失败";
        } catch (QiniuException ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

    public boolean downLoadFile(String fileName) {
        this.QiNiuYunUtils();
        try {
            // 文件名编码转换
            String encodedFileName = URLEncoder.encode(fileName, "utf-8").replace("+", "%20");
            String finalUrl = String.format("%s/%s", url, encodedFileName);
            System.out.println(finalUrl);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return true;
    }


    /***
     * 根据空间名、文件名删除文件
     * @param fileName
     */
    public boolean removeFile(String fileName) {
        this.QiNiuYunUtils();
        uploadManager = new UploadManager(cfg);
        try {
            bucketManager.delete(bucketName, fileName);
            bucketManager.delete(bucketName, fileName);
        } catch (QiniuException e) {
            e.printStackTrace();
        }
        return true;
    }
}
