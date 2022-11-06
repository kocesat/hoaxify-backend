package com.hoaxify.backend.soapclient;

import com.hoaxify.backend.common.AppConstants;
import com.hoaxify.backend.soapclient.participant.service.ParticipantService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = AppConstants.PARTICIPANT_CONTROLLER_BASE_PATH, produces = "application/json")
@AllArgsConstructor
public class ParticipantController {
    private ParticipantService participantService;

    @GetMapping
    public ResponseEntity findParticipants() {
        return ResponseEntity.ok(participantService.getAllParticipants());
    }
}
