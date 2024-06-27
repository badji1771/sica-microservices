package org.formation.entities;



import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Entity

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="type_operation")
public class TypeOperation {

	@Id
	private String code;
	private String libelle;
	private String etat;
	
	
	
}
