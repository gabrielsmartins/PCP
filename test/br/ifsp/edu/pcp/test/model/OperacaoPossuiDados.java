package br.ifsp.edu.pcp.test.model;

import static org.junit.Assert.*;

import org.junit.Test;

import br.ifsp.edu.pcp.model.Operacao;
import br.ifsp.edu.pcp.model.Setor;

public class OperacaoPossuiDados {

	@Test
	public void operacaoPossuiDados() {
		Setor setor = new Setor("USINAGEM");
		Operacao operacao = new Operacao("TORNEAR", "TORNEAR NO COMPRIMENTO", setor );
		assertEquals("TORNEAR",operacao.getDescricao());
		assertEquals("TORNEAR NO COMPRIMENTO",operacao.getInstrucao());
		assertEquals(setor.getDescricao(),operacao.getSetor().getDescricao());
	}

}
