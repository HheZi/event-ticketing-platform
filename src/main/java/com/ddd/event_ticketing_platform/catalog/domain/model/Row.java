package com.ddd.event_ticketing_platform.catalog.domain.model;

import org.jmolecules.ddd.annotation.ValueObject;

import java.util.ArrayList;
import java.util.List;

@ValueObject
public class Row {

    private String label;

    private List<SeatLabel> seatLabels;

    public Row(String sectionCode, String label, Integer seatNumbers) {
        this.label = label;
        this.seatLabels = generateSeatLabels(sectionCode, label, seatNumbers);
    }

    private Row() {

    }

    private static List<SeatLabel> generateSeatLabels(
            String sectionCode, String label, Integer seatNumbers
    ) {
        ArrayList<SeatLabel> list = new ArrayList<>(seatNumbers);

        for (int seatNumber = 1; seatNumber <= seatNumbers; seatNumber++) {
            list.add(new SeatLabel(sectionCode, label, seatNumber));
        }

        return list;
    }

    public String label() {
        return label;
    }

    public List<SeatLabel> seatLabels() {
        return seatLabels;
    }

}
