package br.ifsp.edu.pcp.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="usuario")
public class Usuario implements Serializable {


	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usr_id")
	private Long id;
	@Column(name = "usr_nome")
	private String nome;
	@Column(name = "usr_login")
	private String login;
	@Column(name = "usr_pwd")
	private String senha;
	
	@ManyToOne
	@JoinColumn(name="usr_perf_id")
	private Perfil perfil;
	
	public Usuario(String nome, String login, String senha, Perfil perfil) {
		this.nome = nome;
		this.login = login;
		this.senha = senha;
		this.perfil = perfil;
	}
	
	public Usuario() {
		
	}
	
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}
	

	public String getSenha() {
		return this.senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public Perfil getPerfil() {
		return this.perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

	public String getNome() {
		return this.nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}


	

}
