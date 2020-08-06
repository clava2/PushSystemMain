package com.pushsystem.pushsystemmain.Utils;

import java.util.*;

public class UserViewHistory {
    private Map<String,Integer> viewCount = new HashMap<String,Integer>();

    public void setCount(String fieldName, Integer count){
        viewCount.put(fieldName,count);
    }

    public Map<String,Integer> getViewCount() { return viewCount; }

    public void clearAll(){
        viewCount.clear();
    }

    public void view(String field){
        view(field,1);
    }

    public void view(String field, Integer count){
        if(viewCount.get(field) == null) {
            viewCount.put(field, count);
            return;
        }
        viewCount.put(field,viewCount.get(field) + count);
    }

    public List<String> getFrequentlyViewed(){
        return getFrequentlyViewed(5);
    }

    public List<String> getFrequentlyViewed(Integer count){
        List<String> result = new ArrayList<String>();
        List<FieldCountPair> fieldCountPairs = this.toFieldCountPair();
        Collections.sort(fieldCountPairs,new Comparator<FieldCountPair>(){
            @Override
            public int compare(FieldCountPair fieldCountPair1, FieldCountPair fieldCountPair2){
                if(fieldCountPair1.getCount() < fieldCountPair2.getCount()){
                    return -1;
                }
                else if(fieldCountPair1.getCount() == fieldCountPair2.getCount()){
                    return 0;
                }
                else{
                    return 1;
                }
            }
        });
        for(int i = 0;i != count;i++){
            result.add(fieldCountPairs.get(i).getFieldName());
        }
        return result;
    }

    public List<FieldCountPair> toFieldCountPair(){
        List<FieldCountPair> result = new ArrayList<FieldCountPair>();
        for(Map.Entry<String,Integer> entry: viewCount.entrySet()){
            result.add(new FieldCountPair(entry.getKey(),entry.getValue()));
        }
        return result;
    }
}
