package com.fleetmatrics.modal;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by talhakosen on 07/10/15.
 */
public class SpokenLanguage implements Parcelable {

    @JsonProperty
    private String iso_639_1;
    @JsonProperty
    private String name;

    public SpokenLanguage() {
    }

    protected SpokenLanguage(Parcel in) {
        iso_639_1 = in.readString();
        name = in.readString();
    }

    public static final Creator<SpokenLanguage> CREATOR = new Creator<SpokenLanguage>() {
        @Override
        public SpokenLanguage createFromParcel(Parcel in) {
            return new SpokenLanguage(in);
        }

        @Override
        public SpokenLanguage[] newArray(int size) {
            return new SpokenLanguage[size];
        }
    };

    /**
     * @return The iso6391
     */
    public String getIso6391() {
        return iso_639_1;
    }

    /**
     * @param iso6391 The iso_639_1
     */
    public void setIso6391(String iso6391) {
        this.iso_639_1 = iso6391;
    }

    /**
     * @return The name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name The name
     */
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(iso_639_1);
        dest.writeString(name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SpokenLanguage)) return false;

        SpokenLanguage that = (SpokenLanguage) o;

        if (getIso6391() != null ? !getIso6391().equals(that.getIso6391()) : that.getIso6391() != null)
            return false;
        if (getName() != null ? !getName().equals(that.getName()) : that.getName() != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = getIso6391() != null ? getIso6391().hashCode() : 0;
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "SpokenLanguage{" +
                "iso6391='" + iso_639_1 + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}