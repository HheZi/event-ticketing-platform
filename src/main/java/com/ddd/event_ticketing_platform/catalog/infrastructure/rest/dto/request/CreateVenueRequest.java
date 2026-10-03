package com.ddd.event_ticketing_platform.catalog.infrastructure.rest.dto.request;

import jakarta.validation.constraints.NotBlank;

public class CreateVenueRequest {

    @NotBlank(message = "Name should not be blank")
    private String name;

    @NotBlank(message = "Address should not be blank")
    private String address;

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }
}
