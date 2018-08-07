package com.jaspercloud.mybaits.mapper.annotation;

import org.apache.ibatis.type.TypeHandler;
import org.apache.ibatis.type.UnknownTypeHandler;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface FieldTypeDiscriminator {

    Class<?> javaType() default void.class;

    Class<? extends TypeHandler<?>> typeHandler() default UnknownTypeHandler.class;
}
