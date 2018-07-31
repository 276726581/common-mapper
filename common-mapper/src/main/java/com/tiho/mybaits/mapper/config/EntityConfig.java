package com.tiho.mybaits.mapper.config;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class EntityConfig {

    private String dataSourceName;
    private boolean cache;
    private Class<?> entity;
    private String tableName;
    private String id;
    private Map<String, Field> fieldMap = new HashMap<>();
    private Map<Field, String> columnMap = new HashMap<>();
    private Map<Field, String> sequenceMap = new HashMap<>();

    public String getDataSourceName() {
        return dataSourceName;
    }

    public void setDataSourceName(String dataSourceName) {
        this.dataSourceName = dataSourceName;
    }

    public boolean isCache() {
        return cache;
    }

    public void setCache(boolean cache) {
        this.cache = cache;
    }

    public Class<?> getEntity() {
        return entity;
    }

    public void setEntity(Class<?> entity) {
        this.entity = entity;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Map<String, Field> getFieldMap() {
        return fieldMap;
    }

    public void setFieldMap(Map<String, Field> fieldMap) {
        this.fieldMap = fieldMap;
    }

    public Map<Field, String> getColumnMap() {
        return columnMap;
    }

    public void setColumnMap(Map<Field, String> columnMap) {
        this.columnMap = columnMap;
    }

    public Map<Field, String> getSequenceMap() {
        return sequenceMap;
    }

    public void setSequenceMap(Map<Field, String> sequenceMap) {
        this.sequenceMap = sequenceMap;
    }


    public void addColumnField(Field field, String column) {
        fieldMap.put(column, field);
        columnMap.put(field, column);
    }

    public void addSequenceField(Field field, String generatedValue) {
        sequenceMap.put(field, generatedValue);
    }

    public Field getPrimaryKeyField() {
        Field field = fieldMap.get(id);
        if (null == field) {
            throw new NullPointerException("not found primaryKeyField");
        }
        return field;
    }

    public EntityConfig() {
    }
}
