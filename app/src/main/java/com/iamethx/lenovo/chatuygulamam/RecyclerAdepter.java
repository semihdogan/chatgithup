package com.iamethx.lenovo.chatuygulamam;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class RecyclerAdepter extends RecyclerView.Adapter<RecyclerAdepter.MyviewHolder> {
    private List<String>chatsMessages;

    @NonNull
    @Override
    public MyviewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemview=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.resycler_list_row,viewGroup,false);
        return new MyviewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(@NonNull MyviewHolder myviewHolder, int position) {
        String chatmessage=chatsMessages.get(position);
        myviewHolder.chatmessager.setText(chatmessage);

    }

    @Override
    public int getItemCount() {
        return chatsMessages.size();
    }

    public RecyclerAdepter(List<String> chatsMessage) {
        this.chatsMessages = chatsMessage;
    }

    public class MyviewHolder extends RecyclerView.ViewHolder{
        public TextView chatmessager;

        public MyviewHolder(@NonNull View itemView) {

            super(itemView);
            chatmessager=itemView.findViewById(R.id.recyclerView_textview);
        }
    }

}
