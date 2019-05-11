package com.wd.logindemo2.presenter;

import com.wd.logindemo2.bean.Result;
import com.wd.logindemo2.core.DataCall;
import com.wd.logindemo2.model.DemoModel;


public class GoodsPresenter extends BasePresenter{

    public GoodsPresenter(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    Result getModel(Object... args) throws Exception {
        return DemoModel.getGoodsList((String)args[0]);
    }

}


