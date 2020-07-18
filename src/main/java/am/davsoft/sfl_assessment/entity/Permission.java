package am.davsoft.sfl_assessment.entity;

import am.davsoft.sfl_assessment.helper.PermissionEnum;
import am.davsoft.sfl_assessment.core.entity.BaseEntity;

import javax.persistence.*;
import javax.persistence.Table;

/**
 * @author David Shahbazyan
 * @since Jul 18, 2020
 */
@Entity
@Table
public class Permission extends BaseEntity {
    @Enumerated(EnumType.ORDINAL)
    private PermissionEnum permissionEnum;
    private boolean enabled;

    public PermissionEnum getPermissionEnum() {
        return permissionEnum;
    }

    public void setPermissionEnum(PermissionEnum permissionEnum) {
        this.permissionEnum = permissionEnum;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
