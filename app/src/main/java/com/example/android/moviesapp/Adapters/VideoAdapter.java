package com.example.android.moviesapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.moviesapp.Model.MovieR;
import com.example.android.moviesapp.R;
import com.example.android.moviesapp.Video.Results;

import java.util.List;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.MyHolder> {
    Context context;
    List<Results> results;



    public VideoAdapter(Context context) {
        this.context = context;


    }
    public void setResults(List<Results>results)
    {
        this.results=results;
        notifyDataSetChanged();
    }

    public List<Results> getResults() {
        return results;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.video_row_item,parent,false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        final Results result=results.get(position);
        holder.trailerNum.setText("trailer "+(position+1));

        holder.trailerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/watch?v=".concat(result.getKey())));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return (results==null)? 0:results.size();
    }

    class MyHolder extends RecyclerView.ViewHolder{
    ImageView trailerBtn;
    TextView trailerNum;
    public MyHolder(@NonNull View itemView) {
        super(itemView);
     trailerBtn=itemView.findViewById(R.id.play_trailer);
     trailerNum=itemView.findViewById(R.id.trailer_number);


    }


    }
}
