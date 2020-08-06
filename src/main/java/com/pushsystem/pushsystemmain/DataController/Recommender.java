package com.pushsystem.pushsystemmain.DataController;

import com.pushsystem.pushsystemmain.Utils.DataLoader;
import com.pushsystem.pushsystemmain.Utils.MySQLUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class Recommender {

    @RequestMapping("/recommend")
    public List<String> recommend(@RequestParam("userID") String userID){
        List<String> result = new ArrayList<String>();
        result.add("010101:轴系信息");
        result.add("010102:动力信息");
        if(DataLoader.getUserViewHistoryHashMap().get(userID) != null) {
            result = DataLoader.getUserViewHistoryHashMap().get(userID).getFrequentlyViewed();
        }
        return result;
    }
}
