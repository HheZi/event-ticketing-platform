package com.ddd.event_ticketing_platform.catalog.infrastructure.rest.dto.response;

import java.util.UUID;

public class SeatingLayoutIdResponse {

    private final UUID seatingLayoutId;

    public SeatingLayoutIdResponse(UUID seatingLayoutId) {
        this.seatingLayoutId = seatingLayoutId;
    }

}
