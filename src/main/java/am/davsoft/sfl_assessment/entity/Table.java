package am.davsoft.sfl_assessment.entity;

import am.davsoft.sfl_assessment.core.entity.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

/**
 * @author David Shahbazyan
 * @since Jul 18, 2020
 */
@Entity
public class Table extends BaseEntity {
    private int number;
    private boolean busy;
    private boolean reserved;

    @OneToOne(optional = false)
    private User waiter;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public boolean isBusy() {
        return busy;
    }

    public void setBusy(boolean busy) {
        this.busy = busy;
    }

    public boolean isReserved() {
        return reserved;
    }

    public void setReserved(boolean reserved) {
        this.reserved = reserved;
    }

    public boolean isAvailable() {
        return !busy && !reserved;
    }

    public User getWaiter() {
        return waiter;
    }

    public void setWaiter(User waiter) {
        this.waiter = waiter;
    }
}
