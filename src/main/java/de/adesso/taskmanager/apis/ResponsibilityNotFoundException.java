package de.adesso.taskmanager.apis;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResponsibilityNotFoundException extends RuntimeException {
    public ResponsibilityNotFoundException(Long resId) {
        super("Couldn't find responsibility: " + resId);
    }
}
