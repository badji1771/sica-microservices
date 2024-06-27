package org.formation;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import org.formation.entities.JourneeCompensation;
import org.formation.entities.TypeCompensation;
import org.formation.entities.TypeOperation;
import org.formation.entities.TypeRejet;
import org.formation.repository.JourneeCompensationRepository;
import org.formation.repository.TypeCompensationRepository;
import org.formation.repository.TypeOperationRepository;
import org.formation.repository.TypeRejetRepository;
import org.formation.utils.Utulitaires.ETAT;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import lombok.extern.java.Log;

@Component
@Log
public class DBOperationRunner implements CommandLineRunner {

	private final TypeOperationRepository typeOperationRepository;

	private final TypeCompensationRepository typeCompensationRepository;

	private final TypeRejetRepository typeRejetRepository;

	private final JourneeCompensationRepository jCompensationRepository;

	public DBOperationRunner(TypeOperationRepository typeOperationRepository,
							 TypeCompensationRepository typeCompensationRepository, TypeRejetRepository typeRejetRepository,
							 JourneeCompensationRepository jCompensationRepository) {
		super();
		this.typeOperationRepository = typeOperationRepository;
		this.typeCompensationRepository = typeCompensationRepository;
		this.typeRejetRepository = typeRejetRepository;
		this.jCompensationRepository = jCompensationRepository;
	}

	@Override
	public void run(String... args) throws Exception {

		log.info("----------Start insert Data  into TypeOperation----------------------");
		typeOperationRepository
				.saveAll(Arrays.asList(new TypeOperation("015", "Virement clientèle", ETAT.ACTIVE.name()),
						new TypeOperation("025", "Chèque ", ETAT.ACTIVE.name()),
						new TypeOperation("011", "Virement Banque", ETAT.ACTIVE.name())));

		log.info("----------End insert Data  into TypeOperation----------------------");

		log.info("----------Start insert Data  into TypeCompensation----------------------");
		typeCompensationRepository.saveAll(Arrays.asList(new TypeCompensation("N", "National", ETAT.ACTIVE.name()),
				new TypeCompensation("R", "Regional ", ETAT.ACTIVE.name()),
				new TypeCompensation("ND", "ND", ETAT.DESACTIVE.name())));

		log.info("----------End insert Data  into TypeCompensation----------------------");

		log.info("----------Start insert Data  into typeRejet----------------------");
		TypeRejet tr1 = new TypeRejet();
		TypeRejet tr2 = new TypeRejet();
		TypeRejet tr3 = new TypeRejet();
		tr1.setCode("1");
		tr2.setCode("2");
		tr3.setCode("3");

		tr1.setLibelle("rejet 1");
		tr2.setLibelle("rejet 2");
		tr3.setLibelle("rejet 3");

		typeRejetRepository.saveAll(Arrays.asList(tr1, tr2, tr3));

		log.info("----------End insert Data  into typeRejet----------------------");

		log.info("----------Start insert Data  into JourneeCompensation----------------------");

		JourneeCompensation journeeCompensation = new JourneeCompensation();
		journeeCompensation.setDateJournee(new Date());
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
		Date date = new Date();
		date.setHours(10);
		date.setMinutes(00);

		journeeCompensation.setHeureFermeture(date);
		journeeCompensation.setLibelle("Journéee du  " + dateFormat.format(date));
		journeeCompensation.setTypeCompensation(typeCompensationRepository.findByCode("N"));
		journeeCompensation.setEtat(ETAT.OUVERT.name());
		journeeCompensation = jCompensationRepository.save(journeeCompensation);
		log.info("----------End insert Data  into JourneeCompensation----------------------");

	}

}