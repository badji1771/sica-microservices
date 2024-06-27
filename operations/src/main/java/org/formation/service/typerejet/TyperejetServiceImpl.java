package org.formation.service.typerejet;

import java.util.ArrayList;
import java.util.List;
import org.formation.entities.TypeRejet;
import org.formation.mappers.TypeRejetMapper;
import org.formation.repository.TypeRejetRepository;
import org.formation.service.dto.typerejet.CriteriaTypeRejet;
import org.formation.service.dto.typerejet.TypeRejetRequest;
import org.formation.service.dto.typerejet.TypeRejetResponse;
import org.formation.utils.Utulitaires;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TyperejetServiceImpl implements TyperejetService {

    private final TypeRejetRepository typeRejetRepository;
    private final TypeRejetMapper typeRejetMapper;


    public TyperejetServiceImpl(TypeRejetRepository typeRejetRepository, TypeRejetMapper typeRejetMapper) {
        this.typeRejetRepository = typeRejetRepository;
        this.typeRejetMapper = typeRejetMapper;
    }

    @Override
    public TypeRejetResponse create(TypeRejetRequest request) {
        TypeRejet rejet=typeRejetMapper.typeRejetRequestToTypeRejet(request);
        rejet.setEtat(String.valueOf(Utulitaires.ETAT.ACTIVE));
        return typeRejetMapper.typeRejetToTypeRejetResponse(typeRejetRepository.save(rejet));

    }

    @Override
    public TypeRejetResponse update(TypeRejetRequest request) {
        TypeRejet rejet=typeRejetRepository.findById(request.getCode()).orElseThrow();
        TypeRejet rejetRecu=typeRejetMapper.typeRejetRequestToTypeRejet(request);
        rejetRecu.setCode(rejet.getCode());
        return typeRejetMapper.typeRejetToTypeRejetResponse(typeRejetRepository.save(rejetRecu));

    }

    @Override
    public void delete(String code) {
        TypeRejet rejet=typeRejetRepository.findById(code).orElseThrow();
        typeRejetRepository.delete(rejet);

    }

    @Override
    public List<TypeRejetResponse> getTypeRejet(CriteriaTypeRejet criteria) {
        List<TypeRejet> types=typeRejetRepository.findAll();
        List<TypeRejetResponse> liste=new ArrayList<>();
        if(criteria==null){
            for(TypeRejet typeRejet: types){
                liste.add(typeRejetMapper.typeRejetToTypeRejetResponse(typeRejet));
            }
        }else{
            assert criteria.getType() != null;
            if(!criteria.getType().isEmpty()){
                types=  types.stream().filter(y->y.getType().equals(criteria.getType())).toList();
            }
            assert criteria.getCode() != null;
            if(!criteria.getCode().isEmpty()){
                types= types.stream().filter(y->y.getCode().equals(criteria.getCode())).toList();
            }
            assert criteria.getLibelle() != null;
            if(!criteria.getLibelle().isEmpty()){
                types=  types.stream().filter(y->y.getLibelle().equals(criteria.getLibelle())).toList();
            }
            for(TypeRejet typeRejet: types){
                liste.add(typeRejetMapper.typeRejetToTypeRejetResponse(typeRejet));
            }
        }
        return liste;

    }
}
