package am.davsoft.sfl_assessment.core.controller;

import am.davsoft.sfl_assessment.entity.User;
import am.davsoft.sfl_assessment.exception.YouShallNotPassException;
import am.davsoft.sfl_assessment.helper.PermissionEnum;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * @author David Shahbazyan
 * @since Jul 19, 2020
 */
//@PropertySource("classpath:application.properties")
public class BaseController {
    @Value("${system.isDeleteAllowed}")
    protected boolean isDeleteAllowed;

    protected void checkUserPermission(User user, PermissionEnum permission) {
        if (user == null || !user.getRole().hasPermission(permission)) {
            throw new YouShallNotPassException();
        }
    }

    protected ResponseEntity createMethodNotAllowedResponseEntity(String message) {
        return new ResponseEntity(message, HttpStatus.METHOD_NOT_ALLOWED);
    }
}
