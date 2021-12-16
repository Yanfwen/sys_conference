package org.conference;

import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.annotation.MapperScans;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Description:
 * @Author: Yanfw
 * @Email: 840947210@qq.com
 * @Time: 2021/12/14
 */

@SpringBootApplication(scanBasePackages = {"org.conference.qiniuyun","org.conference.*"})
@MapperScan("org.conference.qiniuyun.mapper")
public class QiNiuYunSystemAppliation {

    public static void main(String[] args) {
        SpringApplication.run(QiNiuYunSystemAppliation.class, args);
    }
}
