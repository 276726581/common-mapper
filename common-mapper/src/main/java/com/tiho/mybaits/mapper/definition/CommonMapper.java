package com.tiho.mybaits.mapper.definition;

import org.apache.ibatis.annotations.*;

import java.util.List;

public interface CommonMapper {

    String PARAM = "param";

    /**
     * 保存
     *
     * @param clazz
     * @param obj
     */
    @InsertProvider(type = CommonMapperProvider.class, method = "insert")
    void insert(Class<?> clazz, @Param(PARAM) Object obj);

    /**
     * 根据主键更新所有的属性(包括空值)
     *
     * @param clazz
     * @param obj
     */
    @UpdateProvider(type = CommonMapperProvider.class, method = "updateAllById")
    void updateAllById(Class<?> clazz, @Param(PARAM) Object obj);

    /**
     * 根据主键更新不为空的属性
     *
     * @param clazz
     * @param obj
     */
    @UpdateProvider(type = CommonMapperProvider.class, method = "updateNotNullById")
    void updateNotNullById(Class<?> clazz, @Param(PARAM) Object obj);

    /**
     * 根据主键删除
     *
     * @param clazz
     * @param id
     */
    @DeleteProvider(type = CommonMapperProvider.class, method = "deleteById")
    void deleteById(Class<?> clazz, @Param(PARAM) Long id);

    /**
     * 根据不为空的属性删除
     *
     * @param clazz
     * @param obj
     */
    @DeleteProvider(type = CommonMapperProvider.class, method = "delete")
    void delete(Class<?> clazz, @Param(PARAM) Object obj);

    /**
     * 根据主键查询
     *
     * @param clazz
     * @param id
     * @param <T>
     * @return
     */
    @SelectProvider(type = CommonMapperProvider.class, method = "selectOneById")
    <T> T selectOneById(Class<T> clazz, @Param(PARAM) Long id);

    /**
     * 根据不为空的属性查询一个
     *
     * @param clazz
     * @param obj
     * @param <T>
     * @return
     */
    @SelectProvider(type = CommonMapperProvider.class, method = "selectOne")
    <T> T selectOne(Class<T> clazz, @Param(PARAM) Object obj);

    /**
     * 根据不为空的属性查询集合
     *
     * @param clazz
     * @param obj
     * @param <T>
     * @return
     */
    @SelectProvider(type = CommonMapperProvider.class, method = "select")
    <T> List<T> select(Class<T> clazz, @Param(PARAM) Object obj);

    /**
     * 查询所有
     *
     * @param clazz
     * @param <T>
     * @return
     */
    @SelectProvider(type = CommonMapperProvider.class, method = "selectAll")
    <T> List<T> selectAll(Class<T> clazz);

    @SelectProvider(type = CommonMapperProvider.class, method = "selectListByPageAsc")
    <T> List<T> selectListByPageAsc(Class<T> clazz, @Param(PARAM) long lastId, int count);

    @SelectProvider(type = CommonMapperProvider.class, method = "selectListByPageDesc")
    <T> List<T> selectListByPageDesc(Class<T> clazz, @Param(PARAM) long lastId, int count);

    /**
     * 获取记录数
     *
     * @param clazz
     * @return
     */
    @SelectProvider(type = CommonMapperProvider.class, method = "count")
    long count(Class<?> clazz);

    /**
     * 根据不为空的属性获取记录数
     *
     * @param clazz
     * @param obj
     * @return
     */
    @SelectProvider(type = CommonMapperProvider.class, method = "countWhere")
    long countWhere(Class<?> clazz, @Param(PARAM) Object obj);
}
