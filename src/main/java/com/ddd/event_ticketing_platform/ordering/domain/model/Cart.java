package com.ddd.event_ticketing_platform.ordering.domain.model;

import com.ddd.event_ticketing_platform.catalog.domain.event.model.SessionId;
import com.ddd.event_ticketing_platform.users.domain.model.BuyerId;
import jakarta.persistence.Embedded;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import org.jmolecules.ddd.annotation.AggregateRoot;
import org.jmolecules.ddd.annotation.Identity;

import java.util.List;

@Entity
@AggregateRoot
public class Cart {

    @Identity
    @EmbeddedId
    private CartId id;

    @Embedded
    private BuyerId buyerId;

    @Embedded
    private SessionId sessionId;

    @OneToMany(mappedBy = "cart", orphanRemoval = true)
    private List<CartItem> cartItems;

}
