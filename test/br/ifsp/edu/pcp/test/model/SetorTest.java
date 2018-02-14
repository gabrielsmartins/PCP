package br.ifsp.edu.pcp.test.model;

import static org.junit.Assert.*;

import org.junit.Test;

import br.ifsp.edu.pcp.model.Setor;

public class SetorTest {

	@Test
	public void setorPossuiDados() {
		Setor setor = new Setor("USINAGEM");
		assertEquals("USINAGEM", setor.getDescricao());
	}

}
