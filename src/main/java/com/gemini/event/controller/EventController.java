package com.gemini.event.controller;

import com.gemini.event.dto.EventDetails;
import com.gemini.event.dto.UpdateEvent;
import com.gemini.event.model.Event;

import com.gemini.event.service.EventService;
import com.gemini.event.service.OrganiserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import java.util.List;


@RestController
@RequestMapping(value = "/event")
public class EventController {

     Logger logger = LoggerFactory.getLogger(EventController.class);

    @Autowired
     EventService eventService;

    @Autowired
    OrganiserService organiserService;


    @GetMapping
    public BaseResponse getAllEvents(){
        List<Event> event = (List<Event>) eventService.getEvent();

        if(event == null) {
            logger.info("events could not be fetched");
            return new BaseResponse("events could not be fetched", HttpStatus.INTERNAL_SERVER_ERROR,null);
        }

        if(event.isEmpty()) {
            logger.info("events not found");
            return new BaseResponse("events not found", HttpStatus.NOT_FOUND,null);
        }

        logger.info("Events retrieved");
        return new BaseResponse("Success",HttpStatus.OK, event);
    }

    @GetMapping(value = "/{eventId}")
    public BaseResponse getEventByEventId(@PathVariable(value = "eventId") int eventId){
        Event event = eventService.findByEventId(eventId);

        if(event == null) {
            logger.info("event not found.");
            return new BaseResponse("Event not found", HttpStatus.NOT_FOUND,null);
        }

        logger.info("event with event id {}, retrieved",eventId);
        return new BaseResponse("Success",HttpStatus.OK, event);
    }

    @DeleteMapping
    public BaseResponse deleteEvent(@RequestParam(value = "eventId") int eventId) {

    	Event event = eventService.findByEventId(eventId);
   	    if(null == event) {
    		logger.warn("event does not exist");
    		return new BaseResponse("event does not exist",HttpStatus.NOT_FOUND,null);
    	}

    	if(eventService.deleteEvent(eventId)) {
    		logger.info("event deleted successfully.");
    		return new BaseResponse("Event deleted successfully",HttpStatus.OK,true);
    	}
    	else {
    		logger.error("event: {} could not be deleted",eventId);
    		return new BaseResponse("event could not be deleted",HttpStatus.INTERNAL_SERVER_ERROR,
    				false);
    	}
    }

    @RequestMapping(
    	     produces = {"application/json"},
    	     consumes = {"multipart/form-data"},
    	     method=RequestMethod.POST)
    public BaseResponse addEvent(EventDetails eve,
    HttpServletResponse response)
    {

        Event new_event = eventService.addEvent(eve.getOrganiserId(),eve.getEventName(),eve.getEventDescription(),
                eve.getEventDate(),eve.getEndDate(),eve.getCreateDate(),eve.getCreatedBy());

        if(new_event == null) {
            logger.warn("event not added");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return new BaseResponse("organiserId is wrong", HttpStatus.BAD_REQUEST,null);
        }else {
            logger.info("event added : {}",new_event);
            response.setStatus(HttpServletResponse.SC_CREATED);
            return new BaseResponse("Event Created",HttpStatus.CREATED,new_event);
        }
    }
    @PutMapping
    public BaseResponse updateEvent(@RequestParam(value = "eventId") int eventId,
                                    @Valid @RequestBody UpdateEvent eve){
        Event event= eventService.findByEventId(eventId);
        if(event==null){
            logger.warn("event does not exist");
            return new BaseResponse("event does not exist",HttpStatus.NOT_FOUND,null);
        }

        else{

            event.setEventName(eve.getEventName());
            event.setEventDescription(eve.getEventDescription());
            event.setEventDate(eve.getEventDate());
            event.setEndDate(eve.getEndDate());
            event.setCreatedBy(eve.getCreatedBy());


            Event updatedevent=eventService.save(event);
            logger.info("event updated successfully.");
            return new BaseResponse("Event updated successfully",HttpStatus.OK,updatedevent);
        }

    }








}
