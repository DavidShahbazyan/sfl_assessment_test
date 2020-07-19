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
    private int number;
    private boolean isBusy;
    private boolean isReserved;

    @OneToOne
    private User waiter;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public boolean isBusy() {
        return isBusy;
    }

    public void setIsBusy(boolean busy) {
        this.isBusy = busy;
    }

    public boolean isReserved() {
        return isReserved;
    }

    public void setIsReserved(boolean reserved) {
        this.isReserved = reserved;
    }

    public boolean isAvailable() {
        return !isBusy && !isReserved;
    }

    public User getWaiter() {
        return waiter;
    }

    public void setWaiter(User waiter) {
        this.waiter = waiter;
    }
}
