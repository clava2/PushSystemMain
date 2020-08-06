package com.pushsystem.pushsystemmain;

import com.pushsystem.pushsystemmain.Utils.MySQLUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PushSystemMainApplication{
	private MySQLUtils mySQLUtils;
	public static void main(String[] args) {
        MySQLUtils.initialize("com.mysql.cj.jdbc.Driver","jdbc:mysql://localhost:3306/user_info?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true","root","");
        MySQLUtils.runFile("src/main/resources/DebugInitialize.sql");
        SpringApplication.run(PushSystemMainApplication.class, args);
	}
}
