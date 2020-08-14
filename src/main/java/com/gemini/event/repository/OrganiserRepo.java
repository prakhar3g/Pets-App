package com.gemini.event.repository;

import com.gemini.event.model.Event;
import com.gemini.event.model.EventOrganiser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganiserRepo extends JpaRepository<EventOrganiser, String> {

    @Query(
            value= "select * from organiser  where organiser_id = ?1", nativeQuery = true)
    EventOrganiser findByOrganiserId(String organiserId);


}
