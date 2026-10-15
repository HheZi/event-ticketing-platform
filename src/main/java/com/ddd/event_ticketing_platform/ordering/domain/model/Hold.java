package com.ddd.event_ticketing_platform.ordering.domain.model;

import com.ddd.event_ticketing_platform.catalog.domain.event.model.SessionId;
import com.ddd.event_ticketing_platform.ordering.domain.enums.HoldStatus;
import com.ddd.event_ticketing_platform.users.domain.model.BuyerId;
import jakarta.persistence.*;

import java.time.Instant;
import java.util.List;

@Entity
public class Hold {

    @EmbeddedId
    private HoldId id;

    @Embedded
    private SessionId sessionId;

    @Embedded
    private BuyerId buyerId;

    private Instant expiresAt;

    @OneToMany(mappedBy = "hold", cascade = CascadeType.ALL)
    private List<HeldItem> heldItems;

    @Enumerated(EnumType.STRING)
    private HoldStatus holdStatus;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Cart cart;

    public Hold(SessionId sessionId, BuyerId buyerId, Instant expiresAt, List<HeldItem> heldItems) {
        this.sessionId = sessionId;
        this.buyerId = buyerId;
        this.expiresAt = expiresAt;
        this.holdStatus = HoldStatus.ACTIVE;
        this.heldItems = heldItems;

        this.heldItems.forEach(heldItem -> heldItem.setHold(this));
    }

    private Hold() {

    }

    protected void setCart(Cart cart) {
        this.cart = cart;
    }

}
