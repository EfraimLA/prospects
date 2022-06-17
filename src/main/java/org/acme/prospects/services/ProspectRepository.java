package org.acme.prospects.services;

import org.acme.prospects.models.Prospect;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProspectRepository extends JpaRepository<Prospect, Long> {
}
