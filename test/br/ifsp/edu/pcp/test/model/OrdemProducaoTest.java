package br.ifsp.edu.pcp.test.model;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.Test;

import br.ifsp.edu.pcp.model.OrdemProducao;
import br.ifsp.edu.pcp.model.Perfil;
import br.ifsp.edu.pcp.model.Produto;
import br.ifsp.edu.pcp.model.SituacaoProduto;
import br.ifsp.edu.pcp.model.UnidadeMedida;
import br.ifsp.edu.pcp.model.Usuario;

public class OrdemProducaoTest {

	@Test
	public void ordemProducaoPossuiDados() {
		Perfil perfil = new Perfil("PCP");
		Usuario usuario = new Usuario("Usuario", "usr", "12345", perfil);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		UnidadeMedida unidadeMedida = new UnidadeMedida("UNIDADE", "UN");
		Produto produto = new Produto("MESA TUBULAR", SituacaoProduto.ATIVO, unidadeMedida, 500.50, 2, 10.00, 5.00);
		OrdemProducao ordemProducao = new OrdemProducao(produto,LocalDate.parse("23/02/2018",formatter),new Usuario("Admin", "admin", "12345", new Perfil("PCP")));
		assertEquals(LocalDate.now(),ordemProducao.getDataEmissao());
		assertEquals(LocalDate.parse("23/02/2018",formatter),ordemProducao.getPrazo());
		assertEquals("Usuario",usuario.getNome());	
	}
	
	

}
