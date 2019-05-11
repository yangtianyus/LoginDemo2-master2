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
import com.wd.logindemo2.bean.Shop;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dingtao
 * @date 2019/4/26 16:25
 * qq:1940870847
 */
public class LeftAdapter extends RecyclerView.Adapter<LeftAdapter.MyHolder> {

    List<Shop> mList;
    Context context;

    public LeftAdapter(Context context) {
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
        Shop shop = mList.get(i);
        myHolder.name.setText(shop.getName());
        myHolder.itemView.setBackgroundColor(shop.getBackground());
        myHolder.name.setTextColor(shop.getTextColor());
        myHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int j = 0; j < mList.size(); j++) {
                    mList.get(j).setBackground(0xFFFFFFFF);
                    mList.get(j).setTextColor(0xff000000);
                }
                shop.setBackground(0xFF666AAA);
                shop.setTextColor(0xffffffff);
                if (onLeftItemClickListener!=null)
                    onLeftItemClickListener.onLeftItemClick(shop);
                notifyDataSetChanged();

            }
        });
    }

    private OnLeftItemClickListener onLeftItemClickListener;

    public void setOnLeftItemClickListener(OnLeftItemClickListener onLeftItemClickListener) {
        this.onLeftItemClickListener = onLeftItemClickListener;
    }

    public interface OnLeftItemClickListener{
        void onLeftItemClick(Shop shop);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void addList(List<Shop> goods) {
        if (goods != null)
            mList.addAll(goods);
    }

    public void addItem(Shop goods) {
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
