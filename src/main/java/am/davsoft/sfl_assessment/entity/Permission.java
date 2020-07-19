package am.davsoft.sfl_assessment.entity;

import am.davsoft.sfl_assessment.core.entity.BaseEntity;
import am.davsoft.sfl_assessment.helper.PermissionEnum;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 * @author David Shahbazyan
 * @since Jul 18, 2020
 */
@Entity
public class Permission extends BaseEntity {
    @Enumerated(EnumType.STRING)
    private PermissionEnum permissionEnum;
    private boolean enabled;

    public static Permission ofType(PermissionEnum permissionEnum) {
        Permission permission = new Permission();
        permission.setPermissionEnum(permissionEnum);
        permission.setEnabled(true);
        return permission;
    }

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
