package com.ddd.event_ticketing_platform.catalog.domain.event.model;

import com.ddd.event_ticketing_platform.catalog.domain.venue.model.SectionId;
import jakarta.persistence.*;
import org.jmolecules.ddd.annotation.Identity;

import java.math.BigDecimal;
import java.util.Optional;

@Entity
public class TicketType {

    @Identity
    @EmbeddedId
    private TicketTypeId id;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Session session;
    private String name;
    @Embedded
    private SectionId sectionId;
    private BigDecimal basePrice;
    private Integer maxPreOrder;

    public TicketType(
            String name, SectionId sectionId,
            BigDecimal basePrice, Integer maxPreOrder
    ) {
        this.name = name;
        this.sectionId = sectionId;
        this.basePrice = basePrice;
        this.maxPreOrder = maxPreOrder;
    }

    private TicketType() {

    }

    protected void setSession(Session session) {
        this.session = session;
    }

    public TicketTypeId id() {
        return id;
    }

    public String name() {
        return name;
    }

    public SectionId sectionId() {
        return sectionId;
    }

    public BigDecimal basePrice() {
        return basePrice;
    }

    public Optional<Integer> maxPreOrder() {
        return Optional.ofNullable(maxPreOrder);
    }
}
