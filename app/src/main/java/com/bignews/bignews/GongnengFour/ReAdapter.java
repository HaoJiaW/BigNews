package com.bignews.bignews.GongnengFour;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bignews.bignews.GongnengOne.ContentClass;
import com.bignews.bignews.R;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**
 * 适配recyclerview的adapter
 */
public class ReAdapter extends RecyclerView.Adapter<ReAdapter.MyViewHolder> {

    Context mcontext;
    private List<NewsBean> mlist;
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //获取列表中,每行的布局文件
        View view= LayoutInflater.from(mcontext).inflate(R.layout.newstitle_recyclerview_item,parent,false);
        final MyViewHolder holder=new MyViewHolder(view);
        holder.myView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position=holder.getAdapterPosition();
                holder.linearLayout.setBackgroundResource(R.drawable.linear_selector);
                Intent intent=new Intent(mcontext, ContentClass.class);
                intent.putExtra("url",mlist.get(position).getUrl());
                mcontext.startActivity(intent);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        //显示子项中的内容
        holder.title.setText(mlist.get(position).getTitle());
        holder.image.setImageResource(R.mipmap.ic_launcher);
        Glide.with(mcontext).load(mlist.get(position).getImageUrl()).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        View myView;
        private TextView title;
        private ImageView image;
        private LinearLayout linearLayout;

        public MyViewHolder(View itemView) {
            super(itemView);
            myView=itemView;
            linearLayout=itemView.findViewById(R.id.news_re_linear);
            title=itemView.findViewById(R.id.news_re_title);
            image=itemView.findViewById(R.id.news_re_image);
        }
    }

    public ReAdapter(List<NewsBean> list,Context context){
        mcontext=context;
        mlist=list;
    }
}