package br.ifsp.edu.pcp.test.model;

import static org.junit.Assert.*;

import org.junit.Test;

import br.ifsp.edu.pcp.model.Recurso;
import br.ifsp.edu.pcp.model.Setor;

public class RecursoTest {

	@Test
	public void recursoPossuiDados() {
		Setor setor = new Setor("USINAGEM");
		Recurso recurso = new Recurso("TORNO HORIZONTAL",setor);
		assertEquals("TORNO HORIZONTAL",recurso.getDescricao());
		assertEquals(setor.getDescricao(),recurso.getSetor().getDescricao());
		
	}

}
