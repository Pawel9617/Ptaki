package com.example.ptaki;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class Log_adapter extends RecyclerView.Adapter<Log_adapter.LogViewHolder> {
    private ArrayList<Log_item> mlogList;
    private OnItemClickListener mListener;

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }

    public static class LogViewHolder extends RecyclerView.ViewHolder{
        public TextView mTextView1;
        public TextView mTextView2;
        public TextView mTextView3;

        public LogViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            mTextView1 = itemView.findViewById(R.id.txtViewGatunek);
            mTextView2 = itemView.findViewById(R.id.txtViewData);
            mTextView3 = itemView.findViewById(R.id.txtViewLokalizacja);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(listener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }

    public Log_adapter(ArrayList<Log_item> logList){
        mlogList = logList;
    }

    @NonNull
    @Override
    public LogViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.log_item, null);
        LogViewHolder logViewHolder = new LogViewHolder(v, mListener);
        return logViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull LogViewHolder logViewHolder, int i) { //i = position
        Log_item currentItem = mlogList.get(i);

        logViewHolder.mTextView1.setText(currentItem.getmGatunek());
        logViewHolder.mTextView2.setText(currentItem.getmData());
        logViewHolder.mTextView3.setText(currentItem.getmLokalizacja());
    }

    @Override
    public int getItemCount() {
        return mlogList.size();
    }
}
