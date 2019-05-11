package com.wd.logindemo2.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wd.logindemo2.R;
import com.wd.logindemo2.bean.Goods;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dingtao
 * @date 2019/4/26 16:25
 * qq:1940870847
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyHolder> {

    List<Goods> mList;
    Context context;

    public MyAdapter(Context context) {
        mList = new ArrayList();
        this.context = context;
    }


    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(viewGroup.getContext(), R.layout.item, null);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, int i) {
        Goods goods = mList.get(i);
        Glide.with(context).load(goods.getMasterPic()).into(myHolder.imagev);
        myHolder.name.setText(goods.getCommodityName());
        myHolder.num.setText(goods.getSaleNum() + "");
        myHolder.price.setText(goods.getPrice() + "");
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void addList(List<Goods> goods) {
        if (goods != null)
            mList.addAll(goods);
    }

    public void addItem(Goods goods) {
        if (goods!=null)
            mList.add(goods);
    }

    public void clear() {
        mList.clear();
    }

    class MyHolder extends RecyclerView.ViewHolder {

        ImageView imagev;
        TextView name;
        TextView num;
        TextView price;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            imagev = itemView.findViewById(R.id.imagev);
            name = itemView.findViewById(R.id.t_name);
            num = itemView.findViewById(R.id.t_num);
            price = itemView.findViewById(R.id.t_price);
        }
    }
}
