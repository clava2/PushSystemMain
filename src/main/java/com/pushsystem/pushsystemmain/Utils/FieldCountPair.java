package com.pushsystem.pushsystemmain.Utils;

public class FieldCountPair {
    private String fieldName;
    private Integer count;

    public FieldCountPair(String fieldName, Integer count){
        this.fieldName = fieldName;
        this.count = count;
    }

    public String getFieldName(){ return fieldName; }
    public Integer getCount() { return count; }
}
