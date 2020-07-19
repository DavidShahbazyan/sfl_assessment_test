package am.davsoft.sfl_assessment.entity;

import am.davsoft.sfl_assessment.core.entity.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

/**
 * @author David Shahbazyan
 * @since Jul 18, 2020
 */
@Entity
public class CafeTable extends BaseEntity {
    private Integer number;
    private Boolean isBusy;
    private Boolean isReserved;

    @OneToOne
    private User waiter;

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public boolean isBusy() {
        return isBusy;
    }

    public void setIsBusy(Boolean busy) {
        this.isBusy = busy;
    }

    public Boolean isReserved() {
        return isReserved;
    }

    public void setIsReserved(Boolean reserved) {
        this.isReserved = reserved;
    }

    public Boolean isAvailable() {
        return !isBusy && !isReserved;
    }

    public User getWaiter() {
        return waiter;
    }

    public void setWaiter(User waiter) {
        this.waiter = waiter;
    }
}
