package com.ddd.event_ticketing_platform.catalog.application.command;

import java.util.List;

public record AddSeatingLayoutCommand(String name, List<SectionInfo> sectionInfos) {

    public record SectionInfo(
            SectionInfo.SectionType type, String name,
            String code, Integer capacity,
            List<SectionInfo.RowLabelSeatNumber> rows
    ) {

        public static SectionInfo reservedSection(
                String name, String code, List<SectionInfo.RowLabelSeatNumber> rows
        ) {
            return new SectionInfo(SectionInfo.SectionType.RESERVED, name, code, null, rows);
        }

        public static SectionInfo generalAdmissionSection(
                String name, String code, Integer capacity
        ) {
            return new SectionInfo(SectionInfo.SectionType.GENERAL_ADMISSION, name, code, capacity, null);
        }

        public enum SectionType {
            GENERAL_ADMISSION,
            RESERVED
        }

        public record RowLabelSeatNumber(String label, int seatNumbers) {

        }
    }
}
