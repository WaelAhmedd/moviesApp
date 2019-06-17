package com.example.android.moviesapp.Fragments;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.moviesapp.Adapters.MainAdapter;
import com.example.android.moviesapp.Activities.DetailsActivity;
import com.example.android.moviesapp.Model.MovieR;
import com.example.android.moviesapp.Network.WebMovieResponse;
import com.example.android.moviesapp.Network.WebServices;
import com.example.android.moviesapp.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class PopularMovies extends Fragment implements MainAdapter.ListItemClickListener {


    private ArrayList<MovieR> moviesArrayList;

    private MainAdapter mAdapter;
    private RecyclerView mainList;

    private static final String MOVIE_STATE = "movie_list_key";

    public Context context;

    public PopularMovies() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        context=getContext();


        View view= inflater.inflate(R.layout.fragment_popular_movies, container, false);
        mAdapter=new MainAdapter(context,this);
        mainList=view.findViewById(R.id.rv_popular_list);
        GridLayoutManager gridLayoutManager=new GridLayoutManager(context,2);
        mainList.setLayoutManager(gridLayoutManager);
        mainList.setAdapter(mAdapter);

        if(savedInstanceState!=null)
        {
            if (savedInstanceState.containsKey(MOVIE_STATE))
            {
                List<MovieR>movieRS=savedInstanceState.getParcelableArrayList(MOVIE_STATE);
                if (movieRS==null)
                {
                    getMovies();

                }
                else {
                    mAdapter.setMovies(movieRS);
                    moviesArrayList=(ArrayList<MovieR>) mAdapter.getMovies();

                }


            }


        }
        else {

            getMovies();
        }
             return view;
    }

    private void getMovies() {
        WebServices webServices=WebServices.getMoviesResponse.create(WebServices.class);
        webServices.getPopularMovies(WebServices.KEY).enqueue(new Callback<WebMovieResponse>() {
            @Override
            public void onResponse(retrofit2.Call<WebMovieResponse> call, Response<WebMovieResponse> response) {
                if (response.body() != null) {
                    mAdapter.setMovies(response.body().getMovies());
                    moviesArrayList=(ArrayList<MovieR>)mAdapter.getMovies();

                }
            }

            @Override
            public void onFailure(retrofit2.Call<WebMovieResponse> call, Throwable t) {

            }
        });
    }


    @Override
    public void onListItemClick(int SelectedMovie) {
        Intent intent=new Intent(context,DetailsActivity.class);

        MovieR movie=moviesArrayList.get(SelectedMovie);
        intent.putExtra("MovieTitle", movie.getTitle());
        intent.putExtra("MoviePoster",movie.getPoster());
        intent.putExtra("MovieOverView",movie.getOverView());
        intent.putExtra("Rate",movie.getVoteAverage());
        intent.putExtra("Date",movie.getReleaseDate());
        intent.putExtra("ID",movie.getId());
        intent.putExtra("backDrop",movie.getBackdrop_path());

        Bundle bundle=new Bundle();
        bundle.putParcelable("MOVIE",movie);
        VideoFragment videoFragment=new VideoFragment();
        videoFragment.setArguments(bundle);


        startActivity(intent);
    }


    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(MOVIE_STATE,moviesArrayList);
    }
}
