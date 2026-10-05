package com.ddd.event_ticketing_platform.catalog.infrastructure.rest;

import com.ddd.event_ticketing_platform.catalog.application.VenueManagement;
import com.ddd.event_ticketing_platform.catalog.application.command.CreateVenueCommand;
import com.ddd.event_ticketing_platform.catalog.domain.venue.model.SeatingLayoutId;
import com.ddd.event_ticketing_platform.catalog.domain.venue.model.VenueId;
import com.ddd.event_ticketing_platform.catalog.infrastructure.rest.dto.request.AddSeatingLayoutRequest;
import com.ddd.event_ticketing_platform.catalog.infrastructure.rest.dto.request.CreateVenueRequest;
import com.ddd.event_ticketing_platform.catalog.infrastructure.rest.dto.response.SeatingLayoutIdResponse;
import com.ddd.event_ticketing_platform.catalog.infrastructure.rest.dto.response.VenueIdResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping("/api/venue-manager/venues")
public class VenueManagerController {

    private final VenueManagement venueManagement;

    public VenueManagerController(VenueManagement venueManagement) {
        this.venueManagement = venueManagement;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VenueIdResponse createVenue(
            @Valid @RequestBody CreateVenueRequest request
    ) {
        VenueId venueId =
                venueManagement.createVenue(new CreateVenueCommand(request.getName(), request.getAddress()));

        return new VenueIdResponse(venueId.venueId());
    }

    @PutMapping("/{venueId}")
    public SeatingLayoutIdResponse addSeatingLayoutToVenue(
            @PathVariable Long venueId,
            @Valid @RequestBody AddSeatingLayoutRequest request
    ) {
        SeatingLayoutId seatingLayoutId =
                venueManagement.addSeatingLayout(new VenueId(venueId), request.toCommand());

        return new SeatingLayoutIdResponse(seatingLayoutId.seatingLayoutId());
    }

}
