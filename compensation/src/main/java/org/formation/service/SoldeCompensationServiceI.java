package org.formation.service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.List;

import org.formation.domain.SoldeCompensation;
import org.formation.dto.JourneeDto;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.formation.dto.operation.Operation;

public interface SoldeCompensationServiceI {


    List<SoldeCompensation> findByDateCompenseAndTypeOperation(String dateCompense, String typeOperation) throws ParseException;

    List<SoldeCompensation> findByDateCompenseAndTypeOperationAndCodeBanque(String dateCompense,  String typeOperation,String codeBanque) throws ParseException;

    BigDecimal getMontantByBanqueAndTypeOperationAndStatusAndSens(String codeBanque, String typeOperation, String etat, String sens, List<Operation> operationList) throws ParseException;


    SoldeCompensation saveSoldeCompensation(SoldeCompensation soldeCompensation)throws ParseException;

    List<SoldeCompensation>  saveAll(List<SoldeCompensation> soldeCompensationList)throws ParseException;

	List<SoldeCompensation> calculSoldeCompense(JourneeDto journee) throws JsonProcessingException, ParseException, Exception;
}
