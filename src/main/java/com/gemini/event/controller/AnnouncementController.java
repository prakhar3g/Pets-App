package com.gemini.event.controller;

import com.gemini.event.dto.UpdateAnnouncement;
import com.gemini.event.model.Announcement;
import com.gemini.event.service.AnnouncementService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/announcement")
public class AnnouncementController {
    @Autowired
    AnnouncementService announcementService;

    Logger logger = LoggerFactory.getLogger(AnnouncementController.class);

    @PostMapping
    public BaseResponse addAnnouncement(@Valid @RequestBody Announcement ann,
                                              HttpServletResponse response) throws InterruptedException{
        Announcement new_ann=announcementService.save(ann);
        if(new_ann == null) {
            logger.warn("announcement not added");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return new BaseResponse("AnnouncementId is wrong", HttpStatus.BAD_REQUEST,null);
        }else {
            logger.info("announcement added : {}",new_ann);
            response.setStatus(HttpServletResponse.SC_CREATED);
            return new BaseResponse("Announcement Created",HttpStatus.CREATED,new_ann);
        }
    }


    @GetMapping
    public BaseResponse getAllAnnouncements(){
        List<Announcement> ann = (List<Announcement>) announcementService.findAll();

        if(ann == null) {
            logger.info("announcements could not be fetched");
            return new BaseResponse("announcement could not be fetched", HttpStatus.INTERNAL_SERVER_ERROR,
                    null);
        }

        if(ann.isEmpty()) {
            logger.info("announcements not found");
            return new BaseResponse("announcements not found", HttpStatus.NOT_FOUND,null);
        }

        logger.info("announcements retrieved");
        return new BaseResponse("Success",HttpStatus.OK, ann);
    }



    @GetMapping(value = "/{annId}")
    public BaseResponse getAnnouncementByAnnId(@PathVariable(value = "annId") int annId){
        Announcement new_ann = announcementService.findOne(annId);

        if(new_ann == null) {
            logger.info("announcement not found.");
            return new BaseResponse("announcement not found", HttpStatus.NOT_FOUND,null);
        }

        logger.info("User {}, retrieved",annId);
        return new BaseResponse("Success",HttpStatus.OK, new_ann);
    }

    @PutMapping
    public BaseResponse updateAnnouncement(@RequestParam(value = "annId") int annId,
                                           @Valid @RequestBody UpdateAnnouncement ann) {

        Announcement announcement = announcementService.findOne(annId);
        if (announcement == null) {
            logger.warn("announcement does not exist");
            return new BaseResponse("announcement does not exist",HttpStatus.NOT_FOUND,null);
        }
        else{

            announcement.setAnnouncementText(ann.getAnnouncementText());
            announcement.setCreateDate(ann.getCreateDate());
            announcement.setCreatedBy(ann.getCreatedBy());


            Announcement updateAnn = announcementService.save(announcement);
            logger.info("announcement updated successfully.");
            return new BaseResponse("announcement updated successfully",HttpStatus.OK,updateAnn);

        }
    }

    @DeleteMapping
    public BaseResponse deleteAnnouncement(@RequestParam(value = "annId") int annId) {

        Announcement ann = announcementService.findOne(annId);
        if(null == ann) {
            logger.warn("announcement does not exist");
            return new BaseResponse("announcement does not exist",HttpStatus.NOT_FOUND,null);
        }

        if(announcementService.deleteAnn(annId)) {
            logger.info("user deleted successfully.");
            return new BaseResponse("announcement deleted successfully",HttpStatus.OK,true);
        }
        else {
            logger.error("user: {} could not be deleted",annId);
            return new BaseResponse("announcement could not be deleted",HttpStatus.INTERNAL_SERVER_ERROR,
                    false);
        }
    }






}












