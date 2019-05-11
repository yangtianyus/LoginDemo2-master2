package com.wd.logindemo2.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.wd.logindemo2.R;
import com.wd.logindemo2.adapter.LeftAdapter;
import com.wd.logindemo2.adapter.MyAdapter;
import com.wd.logindemo2.adapter.RightAdapter;
import com.wd.logindemo2.bean.Goods;
import com.wd.logindemo2.bean.Result;
import com.wd.logindemo2.bean.Shop;
import com.wd.logindemo2.core.DataCall;
import com.wd.logindemo2.presenter.GoodsPresenter;
import com.wd.logindemo2.presenter.SearchListPresenter;
import com.wd.logindemo2.presenter.ShopPresenter;

import java.util.List;

public class LeftRightActivity extends AppCompatActivity {

    private ShopPresenter shopPresenter;
    private GoodsPresenter goodsPresenter;

    private RecyclerView rightView,leftView;
    private LeftAdapter leftAdapter;
    private RightAdapter rightAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_left_right);

        leftView = findViewById(R.id.left_recycler);
        rightView = findViewById(R.id.right_recycler);
        leftView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        rightView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));

        leftAdapter = new LeftAdapter(this);
        leftAdapter.setOnLeftItemClickListener(new LeftAdapter.OnLeftItemClickListener() {
            @Override
            public void onLeftItemClick(Shop shop) {
                goodsPresenter.requestData(shop.getId());
            }
        });
        leftView.setAdapter(leftAdapter);

        rightAdapter = new RightAdapter(this);
        rightView.setAdapter(rightAdapter);

        shopPresenter = new ShopPresenter(new ShopCall());
        goodsPresenter = new GoodsPresenter(new GoodCall());


        shopPresenter.requestData();
    }

    class ShopCall implements DataCall<List<Shop>>{

        @Override
        public void success(List<Shop> result) {
            Shop shop = result.get(0);
            shop.setBackground(0xFF666AAA);
            shop.setTextColor(0xffffffff);
            leftAdapter.addList(result);
            goodsPresenter.requestData(shop.getId());
            leftAdapter.notifyDataSetChanged();
        }

        @Override
        public void fail(Result result) {

        }
    }

    class GoodCall implements DataCall<List<Goods>>{

        @Override
        public void success(List<Goods> result) {
            rightAdapter.clear();
            rightAdapter.addList(result);
            rightAdapter.notifyDataSetChanged();
        }

        @Override
        public void fail(Result result) {

        }
    }


}
