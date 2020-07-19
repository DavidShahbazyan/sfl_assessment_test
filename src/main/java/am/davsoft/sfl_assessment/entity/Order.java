package am.davsoft.sfl_assessment.entity;

import am.davsoft.sfl_assessment.core.entity.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

/**
 * @author David Shahbazyan
 * @since Jul 18, 2020
 */
@Entity
public class Order extends BaseEntity {
    @OneToMany
    private List<ProductInOrder> productsList;
    private double totalAmount;
    private boolean isOpen;

    public List<ProductInOrder> getProductsList() {
        return productsList;
    }

    public void setProductsList(List<ProductInOrder> productsList) {
        this.productsList = productsList;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    public void calculateTotalAmount() {
        productsList.forEach(productInOrder -> this.totalAmount = Double.sum(totalAmount, productInOrder.getAmount()));
    }
}
