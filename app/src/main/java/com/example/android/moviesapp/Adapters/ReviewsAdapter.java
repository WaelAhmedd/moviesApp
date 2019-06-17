package com.example.android.moviesapp.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.TextView;

import com.example.android.moviesapp.R;
import com.example.android.moviesapp.review.Result;

import java.util.List;

public class ReviewsAdapter extends RecyclerView.Adapter<ReviewsAdapter.ReviewsHolder> {

    Context context;
    List<Result> results;

    public ReviewsAdapter(Context context) {
        this.context = context;

    }

    public void setResults(List<Result> results) {
        this.results = results;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ReviewsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.review_row_item,parent,false);
        return new ReviewsHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewsHolder holder, int position) {
        Result result=results.get(position);
        holder.reviewText.setText(result.getContent());
        holder.author.setText(result.getAuthor());
    }

    @Override
    public int getItemCount() {
        return (results==null)?0:results.size();
    }

    class ReviewsHolder extends RecyclerView.ViewHolder {
      TextView reviewText,author;
        public ReviewsHolder(@NonNull View itemView) {
            super(itemView);
            reviewText=itemView.findViewById(R.id.review_tv);
            author=(TextView)itemView.findViewById(R.id.review_author);
        }
    }
}
