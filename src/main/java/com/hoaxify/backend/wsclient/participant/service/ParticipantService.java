package com.hoaxify.backend.wsclient.participant.service;

import com.hoaxify.backend.wsclient.participant.model.Bank;

import java.util.List;

public interface ParticipantService {
    List<Bank> getAllParticipants();
}
