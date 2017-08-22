package com.xjcrepe.lunastore.di;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by LiXijun on 2017/8/20.
 */

@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface ActivityScoped {
}
