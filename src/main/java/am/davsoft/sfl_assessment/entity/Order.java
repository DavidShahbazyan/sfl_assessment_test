package am.davsoft.sfl_assessment.entity;

import am.davsoft.sfl_assessment.core.entity.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.LinkedList;
import java.util.List;

/**
 * @author David Shahbazyan
 * @since Jul 18, 2020
 */
@Entity
@Table
public class Order extends BaseEntity {
    @OneToMany
    private final List<ProductInOrder> productsList = new LinkedList<>();
    private int totalAmount;
    private boolean isOpen;

    public List<ProductInOrder> getProductsList() {
        return productsList;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }
}
