package com.gemini.event.service;

import com.gemini.event.model.Announcement;
import com.gemini.event.model.EventOrganiser;
import com.gemini.event.repository.AnnouncementRepo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;




@Service
public class AnnouncementService {

    @Autowired
    AnnouncementRepo annRepository;


    Logger logger = LoggerFactory.getLogger(AnnouncementService.class);


    public List<Announcement> findAll() {
        List<Announcement> announcement = null;
        try {
            announcement = annRepository.findAll();
        } catch (Exception e) {
            logger.error("annRepository.findAll() threw an exception: {}", e.getMessage());
        }
        return announcement;
    }


    public Announcement findOne(int annId) {
        Announcement announcement = null;
        try {
            announcement = annRepository.findById(annId).orElse(null);
        } catch (Exception e) {
            logger.error("annRepository.findById threw an exception, {} ", e.getMessage());
        }
        return announcement;
    }

    public Announcement save(Announcement ann) {
        Announcement announcement=null;
        try{
            announcement=annRepository.save(ann);
        }catch (Exception e){
            logger.error("annRepository.save threw an exception {}",e.getMessage());

        }
        return announcement;
    }



    public boolean deleteAnn(int annId) {
        try {
            Announcement ann = annRepository.findById(annId).orElse(null);
            annRepository.delete(ann);
        } catch (Exception e) {
            logger.error("annRepository.deleteAnn threw an exception, {} ", e.getMessage());
        }
        return true;
    }




}