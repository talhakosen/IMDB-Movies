package com.fleetmatrics.modal;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductionCountry implements Parcelable {

    @JsonProperty
    private String iso_3166_1;
    @JsonProperty
    private String name;

    public ProductionCountry() {
    }

    protected ProductionCountry(Parcel in) {
        iso_3166_1 = in.readString();
        name = in.readString();
    }

    public static final Creator<ProductionCountry> CREATOR = new Creator<ProductionCountry>() {
        @Override
        public ProductionCountry createFromParcel(Parcel in) {
            return new ProductionCountry(in);
        }

        @Override
        public ProductionCountry[] newArray(int size) {
            return new ProductionCountry[size];
        }
    };

    /**
     * @return The iso31661
     */
    public String getIso31661() {
        return iso_3166_1;
    }

    /**
     * @param iso31661 The iso_3166_1
     */
    public void setIso31661(String iso31661) {
        this.iso_3166_1 = iso31661;
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
        dest.writeString(iso_3166_1);
        dest.writeString(name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductionCountry)) return false;

        ProductionCountry that = (ProductionCountry) o;

        if (getIso31661() != null ? !getIso31661().equals(that.getIso31661()) : that.getIso31661() != null)
            return false;
        if (getName() != null ? !getName().equals(that.getName()) : that.getName() != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = getIso31661() != null ? getIso31661().hashCode() : 0;
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ProductionCountry{" +
                "iso31661='" + iso_3166_1 + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}