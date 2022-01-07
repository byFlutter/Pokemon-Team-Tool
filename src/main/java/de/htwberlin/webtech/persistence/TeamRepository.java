package de.htwberlin.webtech.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

    @Repository
    public interface TeamRepository extends JpaRepository<TeamEntity, Long> {

        List<TeamEntity> findAllByName(String name);
}
