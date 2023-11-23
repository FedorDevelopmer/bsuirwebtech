package by.bsuir.wtl2.webapp.classes.entities;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Order {

    private int id;
    private Date creationDate;

    private double summaryPrice;

    private boolean isAccepted;

    public Order() {

    }

    public Order(Date creationDate, double summaryPrice, boolean isAccepted) {
        this.creationDate = creationDate;
        this.summaryPrice = summaryPrice;
        this.isAccepted = isAccepted;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public double getSummaryPrice() {
        return summaryPrice;
    }

    public void setSummaryPrice(double summaryPrice) {
        this.summaryPrice = summaryPrice;
    }

    public boolean isAccepted() {
        return isAccepted;
    }

    public void setAccepted(boolean accepted) {
        isAccepted = accepted;
    }

    public int getId() {
        return id;
    }

    public void setId(int orderId) {
        this.id = orderId;
    }


}
