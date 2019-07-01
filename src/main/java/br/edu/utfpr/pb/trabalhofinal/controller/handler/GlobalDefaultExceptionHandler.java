package br.edu.utfpr.pb.trabalhofinal.controller.handler;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GlobalDefaultExceptionHandler {
    private static final String DEFAULT_ERROR_PAGE = "error";

    @ResponseStatus
    @ExceptionHandler(value = Exception.class)
    public ModelAndView defaultErrorHandler(HttpServletRequest req, HttpServletResponse res, Exception e) throws Exception {
        if (AnnotationUtils.findAnnotation(
                e.getClass(), ResponseStatus.class) != null){
            throw e;
        }
        ModelAndView model = new ModelAndView();
        model.addObject("exception", e.getMessage());
        model.addObject("url", req.getRequestURI());
        model.setViewName(DEFAULT_ERROR_PAGE);
        return model;
    }
}
