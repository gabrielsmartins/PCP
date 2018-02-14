package br.ifsp.edu.pcp.dao;

import java.io.Serializable;

import br.ifsp.edu.pcp.model.Operacao;

public class OperacaoDAO extends GenericDAO<Operacao, Serializable> {
	
   public OperacaoDAO() {
	   super(Operacao.class);
   }

}
