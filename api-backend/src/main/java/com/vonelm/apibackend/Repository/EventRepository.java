package com.vonelm.apibackend.Repository;

import com.vonelm.apibackend.Model.Event;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface EventRepository extends CrudRepository<Event, Integer> {
    //Status TRUE (1 in MySQL) = Active Event
    @Query(value = "SELECT e FROM Event e WHERE e.status = true")
    Iterable<Event> findAllActiveEvents();

    //STATUS FALSE (0 in MySQL) = Completed Event
    @Query(value = "SELECT e FROM Event e WHERE e.status = false")
    Iterable<Event> findAllCompletedEvents();
}
