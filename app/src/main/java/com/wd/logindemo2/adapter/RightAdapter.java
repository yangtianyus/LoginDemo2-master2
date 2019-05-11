package com.wd.logindemo2.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
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
public class RightAdapter extends RecyclerView.Adapter<RightAdapter.MyHolder> {

    List<Goods> mList;
    Context context;

    public RightAdapter(Context context) {
        mList = new ArrayList();
        this.context = context;
    }


    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.left_item,viewGroup,false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, int i) {
        Goods goods = mList.get(i);
        myHolder.name.setText(goods.getName());
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

        TextView name;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.text);
        }
    }
}
