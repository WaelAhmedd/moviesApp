package com.example.android.moviesapp.Fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.android.moviesapp.Adapters.ReviewsAdapter;
import com.example.android.moviesapp.Model.MovieR;
import com.example.android.moviesapp.Network.WebServices;
import com.example.android.moviesapp.R;
import com.example.android.moviesapp.review.ReviewResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class ReviewFragment extends Fragment {

   private ReviewsAdapter reviewsAdapter;
   private RecyclerView reviewList;
   private MovieR movieR;
   private WebServices webServices;
   private Context context;

    public ReviewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_review, container, false);
            context=getContext();
        if(getArguments()!=null)
        {
            movieR=getArguments().getParcelable("MOVIE");
        }
        webServices=WebServices.getMoviesResponse.create(WebServices.class);
        reviewsAdapter=new ReviewsAdapter(context);
        RecyclerView.LayoutManager linearLayout=new LinearLayoutManager(context);
        reviewList=view.findViewById(R.id.rv_reviews);
        reviewList.setLayoutManager(linearLayout);
        showReviews(movieR.getId());

    return view;}


    private void showReviews(String id)
    {
        webServices.getMovieReview(id,WebServices.KEY).enqueue(new Callback<ReviewResponse>() {
            @Override
            public void onResponse(Call<ReviewResponse> call, Response<ReviewResponse> response) {
                if (response.body() != null) {
                    {

                        reviewsAdapter.setResults(response.body().getResults());
                        reviewList.setAdapter(reviewsAdapter);
                    }
                }
            }

            @Override
            public void onFailure(Call<ReviewResponse> call, Throwable t) {

            }
        });

    }

}
