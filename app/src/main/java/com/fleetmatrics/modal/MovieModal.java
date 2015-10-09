package com.fleetmatrics.modal;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MovieModal implements Parcelable {
    @JsonProperty
    private int page;

    @JsonProperty
    private List<Movie> results;

    @JsonProperty
    private int total_pages;

    @JsonProperty
    private int total_results;

    public MovieModal(){}

    protected MovieModal(Parcel in) {
        page = in.readInt();
        results = in.createTypedArrayList(Movie.CREATOR);
        total_pages = in.readInt();
        total_results = in.readInt();
    }

    public static final Creator<MovieModal> CREATOR = new Creator<MovieModal>() {
        @Override
        public MovieModal createFromParcel(Parcel in) {
            return new MovieModal(in);
        }

        @Override
        public MovieModal[] newArray(int size) {
            return new MovieModal[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(page);
        dest.writeTypedList(results);
        dest.writeInt(total_pages);
        dest.writeInt(total_results);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MovieModal)) return false;

        MovieModal that = (MovieModal) o;

        if (page != that.page) return false;
        if (total_pages != that.total_pages) return false;
        if (total_results != that.total_results) return false;
        if (results != null ? !results.equals(that.results) : that.results != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = page;
        result = 31 * result + (results != null ? results.hashCode() : 0);
        result = 31 * result + total_pages;
        result = 31 * result + total_results;
        return result;
    }

    @Override
    public String toString() {
        return "MovieListItem{" +
                "page=" + page +
                ", items=" + results +
                ", total_pages=" + total_pages +
                ", total_results=" + total_results +
                '}';
    }

    public int getPage() {
        return page;
    }

    public List<Movie> getResults() {
        return results;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public int getTotal_results() {
        return total_results;
    }

    public static Comparator<Movie> popularityComparator = new Comparator<Movie>() {
        @Override
        public int compare(Movie m1, Movie m2) {
            double p1 = m1.getPopularity();
            double p2 = m2.getPopularity();

            if (p1 < p2)
                return -1;
            else if (p1 >= p2)
                return 1;
            else
                return 0;
        }
    };

}











        
