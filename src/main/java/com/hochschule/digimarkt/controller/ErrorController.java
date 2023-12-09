package com.hochschule.digimarkt.controller;

import com.hochschule.digimarkt.exceptions.NotAuthorizedException;
import com.hochschule.digimarkt.exceptions.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Controller advice class that handles global exception handling for the application.
 * Provides methods to handle specific exceptions and return appropriate HTTP responses.
 */
@RestControllerAdvice
public class ErrorController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * Handles the {@code NotFoundException} by returning a 404 Not Found response.
     *
     * @return A string message indicating that the entity was not found.
     */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public String handleNotFound() {
        logger.warn("Entity not found");
        return "Not found";
    }

    /**
     * Handles the {@code NotAuthorizedException} by returning a 401 Unauthorized response.
     *
     * @return A string message indicating that the request is not authorized.
     */
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(NotAuthorizedException.class)
    public String handleNotAuthorized(Exception e) {
        logger.warn("Not authorized");
        return e.getMessage();
    }

    /**
     * Handles generic {@code Exception}s by returning a 500 Internal Server Error response.
     * Logs the exception details for debugging purposes.
     *
     * @param e The unhandled exception.
     * @return A string message indicating an internal server error.
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public String handleInternalError(Exception e) {
        logger.error("Unhandled Exception in Controller", e);
        return "Internal error";
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public String handleBadRequest(IllegalArgumentException e) {
        logger.warn("Bad request: {}", e.getMessage());
        return e.getMessage();
    }

    @ExceptionHandler(IllegalStateException.class)
    public String handleIllegalRequest(IllegalStateException e) {
        logger.warn("Bad request: {}", e.getMessage());
        return e.getMessage();
    }

}
