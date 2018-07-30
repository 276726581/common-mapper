package com.tiho.mybaits.mapper.util;

import com.tiho.mybaits.mapper.config.EntityConfig;

public final class CommonMapperContext {

    private static ThreadLocal<CommonMapperContext> threadLocal = new InheritableThreadLocal<CommonMapperContext>() {
        @Override
        protected CommonMapperContext initialValue() {
            return new CommonMapperContext();
        }
    };

    private CommonMapperContext() {

    }

    public static CommonMapperContext get() {
        CommonMapperContext commonMapperContext = threadLocal.get();
        return commonMapperContext;
    }

    public static void remove() {
        threadLocal.remove();
    }

    public EntityConfig getEntityConfig() {
        return entityConfig;
    }

    private EntityConfig entityConfig;

    public void setEntityConfig(EntityConfig entityConfig) {
        this.entityConfig = entityConfig;
    }
}
