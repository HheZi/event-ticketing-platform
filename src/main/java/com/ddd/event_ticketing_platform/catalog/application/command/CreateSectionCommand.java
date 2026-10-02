package com.ddd.event_ticketing_platform.catalog.application.command;

import java.util.List;

public record CreateSectionCommand(
        SectionType type, String name,
        String code, Integer capacity,
        List<RowLabelSeatNumber> rows
) {

    public static CreateSectionCommand createReservedSection(
            String name, String code, List<RowLabelSeatNumber> rows
    ) {
        return new CreateSectionCommand(SectionType.RESERVED, name, code, null, rows);
    }

    public static CreateSectionCommand createGeneralAdmissionSection(
            String name, String code, Integer capacity
    ) {
        return new CreateSectionCommand(SectionType.GENERAL_ADMISSION, name, code, capacity, null);
    }

    public enum SectionType {
        GENERAL_ADMISSION,
        RESERVED
    }

    public record RowLabelSeatNumber(String label, int seatNumbers) {

    }
}
