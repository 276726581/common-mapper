package com.tiho.mybaits.mapper.annotation;


import com.tiho.mybaits.mapper.support.subtable.IdSubTableProcessor;
import com.tiho.mybaits.mapper.support.subtable.SubTableProcessor;
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
