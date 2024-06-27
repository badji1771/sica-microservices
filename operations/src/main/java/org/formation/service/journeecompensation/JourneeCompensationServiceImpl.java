package org.formation.service.journeecompensation;

import org.formation.entities.JourneeCompensation;
import org.formation.entities.Operation;
import org.formation.entities.TypeCompensation;
import org.formation.mappers.JourneeCompensationMapper;
import org.formation.repository.JourneeCompensationRepository;
import org.formation.repository.OperationRepository;
import org.formation.repository.TypeCompensationRepository;
import org.formation.service.dto.journeecompensation.JourCompensationRequest;
import org.formation.service.dto.journeecompensation.JourCompensationResponse;
import org.formation.service.dto.journeecompensation.JourneeCompensationConsumer;
import org.formation.service.dto.journeecompensation.JourneeCompensationProducer;
import org.formation.service.kafka.FermetureJourneeCompensationProducer;
import org.formation.utils.Utulitaires;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class JourneeCompensationServiceImpl implements JourneeCompensationService {

    private final JourneeCompensationRepository journeeCompensationRepository;

    private final JourneeCompensationMapper journeeCompensationMapper;

    private final TypeCompensationRepository typeCompensationRepository;

    private final OperationRepository operationRepository;

    private final FermetureJourneeCompensationProducer journeeCompensationProducer;



    public JourneeCompensationServiceImpl(JourneeCompensationRepository journeeCompensationRepository, JourneeCompensationMapper journeeCompensationMapper, TypeCompensationRepository typeCompensationRepository, OperationRepository operationRepository, FermetureJourneeCompensationProducer journeeCompensationProducer) {
        this.journeeCompensationRepository = journeeCompensationRepository;
        this.journeeCompensationMapper = journeeCompensationMapper;
        this.typeCompensationRepository = typeCompensationRepository;

        this.operationRepository = operationRepository;
        this.journeeCompensationProducer = journeeCompensationProducer;
    }

    @Override
    public JourCompensationResponse create(JourCompensationRequest request) {
        JourneeCompensation jc=journeeCompensationMapper.jourCompensationRequestToJourneeCompensation(request);
        jc.setEtat(String.valueOf(Utulitaires.ETAT.INITIE));
        TypeCompensation tc=typeCompensationRepository.findByCode(request.getTypeCompensationCode());
        jc.setTypeCompensation(tc);
        return journeeCompensationMapper.journeeCompensationToJourCompensationResponse(journeeCompensationRepository.save(jc));

    }

    @Override
    public JourCompensationResponse update(JourCompensationRequest request) {
        JourneeCompensation jc=journeeCompensationRepository.findById(request.getId()).orElseThrow();
        JourneeCompensation jcRecu=journeeCompensationMapper.jourCompensationRequestToJourneeCompensation(request);
        TypeCompensation tc=typeCompensationRepository.findByCode(request.getTypeCompensationCode());
        jcRecu.setTypeCompensation(tc);
        jcRecu.setEtat(jc.getEtat());
        jcRecu.setId(jc.getId());
        return journeeCompensationMapper.journeeCompensationToJourCompensationResponse(journeeCompensationRepository.save(jc));

    }

    @Override
    public void delete(Long id) {
        JourneeCompensation jc=journeeCompensationRepository.findById(id).orElseThrow();
        List<Operation> operations=operationRepository.getOperationsParJourneeCompense(id);
        operationRepository.deleteAll(operations);
        journeeCompensationRepository.delete(jc);

    }

    @Override
    public JourCompensationResponse ouvrirJournee(Long id) {
        JourneeCompensation jc=journeeCompensationRepository.findById(id).orElseThrow();
        jc.setEtat(String.valueOf(Utulitaires.ETAT.OUVERT));
        return journeeCompensationMapper.journeeCompensationToJourCompensationResponse(journeeCompensationRepository.save(jc));

    }

    @Override
    public JourCompensationResponse fermerJournee(Long id) {
        JourneeCompensation jc=journeeCompensationRepository.findById(id).orElseThrow();
        journeeCompensationProducer.sendJourneeCompensation(JourneeCompensationProducer.builder().id(jc.getId()).dateJournee(jc.getDateJournee()).typeCompensation(jc.getTypeCompensation().getCode()).etat(jc.getEtat()).build());
        jc.setEtat(String.valueOf(Utulitaires.ETAT.ENCOURS));
        return journeeCompensationMapper.journeeCompensationToJourCompensationResponse(journeeCompensationRepository.save(jc));

    }

    @Override
    public void updateEtat(JourneeCompensationConsumer journeeCompensationConsumer) {
        JourneeCompensation jc=journeeCompensationRepository.findById(journeeCompensationConsumer.getId()).orElseThrow();
        jc.setEtat(journeeCompensationConsumer.getEtat());
        journeeCompensationMapper.journeeCompensationToJourCompensationResponse(journeeCompensationRepository.save(jc));
    }


}
