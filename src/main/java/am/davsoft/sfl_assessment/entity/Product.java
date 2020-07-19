package am.davsoft.sfl_assessment.entity;

import am.davsoft.sfl_assessment.core.entity.BaseEntity;

import javax.persistence.Entity;

/**
 * @author David Shahbazyan
 * @since Jul 18, 2020
 */
@Entity
public class Product extends BaseEntity {
    private String name;
    private String description;
    private double price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
