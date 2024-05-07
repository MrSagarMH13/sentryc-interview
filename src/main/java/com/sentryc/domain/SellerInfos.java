package com.sentryc.domain;

import jakarta.persistence.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.UUID;

/**
 * A SellerInfos.
 */
@Entity
@Table(name = "seller_infos")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class SellerInfos implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private UUID id;

    @NotNull
    @Size(max = 2048)
    @Column(name = "name", length = 2048, nullable = false)
    private String name;

    @Size(max = 2048)
    @Column(name = "url", length = 2048)
    private String url;

    @Size(max = 255)
    @Column(name = "country", length = 255)
    private String country;

    @Size(max = 255)
    @Column(name = "external_id", length = 255)
    private String externalId;

    @ManyToOne(fetch = FetchType.EAGER)
    private Marketplaces marketplace;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public UUID getId() {
        return this.id;
    }

    public SellerInfos id(UUID id) {
        this.setId(id);
        return this;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public SellerInfos name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return this.url;
    }

    public SellerInfos url(String url) {
        this.setUrl(url);
        return this;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCountry() {
        return this.country;
    }

    public SellerInfos country(String country) {
        this.setCountry(country);
        return this;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getExternalId() {
        return this.externalId;
    }

    public SellerInfos externalId(String externalId) {
        this.setExternalId(externalId);
        return this;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public Marketplaces getMarketplace() {
        return this.marketplace;
    }

    public void setMarketplace(Marketplaces marketplaces) {
        this.marketplace = marketplaces;
    }

    public SellerInfos marketplace(Marketplaces marketplaces) {
        this.setMarketplace(marketplaces);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SellerInfos)) {
            return false;
        }
        return getId() != null && getId().equals(((SellerInfos) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SellerInfos{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", url='" + getUrl() + "'" +
            ", country='" + getCountry() + "'" +
            ", externalId='" + getExternalId() + "'" +
            "}";
    }
}
