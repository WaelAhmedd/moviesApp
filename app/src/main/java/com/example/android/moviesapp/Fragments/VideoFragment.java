package com.example.android.moviesapp.Fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.moviesapp.Adapters.VideoAdapter;
import com.example.android.moviesapp.Model.MovieR;
import com.example.android.moviesapp.Network.WebServices;
import com.example.android.moviesapp.R;
import com.example.android.moviesapp.Video.Results;
import com.example.android.moviesapp.Video.VideoResponse;
import com.example.android.moviesapp.review.Result;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class VideoFragment extends Fragment {
    private WebServices webServices;

    private RecyclerView TrailerList;
    private VideoAdapter videoAdapter;
    private Context context;
    private MovieR movie;

    public VideoFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_video, container, false);

        if(getArguments()!=null)
        {
            movie=getArguments().getParcelable("MOVIE");
        }
        context=getContext();

        webServices=WebServices.getMoviesResponse.create(WebServices.class);
        videoAdapter=new VideoAdapter(context);
        TrailerList=view.findViewById(R.id.rv_video);
        LinearLayoutManager videoLinearLayoutManager = new LinearLayoutManager(context);
        videoLinearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

        TrailerList.setLayoutManager(videoLinearLayoutManager);
        TrailerList.setAdapter(videoAdapter);
        showTrailer(movie.getId());
        return view;


    }

    private void showTrailer(final String Id)
    {
        webServices.getMovieTrailer(Id,WebServices.KEY).enqueue(new Callback<VideoResponse>() {
            @Override
            public void onResponse(Call<VideoResponse> call, Response<VideoResponse> response) {
                if (response.body() != null) {
                    {


                        videoAdapter.setResults(response.body().getResults());

                        TrailerList.setAdapter(videoAdapter);


                    }
                }
            }

            @Override
            public void onFailure(Call<VideoResponse> call, Throwable t) {

            }
        });


    }

}
