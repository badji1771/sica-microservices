package org.formation.web;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.formation.domain.SoldeCompensation;
import org.formation.service.SoldeCompensationServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.extern.java.Log;

@RestController
@RequestMapping("api/compensations")
@Log
public class CompensationController {

	@Autowired
	SoldeCompensationServiceI SoldeCompensationService;
	@GetMapping("/soldes")
	public ResponseEntity<List<SoldeCompensation>> getSoldeParPeriodeEtParTypeOperation(@RequestParam String periode, @RequestParam( required = false)  String typeOperation ) throws ParseException {

		List<SoldeCompensation> resultat= SoldeCompensationService.findByDateCompenseAndTypeOperation(periode,typeOperation);
		return  new ResponseEntity<List<SoldeCompensation>>(resultat, HttpStatus.OK);

	}

	@GetMapping("/soldes/{codeBanque}")
	public ResponseEntity<List<SoldeCompensation>> getSoldeParPeriodeEtParTypeOpEtBanque(@RequestParam String periode,@RequestParam( required = false)  String typeOperation,
																		  @PathVariable String codeBanque) throws ParseException {
		List<SoldeCompensation> resultat=SoldeCompensationService.findByDateCompenseAndTypeOperationAndCodeBanque(periode,typeOperation,codeBanque);
		return new ResponseEntity<List<SoldeCompensation>>(resultat, HttpStatus.OK);

	}
}
