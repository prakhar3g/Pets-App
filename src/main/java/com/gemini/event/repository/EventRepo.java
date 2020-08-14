package com.gemini.event.repository;

import com.gemini.event.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepo extends JpaRepository<Event, Integer> {

    @Query(
            value= "select * from event where event_id = ?1", nativeQuery = true)
    Event findByEventId(int eventId);


}
