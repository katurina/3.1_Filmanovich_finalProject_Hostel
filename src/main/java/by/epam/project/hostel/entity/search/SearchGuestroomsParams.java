package by.epam.project.hostel.entity.search;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class SearchGuestroomsParams implements Serializable {

    private static final long serialVersionUID = 1L;

    private String sorting;
    private BigDecimal nightPriceFrom;
    private BigDecimal nightPriceTo;
    private Date dateFrom;
    private Date dateTo;
    private Boolean wifi;
    private Boolean tv;
    private Boolean shower;

    public SearchGuestroomsParams() {
    }

    public SearchGuestroomsParams(BigDecimal nightPriceFrom, BigDecimal nightPriceTo, Date dateFrom, Date dateTo, Boolean wifi, Boolean tv, Boolean shower, String sorting) {
        this.nightPriceFrom = nightPriceFrom;
        this.nightPriceTo = nightPriceTo;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.wifi = wifi;
        this.tv = tv;
        this.shower = shower;
        this.sorting = sorting;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public BigDecimal getNightPriceFrom() {
        return nightPriceFrom;
    }

    public void setNightPriceFrom(BigDecimal nightPriceFrom) {
        this.nightPriceFrom = nightPriceFrom;
    }

    public BigDecimal getNightPriceTo() {
        return nightPriceTo;
    }

    public void setNightPriceTo(BigDecimal nightPriceTo) {
        this.nightPriceTo = nightPriceTo;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    public Boolean getWifi() {
        return wifi;
    }

    public void setWifi(Boolean wifi) {
        this.wifi = wifi;
    }

    public Boolean getTv() {
        return tv;
    }

    public void setTv(Boolean tv) {
        this.tv = tv;
    }

    public Boolean getShower() {
        return shower;
    }

    public void setShower(Boolean shower) {
        this.shower = shower;
    }

    public String getSorting() {
        return sorting;
    }

    public void setSorting(String sorting) {
        this.sorting = sorting;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SearchGuestroomsParams that = (SearchGuestroomsParams) o;

        if (getNightPriceFrom() != null ? !getNightPriceFrom().equals(that.getNightPriceFrom()) : that.getNightPriceFrom() != null) {
            return false;
        }
        if (getNightPriceTo() != null ? !getNightPriceTo().equals(that.getNightPriceTo()) : that.getNightPriceTo() != null) {
            return false;
        }
        if (getDateFrom() != null ? !getDateFrom().equals(that.getDateFrom()) : that.getDateFrom() != null) {
            return false;
        }
        if (getDateTo() != null ? !getDateTo().equals(that.getDateTo()) : that.getDateTo() != null) {
            return false;
        }
        if (getWifi() != null ? !getWifi().equals(that.getWifi()) : that.getWifi() != null) {
            return false;
        }
        if (getTv() != null ? !getTv().equals(that.getTv()) : that.getTv() != null) {
            return false;
        }
        if (getShower() != null ? !getShower().equals(that.getShower()) : that.getShower() != null) {
            return false;
        }
        return getSorting() != null ? getSorting().equals(that.getSorting()) : that.getSorting() == null;
    }

    @Override
    public int hashCode() {
        int result = getNightPriceFrom() != null ? getNightPriceFrom().hashCode() : 0;
        result = 31 * result + (getNightPriceTo() != null ? getNightPriceTo().hashCode() : 0);
        result = 31 * result + (getDateFrom() != null ? getDateFrom().hashCode() : 0);
        result = 31 * result + (getDateTo() != null ? getDateTo().hashCode() : 0);
        result = 31 * result + (getWifi() != null ? getWifi().hashCode() : 0);
        result = 31 * result + (getTv() != null ? getTv().hashCode() : 0);
        result = 31 * result + (getShower() != null ? getShower().hashCode() : 0);
        result = 31 * result + (getSorting() != null ? getSorting().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "SearchGuestroomsParams{" +
                "nightPriceFrom=" + nightPriceFrom +
                ", nightPriceTo=" + nightPriceTo +
                ", dateFrom=" + dateFrom +
                ", dateTo=" + dateTo +
                ", wifi=" + wifi +
                ", tv=" + tv +
                ", shower=" + shower +
                ", sorting='" + sorting + '\'' +
                '}';
    }
}
