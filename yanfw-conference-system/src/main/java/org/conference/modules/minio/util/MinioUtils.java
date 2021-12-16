package org.conference.modules.minio.util;

import io.minio.*;
import io.minio.http.Method;
import io.minio.messages.*;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import org.springframework.util.FastByteArrayOutputStream;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Component
public class MinioUtils {

    @Resource
    MinioClient minioClient;

    /**
     * 默认超时时间
     */
    private static final int DEFAULT_EXPIRY_TIME = 7 * 24 * 3600;

    /**
     * 查看存储bucket是否存在
     *
     * @param bucketName 存储bucket
     * @return boolean
     */
    public Boolean bucketExists(String bucketName) {
        Boolean found;
        try {
            found = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return found;
    }


    /**
     * 创建存储bucket
     *
     * @param bucketName 存储bucket名称
     * @return Boolean
     */
    public Boolean makeBucket(String bucketName) {
        try {
            minioClient.makeBucket(MakeBucketArgs.builder()
                    .bucket(bucketName)
                    .build());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


    /**
     * 列出所有存储桶名称
     *
     * @return
     */
    @SneakyThrows
    public List<String> listBucketNames() {
        List<Bucket> bucketList = minioClient.listBuckets();
        List<String> bucketListName = new ArrayList<>();
        for (Bucket bucket : bucketList) {
            bucketListName.add(bucket.name());
        }
        return bucketListName;
    }

    /**
     * 列出存储桶中的所有对象
     *
     * @param bucketName 存储桶名称
     * @return
     */
    @SneakyThrows
    public Iterable<Result<Item>> listBucketFileObjects(String bucketName) {
        List<String> bucketListName = new ArrayList<>();
        if (bucketExists(bucketName)) {
            Iterable<Result<Item>> myObjects = minioClient.listObjects(ListObjectsArgs.builder().bucket(bucketName).build());
            return myObjects;
        }
        return null;
    }

    /**
     * 删除一个对象
     *
     * @param bucketName 存储桶名称
     */
    @SneakyThrows
    public Boolean removeBucket(String bucketName) {
        boolean flag = bucketExists(bucketName);
        if (flag) {
            minioClient.removeObject(RemoveObjectArgs.builder().bucket(bucketName).build());
            return true;
        }
        return false;
    }

    /**
     * 删除存储bucket
     *
     * @param bucketName 存储bucket名称
     * @return Boolean
     */
    public Boolean removeNullBucket(String bucketName) {
        try {
            // 有子项目不删除
            if (bucketExists(bucketName)) {
                Iterable<Result<Item>> myObjects = minioClient.listObjects(ListObjectsArgs.builder().bucket(bucketName).build());
                for (Result<Item> result : myObjects) {
                    Item item = result.get();
                    // 有对象文件，则删除失败
                    if (item.size() > 0) {
                        return false;
                    }
                }
            }
            // 删除 空bucket
            minioClient.removeBucket(RemoveBucketArgs.builder()
                    .bucket(bucketName)
                    .build());
            if (!bucketExists(bucketName)) {
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 文件上传
     *
     * @param file       文件
     * @param bucketName 存储bucket
     * @return Boolean
     */
    public Boolean upload(MultipartFile file, String bucketName) {
        if (file == null) {
            return null;
        }

        try {
            // 获取文件名
            String fileName = file.getOriginalFilename();
//            String suffixName = "";
//            if (fileName.contains(".")) {
//                // 获取文件名后缀
//                suffixName = fileName.substring(fileName.lastIndexOf("."));
//            }
            // 获取唯一ID
            UUID uuid = UUID.randomUUID();
            StringBuilder uuidStr = new StringBuilder();
            // 去除UUID的"-"
            uuidStr.append(uuid.toString().replace("-", ""));
            // 获取新文件名
            String newFileName = "conference_" + uuidStr + "_" + fileName;
            StringBuilder s = new StringBuilder();
            PutObjectArgs objectArgs = PutObjectArgs.builder()
                    .bucket(bucketName)
                    .object(newFileName)
                    .stream(file.getInputStream(), file.getSize(), -1)
                    // 设置文件类型 contentType("video/mp4")
                    .contentType(file.getContentType())
                    .build();
            // 文件名称相同会覆盖
            minioClient.putObject(objectArgs);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 文件下载
     *
     * @param bucketName 存储bucket名称
     * @param fileName   文件名称
     * @param res        response
     * @return Boolean
     */
    public Boolean download(String bucketName, String fileName, HttpServletResponse res) {
        GetObjectArgs objectArgs = GetObjectArgs.builder().bucket(bucketName)
                .object(fileName).build();
        try (GetObjectResponse response = minioClient.getObject(objectArgs)) {
            byte[] buf = new byte[1024];
            int len;
            try (FastByteArrayOutputStream os = new FastByteArrayOutputStream()) {
                while ((len = response.read(buf)) != -1) {
                    os.write(buf, 0, len);
                }
                os.flush();
                byte[] bytes = os.toByteArray();
                // 设置编码
                res.setCharacterEncoding("utf-8");
                // 设置强制下载不打开
                res.setContentType("application/force-download");
                res.addHeader("Content-Disposition", "attachment;fileName=" + fileName);
                try (ServletOutputStream stream = res.getOutputStream()) {
                    stream.write(bytes);
                    stream.flush();
                }
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 删除桶
     *
     * @param bucketName 存储bucket名称
     * @param fileName   文件名称
     * @return Boolean
     */
    public Boolean deleteFile(String bucketName, String fileName) {
        try {
            minioClient.removeObject(RemoveObjectArgs.builder().bucket(bucketName).object(fileName).build());
            return true;
        } catch (Exception e) {
            e.getMessage();
            return false;
        }
    }


    /**
     * 生成一个给HTTP PUT请求用的presigned URL。
     * 浏览器/移动端的客户端可以用这个URL进行上传，即使其所在的存储桶是私有的。这个presigned URL可以设置一个失效时间，默认值是7天。
     *
     * @param bucketName 存储桶名称
     * @param fileName   存储桶里的对象名称
     */
    @SneakyThrows
    public String getFileUploadURL(String bucketName, String fileName) {
        // 设置请求头参数
//        Map<String, String> reqParams = new HashMap<String, String>();
//        reqParams.put("response-content-type", "application/json");
        boolean flag = bucketExists(bucketName);
        String url = "";
        if (flag) {
            url = minioClient.getPresignedObjectUrl(
                    GetPresignedObjectUrlArgs.builder()
                            .method(Method.GET)
                            .bucket(bucketName)
                            .object(fileName)
                            // 设置时间有效性 两小时 expiry(2, TimeUnit.HOURS)
                            .expiry(1, TimeUnit.DAYS)
                            // .extraQueryParams(reqParams)
                            .build());
            System.out.println(url);
        }
        return url;
    }
}
