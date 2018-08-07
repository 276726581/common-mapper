package com.jaspercloud.mybaits.mapper.config;

import com.jaspercloud.mybaits.mapper.support.subtable.SubTableProcessor;
import org.apache.ibatis.type.TypeHandler;

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
    private Map<String, Field> fieldNameMap = new HashMap<>();
    private Map<Field, String> columnMap = new HashMap<>();
    private Map<Field, String> sequenceMap = new HashMap<>();
    private Map<Field, Class<? extends TypeHandler<?>>> fieldTypeHandlerMap = new HashMap<>();
    private String subAttribute;
    private SubTableProcessor subTableProcessor;

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

    public Map<String, Field> getFieldNameMap() {
        return fieldNameMap;
    }

    public void setFieldNameMap(Map<String, Field> fieldNameMap) {
        this.fieldNameMap = fieldNameMap;
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

    public Map<Field, Class<? extends TypeHandler<?>>> getFieldTypeHandlerMap() {
        return fieldTypeHandlerMap;
    }

    public void setFieldTypeHandlerMap(Map<Field, Class<? extends TypeHandler<?>>> fieldTypeHandlerMap) {
        this.fieldTypeHandlerMap = fieldTypeHandlerMap;
    }

    public SubTableProcessor getSubTableProcessor() {
        return subTableProcessor;
    }

    public void setSubTableProcessor(SubTableProcessor subTableProcessor) {
        this.subTableProcessor = subTableProcessor;
    }

    public String getSubAttribute() {
        return subAttribute;
    }

    public void setSubAttribute(String subAttribute) {
        this.subAttribute = subAttribute;
    }

    public void addColumnField(Field field, String column) {
        fieldMap.put(column, field);
        fieldNameMap.put(field.getName(), field);
        columnMap.put(field, column);
    }

    public void addSequenceField(Field field, String generatedValue) {
        sequenceMap.put(field, generatedValue);
    }

    public void addFieldTypeHandler(Field field, Class<? extends TypeHandler<?>> clazz) {
        fieldTypeHandlerMap.put(field, clazz);
    }

    public String getSubTableName() {
        if (null == subTableProcessor) {
            return tableName;
        }
        String subTableName = subTableProcessor.getTableName(this.tableName, subAttribute);
        return subTableName;
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
