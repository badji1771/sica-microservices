package org.formation.service.typerejet;

import org.formation.service.dto.typerejet.CriteriaTypeRejet;
import org.formation.service.dto.typerejet.TypeRejetRequest;
import org.formation.service.dto.typerejet.TypeRejetResponse;

import java.util.List;

public interface TyperejetService {

    TypeRejetResponse create(TypeRejetRequest request);

    TypeRejetResponse update(TypeRejetRequest request);

    void delete(String code);

    List<TypeRejetResponse> getTypeRejet(CriteriaTypeRejet criteria);
}
