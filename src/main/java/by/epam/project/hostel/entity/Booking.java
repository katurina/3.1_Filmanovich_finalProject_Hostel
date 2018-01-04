package by.epam.project.hostel.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Booking extends Entity implements Serializable {
    private static final long serialVersionUID = -8053748141053902283L;

    private int id;
    private BigDecimal nightPrice;
    private Date startDay;
    private Date lastDay;
    private boolean payed;
    private Date bookDay;
    private BigDecimal finalCost;
    private int userId;
    private int guestroomId;

    public Booking() {
    }

    public Booking(
            BigDecimal nightPrice, Date startDay, Date lastDay,
            boolean payed, Date bookDay, BigDecimal finalCost,
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

    public Booking(
            int id, BigDecimal nightPrice, Date startDay,
            Date lastDay, boolean payed,
            Date bookDay, BigDecimal finalCost,
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

    public BigDecimal getNightPrice() {
        return nightPrice;
    }

    public void setNightPrice(BigDecimal nightPrice) {
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

    public void setPayed(int payed) {
        if (payed == 0) {
            this.payed = false;
        } else if (payed == 1) {
            this.payed = true;
        }
    }

    public Date getBookDay() {
        return bookDay;
    }

    public void setBookDay(Date bookDay) {
        this.bookDay = bookDay;
    }

    public BigDecimal getFinalCost() {
        return finalCost;
    }

    public void setFinalCost(BigDecimal finalCost) {
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

        Booking booking = (Booking) o;

        if (getId() != booking.getId()) {
            return false;
        }
        if (isPayed() != booking.isPayed()) {
            return false;
        }
        if (getUserId() != booking.getUserId()) {
            return false;
        }
        if (getGuestroomId() != booking.getGuestroomId()) {
            return false;
        }
        if (getNightPrice() != null ? !getNightPrice().equals(booking.getNightPrice()) : booking.getNightPrice() != null) {
            return false;
        }
        if (getStartDay() != null ? !getStartDay().equals(booking.getStartDay()) : booking.getStartDay() != null) {
            return false;
        }
        if (getLastDay() != null ? !getLastDay().equals(booking.getLastDay()) : booking.getLastDay() != null) {
            return false;
        }
        if (getBookDay() != null ? !getBookDay().equals(booking.getBookDay()) : booking.getBookDay() != null) {
            return false;
        }
        return getFinalCost() != null ? getFinalCost().equals(booking.getFinalCost()) : booking.getFinalCost() == null;
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + (getNightPrice() != null ? getNightPrice().hashCode() : 0);
        result = 31 * result + (getStartDay() != null ? getStartDay().hashCode() : 0);
        result = 31 * result + (getLastDay() != null ? getLastDay().hashCode() : 0);
        result = 31 * result + (isPayed() ? 1 : 0);
        result = 31 * result + (getBookDay() != null ? getBookDay().hashCode() : 0);
        result = 31 * result + (getFinalCost() != null ? getFinalCost().hashCode() : 0);
        result = 31 * result + getUserId();
        result = 31 * result + getGuestroomId();
        return result;
    }
}
