package com.bignews.bignews;

import android.content.Context;
import android.nfc.TagLostException;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class TitleAdapter extends RecyclerView.Adapter<TitleAdapter.ViewHolder> {

    private Context mcontext;
    private List<TitleClass> mlist;

    static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;

        TextView item_titie;
        public ViewHolder(View itemView) {
            super(itemView);
            cardView= (CardView) itemView;

            item_titie=itemView.findViewById(R.id.item_titie);

        }
    }

    public TitleAdapter(List<TitleClass> list){
        mlist=list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mcontext == null){
            mcontext=parent.getContext();
        }
        View view = LayoutInflater.from(mcontext).inflate(R.layout.titie_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
            TitleClass titleClass=mlist.get(position);
        holder.item_titie.setText(titleClass.getTitle());


    }


    @Override
    public int getItemCount() {
        return mlist.size();
    }
}