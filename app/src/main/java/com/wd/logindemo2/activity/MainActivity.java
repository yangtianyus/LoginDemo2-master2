package com.wd.logindemo2.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.wd.logindemo2.R;
import com.wd.logindemo2.adapter.MyAdapter;
import com.wd.logindemo2.bean.Goods;
import com.wd.logindemo2.bean.Result;
import com.wd.logindemo2.core.DataCall;
import com.wd.logindemo2.presenter.SearchListPresenter;

import java.util.List;

public class MainActivity extends AppCompatActivity implements DataCall<List<Goods>> ,XRecyclerView.LoadingListener {

    private SearchListPresenter searchListPresenter;

    private XRecyclerView recyclerView;
    private MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerview);
//        recyclerView.setPullRefreshEnabled(false);//默认true(可刷新)，false为禁止滑动刷新。
//        recyclerView.setLoadingMoreEnabled(false);//默认true(可加载更多)，false为禁止加载更多。
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(gridLayoutManager);

        recyclerView.setLoadingListener(this);

        myAdapter = new MyAdapter(this);
        recyclerView.setAdapter(myAdapter);
        searchListPresenter = new SearchListPresenter(this);
        recyclerView.refresh();
    }

    @Override
    public void success(List<Goods> goods) {
        recyclerView.loadMoreComplete();//加载更多完成
        recyclerView.refreshComplete();//刷新完成
        if (searchListPresenter.getPage()==1){//presenter的page是1肯定是刷新，需要清空数据
            myAdapter.clear();
        }
        myAdapter.addList(goods);
        myAdapter.notifyDataSetChanged();
    }

    @Override
    public void fail(Result data) {
        recyclerView.loadMoreComplete();//加载更多完成
        recyclerView.refreshComplete();//刷新完成
        Toast.makeText(getBaseContext(),data.getStatus()+"  "+data.getMessage(),Toast.LENGTH_LONG).show();
    }

    @Override
    public void onRefresh() {
        searchListPresenter.requestData(true,"鞋");
    }

    @Override
    public void onLoadMore() {
        searchListPresenter.requestData(false,"鞋");
    }
}
