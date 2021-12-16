package org.conference.qiniuyun.controller;

import org.conference.common.api.Result;
import org.conference.qiniuyun.service.IQiNiuYunService;
import org.conference.qiniuyun.utils.QiNiuYunUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


/**
 * @Description:
 * @Author: Yanfw
 * @Email: 840947210@qq.com
 * @Time: 2021/12/14
 */
@RestController
@RequestMapping("/qiniuyun")
public class QiNiuYunController {
    String url = "http://yanq.top";
    String accessKey = "Rf5Zrvb3tEah__pcgbVIOt5R8hgovdHJYDBw6nGa";
    String secretKey = "LYyGS_-loCJWAjLJlp63kJtBD1gzIehReMoFmStH";
    String bucket = "yanfw-conference";
    @Autowired
    IQiNiuYunService qiNiuYunService;
    @Autowired
    QiNiuYunUtils qiNiuYunUtils;

    @GetMapping("/getFileInfo")
    public Result getFileInfo(@RequestParam("fileName") String fileName) {
        //"QQ图片20211212155118.jpg"
        return Result.ok(qiNiuYunUtils.getFileInfo(fileName));
    }

    @GetMapping("/getFileUrl")
    public Result getFileUrl(@RequestParam("fileName") String fileName) {
        //"QQ图片20211212155118.jpg"
        return Result.ok(qiNiuYunUtils.getFileInfo(fileName));
    }

    @GetMapping("/setFileLifeTime")
    public Result setFileLifeTime(@RequestParam("fileName") String fileName, @RequestParam("days") Integer days) {
        //"QQ图片20211212155118.jpg"
        return Result.ok(qiNiuYunUtils.setFileLifeTime(fileName, days));
    }

    @GetMapping("/getFileList")
    public Result<Object> getFileList() {
        return Result.ok(qiNiuYunUtils.getFileList());
    }

    @PostMapping("/uploadFile")
    public Result<Object>  uploadFile(@RequestParam("file")MultipartFile file){
        return Result.ok(qiNiuYunUtils.uploadFile(file));
    }

    @PostMapping("/removeFile")
    public Result<Object>  removeFile(@RequestParam("fileName")String fileName){
        return Result.ok(qiNiuYunUtils.removeFile(fileName));
    }


    @GetMapping("/downLoadFile")
    public Result<Object>  downLoadFile(@RequestParam("fileName")String fileName){
        return Result.ok(qiNiuYunUtils.downLoadFile(fileName));
    }

}
