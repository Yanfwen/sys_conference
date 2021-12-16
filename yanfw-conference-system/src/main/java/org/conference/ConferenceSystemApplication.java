package org.conference;

import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.annotation.MapperScans;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;

/**
 * @Description:
 * @Author: Yanfw
 * @Email: 840947210@qq.com
 * @Time: 2021/12/12
 */
// com.mongodb.MongoSocketOpenException: Exception opening socket
@SpringBootApplication(
        scanBasePackages = {
                "org.conference", "org.conference.system",
                "org.conference.common.utils","org.conference.modules"}
        , exclude = MongoAutoConfiguration.class)
// A component required a bean of type 'org.conference.system.mapper.SysCheckinMapper' that could not be found.
@MapperScans({
        @MapperScan("org.conference.system.mapper"),
        @MapperScan("org.conference.modules.*.mapper")
})
public class ConferenceSystemApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConferenceSystemApplication.class, args);
    }
}
