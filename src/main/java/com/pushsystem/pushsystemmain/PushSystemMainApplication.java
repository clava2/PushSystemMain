package com.pushsystem.pushsystemmain;

import com.pushsystem.pushsystemmain.Utils.DataLoader;
import com.pushsystem.pushsystemmain.Utils.MySQLUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
public class PushSystemMainApplication{
	private MySQLUtils mySQLUtils;
	public static void main(String[] args) {
        MySQLUtils.initialize("com.mysql.cj.jdbc.Driver","jdbc:mysql://localhost:3306/user_info?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true","root","123456");
        MySQLUtils.runFile("src/main/resources/DebugInitialize.sql");

        try {
            DataLoader.load(MySQLUtils.getConnection());
        }
        catch(Exception e){
            e.printStackTrace();
        }

        SpringApplication.run(PushSystemMainApplication.class, args);
	}
}
