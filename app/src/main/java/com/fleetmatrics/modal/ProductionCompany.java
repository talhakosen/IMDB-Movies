package com.fleetmatrics.modal;

/**
 * Created by talhakosen on 07/10/15.
 */

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by talhakosen on 07/10/15.
 */
public class ProductionCompany implements Parcelable{

    @JsonProperty
    private String name;
    @JsonProperty
    private int id;

    public ProductionCompany() {
    }

    protected ProductionCompany(Parcel in) {
        name = in.readString();
        id = in.readInt();
    }

    public static final Creator<ProductionCompany> CREATOR = new Creator<ProductionCompany>() {
        @Override
        public ProductionCompany createFromParcel(Parcel in) {
            return new ProductionCompany(in);
        }

        @Override
        public ProductionCompany[] newArray(int size) {
            return new ProductionCompany[size];
        }
    };

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

    /**
     * @return The id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id The id
     */
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductionCompany)) return false;

        ProductionCompany that = (ProductionCompany) o;

        if (getId() != that.getId()) return false;
        return !(getName() != null ? !getName().equals(that.getName()) : that.getName() != null);

    }

    @Override
    public int hashCode() {
        int result = getName() != null ? getName().hashCode() : 0;
        result = 31 * result + getId();
        return result;
    }

    @Override
    public String toString() {
        return "ProductionCompany{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}