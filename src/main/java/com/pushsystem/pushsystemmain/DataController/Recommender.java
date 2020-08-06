package com.pushsystem.pushsystemmain.DataController;

import com.pushsystem.pushsystemmain.Utils.DataLoader;
import com.pushsystem.pushsystemmain.Utils.MySQLUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Recommender {

    @RequestMapping("/initialize")
    public void initialize(){
        try {
            DataLoader.load(MySQLUtils.getConnection());
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    @RequestMapping("/recommend")
    public List<String> recommend(@RequestParam("userID") String userID){
        List<String> result = DataLoader.getUserViewHistoryHashMap().get(userID).getFrequentlyViewed();
        return result;
    }
}
