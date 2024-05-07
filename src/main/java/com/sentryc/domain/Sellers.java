package com.sentryc.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sentryc.graphql.enums.SellerState;
import jakarta.persistence.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.UUID;

/**
 * A Sellers.
 */
@Entity
@Table(name = "sellers")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Sellers implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private UUID id;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Size(max = 255)
    @Column(name = "state", length = 255, nullable = false)
    private SellerState state;

    @JsonIgnoreProperties(value = {"sellers"}, allowSetters = true)
    @OneToOne(fetch = FetchType.EAGER, optional = false)
    @NotNull
    @JoinColumn(unique = true)
    private Producers producer;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnoreProperties(value = {"marketplace"}, allowSetters = true)
    private SellerInfos sellerInfo;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public UUID getId() {
        return this.id;
    }

    public Sellers id(UUID id) {
        this.setId(id);
        return this;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public SellerState getState() {
        return this.state;
    }

    public Sellers state(SellerState state) {
        this.setState(state);
        return this;
    }

    public void setState(SellerState state) {
        this.state = state;
    }

    public Producers getProducer() {
        return this.producer;
    }

    public void setProducer(Producers producers) {
        this.producer = producers;
    }

    public Sellers producer(Producers producers) {
        this.setProducer(producers);
        return this;
    }

    public SellerInfos getSellerInfo() {
        return this.sellerInfo;
    }

    public void setSellerInfo(SellerInfos sellerInfos) {
        this.sellerInfo = sellerInfos;
    }

    public Sellers sellerInfo(SellerInfos sellerInfos) {
        this.setSellerInfo(sellerInfos);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Sellers)) {
            return false;
        }
        return getId() != null && getId().equals(((Sellers) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Sellers{" +
                "id=" + getId() +
                ", state='" + getState() + "'" +
                "}";
    }

    public String getSellerName() {
        return this.sellerInfo != null ? this.sellerInfo.getName() : null;
    }

    public String getMarketplaceId() {
        return this.sellerInfo != null ? this.sellerInfo.getMarketplace().getId() : null;
    }

    public String getExternalId() {
        return this.sellerInfo != null ? this.sellerInfo.getExternalId() : null;
    }

    public String getProducerName() {
        return this.producer != null ? this.producer.getName() : null;
    }

    public UUID getProducerId() {
        return this.producer != null ? this.producer.getId() : null;
    }
}
