package com.tiho.mybaits.mapper.definition;

import com.tiho.mybaits.mapper.config.EntityConfig;
import com.tiho.mybaits.mapper.util.CommonMapperContext;
import com.tiho.mybaits.mapper.util.ReflectUtils;
import org.apache.ibatis.annotations.Param;

import java.lang.reflect.Field;
import java.util.Map;

public class CommonMapperProvider {

    public String insert(Class<?> clazz, @Param(CommonMapper.PARAM) Object obj) {
        CommonMapperContext commonMapperContext = CommonMapperContext.get();
        EntityConfig entityConfig = commonMapperContext.getEntityConfig();

        String tableName = entityConfig.getTableName();
        Map<String, Field> fieldMap = entityConfig.getFieldMap();
        StringBuilder builder = new StringBuilder();
        builder.append("insert into ").append(tableName);
        builder.append(" ");
        builder.append("(");
        if (!fieldMap.isEmpty()) {
            for (Map.Entry<String, Field> entry : fieldMap.entrySet()) {
                builder.append(entry.getKey()).append(",");
            }
            builder.deleteCharAt(builder.length() - 1);
        }
        builder.append(")");
        builder.append(" values ");
        builder.append("(");
        if (!fieldMap.isEmpty()) {
            for (Map.Entry<String, Field> entry : fieldMap.entrySet()) {
                builder.append("#{").append(CommonMapper.PARAM).append(".")
                        .append(entry.getValue().getName()).append("}").append(",");
            }
            builder.deleteCharAt(builder.length() - 1);
        }
        builder.append(")");
        String sql = builder.toString();
        return sql;
    }

    public String updateAllById(Class<?> clazz, @Param(CommonMapper.PARAM) Object obj) {
        String sql = updateSql(clazz, obj, true);
        return sql;
    }

    public String updateNotNullById(Class<?> clazz, @Param(CommonMapper.PARAM) Object obj) {
        String sql = updateSql(clazz, obj, false);
        return sql;
    }

    public String deleteById(Class<?> clazz, @Param(CommonMapper.PARAM) Long id) {
        CommonMapperContext commonMapperContext = CommonMapperContext.get();
        EntityConfig entityConfig = commonMapperContext.getEntityConfig();

        StringBuilder builder = new StringBuilder();
        builder.append("delete from ").append(entityConfig.getTableName())
                .append(" where ").append(entityConfig.getId()).append("=")
                .append("#{").append(CommonMapper.PARAM).append("}");
        String sql = builder.toString();
        return sql;
    }

    public String delete(Class<?> clazz, @Param(CommonMapper.PARAM) Object obj) {
        CommonMapperContext commonMapperContext = CommonMapperContext.get();
        EntityConfig entityConfig = commonMapperContext.getEntityConfig();

        StringBuilder builder = new StringBuilder();
        builder.append("delete from ").append(entityConfig.getTableName()).append(" where ");
        foreachMap(builder, entityConfig, obj, " and ", false);
        String sql = builder.toString();
        return sql;
    }

    public String selectOneById(Class<?> clazz, @Param(CommonMapper.PARAM) Long id) {
        CommonMapperContext commonMapperContext = CommonMapperContext.get();
        EntityConfig entityConfig = commonMapperContext.getEntityConfig();

        StringBuilder builder = new StringBuilder();
        builder.append("select * from ").append(entityConfig.getTableName()).append(" where ")
                .append(entityConfig.getId()).append("=").append("#{").append(CommonMapper.PARAM).append("}");
        String sql = builder.toString();
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

        StringBuilder builder = new StringBuilder();
        builder.append("select * from ").append(entityConfig.getTableName()).append(" where ");
        foreachMap(builder, entityConfig, obj, " and ", false);
        String sql = builder.toString();
        return sql;
    }

    public String selectAll(Class<?> clazz) {
        CommonMapperContext commonMapperContext = CommonMapperContext.get();
        EntityConfig entityConfig = commonMapperContext.getEntityConfig();
        StringBuilder builder = new StringBuilder().append("select * from ").append(entityConfig.getTableName());
        String sql = builder.toString();
        return sql;
    }

    public String count(Class<?> clazz) {
        CommonMapperContext commonMapperContext = CommonMapperContext.get();
        EntityConfig entityConfig = commonMapperContext.getEntityConfig();
        StringBuilder builder = new StringBuilder().append("select count(*) from ").append(entityConfig.getTableName());
        String sql = builder.toString();
        return sql;
    }

    public String countWhere(Class<?> clazz, @Param(CommonMapper.PARAM) Object obj) {
        CommonMapperContext commonMapperContext = CommonMapperContext.get();
        EntityConfig entityConfig = commonMapperContext.getEntityConfig();
        StringBuilder builder = new StringBuilder().append("select count(*) from ").append(entityConfig.getTableName());
        foreachMap(builder, entityConfig, obj, " and ", false);
        String sql = builder.toString();
        return sql;
    }

    private String updateSql(Class<?> clazz, Object obj, boolean allowNull) {
        CommonMapperContext commonMapperContext = CommonMapperContext.get();
        EntityConfig entityConfig = commonMapperContext.getEntityConfig();

        StringBuilder builder = new StringBuilder();
        builder.append("update ").append(entityConfig.getTableName()).append(" set ");
        foreachMap(builder, entityConfig, obj, ",", allowNull);
        builder.append(" where ")
                .append(entityConfig.getId())
                .append("=")
                .append("#{").append(CommonMapper.PARAM).append(".")
                .append(entityConfig.getPrimaryKeyField().getName()).append("}");
        String sql = builder.toString();
        return sql;
    }

    private void foreachMap(StringBuilder builder, EntityConfig entityConfig, Object obj, String separator, boolean allowNull) {
        Map<String, Field> fieldMap = entityConfig.getFieldMap();
        if (!fieldMap.isEmpty()) {
            for (Map.Entry<String, Field> entry : fieldMap.entrySet()) {
                String key = entry.getKey();
                Field field = entry.getValue();
                Object value = ReflectUtils.createFieldBuilder(field).accessible().getValue(obj);
                if (null != value || true == allowNull) {
                    builder.append(key).append("=").append("#{").append(CommonMapper.PARAM).append(".")
                            .append(entry.getValue().getName()).append("}").append(separator);
                }
            }
            builder.delete(builder.length() - separator.length(), builder.length());
        }
    }
}
