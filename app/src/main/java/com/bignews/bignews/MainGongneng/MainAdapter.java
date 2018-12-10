package com.bignews.bignews.MainGongneng;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bignews.bignews.HandleMessage;
import com.bignews.bignews.PlayerActivity;
import com.bignews.bignews.PlayerWebview;
import com.bignews.bignews.R;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MyViewHolder>{

    List<MusicInfoBean> mlist=new ArrayList<>();
    Context mcontext;
    HandleMessage handleMessage;

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mcontext).inflate(R.layout.main_item,parent,false);
        handleMessage=new HandleMessage(mcontext,"");
       final  MyViewHolder holder=new MyViewHolder(view);
        holder.myView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int position=holder.getAdapterPosition();
                holder.linearLayout.setBackgroundResource(R.drawable.linear_selector);
                Intent intent=new Intent(mcontext,PlayerActivity.class);
                intent.putExtra("singer",mlist.get(position).getSinger());
                intent.putExtra("musicName",mlist.get(position).getMusicName());
                intent.putExtra("imageUrl",mlist.get(position).getImageURl());
                intent.putExtra("musicUrl",mlist.get(position).getMusicUrl());
                intent.putExtra("mpF",mlist.get(position).getMpF());
                mcontext.startActivity(intent);

            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        holder.singer.setText(mlist.get(position).getSinger());
        holder.musicName.setText(mlist.get(position).getMusicName());
        holder.image.setImageResource(R.mipmap.ic_launcher);
        Glide.with(mcontext).load(mlist.get(position).getImageURl()).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView singer,musicName;
        public ImageView image;
        public View myView;
        public LinearLayout linearLayout;

        public MyViewHolder(View itemView) {
            super(itemView);
            myView=itemView;
            singer=itemView.findViewById(R.id.main_item_singer);
            musicName=itemView.findViewById(R.id.main_item_musicName);
            image=itemView.findViewById(R.id.main_item_image);
            linearLayout=itemView.findViewById(R.id.main_item_layout);

        }
    }

    //adapter的构造器
    public MainAdapter(List<MusicInfoBean> list,Context context){
        mlist=list;
        mcontext=context;
    }
}