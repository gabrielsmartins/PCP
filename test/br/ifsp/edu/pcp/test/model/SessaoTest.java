package br.ifsp.edu.pcp.test.model;

import static org.junit.Assert.assertEquals;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import br.ifsp.edu.pcp.model.Perfil;

import br.ifsp.edu.pcp.model.Usuario;
import br.ifsp.edu.pcp.util.Sessao;

public class SessaoTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Perfil perfil = new Perfil("PCP");
		Usuario usuario = new Usuario("Usuario", "usuario", "12345", perfil);
		Sessao.getInstance().setUsuario(usuario);
	}


	@Test
	public void obterUsuarioDaSessao() {
		Usuario usuario = Sessao.getInstance().getUsuario();
		assertEquals("Usuario", usuario.getNome());
		assertEquals("usuario", usuario.getLogin());
		assertEquals("12345", usuario.getSenha());
		assertEquals("PCP", usuario.getPerfil().getDescricao());
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		Sessao.getInstance().encerrarSessao();
		
	}

}
