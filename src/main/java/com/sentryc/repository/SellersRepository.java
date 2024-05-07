package com.sentryc.repository;

import com.sentryc.domain.Sellers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * Spring Data JPA repository for the Sellers entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SellersRepository extends JpaRepository<Sellers, UUID>, JpaSpecificationExecutor<Sellers> {
}
