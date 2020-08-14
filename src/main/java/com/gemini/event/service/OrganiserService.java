package com.gemini.event.service;

import com.gemini.event.model.EventOrganiser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import  com.gemini.event.repository.OrganiserRepo;

import java.util.List;
@Service
public class OrganiserService {

    @Autowired
    OrganiserRepo organiserRepository;

    Logger logger = LoggerFactory.getLogger(OrganiserService.class);


    public EventOrganiser save(EventOrganiser organiser) {
        EventOrganiser new_organiser = null;
        try {
            new_organiser = organiserRepository.save(organiser);
        } catch (Exception e) {
            logger.error("organiserRepository.save threw an exception");
        }

        return new_organiser;
    }


    public List<EventOrganiser> findAll() {
        List<EventOrganiser> list = null;
        try {
            list = organiserRepository.findAll();
        } catch (Exception e) {
            logger.error("organiserRepository.findAll threw an exception");
        }
        return list;
    }


    public EventOrganiser findOne(String organiserId) {
        EventOrganiser org = null;
        try {
            org = organiserRepository.findById(organiserId).orElse(null);
        } catch (Exception e) {
            logger.error("organiserRepository.findById threw an exception");
        }
        return org;
    }


    public void delete(EventOrganiser org) {
        try {
            organiserRepository.delete(org);
        } catch (Exception e) {
            logger.error("organiserRepository.delete threw an exception");
        }

    }
}



