package org.baldzhiyski.kafkaconsumerdatabase.repository;

import org.baldzhiyski.kafkaconsumerdatabase.entity.WikiData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WikiDataRepository extends JpaRepository<WikiData,Long> {
}
