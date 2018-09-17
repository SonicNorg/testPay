package com.axel.testpay.util;

public class QuerryUtils {

    public static String getSelectQueryForEntity(String entityName, int id) {
        return "SELECT * FROM "+entityName+" WHERE id = "+id;
    }

    public static String getDeleteQueryForEntity(String entityName, int id) {
        return "DELETE FROM "+entityName+" WHERE id="+id;
    }
    //update and insert is very specialize


}
