package com.jaspercloud.mybaits.mapper.util;

import com.jaspercloud.mybaits.mapper.definition.CommonMapper;
import org.apache.ibatis.jdbc.SQL;

public class CommonSQL extends SQL {

    public SQL SET(String column, String value) {
        String str = getCondition(column, value);
        SQL sql = SET(str);
        return sql;
    }

    public SQL WHERE(String column, String value) {
        String str = getCondition(column, value);
        SQL sql = WHERE(str);
        return sql;
    }

    public String getCondition(String column, String value) {
        StringBuilder builder = new StringBuilder();
        builder.append(column).append("=").append(value);
        String str = builder.toString();
        return str;
    }

    public static String VAR(String value) {
        StringBuilder builder = new StringBuilder();
        builder.append("#{")
                .append(value)
                .append("}");
        String str = builder.toString();
        return str;
    }

    public static String PARAM(String value) {
        StringBuilder builder = new StringBuilder();
        builder.append(CommonMapper.PARAM)
                .append(".")
                .append(value);
        String str = builder.toString();
        return str;
    }
}
