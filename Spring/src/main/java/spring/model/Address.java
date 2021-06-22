package spring.model;

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
	
	@Column(name="streetName", nullable = false)
	private String streetName;
	
	@Column(name="number", nullable = false)
	private String number;
	
	@Column(name="complement")
	private String complement;
	
	@Column(name="neighbourhood", nullable = false)
	private String neighbourhood;
	
	@Column(name="city", nullable = false)
	private String city;
	
	@Column(name="state", nullable = false)
	private String state;
	
	@Column(name="country", nullable = false)
	private String country;
	
	@Column(name="zipcode", nullable = false)
	private String zipcode;
	
	@Column(name="latitude")
	private String latitude;
	
	@Column(name="longitude")
	private String longitude;
	
	
	
}
