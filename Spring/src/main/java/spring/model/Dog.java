package spring.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "dog")
public class Dog {

	private long id;
	private String nome;
	private String raca;
	private String nomeDono;
	
	public Dog() {
		
	}
	
	public Dog(String nome, String raca, String nomeDono) {
		this.nome = nome;
		this.raca = raca;
		this.nomeDono = nomeDono;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	@Column(name = "nome", nullable = false)
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@Column(name = "raca", nullable = false)
	public String getRaca() {
		return raca;
	}
	public void setRaca(String raca) {
		this.raca = raca;
	}
	
	@Column(name = "nome_dono", nullable = false)
	public String getNomeDono() {
		return nomeDono;
	}
	public void setNomeDono(String nomeDono) {
		this.nomeDono = nomeDono;
	}

	@Override
	public String toString() {
		return "Dog [id=" + id + ", nome=" + nome + ", raca=" + raca + ", nomeDono=" + nomeDono
				+ "]";
	}
	
}
