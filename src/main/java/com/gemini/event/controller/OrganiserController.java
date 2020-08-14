package com.gemini.event.controller;

import com.gemini.event.model.EventOrganiser;
import com.gemini.event.service.EventService;
import com.gemini.event.service.OrganiserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/organiser")
public class OrganiserController {

    @Autowired
    OrganiserService organiserService;

    Logger logger = LoggerFactory.getLogger(OrganiserController.class);


        @PostMapping
        public EventOrganiser addOrganiser(@Valid @RequestBody EventOrganiser org) {
            EventOrganiser organiser=organiserService.save(org);

            if(organiser == null){
                logger.warn("Organiser not added");
            }

                logger.info("New Organiser added successfully");
                return organiser;




        }

        @GetMapping
        public List<EventOrganiser> getAllOrganisers() {
            return organiserService.findAll();
        }


        @GetMapping("/{organiserId}")
        public ResponseEntity<EventOrganiser> getOrganiserById(@PathVariable(value = "organiserId") String organiserId) {

            EventOrganiser org = organiserService.findOne(organiserId);

            if (org == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok().body(org);

        }

        @DeleteMapping("/{organiserId}")
        public ResponseEntity<EventOrganiser> deleteOrganiser(@PathVariable(value = "organiserId") String organiserId) {

            EventOrganiser org = organiserService.findOne(organiserId);
            if (org == null) {
                return ResponseEntity.notFound().build();
            }
            organiserService.delete(org);

            return ResponseEntity.ok().build();



        }
    }
