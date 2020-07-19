package am.davsoft.sfl_assessment.entity;

import am.davsoft.sfl_assessment.core.entity.BaseEntity;
import am.davsoft.sfl_assessment.helper.PermissionEnum;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Set;

/**
 * @author David Shahbazyan
 * @since Jul 18, 2020
 */
@Entity
public class UserRole extends BaseEntity {
    private String roleName;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Permission> permissions;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public boolean hasPermission(PermissionEnum permission) {
        return permissions.stream().anyMatch(p -> p.getPermissionEnum().equals(permission) && p.isEnabled());
    }

    public void grantPermission(PermissionEnum permission) {
        this.permissions.add(Permission.ofType(permission));
    }

    public void revokePermission(PermissionEnum permission) {
        this.permissions.removeIf(p -> p.getPermissionEnum().equals(permission));
    }
}
