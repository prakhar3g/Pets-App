package com.gemini.event.repository;

import com.gemini.event.model.Announcement;
import com.gemini.event.model.EventOrganiser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Repository
public interface AnnouncementRepo extends JpaRepository<Announcement,Integer> {



}
