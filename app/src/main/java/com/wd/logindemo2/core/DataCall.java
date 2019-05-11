package com.wd.logindemo2.core;

import com.wd.logindemo2.bean.Result;


public interface DataCall<T> {
    void success(T result);
    void fail(Result result);
}
