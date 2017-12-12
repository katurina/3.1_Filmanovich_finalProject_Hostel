package by.epam.project.hostel.entity;

import java.io.Serializable;
import java.util.Date;

public class Booking extends Entity implements Serializable {
    private static final long serialVersionUID = -8053748141053902283L;

    private int id;
    private double nightPrice;
    private Date startDay;
    private Date lastDay;
    private boolean payed;
    private Date bookDay;
    private double finalCost;
    private int userId;
    private int guestroomId;

    public Booking() {
    }

    public Booking(double nightPrice, Date startDay, Date lastDay,
                   boolean payed, Date bookDay, double finalCost,
                   int userId, int guestroomId) {
        this.nightPrice = nightPrice;
        this.startDay = startDay;
        this.lastDay = lastDay;
        this.payed = payed;
        this.bookDay = bookDay;
        this.finalCost = finalCost;
        this.userId = userId;
        this.guestroomId = guestroomId;
    }

    public Booking(int id, double nightPrice, Date startDay,
                   Date lastDay, boolean payed,
                   Date bookDay, double finalCost,
                   int userId, int guestroomId) {
        this.id = id;
        this.nightPrice = nightPrice;
        this.startDay = startDay;
        this.lastDay = lastDay;
        this.payed = payed;
        this.bookDay = bookDay;
        this.finalCost = finalCost;
        this.userId = userId;
        this.guestroomId = guestroomId;
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

    public double getNightPrice() {
        return nightPrice;
    }

    public void setNightPrice(double nightPrice) {
        this.nightPrice = nightPrice;
    }

    public Date getStartDay() {
        return startDay;
    }

    public void setStartDay(Date startDay) {
        this.startDay = startDay;
    }

    public Date getLastDay() {
        return lastDay;
    }

    public void setLastDay(Date lastDay) {
        this.lastDay = lastDay;
    }

    public boolean isPayed() {
        return payed;
    }

    public void setPayed(boolean payed) {
        this.payed = payed;
    }

    public Date getBookDay() {
        return bookDay;
    }

    public void setBookDay(Date bookDay) {
        this.bookDay = bookDay;
    }

    public double getFinalCost() {
        return finalCost;
    }

    public void setFinalCost(double finalCost) {
        this.finalCost = finalCost;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getGuestroomId() {
        return guestroomId;
    }

    public void setGuestroomId(int guestroomId) {
        this.guestroomId = guestroomId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Booking bookings = (Booking) o;

        if (getId() != bookings.getId()) {
            return false;
        }
        if (Double.compare(bookings.getNightPrice(), getNightPrice()) != 0) {
            return false;
        }
        if (isPayed() != bookings.isPayed()) {
            return false;
        }
        if (Double.compare(bookings.getFinalCost(), getFinalCost()) != 0) {
            return false;
        }
        if (getUserId() != bookings.getUserId()) {
            return false;
        }
        if (getGuestroomId() != bookings.getGuestroomId()) {
            return false;
        }
        if (getStartDay() != null ? !getStartDay().equals(bookings.getStartDay()) : bookings.getStartDay() != null) {
            return false;
        }
        if (getLastDay() != null ? !getLastDay().equals(bookings.getLastDay()) : bookings.getLastDay() != null) {
            return false;
        }
        return getBookDay() != null ? getBookDay().equals(bookings.getBookDay()) : bookings.getBookDay() == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = getId();
        temp = Double.doubleToLongBits(getNightPrice());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (getStartDay() != null ? getStartDay().hashCode() : 0);
        result = 31 * result + (getLastDay() != null ? getLastDay().hashCode() : 0);
        result = 31 * result + (isPayed() ? 1 : 0);
        result = 31 * result + (getBookDay() != null ? getBookDay().hashCode() : 0);
        temp = Double.doubleToLongBits(getFinalCost());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + getUserId();
        result = 31 * result + getGuestroomId();
        return result;
    }

}
