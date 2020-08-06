package com.pushsystem.pushsystemmain.DataController;

import com.pushsystem.pushsystemmain.Utils.DataLoader;
import com.pushsystem.pushsystemmain.Utils.MySQLUtils;
import com.pushsystem.pushsystemmain.Utils.UserViewHistory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class UserEventController {

    /**
     * 用户执行查询操作时调用该接口。改接口记录用户的查询操作
     * @param userID 用户ID，为0则表示影响所有用户。
     * @param field  用户查询的字段，字段格式为<filed_code>:<filed_name>，
     *               字段名称需要与构建的字段树的叶子节点id一致，否则会导致
     *               无法查询得到字段对应的id。
     */
    @RequestMapping("/view")
    public void userView(@RequestParam(value="userID") String userID,
                         @RequestParam(value="field")  String field){
        if(DataLoader.getUserViewHistoryHashMap().get(userID) != null) {
            DataLoader.getUserViewHistoryHashMap().get(userID).view(field);
        }
        else{
            DataLoader.getUserViewHistoryHashMap().put(userID,new UserViewHistory(field,1));
        }
        DataLoader.save(MySQLUtils.getConnection());
    }


    @RequestMapping("/clearView")
    public void clearUserView(@RequestParam(value="userID") String userID) {
        DataLoader.getUserViewHistoryHashMap().get(userID).clearAll();
        DataLoader.save(MySQLUtils.getConnection());
    }
}
