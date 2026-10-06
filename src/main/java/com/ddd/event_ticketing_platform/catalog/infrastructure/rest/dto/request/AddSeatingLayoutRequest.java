package com.ddd.event_ticketing_platform.catalog.infrastructure.rest.dto.request;

import com.ddd.event_ticketing_platform.catalog.application.command.AddSeatingLayoutCommand;
import com.ddd.event_ticketing_platform.catalog.application.command.AddSeatingLayoutCommand.SectionInfo;
import com.ddd.event_ticketing_platform.catalog.application.command.AddSeatingLayoutCommand.SectionInfo.RowLabelSeatNumber;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public class AddSeatingLayoutRequest {

    @NotBlank(message = "Seating layout name cannot be blank")
    private String name;

    @Valid
    @NotNull(message = "Sections should be specified")
    @NotEmpty(message = "Sections should not be empty")
    private List<SectionInfoRequest> sections;

    public AddSeatingLayoutCommand toCommand() {
        List<SectionInfo> sectionInfos = sections.stream().map(SectionInfoRequest::toSectionInfo)
                .toList();

        return new AddSeatingLayoutCommand(name, sectionInfos);
    }


    public enum SectionType {
        GENERAL_ADMISSION,
        RESERVED
    }

    public static class SectionRows {
        String label;

        Integer seatNumbers;

        public RowLabelSeatNumber toRowLabelSeatNumber() {
            return new RowLabelSeatNumber(label, seatNumbers);
        }
    }

    public static class SectionInfoRequest {

        @NotNull(message = "Section must be chosen")
        private SectionType type;

        @NotBlank(message = "Section name cannot be blank")
        private String name;

        @NotBlank(message = "Code cannot be blank")
        private String code;

        private Integer capacity;

        private List<SectionRows> rows;

        public SectionInfo toSectionInfo() {
            return switch (type) {
                case GENERAL_ADMISSION -> SectionInfo.generalAdmissionSection(name, code, capacity);
                case RESERVED -> {
                    List<RowLabelSeatNumber> rowLabelSeatNumbers = rows.stream().map(SectionRows::toRowLabelSeatNumber)
                            .toList();

                    yield SectionInfo.reservedSection(name, code, rowLabelSeatNumbers);
                }
            };
        }

    }
}
