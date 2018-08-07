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
import java.util.ArrayList;
import java.util.List;

public class ImageListTypeHandler implements TypeHandler<List<String>> {

    private static Gson gson = new Gson();

    @Override
    public void setParameter(PreparedStatement ps, int i, List<String> parameter, JdbcType jdbcType) throws SQLException {
        String images = gson.toJson(parameter);
        ps.setString(i, images);
    }

    @Override
    public List<String> getResult(ResultSet rs, String columnName) throws SQLException {
        String images = rs.getString(columnName);
        if (StringUtils.isEmpty(images)) {
            return new ArrayList<>();
        }
        List<String> list = gson.fromJson(images, new TypeToken<List<String>>() {
        }.getType());
        return list;
    }

    @Override
    public List<String> getResult(ResultSet rs, int columnIndex) throws SQLException {
        String images = rs.getString(columnIndex);
        if (StringUtils.isEmpty(images)) {
            return new ArrayList<>();
        }
        List<String> list = gson.fromJson(images, new TypeToken<List<String>>() {
        }.getType());
        return list;
    }

    @Override
    public List<String> getResult(CallableStatement cs, int columnIndex) throws SQLException {
        String images = cs.getString(columnIndex);
        if (StringUtils.isEmpty(images)) {
            return new ArrayList<>();
        }
        List<String> list = gson.fromJson(images, new TypeToken<List<String>>() {
        }.getType());
        return list;
    }
}
