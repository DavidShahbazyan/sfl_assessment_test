package am.davsoft.sfl_assessment.entity;

import am.davsoft.sfl_assessment.core.entity.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.time.LocalDate;

/**
 * @author David Shahbazyan
 * @since Jul 18, 2020
 */
@Entity
public class User extends BaseEntity {
    private String firstName;
    private String lastName;
    private LocalDate birthDate;

    @OneToOne
    private UserRole role;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }
}
