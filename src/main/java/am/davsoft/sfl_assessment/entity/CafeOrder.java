package am.davsoft.sfl_assessment.entity;

import am.davsoft.sfl_assessment.core.entity.BaseEntity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

/**
 * @author David Shahbazyan
 * @since Jul 18, 2020
 */
@Entity
public class CafeOrder extends BaseEntity {
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductInOrder> productsList;
    private Double totalAmount;
    private Boolean isOpen;

    public List<ProductInOrder> getProductsList() {
        return productsList;
    }

    public void setProductsList(List<ProductInOrder> productsList) {
        this.productsList = productsList;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Boolean isOpen() {
        return isOpen;
    }

    public void setOpen(Boolean open) {
        isOpen = open;
    }

    public void calculateTotalAmount() {
        productsList.forEach(productInOrder -> this.totalAmount = Double.sum(totalAmount, productInOrder.getAmount()));
    }
}
