package com.ddd.event_ticketing_platform.catalog.infrastructure.rest.dto.request;

import jakarta.validation.constraints.NotBlank;

public class CreateEventRequest {

    @NotBlank(message = "Name should not be blank")
    private String name;

    private String description;

    @NotBlank(message = "Category should not be blank")
    private String category;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }
}
