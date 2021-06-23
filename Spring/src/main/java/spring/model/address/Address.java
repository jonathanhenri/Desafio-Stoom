package spring.model.address;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@AllArgsConstructor //cria automaticamente um construtor com todas os atributos da classe como argumento.
@NoArgsConstructor //cria automaticamente um construtor vazio (sem argumentos).
@Data //cria automaticamente os métodos toString, equals, hashCode, getters e setters.
@Entity
@Table(name = "address")
public class Address {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotBlank(message = "{streetName.not.blank}")
	@Column(name="streetName", nullable = false)
	private String streetName;
	
	@NotBlank(message = "{number.not.blank}")
	@Column(name="number",nullable = false)
	private String number;
	
	@Column(name="complement")
	private String complement;
	
	@NotBlank(message = "{neighbourhood.not.blank}")
	@Column(name="neighbourhood", nullable = false)
	private String neighbourhood;
	
	@NotBlank(message = "{city.not.blank}")
	@Column(name="city", nullable = false)
	private String city;
	
	@NotBlank(message = "{state.not.blank}")
	@Column(name="state", nullable = false)
	private String state;
	
	@NotBlank(message = "{country.not.blank}")
	@Column(name="country", nullable = false)
	private String country;
	
	@NotBlank(message = "{zipcode.not.blank}")
	@Column(name="zipcode", nullable = false)
	private String zipcode;
	
	@Column(name="latitude")
	private String latitude;
	
	@Column(name="longitude")
	private String longitude;
	
	
	
}
