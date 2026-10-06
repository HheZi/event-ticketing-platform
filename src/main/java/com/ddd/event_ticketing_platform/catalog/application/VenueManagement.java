package com.ddd.event_ticketing_platform.catalog.application;

import com.ddd.event_ticketing_platform.catalog.application.command.AddSeatingLayoutCommand;
import com.ddd.event_ticketing_platform.catalog.application.command.AddSeatingLayoutCommand.SectionInfo;
import com.ddd.event_ticketing_platform.catalog.application.command.CreateVenueCommand;
import com.ddd.event_ticketing_platform.catalog.domain.event.exception.UnknowSectionType;
import com.ddd.event_ticketing_platform.catalog.domain.venue.exception.VenueNotFound;
import com.ddd.event_ticketing_platform.catalog.domain.venue.model.*;
import com.ddd.event_ticketing_platform.catalog.domain.venue.repository.VenueRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
                .orElseThrow(() -> new VenueNotFound(venueId));

        List<Section> sections = new ArrayList<>(command.sectionInfos().size());
        for (SectionInfo sectionInfo : command.sectionInfos()) {
            switch (sectionInfo.type()) {
                case RESERVED -> {
                    List<Row> rows = sectionInfo.rows().stream()
                            .map(row -> new Row(sectionInfo.code(), row.label(), row.seatNumbers()))
                            .toList();

                    sections.add(new ReservedSection(sectionInfo.name(), sectionInfo.code(), rows));
                }
                case GENERAL_ADMISSION ->
                        sections.add(new GeneralAdmissionSection(sectionInfo.name(), sectionInfo.code(), sectionInfo.capacity()));
                case null, default -> throw new UnknowSectionType();
            }
        }

        SeatingLayout seatingLayout = new SeatingLayout(command.name(), sections);

        venue.addSeatingLayout(seatingLayout);

        venues.save(venue);

        return seatingLayout.id();
    }

}
