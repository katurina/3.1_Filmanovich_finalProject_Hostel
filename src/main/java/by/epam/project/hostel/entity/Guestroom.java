package by.epam.project.hostel.entity;

import java.io.Serializable;
import java.util.List;

public class Guestroom extends Entity implements Serializable {
    private static final long serialVersionUID = -8053748141053902283L;

    private int id;
    private int hostelId;
    private double nightPrice;
    private boolean tv;
    private boolean wifi;
    private boolean bath;
    private int capacity;
    private String description;
    private List<String> imgPath;

    public Guestroom() {
    }

    public Guestroom(
            int id, int hostelId, double nightPrice, boolean tv,
            boolean wifi, boolean bath, int capacity, String description,
            List<String> imgPath) {
        this.id = id;
        this.hostelId = hostelId;
        this.nightPrice = nightPrice;
        this.tv = tv;
        this.wifi = wifi;
        this.bath = bath;
        this.capacity = capacity;
        this.description = description;
        this.imgPath = imgPath;
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

    public int getHostelId() {
        return hostelId;
    }

    public void setHostelId(int hostelId) {
        this.hostelId = hostelId;
    }

    public double getNightPrice() {
        return nightPrice;
    }

    public void setNightPrice(double nightPrice) {
        this.nightPrice = nightPrice;
    }

    public boolean isTv() {
        return tv;
    }

    public void setTv(boolean tv) {
        this.tv = tv;
    }

    public void setTv(int tv) {
        if (tv == 0) {
            this.tv = false;
        } else if (tv == 1) {
            this.tv = true;
        }
    }

    public boolean isWifi() {
        return wifi;
    }

    public void setWifi(boolean wifi) {
        this.wifi = wifi;
    }

    public void setWifi(int wifi) {
        if (wifi == 0) {
            this.wifi = false;
        } else if (wifi == 1) {
            this.wifi = true;
        }
    }

    public boolean isBath() {
        return bath;
    }

    public void setBath(boolean bath) {
        this.bath = bath;
    }

    public void setBath(int bath) {
        if (bath == 0) {
            this.bath = false;
        } else if (bath == 1) {
            this.bath = true;
        }
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getImgPath() {
        return imgPath;
    }

    public void setImgPath(List<String> imgPath) {
        this.imgPath = imgPath;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Guestroom guestroom = (Guestroom) o;

        if (getId() != guestroom.getId()) {
            return false;
        }
        if (getHostelId() != guestroom.getHostelId()) {
            return false;
        }
        if (Double.compare(guestroom.getNightPrice(), getNightPrice()) != 0) {
            return false;
        }
        if (isTv() != guestroom.isTv()) {
            return false;
        }
        if (isWifi() != guestroom.isWifi()) {
            return false;
        }
        if (isBath() != guestroom.isBath()) {
            return false;
        }
        if (getCapacity() != guestroom.getCapacity()) {
            return false;
        }
        if (getDescription() != null ? !getDescription().equals(guestroom.getDescription()) : guestroom.getDescription() != null) {
            return false;
        }
        return getImgPath() != null ? getImgPath().equals(guestroom.getImgPath()) : guestroom.getImgPath() == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = getId();
        result = 31 * result + getHostelId();
        temp = Double.doubleToLongBits(getNightPrice());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (isTv() ? 1 : 0);
        result = 31 * result + (isWifi() ? 1 : 0);
        result = 31 * result + (isBath() ? 1 : 0);
        result = 31 * result + getCapacity();
        result = 31 * result + (getDescription() != null ? getDescription().hashCode() : 0);
        result = 31 * result + (getImgPath() != null ? getImgPath().hashCode() : 0);
        return result;
    }
}
