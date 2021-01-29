package com.xawl.mxd;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.xawl.mxd.dao")
public class SpringbootCrudV1Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootCrudV1Application.class, args);
	}

}
