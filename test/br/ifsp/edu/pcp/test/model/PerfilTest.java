package br.ifsp.edu.pcp.test.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import br.ifsp.edu.pcp.model.Perfil;

public class PerfilTest {

	

	@Test
	public void PerfilPossuiDados() {
		Perfil perfil = new Perfil("PCP");
        assertEquals("PCP", perfil.getDescricao());
	}
	
	

}
