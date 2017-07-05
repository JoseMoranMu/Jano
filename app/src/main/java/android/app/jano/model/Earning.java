package android.app.jano.model;

import java.util.Date;

/**
 * Created by Jose on 05/07/2017.
 */

public class Earning {
    int id;
    double value;
    String description;
    Date date;
    Category category;

    public Earning() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Earning(double value, String description, Date date, Category category) {
        this.value = value;
        this.description = description;
        this.date = date;
        this.category = category;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
