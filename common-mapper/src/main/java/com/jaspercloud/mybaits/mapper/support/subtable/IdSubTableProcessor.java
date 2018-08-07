package com.jaspercloud.mybaits.mapper.support.subtable;

public class IdSubTableProcessor implements SubTableProcessor {

    @Override
    public String getTableName(String tableName, Object attribute) {
        if (!(attribute instanceof Integer || attribute instanceof Long)) {
            throw new UnsupportedOperationException("not integer or long");
        }
        String str = String.valueOf(attribute);
        String sub = str.substring(str.length() - 3, str.length());
        String subTableName = tableName + "_" + sub;
        return subTableName;
    }
}
