package am.davsoft.sfl_assessment.entity;

import am.davsoft.sfl_assessment.core.entity.BaseEntity;
import am.davsoft.sfl_assessment.helper.PermissionEnum;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Objects;

/**
 * @author David Shahbazyan
 * @since Jul 18, 2020
 */
@Entity
public class Permission extends BaseEntity {
    @Enumerated(EnumType.STRING)
    private PermissionEnum permissionEnum;
    private Boolean enabled;

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

    public Boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Permission that = (Permission) o;
        return permissionEnum == that.permissionEnum &&
                Objects.equals(enabled, that.enabled);
    }

    @Override
    public int hashCode() {
        return Objects.hash(permissionEnum, enabled);
    }
}
