package com.example.android.moviesapp.Model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

    @Entity(tableName = "Movies")

 public class MovieR implements Parcelable {

    @PrimaryKey
    @NonNull
    private String  id;

    private String title;

    @SerializedName("vote_average")
    private String voteAverage;

    @SerializedName("release_date")
    private String releaseDate;

    @SerializedName("poster_path")
    private String poster;

    @SerializedName("overview")
    private String overView;

    @SerializedName("backdrop_path")
    private String backdrop_path;


        public MovieR(@NonNull String id, String title, String voteAverage, String releaseDate, String poster, String overView, String backdrop_path) {
            this.id = id;
            this.title = title;
            this.voteAverage = voteAverage;
            this.releaseDate = releaseDate;
            this.poster = poster;
            this.overView = overView;
            this.backdrop_path = backdrop_path;
        }

        @Ignore
        MovieR(){

        }

        @Ignore
        MovieR(Parcel in)
        {
        this.id=in.readString();
        this.title=in.readString();
        this.poster=in.readString();
        this.backdrop_path=in.readString();
        this.voteAverage=in.readString();
        this.overView=in.readString();

        }
    public void setVoteAverage(String voteAverage) {
        this.voteAverage = voteAverage;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public void setOverView(String overView) {
        this.overView = overView;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public String getVoteAverage() {

        return voteAverage;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getPoster() {
        return poster;
    }

    public String getOverView() {
        return overView;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }



    //////////////////////////////////////////////////////////////////////////////////////////////////////////////

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.id);
        parcel.writeString(this.title);
        parcel.writeString(this.voteAverage);
        parcel.writeString(this.releaseDate);
        parcel.writeString(this.poster);
        parcel.writeString(this.overView);
        parcel.writeString(this.backdrop_path);


        }
        public static Parcelable.Creator<MovieR> CREATOR= new Creator<MovieR>() {
            @Override
            public MovieR createFromParcel(Parcel source) {
                return new MovieR(source);
            }

            @Override
            public MovieR[] newArray(int size) {
                return new MovieR[size];
            }
        };
    }
