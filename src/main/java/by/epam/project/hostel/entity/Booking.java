package by.epam.project.hostel.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

public class Booking extends Entity implements Serializable {
    private static final long serialVersionUID = -8053748141053902283L;

    private BigDecimal nightPrice;
    private LocalDate startDay;
    private LocalDate lastDay;
    private boolean payed;
    private LocalDate bookDay;
    private BigDecimal finalCost;
    private int userId;
    private int guestroomId;
    private int nightsCount;

    public BigDecimal getNightPrice() {
        return nightPrice;
    }

    public void setNightPrice(BigDecimal nightPrice) {
        this.nightPrice = nightPrice;
    }

    public LocalDate getStartDay() {
        return startDay;
    }

    public void setStartDay(LocalDate startDay) {
        this.startDay = startDay;
    }

    public LocalDate getLastDay() {
        return lastDay;
    }

    public void setLastDay(LocalDate lastDay) {
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

    public LocalDate getBookDay() {
        return bookDay;
    }

    public void setBookDay(LocalDate bookDay) {
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

    public int getNightsCount() {
        return nightsCount;
    }

    public void setNightsCount(int nightsCount) {
        this.nightsCount = nightsCount;
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
        if (getNightsCount() != booking.getNightsCount()) {
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
        result = 31 * result + getNightsCount();
        return result;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "id=" + id +
                ", nightPrice=" + nightPrice +
                ", startDay=" + startDay +
                ", lastDay=" + lastDay +
                ", payed=" + payed +
                ", bookDay=" + bookDay +
                ", finalCost=" + finalCost +
                ", userId=" + userId +
                ", guestroomId=" + guestroomId +
                ", nightsCount=" + nightsCount +
                '}';
    }
}
