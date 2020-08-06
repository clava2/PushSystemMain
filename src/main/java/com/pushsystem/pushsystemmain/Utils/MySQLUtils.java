package com.pushsystem.pushsystemmain.Utils;

import java.io.*;


import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import java.util.logging.Logger;

public class MySQLUtils {
    public static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    //public static final String DB_URL = "jdbc:mysql://localhost:3306/user_info?useSSL=false&serverTimezone=UTC";
    public static Connection connection = null;
    public static Logger logger = Logger.getLogger("creating table");
    public static final String USER = "root";
    public static final String PASSWORD = "";

    public static Vector<String> STRING_ITEMS = new Vector<String>();


    public static Map<String,Object> frequentlyShouldUpdate = new HashMap<String,Object>();
    public static Map<String,Object> allShouldUpdate = new HashMap<String,Object>();
    public static Map<String,Object> userShouldUpdate = new HashMap<String,Object>();

    public static Connection getConnection(){
        return connection;
    }

    public static boolean initialize(String JDBCDriver, String DBURL, String User, String password) {
        try {
            Class.forName(JDBC_DRIVER);

            logger.info("连接数据库");
            connection = DriverManager.getConnection(DBURL, User, password);
        } catch (ClassNotFoundException e) {
            logger.info("找不到类");
            e.printStackTrace();
            return false;
        } catch (SQLException e) {
            logger.info("连接数据库失败");
            e.printStackTrace();
            return false;
        }
        STRING_ITEMS.add("name");
        STRING_ITEMS.add("password");
        return true;
    }

    public static  void runFile(String sqlFileName) {
        File file = new File(sqlFileName);
        BufferedReader sqlFileReader = null;
        try {
            sqlFileReader = new BufferedReader(new InputStreamReader(new FileInputStream(file),"UTF-8"));
            String tempSQL = null;
            while ((tempSQL = sqlFileReader.readLine()) != null) {
                System.out.println("read line: " + tempSQL);
                Statement statement = connection.createStatement();
                statement.execute(tempSQL);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}

//class info{
//    public int ID;
//    public String device;
//    public String type;
//    public int emergency;
//    public String toString(){
//        String result = new String();
//        result += "ID: " + ID;
//        result += "\tdevice: " + device;
//        result += "\t type: " + type;
//        result += "\t emergency: " + emergency;
//        return result;
//    }
//}
























