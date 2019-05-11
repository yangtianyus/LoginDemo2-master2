package com.wd.logindemo2.presenter;

import com.wd.logindemo2.bean.Result;
import com.wd.logindemo2.core.DataCall;
import com.wd.logindemo2.model.DemoModel;

/**
 * @author dingtao
 * @date 2019/4/25 11:42
 * qq:1940870847
 */
public class ShopPresenter extends BasePresenter{

    public ShopPresenter(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    Result getModel(Object... args) throws Exception {
        return DemoModel.getShopList();
    }
}
