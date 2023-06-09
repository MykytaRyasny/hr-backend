package com.tfg.proyect.controller.utils;

import com.tfg.proyect.controller.EmployeeController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * The type Logger utils.
 */
public class LoggerUtils {
    private LoggerUtils() {
    }

    /**
     * The constant LOGGER.
     */
    public static final Logger LOGGER = LoggerFactory.getLogger(EmployeeController.class);

    /**
     * Logger info.
     *
     * @param methodName the method name
     * @param className  the class name
     */
    public static void LoggerInfo(String methodName, String className) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentAuth = authentication.getAuthorities().toString();
        String currentUser = authentication.getName();
        LOGGER.info("Role of user {} with rol {} used {} method in {}}", currentUser, currentAuth, methodName, className);
    }
}
