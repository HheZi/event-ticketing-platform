package com.ddd.event_ticketing_platform.catalog.application.command;

public record AddSeatingLayoutCommand(String name, CreateSectionCommand createSectionCommand) {
}
