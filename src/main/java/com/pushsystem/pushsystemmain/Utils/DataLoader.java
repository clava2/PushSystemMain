package com.pushsystem.pushsystemmain.Utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class DataLoader {
    private static Map<String, UserViewHistory> userViewHistoryHashMap = new HashMap<String, UserViewHistory>();

    public static Map<String,UserViewHistory> getUserViewHistoryHashMap(){return userViewHistoryHashMap;}

    public static void load(Connection connection) throws Exception{
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from query_frequency_info");

        while(resultSet.next()){
            UserViewHistory userViewHistory = new UserViewHistory();
            userViewHistory.setCount(resultSet.getString("fieldName"),resultSet.getInt("count"));
            userViewHistoryHashMap.put(resultSet.getString("userName"),userViewHistory);
        }
    }

    public static void save(Connection connection){
        try {
            Statement statement = connection.createStatement();
            for (Map.Entry<String, UserViewHistory> entry : userViewHistoryHashMap.entrySet()) {
                String userName = entry.getKey();
                Map<String, Integer> viewCount = entry.getValue().getViewCount();
                for (Map.Entry<String, Integer> innerEntry : viewCount.entrySet()) {
                    statement.execute("insert into query_frequency_info (userName,fieldName,count) values (" + userName + "," + innerEntry.getKey() + "," + innerEntry.getValue() + ");");
                }
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

}
