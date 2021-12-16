package org.conference.system.config;

import io.minio.MinioClient;
import lombok.extern.slf4j.Slf4j;
import org.conference.common.exception.ConferenceException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description:
 * @Author: Yanfw
 * @Email: 840947210@qq.com
 * @Time: 2021/12/9
 */

@Slf4j
@Configuration
public class MinioConfig {
    @Value("${minio.minio_url}")
    private String endpoint;
    @Value("${minio.minio_name}")
    private String accessKey;
    @Value("${minio.minio_pass}")
    private String secretKey;
    /**
     * 注入minio 客户端
     */
    @Bean
    public MinioClient minioClient() {
        return MinioClient.builder()
                .endpoint(endpoint)
                .credentials(accessKey, secretKey)
                .build();
    }
}
