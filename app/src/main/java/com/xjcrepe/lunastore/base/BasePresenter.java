package com.xjcrepe.lunastore.base;

/**
 * Created by LiXijun on 2017/8/20.
 */

public interface BasePresenter<T> {

    void bindView(T view);

    void unbindView();
}
