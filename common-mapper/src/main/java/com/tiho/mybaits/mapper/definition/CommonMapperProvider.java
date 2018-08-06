package com.tiho.mybaits.mapper.definition;

import com.tiho.mybaits.mapper.config.EntityConfig;
import com.tiho.mybaits.mapper.util.CommonMapperContext;
import com.tiho.mybaits.mapper.util.CommonSQL;
import com.tiho.mybaits.mapper.util.ReflectUtils;
import org.apache.ibatis.annotations.Param;

import java.lang.reflect.Field;
import java.util.Map;

public class CommonMapperProvider {

    public String insert(Class<?> clazz, @Param(CommonMapper.PARAM) Object obj) {
        CommonMapperContext commonMapperContext = CommonMapperContext.get();
        EntityConfig entityConfig = commonMapperContext.getEntityConfig();
        String sql = new CommonSQL() {
            {
                INSERT_INTO(entityConfig.getSubTableName());
                for (Map.Entry<String, Field> entry : entityConfig.getFieldMap().entrySet()) {
                    String key = entry.getKey();
                    Field field = entry.getValue();
                    VALUES(key, VAR(PARAM(field.getName())));
                }
            }
        }.toString();
        return sql;
    }

    public String updateAllById(Class<?> clazz, @Param(CommonMapper.PARAM) Object obj) {
        String sql = updateById(clazz, obj, true);
        return sql;
    }

    public String updateNotNullById(Class<?> clazz, @Param(CommonMapper.PARAM) Object obj) {
        String sql = updateById(clazz, obj, false);
        return sql;
    }

    public String deleteById(Class<?> clazz, @Param(CommonMapper.PARAM) Long id) {
        CommonMapperContext commonMapperContext = CommonMapperContext.get();
        EntityConfig entityConfig = commonMapperContext.getEntityConfig();

        String sql = new CommonSQL() {
            {
                DELETE_FROM(entityConfig.getSubTableName());
                WHERE(entityConfig.getId(), VAR(CommonMapper.PARAM));
            }
        }.toString();
        return sql;
    }

    public String delete(Class<?> clazz, @Param(CommonMapper.PARAM) Object obj) {
        CommonMapperContext commonMapperContext = CommonMapperContext.get();
        EntityConfig entityConfig = commonMapperContext.getEntityConfig();

        String sql = new CommonSQL() {
            {
                DELETE_FROM(entityConfig.getSubTableName());
                for (Map.Entry<String, Field> entry : entityConfig.getFieldMap().entrySet()) {
                    String key = entry.getKey();
                    Field field = entry.getValue();
                    Object value = ReflectUtils.createFieldBuilder(field).accessible().getValue(obj);
                    if (null != value) {
                        WHERE(key, VAR(PARAM(field.getName())));
                    }
                }
            }
        }.toString();
        return sql;
    }

    public String selectOneById(Class<?> clazz, @Param(CommonMapper.PARAM) Long id) {
        CommonMapperContext commonMapperContext = CommonMapperContext.get();
        EntityConfig entityConfig = commonMapperContext.getEntityConfig();

        String sql = new CommonSQL() {
            {
                SELECT("*");
                FROM(entityConfig.getSubTableName());
                WHERE(entityConfig.getId(), VAR(CommonMapper.PARAM));
            }
        }.toString();
        return sql;
    }

    public String selectOne(Class<?> clazz, @Param(CommonMapper.PARAM) Object obj) {
        String baseSql = select(clazz, obj);
        StringBuilder builder = new StringBuilder(baseSql);
        builder.append("limit 1");
        String sql = builder.toString();
        return sql;
    }

