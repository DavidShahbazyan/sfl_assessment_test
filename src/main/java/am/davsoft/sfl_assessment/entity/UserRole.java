package am.davsoft.sfl_assessment.entity;

import am.davsoft.sfl_assessment.helper.PermissionEnum;
import am.davsoft.sfl_assessment.core.entity.BaseEntity;

import javax.persistence.*;
import javax.persistence.Table;
import java.util.Set;

/**
 * @author David Shahbazyan
 * @since Jul 18, 2020
 */
@Entity
@Table
public class UserRole extends BaseEntity {
    private String roleName;

//    @OneToMany
//    private Set<PermissionEnum> permissionSet;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

//    public boolean hasPermission(PermissionEnum permission) {
//        return permissionSet.contains(permission);
//    }

//    public void grantPermission(PermissionEnum permission) {
//        this.permissionSet.add(permission);
//    }

//    public void revokePermission(PermissionEnum permission) {
//        this.permissionSet.remove(permission);
//    }
}
