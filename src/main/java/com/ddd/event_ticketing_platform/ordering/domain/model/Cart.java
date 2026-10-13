package com.ddd.event_ticketing_platform.ordering.domain.model;

import com.ddd.event_ticketing_platform.users.domain.model.BuyerId;
import jakarta.persistence.*;
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

    @OneToMany(mappedBy = "cart", orphanRemoval = true)
    private List<CartItem> cartItems;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
    private List<Hold> holds;

    public Cart(BuyerId buyerId, List<CartItem> cartItems, List<Hold> holds) {
        this.buyerId = buyerId;
        this.cartItems = cartItems;
        this.holds = holds;

        this.holds.forEach(hold -> hold.setCart(this));
    }

    private Cart() {

    }

    public CartId id() {
        return id;
    }

    public BuyerId buyerId() {
        return buyerId;
    }

    public List<CartItem> cartItems() {
        return cartItems;
    }

    public List<Hold> holds() {
        return holds;
    }
}
