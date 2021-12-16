package org.conference.modules.minio.controller;

import io.micrometer.core.instrument.util.StringUtils;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.conference.common.api.Result;
import org.conference.modules.minio.util.MinioUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description:
 * @Author: Yanfw
 * @Email: 840947210@qq.com
 * @Time: 2021/12/11
 */

@Slf4j
@RestController
@RequestMapping("/minio")
public class MinioController {
    @Resource
    MinioUtils minioUtils;

    @ApiOperation(value = "判断Bucket是否存在")
    @GetMapping("/bucketExists")
    public Result bucketExists(@RequestParam(value = "bucketName") String bucketName) {
        if (minioUtils.bucketExists(bucketName)) {
            return Result.ok("Bucket已存在！");
        }
        log.error("Bucket不存在！");
        return Result.error("Bucket不存在！");
    }

    @ApiOperation(value = "获取所有的bucket")
    @GetMapping("/getBucket")
    public Result getBucket() {
        return Result.ok(minioUtils.listBucketNames());
    }


    @ApiOperation(value = "获取所有的bucketFile")
    @GetMapping("/getBucketFile")
    public Result getBucketFile() {
        return Result.ok(minioUtils.listBucketFileObjects("conference"));
    }

    @ApiOperation(value = "创建Bucket")
    @GetMapping("/makeBucket")
    public Result makeBucket(@RequestParam(value = "bucketName") String bucketName) {
        if (minioUtils.makeBucket(bucketName)) {
            return Result.ok("创建Bucket成功！");
        }
        log.error("创建Bucket失败！");
        return Result.error("创建Bucket失败！");
    }

    @ApiOperation(value = "删除Bucket")
    @GetMapping("/removeNullBucket")
    public Result removeNullBucket(@RequestParam(value = "bucketName") String bucketName) {
        if (minioUtils.removeNullBucket(bucketName)) {
            return Result.ok("删除Bucket成功！");
        }
        log.error("删除Bucket失败！");
        return Result.error("删除Bucket失败！");
    }

    @ApiOperation(value = "删除Bucket")
    @GetMapping("/removeBucket")
    public Result removeBucket(@RequestParam(value = "bucketName") String bucketName) {
        if (minioUtils.removeBucket(bucketName)) {
            return Result.ok("删除Bucket成功！");
        }
        log.error("删除Bucket失败！");
        return Result.error("删除Bucket失败！");
    }

    @ApiOperation(value = "删除Bucket中的file")
    @GetMapping("/removeBucketFile")
    public Result removeBucketFile(@RequestParam(value = "bucketName") String bucketName, @RequestParam("fileName") String fileName) {
        if (minioUtils.deleteFile(bucketName, fileName)) {
            return Result.ok("删除file成功！");
        }
        log.error("删除file失败！");
        return Result.error("删除file失败！");
    }

    @ApiOperation(value = "Minio文件上传")
    @PostMapping("/upload")
    public Result upload(MultipartFile file, @RequestParam(value = "bucketName") String bucketName) {
        if (StringUtils.isBlank(bucketName)) {
            log.error("存储bucket名称为空，无法上传");
            return Result.error("存储bucket名称为空，无法上传");
        }
        if (!minioUtils.upload(file, bucketName)) {
            log.error("文件上传异常");
            return Result.error("文件上传异常");
        }
        return Result.ok("文件上传成功");
    }


    @ApiOperation(value = "Minio文件下载")
    @GetMapping(value = "/download")
    public Result download(@RequestParam(value = "bucketName") String bucketName, @RequestParam("fileName") String fileName, HttpServletResponse response) {
        if (!minioUtils.download(bucketName, fileName, response)) {
            log.error("文件下载错误！");
        }
        return Result.ok("文件下载成功！");
    }

    @ApiOperation(value = "file生产URL链接")
    @GetMapping(value = "/getFileUploadURL")
    public Result getFileUploadURL(@RequestParam(value = "bucketName") String bucketName, @RequestParam("fileName") String fileName) {
        return Result.ok(minioUtils.getFileUploadURL(bucketName, fileName));
    }





}
