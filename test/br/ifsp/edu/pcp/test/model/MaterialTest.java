package br.ifsp.edu.pcp.test.model;

import static org.junit.Assert.*;

import org.junit.Test;

import br.ifsp.edu.pcp.model.Material;
import br.ifsp.edu.pcp.model.SituacaoProduto;
import br.ifsp.edu.pcp.model.UnidadeMedida;

public class MaterialTest {

	@Test
	public void materialPossuiDados() {
		UnidadeMedida unidadeMedida = new UnidadeMedida("Unidade","UN");
		Material material = new Material("Chapa ACO #20",SituacaoProduto.ATIVO,unidadeMedida,50.00,2,50.00,25.00);
		material.setCodigoInterno("CHA-50");
		material.setPeso(0.5);
		material.setAltura(2.0);
		material.setComprimento(500.00);
		material.setLargura(200.00);
		assertEquals("Chapa ACO #20", material.getDescricao());
		assertEquals("CHA-50",material.getCodigoInterno());
		assertEquals("Unidade", material.getUnidadeMedida().getDescricao());
		assertEquals("UN", material.getUnidadeMedida().getSigla());
		assertEquals(50.00, material.getValorUnitario(),0);
		assertEquals(2, material.getLeadTime(),0);
		assertEquals(50.00, material.getQuantidadeEstoque(),0);
		assertEquals(25.00, material.getQuantidadeMinima(),0);
		assertEquals(0.5, material.getPeso(),0);
		assertEquals(2.0, material.getAltura(),0);
		assertEquals(500.0, material.getComprimento(),0);
		assertEquals(200.0, material.getLargura(),0);
		assertEquals(SituacaoProduto.ATIVO, material.getSituacao());
	}

}
