package com.tiho.mybaits.mapper.definition;

import org.apache.ibatis.annotations.*;

import java.util.List;

public interface CommonMapper {

    String PARAM = "param";

    @InsertProvider(type = CommonMapperProvider.class, method = "insert")
    void insert(Class<?> clazz, @Param(PARAM) Object obj);

    @UpdateProvider(type = CommonMapperProvider.class, method = "updateAllById")
    void updateAllById(Class<?> clazz, @Param(PARAM) Object obj);

    @UpdateProvider(type = CommonMapperProvider.class, method = "updateNotNullById")
    void updateNotNullById(Class<?> clazz, @Param(PARAM) Object obj);

    @DeleteProvider(type = CommonMapperProvider.class, method = "deleteById")
    void deleteById(Class<?> clazz, @Param(PARAM) Long id);

    @DeleteProvider(type = CommonMapperProvider.class, method = "delete")
    void delete(Class<?> clazz, @Param(PARAM) Object obj);

    @SelectProvider(type = CommonMapperProvider.class, method = "selectOneById")
    <T> T selectOneById(Class<T> clazz, @Param(PARAM) Long id);

    @SelectProvider(type = CommonMapperProvider.class, method = "selectOne")
    <T> T selectOne(Class<T> clazz, @Param(PARAM) Object obj);

    @SelectProvider(type = CommonMapperProvider.class, method = "select")
    <T> List<T> select(Class<T> clazz, @Param(PARAM) Object obj);

    @SelectProvider(type = CommonMapperProvider.class, method = "selectAll")
    <T> List<T> selectAll(Class<T> clazz);

    @SelectProvider(type = CommonMapperProvider.class, method = "count")
    long count(Class<?> clazz);
}
