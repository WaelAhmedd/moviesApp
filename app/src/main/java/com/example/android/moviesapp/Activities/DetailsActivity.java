package com.example.android.moviesapp.Activities;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.moviesapp.AppExecutors;
import com.example.android.moviesapp.Database.AppDatabase;

import com.example.android.moviesapp.Fragments.ReviewFragment;
import com.example.android.moviesapp.Fragments.VideoFragment;
import com.example.android.moviesapp.Model.MovieR;
import com.example.android.moviesapp.Network.Api;
import com.example.android.moviesapp.Network.WebServices;
import com.example.android.moviesapp.R;
import com.squareup.picasso.Picasso;

import java.net.URL;
import java.util.List;

public class DetailsActivity extends AppCompatActivity {
    private TextView textViewTitle;

    private TextView relaseDate;



    private TextView overView;
    private ImageView backDrop;
    private RatingBar ratingBar;
    private WebServices webServices;

    AppDatabase mdb;
    private Button saveBtn;
    private MovieR movieR;

    VideoFragment videoFragment;
    ReviewFragment reviewFragment;
    ImageView movieImage;

    private String nameTest;
    private boolean checkTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        movieR=getMovieFromIntent();
        setContentView(R.layout.activity_detailss);

        defineViews();

        webServices=WebServices.getMoviesResponse.create(WebServices.class);
        mdb=AppDatabase.getsInstance(getApplicationContext());
        videoFragment=new VideoFragment();
        reviewFragment=new ReviewFragment();
        Bundle bundle=new Bundle();
        bundle.putParcelable("MOVIE",movieR);
        reviewFragment.setArguments(bundle);
        videoFragment.setArguments(bundle);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        transaction.add(R.id.trailer_container, videoFragment);
        transaction.add(R.id.review_container,reviewFragment);
        transaction.commit();


         float f=Float.parseFloat(movieR.getVoteAverage());
        textViewTitle.setText(movieR.getTitle());
        ratingBar.setRating(Float.parseFloat(movieR.getVoteAverage()));
        relaseDate.setText(movieR.getReleaseDate());
        overView.setText(movieR.getOverView());
        URL posterUrl= Api.buildPosterUrl(movieR.getPoster());
        Picasso.with(this)
                .load(posterUrl.toString())
                .placeholder(R.color.colorAccent)
                .into(backDrop);
        URL backdrop= Api.buildPosterUrl(movieR.getBackdrop_path());
        Picasso.with(this)
                .load(backdrop.toString())
                .placeholder(R.color.colorAccent)
                .into(movieImage);
        retrieveData();

        saveBtn.setOnClickListener(
                new View.OnClickListener() {

    @Override
    public void onClick(View view) {
        if(!checkTest) {

            save(view);
            Toast.makeText(DetailsActivity.this, "saved in favourites", Toast.LENGTH_SHORT).show();
                 }
                 else {
            Toast.makeText(DetailsActivity.this, "this is already exist in favourites", Toast.LENGTH_SHORT).show();
        }

        }
});






    }
    public void save(View view) {
        final MovieR movieR2=new MovieR(movieR.getId()
                ,movieR.getTitle(),movieR.getVoteAverage()
                ,movieR.getReleaseDate(),movieR.getPoster()
                ,movieR.getOverView(),movieR.getBackdrop_path());

        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                   mdb.movieDao().insertMovie(movieR2);
               }


        });
    }

    private void defineViews()
    {

        saveBtn=(Button)findViewById(R.id.btn_save);
        ratingBar=(RatingBar)findViewById(R.id.movie_rate_bar);
        backDrop=(ImageView)findViewById(R.id.iv_movieImage);
        textViewTitle = (TextView) findViewById(R.id.tv_movieName);

        relaseDate = (TextView) findViewById(R.id.tv_movieDate);
        overView = (TextView) findViewById(R.id.tv_movieOverView);
        movieImage=(ImageView)findViewById(R.id.img_poster);
    }

    private MovieR getMovieFromIntent()
    {
        Intent intent=getIntent();
            String id,title,poster,date,rate,overview,backdrop;
            id=intent.getStringExtra("ID");
            title=intent.getStringExtra("MovieTitle");
            nameTest=title;
            poster=intent.getStringExtra("MoviePoster");
            rate=intent.getStringExtra("Rate");
            date=intent.getStringExtra("Date");
            overview=intent.getStringExtra("MovieOverView");
            backdrop=intent.getStringExtra("backDrop");


        return new MovieR(id,title,rate,date,poster,overview,backdrop);


    }

    private void retrieveData()
    {

         LiveData<List<MovieR>> movies=mdb.movieDao().getAllMovies();
         movies.observe( this, new Observer<List<MovieR>>() {
             @Override
             public void onChanged(@Nullable List<MovieR> movieRS) {

                 assert movieRS != null;
                 for(int i = 0; i<movieRS.size(); i++)
                 {
                   if(nameTest.equals(movieRS.get(i).getTitle()))
                   {
                       checkTest=true;
                       break;
                   }
                   else {

                       checkTest=false;
                   }
                 }
             }
         });

     }

}



