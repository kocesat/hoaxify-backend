package com.hoaxify.backend.outbox.controller;

import com.hoaxify.backend.outbox.model.Announcement;
import com.hoaxify.backend.outbox.service.AnnouncementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("api/v1/announcement")
@RequiredArgsConstructor
public class AnnouncementController {
  private final AnnouncementService announcementService;
  @PostMapping
public ResponseEntity<Announcement> create(@RequestBody @Valid Announcement announcement) {
    final Announcement announcementSaved = announcementService.create(announcement);
    return ResponseEntity.ok(announcementSaved);
  }
}
