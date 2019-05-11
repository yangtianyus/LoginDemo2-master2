package com.wd.logindemo2.presenter;

import com.wd.logindemo2.bean.Result;
import com.wd.logindemo2.core.DataCall;
import com.wd.logindemo2.model.DemoModel;

/**
 * @author dingtao
 * @date 2019/4/26 16:50
 * qq:1940870847
 */
public class SearchListPresenter extends BasePresenter {

    private int page=1;

    public SearchListPresenter(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    Result getModel(Object... args) {
        boolean isRefresh = (boolean) args[0];
        if (isRefresh){//如果是刷新
            page = 1;
        }else{//加载更多
            page++;
        }
        return DemoModel.getSearchList((String)args[1],String.valueOf(page),"10");
    }

    public int getPage() {
        return page;
    }
}
