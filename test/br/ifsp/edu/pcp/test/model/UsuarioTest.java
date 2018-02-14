package br.ifsp.edu.pcp.test.model;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import br.ifsp.edu.pcp.model.Perfil;
import br.ifsp.edu.pcp.model.Usuario;

public class UsuarioTest {

	

	@Test
	public void UsuarioPossuiDados() {
		Perfil perfil = new Perfil("PCP");
		Usuario usuario = new Usuario("Usuario 1","usuario","12345",perfil);
		assertEquals("Usuario 1", usuario.getNome());
		assertEquals("usuario", usuario.getLogin());
		assertEquals("12345", usuario.getSenha());
		assertEquals("PCP", usuario.getPerfil().getDescricao());
	}
	
	

}
