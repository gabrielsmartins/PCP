package br.ifsp.edu.pcp.test.model;

import static org.junit.Assert.assertEquals;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.junit.Test;

import br.ifsp.edu.pcp.model.Operacao;
import br.ifsp.edu.pcp.model.Roteiro;
import br.ifsp.edu.pcp.model.Setor;

public class RoteiroTest {

	@Test
	public void roteiroPossuiDados() {
		Setor setor = new Setor("USINAGEM");
		Operacao operacao = new Operacao("TORNEAR", "TORNEAR CONFORME DESENHO", setor);
		Roteiro roteiro = new Roteiro();
		roteiro.setOperacao(operacao);

		LocalTime tempoSetup = LocalTime.of(5, 40, 20);
		LocalTime tempoProducao = LocalTime.of(10, 50, 10);
		LocalTime tempoFinalizacao = LocalTime.of(02, 10, 05);

		roteiro.setTempoSetup(tempoSetup);
		roteiro.setTempoProducao(tempoProducao);
		roteiro.setTempoFinalizacao(tempoFinalizacao);

		assertEquals(tempoSetup, roteiro.getTempoSetup());
		assertEquals(tempoProducao, roteiro.getTempoProducao());
		assertEquals(tempoFinalizacao, roteiro.getTempoFinalizacao());

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("hh:mm:ss");
		System.out.println("Tempo Setup: " + tempoSetup.format(dtf));
		System.out.println("Tempo Produção: " + tempoProducao.format(dtf));
		System.out.println("Tempo Finalização: " + tempoFinalizacao.format(dtf));

	}

}
