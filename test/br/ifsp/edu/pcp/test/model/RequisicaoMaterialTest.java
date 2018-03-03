package br.ifsp.edu.pcp.test.model;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.Test;

import br.ifsp.edu.pcp.model.Material;
import br.ifsp.edu.pcp.model.Perfil;
import br.ifsp.edu.pcp.model.RequisicaoMaterial;
import br.ifsp.edu.pcp.model.SituacaoProduto;
import br.ifsp.edu.pcp.model.UnidadeMedida;
import br.ifsp.edu.pcp.model.Usuario;

public class RequisicaoMaterialTest {
	


	@Test
	public void requisicaoMaterialPossuiDados() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		Perfil perfil = new Perfil("PCP");
		Usuario responsavel = new Usuario("Admin", "admin", "12345", perfil);
		UnidadeMedida unidadeMedida = new UnidadeMedida("UNIDADE", "UN");
		Material material1 = new Material("BARRA AÇO INOX", SituacaoProduto.ATIVO, unidadeMedida, 500.50, 2, 20.00, 15.00);
		Material material2 = new Material("TUBO ALUMINIO",  SituacaoProduto.ATIVO, unidadeMedida, 475.25, 10, 30.00, 2.00);
		
		RequisicaoMaterial requisicaoMaterial = new RequisicaoMaterial(LocalDate.parse("26/02/2018", formatter), responsavel);
		
		
		requisicaoMaterial.adicionarMaterial(material1, 5.00);
		requisicaoMaterial.adicionarMaterial(material2, 8.00);
		assertEquals(LocalDate.now(),requisicaoMaterial.getDataEmissao());
		assertEquals(LocalDate.parse("26/02/2018", formatter),requisicaoMaterial.getPrazo());
		assertEquals(2,requisicaoMaterial.getItens().size());
		assertEquals(responsavel.getLogin(),requisicaoMaterial.getResponsavel().getLogin());
	}

}
