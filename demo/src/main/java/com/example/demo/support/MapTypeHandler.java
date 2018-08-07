package com.example.demo.support;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;
import org.springframework.util.StringUtils;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class MapTypeHandler implements TypeHandler<Map<String, String>> {

    private static Gson gson = new Gson();

    @Override
    public void setParameter(PreparedStatement ps, int i, Map<String, String> parameter, JdbcType jdbcType) throws SQLException {
        String json = gson.toJson(parameter);
        ps.setString(i, json);
    }

    @Override
    public Map<String, String> getResult(ResultSet rs, String columnName) throws SQLException {
        String json = rs.getString(columnName);
        if (StringUtils.isEmpty(json)) {
            return new HashMap<>();
        }
        Map<String, String> map = gson.fromJson(json, new TypeToken<Map<String, String>>() {
        }.getType());
        return map;
    }

    @Override
    public Map<String, String> getResult(ResultSet rs, int columnIndex) throws SQLException {
        String json = rs.getString(columnIndex);
        if (StringUtils.isEmpty(json)) {
            return new HashMap<>();
        }
        Map<String, String> map = gson.fromJson(json, new TypeToken<Map<String, String>>() {
        }.getType());
        return map;
    }

    @Override
    public Map<String, String> getResult(CallableStatement cs, int columnIndex) throws SQLException {
        String json = cs.getString(columnIndex);
        if (StringUtils.isEmpty(json)) {
            return new HashMap<>();
        }
        Map<String, String> map = gson.fromJson(json, new TypeToken<Map<String, String>>() {
        }.getType());
        return map;
    }
}
