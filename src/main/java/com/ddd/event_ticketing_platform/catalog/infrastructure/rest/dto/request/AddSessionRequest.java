package com.ddd.event_ticketing_platform.catalog.infrastructure.rest.dto.request;

import com.ddd.event_ticketing_platform.catalog.application.command.AddSessionToEventCommand;
import com.ddd.event_ticketing_platform.catalog.domain.venue.model.SeatingLayoutId;
import com.ddd.event_ticketing_platform.catalog.domain.venue.model.SectionId;
import com.ddd.event_ticketing_platform.catalog.domain.venue.model.VenueId;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

public class AddSessionRequest {

    @NotNull(message = "Venue should be specified")
    private Long venueId;

    @NotNull(message = "Seating layout should be specified")
    private UUID seatingLayoutId;

    @NotNull(message = "Start time should be specified")
    @Future(message = "Start time should be in future")
    private Instant startTime;

    @NotNull(message = "Sales start time should be specified")
    @Future(message = "Sales start time should be in future")
    private Instant salesStart;

    @NotNull(message = "Sales end time should be specified")
    @Future(message = "Sales end time should be in future")
    private Instant salesEnd;

    @NotEmpty(message = "Tickets should be specified")
    private List<@Valid TicketTypeInfoRequest> tickets;

    public AddSessionToEventCommand toCommand() {
        List<AddSessionToEventCommand.TicketTypeInfo> ticketTypeInfos = tickets.stream()
                .map(TicketTypeInfoRequest::toTicketTypeInfo)
                .toList();

        return new AddSessionToEventCommand(
                new VenueId(venueId), new SeatingLayoutId(seatingLayoutId),
                startTime, salesStart, salesEnd, ticketTypeInfos
        );
    }


    public static class TicketTypeInfoRequest {

        @NotNull(message = "Ticket type name should be specified")
        private String name;

        @NotNull(message = "Section should be specified")
        private UUID sectionId;

        @NotNull(message = "Base price should be specified")
        @Positive(message = "Base price should be positive number")
        private BigDecimal basePrice;

        @Positive(message = "Max pre order should be positive number")
        private Integer maxPreOrder;

        public AddSessionToEventCommand.TicketTypeInfo toTicketTypeInfo() {
            return new AddSessionToEventCommand.TicketTypeInfo(
                    name, new SectionId(sectionId), basePrice, maxPreOrder
            );
        }

    }

}
