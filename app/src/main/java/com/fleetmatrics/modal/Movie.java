package com.fleetmatrics.modal;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fleetmatrics.core.FleetMetricsApplication;

import java.util.ArrayList;
import java.util.List;

public class Movie implements Parcelable {
    @JsonProperty
    private boolean adult;
    @JsonProperty
    private String backdrop_path;
    @JsonProperty
    private List<Integer> genre_ids = new ArrayList<Integer>();
    @JsonProperty
    private int id;
    @JsonProperty
    private String original_language;
    @JsonProperty
    private String original_title;
    @JsonProperty
    private String overview;
    @JsonProperty
    private String release_date;
    @JsonProperty
    private String poster_path;
    @JsonProperty
    private double popularity;
    @JsonProperty
    private String title;
    @JsonProperty
    private boolean video;
    @JsonProperty
    private int vote_average;
    @JsonProperty
    private int vote_count;

    private String genreAsString;

    public Movie(){}

    protected Movie(Parcel in) {
        adult = in.readByte() != 0;
        backdrop_path = in.readString();
        id = in.readInt();
        original_language = in.readString();
        original_title = in.readString();
        overview = in.readString();
        release_date = in.readString();
        poster_path = in.readString();
        popularity = in.readDouble();
        title = in.readString();
        video = in.readByte() != 0;
        vote_average = in.readInt();
        vote_count = in.readInt();
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    @Override
    public boolean equals(Object o) {


        if (this == o) return true;
        if (!(o instanceof Movie)) return false;

        Movie movie = (Movie) o;

        if (adult != movie.adult) return false;
        if (id != movie.id) return false;
        if (Double.compare(movie.popularity, popularity) != 0) return false;
        if (video != movie.video) return false;
        if (vote_average != movie.vote_average) return false;
        if (vote_count != movie.vote_count) return false;
        if (backdrop_path != null ? !backdrop_path.equals(movie.backdrop_path) : movie.backdrop_path != null)
            return false;
        if (genre_ids != null ? !genre_ids.equals(movie.genre_ids) : movie.genre_ids != null)
            return false;
        if (original_language != null ? !original_language.equals(movie.original_language) : movie.original_language != null)
            return false;
        if (original_title != null ? !original_title.equals(movie.original_title) : movie.original_title != null)
            return false;
        if (overview != null ? !overview.equals(movie.overview) : movie.overview != null)
            return false;
        if (release_date != null ? !release_date.equals(movie.release_date) : movie.release_date != null)
            return false;
        if (poster_path != null ? !poster_path.equals(movie.poster_path) : movie.poster_path != null)
            return false;
        return !(title != null ? !title.equals(movie.title) : movie.title != null);

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = (adult ? 1 : 0);
        result = 31 * result + (backdrop_path != null ? backdrop_path.hashCode() : 0);
        result = 31 * result + (genre_ids != null ? genre_ids.hashCode() : 0);
        result = 31 * result + id;
        result = 31 * result + (original_language != null ? original_language.hashCode() : 0);
        result = 31 * result + (original_title != null ? original_title.hashCode() : 0);
        result = 31 * result + (overview != null ? overview.hashCode() : 0);
        result = 31 * result + (release_date != null ? release_date.hashCode() : 0);
        result = 31 * result + (poster_path != null ? poster_path.hashCode() : 0);
        temp = Double.doubleToLongBits(popularity);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (video ? 1 : 0);
        result = 31 * result + vote_average;
        result = 31 * result + vote_count;
        return result;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "adult=" + adult +
                ", backdrop_path='" + backdrop_path + '\'' +
                ", genre_ids=" + genre_ids +
                ", id=" + id +
                ", original_language='" + original_language + '\'' +
                ", original_title='" + original_title + '\'' +
                ", overview='" + overview + '\'' +
                ", release_date='" + release_date + '\'' +
                ", poster_path='" + poster_path + '\'' +
                ", popularity=" + popularity +
                ", title='" + title + '\'' +
                ", video=" + video +
                ", vote_average=" + vote_average +
                ", vote_count=" + vote_count +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte((byte) (adult ? 1 : 0));
        dest.writeString(backdrop_path);
        dest.writeInt(id);
        dest.writeString(original_language);
        dest.writeString(original_title);
        dest.writeString(overview);
        dest.writeString(release_date);
        dest.writeString(poster_path);
        dest.writeDouble(popularity);
        dest.writeString(title);
        dest.writeByte((byte) (video ? 1 : 0));
        dest.writeInt(vote_average);
        dest.writeInt(vote_count);
    }

    public boolean isAdult() {
        return adult;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public static Creator<Movie> getCREATOR() {
        return CREATOR;
    }

    public List<Integer> getGenre_ids() {
        return genre_ids;
    }

    public int getId() {
        return id;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public String getOverview() {
        return overview;
    }

    public double getPopularity() {
        return popularity;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public String getRelease_date() {
        return release_date;
    }

    public String getTitle() {
        return title;
    }

    public boolean isVideo() {
        return video;
    }

    public int getVote_average() {
        return vote_average;
    }

    public int getVote_count() {
        return vote_count;
    }

    public String getGenresAsString(){
        if(genreAsString!=null)
            return genreAsString;

        int index =0;
        StringBuilder sb = new StringBuilder();
        for (int i : genre_ids){

            if(index >= genre_ids.size())
                sb.append(FleetMetricsApplication.getInstance().getGenres().get(i).getName()+", ");
            else
                sb.append(FleetMetricsApplication.getInstance().getGenres().get(i).getName());

            index++;
        }

        return sb.toString();
    }
}











        
