package com.fleetmatrics.modal;

import android.os.Parcel;
import android.os.Parcelable;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class MovieDetail implements Parcelable{

    @JsonProperty
    private boolean adult;
    @JsonProperty
    private String backdrop_path;
    @JsonProperty
    private BelongsToCollection belongs_to_collection;
    @JsonProperty
    private int budget;
    @JsonProperty
    private List<Genre> genres = new ArrayList<Genre>();
    @JsonProperty
    private String homepage;
    @JsonProperty
    private int id;
    @JsonProperty
    private String imdb_id;
    @JsonProperty
    private String original_language;
    @JsonProperty
    private String original_title;
    @JsonProperty
    private String overview;
    @JsonProperty
    private double popularity;
    @JsonProperty
    private String poster_path;
    @JsonProperty
    private List<ProductionCompany> production_companies = new ArrayList<ProductionCompany>();
    @JsonProperty
    private List<ProductionCountry> production_countries = new ArrayList<ProductionCountry>();
    @JsonProperty
    private String release_date;
    @JsonProperty
    private int revenue;
    @JsonProperty
    private int runtime;
    @JsonProperty
    private List<SpokenLanguage> spoken_languages = new ArrayList<SpokenLanguage>();
    @JsonProperty
    private String status;
    @JsonProperty
    private String tagline;
    @JsonProperty
    private String title;
    @JsonProperty
    private boolean video;
    @JsonProperty
    private double vote_average;
    @JsonProperty
    private int vote_count;

    public MovieDetail() {
    }


    protected MovieDetail(Parcel in) {
        adult = in.readByte() != 0;
        backdrop_path = in.readString();
        belongs_to_collection = in.readParcelable(BelongsToCollection.class.getClassLoader());
        budget = in.readInt();
        genres = in.createTypedArrayList(Genre.CREATOR);
        homepage = in.readString();
        id = in.readInt();
        imdb_id = in.readString();
        original_language = in.readString();
        original_title = in.readString();
        overview = in.readString();
        popularity = in.readDouble();
        poster_path = in.readString();
        production_companies = in.createTypedArrayList(ProductionCompany.CREATOR);
        production_countries = in.createTypedArrayList(ProductionCountry.CREATOR);
        release_date = in.readString();
        revenue = in.readInt();
        runtime = in.readInt();
        spoken_languages = in.createTypedArrayList(SpokenLanguage.CREATOR);
        status = in.readString();
        tagline = in.readString();
        title = in.readString();
        video = in.readByte() != 0;
        vote_average = in.readDouble();
        vote_count = in.readInt();
    }

    public static final Creator<MovieDetail> CREATOR = new Creator<MovieDetail>() {
        @Override
        public MovieDetail createFromParcel(Parcel in) {
            return new MovieDetail(in);
        }

        @Override
        public MovieDetail[] newArray(int size) {
            return new MovieDetail[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte((byte) (adult ? 1 : 0));
        dest.writeString(backdrop_path);
        dest.writeParcelable(belongs_to_collection, flags);
        dest.writeInt(budget);
        dest.writeTypedList(genres);
        dest.writeString(homepage);
        dest.writeInt(id);
        dest.writeString(imdb_id);
        dest.writeString(original_language);
        dest.writeString(original_title);
        dest.writeString(overview);
        dest.writeDouble(popularity);
        dest.writeString(poster_path);
        dest.writeTypedList(production_companies);
        dest.writeTypedList(production_countries);
        dest.writeString(release_date);
        dest.writeInt(revenue);
        dest.writeInt(runtime);
        dest.writeTypedList(spoken_languages);
        dest.writeString(status);
        dest.writeString(tagline);
        dest.writeString(title);
        dest.writeByte((byte) (video ? 1 : 0));
        dest.writeDouble(vote_average);
        dest.writeInt(vote_count);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MovieDetail)) return false;

        MovieDetail that = (MovieDetail) o;

        if (adult != that.adult) return false;
        if (budget != that.budget) return false;
        if (id != that.id) return false;
        if (Double.compare(that.popularity, popularity) != 0) return false;
        if (revenue != that.revenue) return false;
        if (runtime != that.runtime) return false;
        if (video != that.video) return false;
        if (Double.compare(that.vote_average, vote_average) != 0) return false;
        if (vote_count != that.vote_count) return false;
        if (backdrop_path != null ? !backdrop_path.equals(that.backdrop_path) : that.backdrop_path != null)
            return false;
        if (belongs_to_collection != null ? !belongs_to_collection.equals(that.belongs_to_collection) : that.belongs_to_collection != null)
            return false;
        if (genres != null ? !genres.equals(that.genres) : that.genres != null) return false;
        if (homepage != null ? !homepage.equals(that.homepage) : that.homepage != null)
            return false;
        if (imdb_id != null ? !imdb_id.equals(that.imdb_id) : that.imdb_id != null) return false;
        if (original_language != null ? !original_language.equals(that.original_language) : that.original_language != null)
            return false;
        if (original_title != null ? !original_title.equals(that.original_title) : that.original_title != null)
            return false;
        if (overview != null ? !overview.equals(that.overview) : that.overview != null)
            return false;
        if (poster_path != null ? !poster_path.equals(that.poster_path) : that.poster_path != null)
            return false;
        if (production_companies != null ? !production_companies.equals(that.production_companies) : that.production_companies != null)
            return false;
        if (production_countries != null ? !production_countries.equals(that.production_countries) : that.production_countries != null)
            return false;
        if (release_date != null ? !release_date.equals(that.release_date) : that.release_date != null)
            return false;
        if (spoken_languages != null ? !spoken_languages.equals(that.spoken_languages) : that.spoken_languages != null)
            return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (tagline != null ? !tagline.equals(that.tagline) : that.tagline != null) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = (adult ? 1 : 0);
        result = 31 * result + (backdrop_path != null ? backdrop_path.hashCode() : 0);
        result = 31 * result + (belongs_to_collection != null ? belongs_to_collection.hashCode() : 0);
        result = 31 * result + budget;
        result = 31 * result + (genres != null ? genres.hashCode() : 0);
        result = 31 * result + (homepage != null ? homepage.hashCode() : 0);
        result = 31 * result + id;
        result = 31 * result + (imdb_id != null ? imdb_id.hashCode() : 0);
        result = 31 * result + (original_language != null ? original_language.hashCode() : 0);
        result = 31 * result + (original_title != null ? original_title.hashCode() : 0);
        result = 31 * result + (overview != null ? overview.hashCode() : 0);
        temp = Double.doubleToLongBits(popularity);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (poster_path != null ? poster_path.hashCode() : 0);
        result = 31 * result + (production_companies != null ? production_companies.hashCode() : 0);
        result = 31 * result + (production_countries != null ? production_countries.hashCode() : 0);
        result = 31 * result + (release_date != null ? release_date.hashCode() : 0);
        result = 31 * result + revenue;
        result = 31 * result + runtime;
        result = 31 * result + (spoken_languages != null ? spoken_languages.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (tagline != null ? tagline.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (video ? 1 : 0);
        temp = Double.doubleToLongBits(vote_average);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + vote_count;
        return result;
    }

    @Override
    public String toString() {
        return "MovieDetail{" +
                "adult=" + adult +
                ", backdrop_path='" + backdrop_path + '\'' +
                ", belongs_to_collection=" + belongs_to_collection +
                ", budget=" + budget +
                ", genres=" + genres +
                ", homepage='" + homepage + '\'' +
                ", id=" + id +
                ", imdb_id='" + imdb_id + '\'' +
                ", original_language='" + original_language + '\'' +
                ", original_title='" + original_title + '\'' +
                ", overview='" + overview + '\'' +
                ", popularity=" + popularity +
                ", poster_path='" + poster_path + '\'' +
                ", production_companies=" + production_companies +
                ", production_countries=" + production_countries +
                ", release_date='" + release_date + '\'' +
                ", revenue=" + revenue +
                ", runtime=" + runtime +
                ", spoken_languages=" + spoken_languages +
                ", status='" + status + '\'' +
                ", tagline='" + tagline + '\'' +
                ", title='" + title + '\'' +
                ", video=" + video +
                ", vote_average=" + vote_average +
                ", vote_count=" + vote_count +
                '}';
    }

    public boolean isAdult() {
        return adult;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public BelongsToCollection getBelongs_to_collection() {
        return belongs_to_collection;
    }

    public int getBudget() {
        return budget;
    }

    public static Creator<MovieDetail> getCREATOR() {
        return CREATOR;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public String getHomepage() {
        return homepage;
    }

    public int getId() {
        return id;
    }

    public String getImdb_id() {
        return imdb_id;
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

    public List<ProductionCompany> getProduction_companies() {
        return production_companies;
    }

    public List<ProductionCountry> getProduction_countries() {
        return production_countries;
    }

    public String getRelease_date() {
        return release_date;
    }

    public int getRevenue() {
        return revenue;
    }

    public int getRuntime() {
        return runtime;
    }

    public List<SpokenLanguage> getSpoken_languages() {
        return spoken_languages;
    }

    public String getStatus() {
        return status;
    }

    public String getTagline() {
        return tagline;
    }

    public String getTitle() {
        return title;
    }

    public boolean isVideo() {
        return video;
    }

    public double getVote_average() {
        return vote_average;
    }

    public int getVote_count() {
        return vote_count;
    }
}












        
