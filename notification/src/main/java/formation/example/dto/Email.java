package formation.example.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Email {

	@NotNull
	private String objet;
	
	@NotEmpty
	private String[] mListeExpeditor;
	
	 @NotNull
	private String content;
}
