package am.davsoft.sfl_assessment.core.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

/**
 * @author David Shahbazyan
 * @since Jul 19, 2020
 */
//@PropertySource("classpath:application.properties")
public class BaseController {
    @Value("${system.isDeleteAllowed}")
    protected boolean isDeleteAllowed;
}
