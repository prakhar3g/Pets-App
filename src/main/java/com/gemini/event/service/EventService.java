package com.gemini.event.service;


import com.gemini.event.model.Event;

import com.gemini.event.model.EventOrganiser;
import com.gemini.event.repository.EventRepo;
import com.gemini.event.repository.OrganiserRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import java.util.Date;

import java.util.List;

@Service
public class EventService {

    @Autowired
    EventRepo eventRepository;

    @Autowired
    OrganiserRepo organiserRepository;


    Logger logger = LoggerFactory.getLogger(EventService.class);


    public List<Event> getEvent() {
        List<Event> event = null;
        try {
            event = eventRepository.findAll();
        } catch (Exception e) {
            logger.error("eventRepository.findAll() threw an exception: {}", e.getMessage());
        }
        return event;
    }

    public Event findByEventId(int eventId) {
        Event event = null;
        try {
            event = eventRepository.findByEventId(eventId);
        } catch (Exception e) {
            logger.error("eventRepository.findByEventId() threw an exception, {} ", e.getMessage());
        }
        return event;
    }

    public boolean deleteEvent(int eventId) {
        try {
            Event event = eventRepository.findByEventId(eventId);
            eventRepository.delete(event);
        } catch (Exception e) {
            logger.error("eventRepository.deleteByEventId threw an exception, {} ", e.getMessage());
        }
        return findByEventId(eventId) == null;
    }




    public Event addEvent(String organiserId,String eventName,String eventDescription,Date eventDate,
                          Date endDate,Date createDate,String createdBy ){
        Event new_event=null;
        try{
         new_event=eventRepository.save(new Event(organiserId,eventName,eventDescription,
                 eventDate,endDate,createDate,createdBy));

        EventOrganiser org=organiserRepository.findByOrganiserId(new_event.getOrganiser().getOrganiserId());
        new_event.setOrganiser(org);}
        catch (Exception e){
            logger.error("eventRepository.save threw an exception");
        }

        return new_event;



    }
    public Event save(Event eve) {
        Event event=null;
        try{
            event=eventRepository.save(eve);
        }catch (Exception e){
            logger.error("eventRepository.save threw an exception {}",e.getMessage());

        }
        return event;
    }






}
