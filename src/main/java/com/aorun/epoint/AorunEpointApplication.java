package com.aorun.epoint;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
// mapper 接口类扫描包配置
@MapperScan("com.aorun.epoint.dao")
public class AorunEpointApplication {

    public static void main(String[] args) {
        SpringApplication.run(AorunEpointApplication.class, args);
    }

}
