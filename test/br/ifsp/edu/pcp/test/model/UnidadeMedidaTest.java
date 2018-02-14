package br.ifsp.edu.pcp.test.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import br.ifsp.edu.pcp.model.UnidadeMedida;

public class UnidadeMedidaTest {

	

	@Test
	public void unidadeMedidaTemDados() {
		UnidadeMedida unidadeMedida = new UnidadeMedida("Unidade", "UN");
		assertEquals("Unidade", unidadeMedida.getDescricao());
		assertEquals("UN", unidadeMedida.getSigla());
	}

}
