package com.example.appsmartlearn;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class WordAdapter extends RecyclerView.Adapter<WordAdapter.myWordHolder> {

    private Context context;
    ArrayList<Word> list;

    public WordAdapter(ArrayList<Word> list) {
        this.list = list;
    }
    @NonNull
    @Override
    public myWordHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.word_card_holder, viewGroup, false);
        return new myWordHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myWordHolder myWordHolder, int i) {
        myWordHolder.engWord.setText(list.get(i).getEngWord());
        String word = list.get(i).getEngWord();
        String mean = list.get(i).getMeaning();
        String mean2 = list.get(i).getMeaning2();
        String type = list.get(i).getType();
        String read = list.get(i).getRead();
        String ex = list.get(i).getEx();
        String ex2 = list.get(i).getEx2();
        myWordHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent intent = new Intent(context, DefinitionWord.class);
                intent.putExtra("engWord", word);
                intent.putExtra("meaning", mean);
                intent.putExtra("meaning2", mean2);
                intent.putExtra("type", type);
                intent.putExtra("read", read);
                intent.putExtra("ex", ex);
                intent.putExtra("ex2", ex2);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class myWordHolder extends RecyclerView.ViewHolder {
        TextView engWord;
        public myWordHolder(@NonNull View itemView) {
            super(itemView);
            engWord = itemView.findViewById(R.id.tvEngWord);
            context = itemView.getContext();
        }
    }
}
