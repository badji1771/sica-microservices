package org.formation.service.operationrejet;

import java.util.ArrayList;
import java.util.List;

import org.formation.entities.OperationRejet;
import org.formation.entities.TypeOperation;
import org.formation.entities.TypeRejet;
import org.formation.mappers.OperationRejetMapper;
import org.formation.repository.OperationRejetRepository;
import org.formation.repository.TypeOperationRepository;
import org.formation.repository.TypeRejetRepository;
import org.formation.service.dto.operationrejet.OperationRejetRequest;
import org.formation.service.dto.operationrejet.OperationRejetResponse;
import org.formation.utils.Utulitaires;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class OperationRejetServiceImpl implements OperationRejetService {
    private final OperationRejetRepository operationRejetRepository;

    private final TypeRejetRepository typeRejetRepository;

    private final TypeOperationRepository typeOperationRepository;

    private final OperationRejetMapper operationRejetMapper;

    public OperationRejetServiceImpl(OperationRejetRepository operationRejetRepository, TypeRejetRepository typeRejetRepository, TypeOperationRepository typeOperationRepository, OperationRejetMapper operationRejetMapper) {
        this.operationRejetRepository = operationRejetRepository;
        this.typeRejetRepository = typeRejetRepository;
        this.typeOperationRepository = typeOperationRepository;

        this.operationRejetMapper = operationRejetMapper;
    }

    @Override
    public OperationRejetResponse create(OperationRejetRequest request) {
        TypeRejet rejet= typeRejetRepository.findById(request.getRejet()).orElseThrow();
        TypeOperation typeOperation=typeOperationRepository.findById(request.getTypeOperation()).orElseThrow();
        OperationRejet operation=OperationRejet.builder().rejet(rejet).typeOperation(typeOperation).etat(String.valueOf(Utulitaires.ETAT.ACTIVE)).build();
        return operationRejetMapper.operationRejetToOperationRejetResponse(operationRejetRepository.save(operation));
    }

    @Override
    public OperationRejetResponse update(OperationRejetRequest request) {
        OperationRejet operation=operationRejetRepository.findById(request.getId()).orElseThrow();
        TypeRejet rejet=typeRejetRepository.findById(request.getRejet()).orElseThrow();
        TypeOperation typeOperation=typeOperationRepository.findByCode(request.getTypeOperation());
        operation.setRejet(rejet);
        operation.setTypeOperation(typeOperation);
        return operationRejetMapper.operationRejetToOperationRejetResponse(operationRejetRepository.save(operation));

    }

    @Override
    public void delete(Long id) {
        OperationRejet operationRejet=operationRejetRepository.findById(id).orElseThrow();
        operationRejetRepository.delete(operationRejet);

    }

    @Override
    public List<OperationRejetResponse> getOperationRejet(OperationRejetRequest criteria) {
        List<OperationRejet> operationRejets=operationRejetRepository.findAll();
        List<OperationRejetResponse> liste= new ArrayList<>();
        if(criteria==null){
            for(OperationRejet operationRejet: operationRejets){
                liste.add(operationRejetMapper.operationRejetToOperationRejetResponse(operationRejet));
            }
        }else{
            if(!criteria.getRejet().isEmpty()){
                operationRejets=  operationRejets.stream().filter(y->y.getRejet().getCode().equals(criteria.getRejet())).toList();
            }
            if(!criteria.getTypeOperation().isEmpty()){
                operationRejets=  operationRejets.stream().filter(y->y.getTypeOperation().getCode().equals(criteria.getTypeOperation())).toList();
            }
            for(OperationRejet operationRejet: operationRejets){
                liste.add(operationRejetMapper.operationRejetToOperationRejetResponse(operationRejet));
            }
        }
        return liste;

    }
}
