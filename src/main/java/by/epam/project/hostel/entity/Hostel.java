package by.epam.project.hostel.entity;

import java.io.Serializable;

public class Hostel extends Entity implements Serializable {
    private static final long serialVersionUID = -8053748141053902283L;

    private int id;
    private int stars;
    private String country;
    private String city;
    private String description;
    private int hostelId;
    private String imgPath;
    private String address;

    public Hostel() {
    }

    public Hostel(
            int id, int stars, String country, String city,
            String description, int hostelId, String imgPath, String address) {
        this.id = id;
        this.stars = stars;
        this.country = country;
        this.city = city;
        this.description = description;
        this.hostelId = hostelId;
        this.imgPath = imgPath;
        this.address = address;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getHostelId() {
        return hostelId;
    }

    public void setHostelId(int hostelId) {
        this.hostelId = hostelId;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Hostel hostel = (Hostel) o;

        if (getId() != hostel.getId()) {
            return false;
        }
        if (getStars() != hostel.getStars()) {
            return false;
        }
        if (getHostelId() != hostel.getHostelId()) {
            return false;
        }
        if (getCountry() != null ? !getCountry().equals(hostel.getCountry()) : hostel.getCountry() != null) {
            return false;
        }
        if (getCity() != null ? !getCity().equals(hostel.getCity()) : hostel.getCity() != null) {
            return false;
        }
        if (getDescription() != null ? !getDescription().equals(hostel.getDescription()) : hostel.getDescription() != null) {
            return false;
        }
        if (getImgPath() != null ? !getImgPath().equals(hostel.getImgPath()) : hostel.getImgPath() != null) {
            return false;
        }
        return getAddress() != null ? getAddress().equals(hostel.getAddress()) : hostel.getAddress() == null;
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + getStars();
        result = 31 * result + (getCountry() != null ? getCountry().hashCode() : 0);
        result = 31 * result + (getCity() != null ? getCity().hashCode() : 0);
        result = 31 * result + (getDescription() != null ? getDescription().hashCode() : 0);
        result = 31 * result + getHostelId();
        result = 31 * result + (getImgPath() != null ? getImgPath().hashCode() : 0);
        result = 31 * result + (getAddress() != null ? getAddress().hashCode() : 0);
        return result;
    }
}
