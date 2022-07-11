package com.techno_soft.employee_directory.controller;

import com.techno_soft.employee_directory.dto.ExceptionBody;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller to handle 404 page not found exceptions and others (which are not captured by Advice controller)
 *
 * @author ubaid
 */
@Controller
public class ErrorHandlerController implements ErrorController {

    private static final String ERROR_PARAMETER = "/error";

    private static final String ERROR_VIEW_FRAGMENT = "/views/error/error";

    private static final String EXCEPTION_ATTRIBUTE = "exception";

    @RequestMapping(ERROR_PARAMETER)
    public String handleUnhandledError(Model model, Exception exp) {
        ExceptionBody exceptionBody = new ExceptionBody();
        try {
            exceptionBody.setCause(exp.getCause().getMessage());
        } catch (NullPointerException ignore) {

        }
        exceptionBody.setMessage(exp.getMessage());
        model.addAttribute(EXCEPTION_ATTRIBUTE, exceptionBody);
        return ERROR_VIEW_FRAGMENT;
    }

    @Override
    public String getErrorPath() {
        return ERROR_PARAMETER;
    }
}
