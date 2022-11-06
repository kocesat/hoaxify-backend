package com.hoaxify.backend.soapclient.participant.service;

import com.hoaxify.backend.soapclient.participant.model.Bank;

import java.util.List;

public interface ParticipantService {
    List<Bank> getAllParticipants();
}