    public String select(Class<?> clazz, @Param(CommonMapper.PARAM) Object obj) {
        CommonMapperContext commonMapperContext = CommonMapperContext.get();
        EntityConfig entityConfig = commonMapperContext.getEntityConfig();

        String sql = new CommonSQL() {
            {
                SELECT("*");
                FROM(entityConfig.getTableName());
                for (Map.Entry<String, Field> entry : entityConfig.getFieldMap().entrySet()) {
                    String key = entry.getKey();
                    Field field = entry.getValue();
                    Object value = ReflectUtils.createFieldBuilder(field).accessible().getValue(obj);
                    if (null != value) {
                        WHERE(key, VAR(PARAM(field.getName())));
                    }
                }
            }
        }.toString();
        return sql;
    }

    public String selectAll(Class<?> clazz) {
        CommonMapperContext commonMapperContext = CommonMapperContext.get();
        EntityConfig entityConfig = commonMapperContext.getEntityConfig();

        String sql = new CommonSQL() {
            {
                SELECT("*");
                FROM(entityConfig.getTableName());
            }
        }.toString();
        return sql;
    }

    public String selectListByPageAsc(Class<?> clazz, @Param(CommonMapper.PARAM) long lastId, int count) {
        String sql = selectListByPage(clazz, lastId, count, "asc");
        return sql;
    }

    public String selectListByPageDesc(Class<?> clazz, @Param(CommonMapper.PARAM) long lastId, int count) {
        String sql = selectListByPage(clazz, lastId, count, "desc");
        return sql;
    }

    public String selectListByPage(Class<?> clazz, long lastId, int count, String order) {
        CommonMapperContext commonMapperContext = CommonMapperContext.get();
        EntityConfig entityConfig = commonMapperContext.getEntityConfig();

        StringBuilder builder = new StringBuilder();
        builder.append("select * from ").append(entityConfig.getTableName());
        builder.append(" where ").append(entityConfig.getId()).append(">").append(CommonSQL.VAR(CommonMapper.PARAM));
        builder.append(" order by ").append(entityConfig.getId()).append(" ").append(order);
        builder.append(" limit ").append(count);
        String pageSql = builder.toString();
        return pageSql;
    }

    public String count(Class<?> clazz) {
        CommonMapperContext commonMapperContext = CommonMapperContext.get();
        EntityConfig entityConfig = commonMapperContext.getEntityConfig();

        String sql = new CommonSQL() {
            {
                SELECT("count(*)");
                FROM(entityConfig.getTableName());
            }
        }.toString();
        return sql;
    }

    public String countWhere(Class<?> clazz, @Param(CommonMapper.PARAM) Object obj) {
        CommonMapperContext commonMapperContext = CommonMapperContext.get();
        EntityConfig entityConfig = commonMapperContext.getEntityConfig();

        String sql = new CommonSQL() {
            {
                SELECT("count(*)");
                FROM(entityConfig.getTableName());
                for (Map.Entry<String, Field> entry : entityConfig.getFieldMap().entrySet()) {
                    String key = entry.getKey();
                    Field field = entry.getValue();
                    Object value = ReflectUtils.createFieldBuilder(field).accessible().getValue(obj);
                    if (null != value) {
                        WHERE(key, VAR(PARAM(field.getName())));
                    }
                }
            }
        }.toString();
        return sql;
    }

    private String updateById(Class<?> clazz, Object obj, boolean allowNull) {
        CommonMapperContext commonMapperContext = CommonMapperContext.get();
        EntityConfig entityConfig = commonMapperContext.getEntityConfig();

        String sql = new CommonSQL() {
            {
                UPDATE(entityConfig.getSubTableName());
                for (Map.Entry<String, Field> entry : entityConfig.getFieldMap().entrySet()) {
                    String key = entry.getKey();
                    Field field = entry.getValue();
                    Object value = ReflectUtils.createFieldBuilder(field).accessible().getValue(obj);
                    if (null != value || true == allowNull) {
                        SET(key, VAR(PARAM(field.getName())));
                    }
                }
                WHERE(entityConfig.getId(), VAR(PARAM(entityConfig.getPrimaryKeyField().getName())));
            }
        }.toString();
        return sql;
    }
}
