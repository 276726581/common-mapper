package com.jaspercloud.mybaits.mapper.annotation;


import com.jaspercloud.mybaits.mapper.support.subtable.IdSubTableProcessor;
import com.jaspercloud.mybaits.mapper.support.subtable.SubTableProcessor;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface SubTable {

    Class<? extends SubTableProcessor> value() default IdSubTableProcessor.class;

    String attribute() default "id";
}
