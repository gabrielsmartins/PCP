package br.ifsp.edu.pcp.test.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import br.ifsp.edu.pcp.model.Operacao;
import br.ifsp.edu.pcp.model.Setor;

public class OperacaoTest {

	

	@Test
	public void operacaoPossuiDados() {
		Setor setor = new Setor("Corte");
		Operacao operacao = new Operacao("Cortar","Cortar no comprimento",setor);
		assertEquals("Cortar",operacao.getDescricao());
		assertEquals("Cortar no comprimento",operacao.getInstrucao());
		assertEquals("Corte",operacao.getSetor().getDescricao());
		
	}

}
