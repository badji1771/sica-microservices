package org.formation.service.kafka;

import org.formation.service.dto.journeecompensation.JourneeCompensationProducer;


public interface FermetureJourneeCompensationProducer {
    void sendJourneeCompensation(JourneeCompensationProducer journeeCompensationProducer);
}
