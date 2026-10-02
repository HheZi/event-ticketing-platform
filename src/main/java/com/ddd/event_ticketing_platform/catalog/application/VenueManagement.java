package com.ddd.event_ticketing_platform.catalog.application;

import com.ddd.event_ticketing_platform.catalog.application.command.AddSeatingLayoutCommand;
import com.ddd.event_ticketing_platform.catalog.application.command.CreateSectionCommand;
import com.ddd.event_ticketing_platform.catalog.application.command.CreateVenueCommand;
import com.ddd.event_ticketing_platform.catalog.application.exception.UnknowSectionType;
import com.ddd.event_ticketing_platform.catalog.application.exception.VenueIsNotFound;
import com.ddd.event_ticketing_platform.catalog.domain.model.*;
import com.ddd.event_ticketing_platform.catalog.domain.repository.VenueRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class VenueManagement {

    private final VenueRepository venues;

    public VenueManagement(VenueRepository venues) {
        this.venues = venues;
    }

    @Transactional
    public VenueId createVenue(CreateVenueCommand command) {
        Venue venue = new Venue(command.name(), command.address());

        Venue savedVenue = venues.save(venue);

        return savedVenue.id();
    }

    @Transactional
    public SeatingLayoutId addSeatingLayout(VenueId venueId, AddSeatingLayoutCommand command) {
        Venue venue = venues.findById(venueId)
                .orElseThrow(() -> new VenueIsNotFound(venueId));

        CreateSectionCommand sectionCommand = command.createSectionCommand();

        Section section;
        if (sectionCommand.type() == CreateSectionCommand.SectionType.RESERVED) {
            List<Row> rows = sectionCommand.rows().stream()
                    .map(row -> new Row(sectionCommand.code(), row.label(), row.seatNumbers()))
                    .toList();

            section = new ReservedSection(sectionCommand.name(), sectionCommand.code(), rows);
        } else if (sectionCommand.type() == CreateSectionCommand.SectionType.GENERAL_ADMISSION) {
            section = new GeneralAdmissionSection(sectionCommand.name(), sectionCommand.code(), sectionCommand.capacity());
        } else {
            throw new UnknowSectionType();
        }

        SeatingLayout seatingLayout = new SeatingLayout(command.name(), section);

        venue.addSeatingLayout(seatingLayout);

        venues.save(venue);

        return seatingLayout.id();
    }

}
