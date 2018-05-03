
DROP DATABASE IF EXISTS bdpcp;
CREATE DATABASE bdpcp;
USE bdpcp;

CREATE TABLE PERFIL(
	perf_id BIGINT NOT NULL AUTO_INCREMENT,
    perf_desc VARCHAR(200) NOT NULL,
	CONSTRAINT PK_perfil PRIMARY KEY(perf_id),
    CONSTRAINT UNQ_perfil_desc UNIQUE(perf_desc)
);



CREATE TABLE usuario(
       usr_id BIGINT AUTO_INCREMENT NOT NULL,
       usr_perf_id BIGINT NOT NULL,
       usr_nome VARCHAR(200) NOT NULL,
       usr_login VARCHAR(200) NOT NULL,
       usr_pwd VARCHAR(200) NOT NULL,
       CONSTRAINT PK_usuario PRIMARY KEY(usr_id),
       CONSTRAINT FK_usuario_perfil FOREIGN KEY(usr_perf_id) REFERENCES perfil(perf_id) ON DELETE CASCADE,
       CONSTRAINT UNQ_usuario_nome UNIQUE(usr_nome)
);



CREATE TABLE unidade(
       unid_id BIGINT AUTO_INCREMENT NOT NULL,
       unid_desc VARCHAR(200) NOT NULL,
       unid_sig VARCHAR(3) NOT NULL,
       CONSTRAINT PK_unidade PRIMARY KEY(unid_id),
       CONSTRAINT UNQ_unidade_desc UNIQUE(unid_desc),
       CONSTRAINT UNQ_sig UNIQUE(unid_sig)
);



CREATE TABLE setor(
       setr_id BIGINT AUTO_INCREMENT NOT NULL,
       setr_desc VARCHAR(200) NOT NULL,
       CONSTRAINT PK_setr_id PRIMARY KEY(setr_id),
       CONSTRAINT UNQ_setor_desc UNIQUE(setr_desc)	
);


CREATE TABLE operacao(
       oper_id BIGINT AUTO_INCREMENT NOT NULL,
       oper_desc VARCHAR(200) NOT NULL,
       oper_instr VARCHAR(500) NOT NULL,
       oper_setr_id BIGINT NOT NULL,
       CONSTRAINT PK_oper_id  PRIMARY KEY(oper_id),
       CONSTRAINT FK_operacao_setor FOREIGN KEY(oper_setr_id) REFERENCES setor(setr_id)  ON DELETE CASCADE,
       CONSTRAINT UNQ_operacao_desc UNIQUE(oper_desc)	
);


CREATE TABLE recurso(
       recr_id BIGINT AUTO_INCREMENT NOT NULL,
       recr_desc VARCHAR(200) NOT NULL,
       recr_setr_id BIGINT NOT NULL,
       CONSTRAINT PK_recr_id PRIMARY KEY(recr_id),
       CONSTRAINT FK_recurso_operacao FOREIGN KEY (recr_setr_id) REFERENCES setor(setr_id)  ON DELETE CASCADE,
       CONSTRAINT UNQ_recurso_desc UNIQUE(recr_desc)	
);



CREATE TABLE produto(
       prod_id BIGINT AUTO_INCREMENT NOT NULL,
       prod_cod_intr VARCHAR(500) DEFAULT NULL,
       prod_unid_id BIGINT NOT NULL,
       prod_desc VARCHAR(200) NOT NULL,
       prod_sit VARCHAR(200) NOT NULL,
       prod_peso_kg NUMERIC(15,4) DEFAULT NULL,
       prod_comp_mm NUMERIC(15,4) DEFAULT NULL,
       prod_larg_mm NUMERIC(15,4) DEFAULT NULL,
       prod_alt_mm NUMERIC(15,4) DEFAULT NULL,
       prod_vlr_unit NUMERIC(15,2) NOT NULL,
       prod_lead_time INT NOT NULL,
       prod_qntd_estq NUMERIC(15,4) NOT NULL,
       prod_qntd_min NUMERIC(15,4) NOT NULL,
       prod_tipo VARCHAR(200) NOT NULL,
       CONSTRAINT PK_produto PRIMARY KEY(prod_id),
       CONSTRAINT UNQ_produto_codigo_interno UNIQUE(prod_cod_intr),
       CONSTRAINT FK_produto_unidade FOREIGN KEY(prod_unid_id) REFERENCES unidade(unid_id)  ON DELETE CASCADE,
       CONSTRAINT CHK_produto_situacao CHECK(prod_sit IN('ATIVO','INATIVO','FORA_DE_LINHA'))
);



CREATE TABLE estrutura_produto(
       prod_id BIGINT NOT NULL,
       prod_sub_id BIGINT NOT NULL,
       prod_sub_qntd NUMERIC(15,4) NOT NULL,
       CONSTRAINT PK_estrutura_produto PRIMARY KEY(prod_id,prod_sub_id),
       CONSTRAINT FK_estrutura_produto_produto FOREIGN KEY(prod_id)REFERENCES produto(prod_id)  ON UPDATE CASCADE ON DELETE CASCADE,
       CONSTRAINT FK_estrutura_produto_subproduto FOREIGN KEY(prod_sub_id)REFERENCES produto(prod_id)  ON UPDATE CASCADE ON DELETE CASCADE
);


CREATE TABLE roteiro(
		 rot_prod_id BIGINT NOT NULL,
	     rot_oper_id BIGINT NOT NULL,
	     rot_seq INT NOT NULL,
	     rot_tmp_stp TIME NOT NULL,
	     rot_tmp_prd TIME NOT NULL,
	     rot_tmp_fnl TIME NOT NULL,
		 CONSTRAINT PK_roteiro PRIMARY KEY(rot_prod_id,rot_oper_id,rot_seq),
		 CONSTRAINT FK_roteiro_produto FOREIGN KEY(rot_prod_id) REFERENCES produto(prod_id) ON UPDATE CASCADE ON DELETE CASCADE,
		 CONSTRAINT FK_roteiro_operacao FOREIGN KEY(rot_oper_id) REFERENCES operacao(oper_id) ON UPDATE CASCADE ON DELETE CASCADE
);


CREATE TABLE ordem_producao(
	     ord_id BIGINT AUTO_INCREMENT NOT NULL,
		 ord_prod_id BIGINT NOT NULL,
	     ord_dt_emi TIMESTAMP  DEFAULT CURRENT_TIMESTAMP,
	     ord_prazo  DATE NOT NULL,
	     ord_usr_id BIGINT NOT NULL,
	     ord_dt_concl TIMESTAMP ,
	     ord_status VARCHAR(500) NOT NULL DEFAULT 'EMITIDA',
	     CONSTRAINT PK_ordem_producao PRIMARY KEY(ord_prod_id),
	     CONSTRAINT FK_ordem_producao_produto FOREIGN KEY(ord_id) REFERENCES produto(prod_id),
	     CONSTRAINT FK_ordem_producao_usuario FOREIGN KEY(ord_usr_id) REFERENCES usuario(usr_id),
	     CONSTRAINT CHK_ordem_producao_status CHECK(ord_status IN('EMITIDA','INICIADA','ENCERRADA','CANCELADA'))     
);



CREATE TABLE requisicao_material(
	     rm_id BIGINT AUTO_INCREMENT NOT NULL,
	     rm_dt_emi TIMESTAMP  DEFAULT CURRENT_TIMESTAMP,
	     rm_prazo  DATE NOT NULL,
	     rm_usr_id BIGINT NOT NULL,
	     rm_dt_concl TIMESTAMP ,
	     rm_status VARCHAR(500) NOT NULL DEFAULT 'EMITIDA',
	     CONSTRAINT PK_requisicao_material PRIMARY KEY(rm_id),
	     CONSTRAINT FK_requisicao_material_usuario FOREIGN KEY(rm_usr_id) REFERENCES usuario(usr_id),
	     CONSTRAINT CHK_requisicao_material_status CHECK(rm_status IN('EMITIDA','CONCLUIDA PARCIAL','CONCLUIDA TOTAL','CANCELADA'))
	     
);



CREATE TABLE requisicao_material_detalhe(
	     rm_id BIGINT NOT NULL,
	     rm_prod_id BIGINT NOT NULL,
	     rm_prod_qntd NUMERIC(15,2) NOT NULL,
	     CONSTRAINT PK_requisicao_material_detalhe PRIMARY KEY(rm_id,rm_prod_id),
	     CONSTRAINT FK_requisicao_material_detalhe_requisicao FOREIGN KEY(rm_id) REFERENCES requisicao_material(rm_id) ON UPDATE CASCADE ON DELETE CASCADE,
	     CONSTRAINT FK_requisicao_material_detalhe_produto FOREIGN KEY(rm_prod_id) REFERENCES produto(prod_id) ON UPDATE CASCADE ON DELETE CASCADE
);





SELECT * FROM roteiro;
SELECT * FROM produto;
SELECT * FROM estrutura_produto;
SELECT * FROM setor;
SELECT * FROM operacao;
SELECT * FROM unidade;


INSERT INTO perfil(perf_desc)VALUES('PCP');
INSERT INTO perfil(perf_desc)VALUES('PROGRAMADOR PCP');
INSERT INTO perfil(perf_desc)VALUES('GERENTE PCP');
INSERT INTO perfil(perf_desc)VALUES('PRODUCAO');
INSERT INTO perfil(perf_desc)VALUES('ALMOXARIFADO');
INSERT INTO perfil(perf_desc)VALUES('ENGENHARIA');

INSERT INTO usuario(usr_perf_id,usr_nome,usr_login,usr_pwd)VALUES(1,'Gabriel Martins','admin',12345);

INSERT INTO unidade(unid_desc,unid_sig)VALUES('UNIDADE','UN');
INSERT INTO unidade(unid_desc,unid_sig)VALUES('CAIXA','CX');
INSERT INTO unidade(unid_desc,unid_sig)VALUES('MILIMETRO','MM');





/*
CREATE TABLE Produto (
    id_prod int PRIMARY KEY,
    qntd_min numeric(15,2),
    qntd_estq numeric(15,2),
    vlr_unit blob,
    desc varchar(500),
    qntd_emp numeric(15,2),
    tipo varchar(200),
    leadtime numeric(15,2),
    FK_UnidadeMedida_id_unid int
);

CREATE TABLE Operacao (
    id_oper int PRIMARY KEY,
    desc varchar(500),
    instr varchar(500),
    FK_CentroTrabalho_id_ct int
);

CREATE TABLE OrdemProducao (
    id_ord int PRIMARY KEY,
    dt_hr_emi datetime,
    dt_hr_rlz datetime,
    status varchar(200),
    prazo date,
    FK_OrdemProducao_id_ord int
);

CREATE TABLE OrdemDetalhe_Contem (
    qntd_rlz numeric(15,2),
    qntd_prev numeric(15,2),
    seq int,
    obs varchar(500),
    FK_Produto_id_prod int,
    FK_OrdemProducao_id_ord int
);

CREATE TABLE Apontamento (
    id_apont int PRIMARY KEY,
    dt_hr_ini datetime,
    qntd varchar(200),
    dt_hr_fim datetime,
    tipo varchar(200),
    FK_Programacao_id_prog int
);

CREATE TABLE CentroTrabalho (
    desc varchar(500),
    id_ct int PRIMARY KEY
);

CREATE TABLE Requisicao (
    dt_hr_emi datetime,
    dt_prev date,
    dt_hr_rlz datetime,
    status varchar(200)
);

CREATE TABLE RequisicaoDetalhe_Contem (
    seq int,
    qntd_prev numeric(15,2),
    qntd_rlz numeric(15,2),
    FK_Produto_id_prod int
);

CREATE TABLE Recebimento (
    id_receb int PRIMARY KEY,
    dt_hr datetime
);

CREATE TABLE UnidadeMedida (
    id_unid int PRIMARY KEY,
    desc varchar(500),
    sigla varchar(500)
);

CREATE TABLE Recurso (
    id_rec int PRIMARY KEY,
    desc varchar(200),
    vlr_hr numeric(15,2),
    FK_CentroTrabalho_id_ct int
);

CREATE TABLE Lote (
    id_lote int PRIMARY KEY,
    dt_emi datetime,
    prazo date,
    qnt_total numeric(15,2),
    FK_Roteiro_id_rot int
);

CREATE TABLE Retirada (
    id_retr int PRIMARY KEY,
    dt_hr datetime
);

CREATE TABLE Programacao (
    dt_ini_prev datetime,
    id_prog int PRIMARY KEY,
    dt_fim_prev datetime,
    total_hr_prev time,
    FK_Operacao_id_oper int,
    FK_Recurso_id_rec int
);

CREATE TABLE Roteiro (
    id_rot int PRIMARY KEY,
    tmp_total time,
    FK_Produto_id_prod int
);

CREATE TABLE Composicao (
    FK_Produto_id_prod int,
    FK_Produto_id_prod_Composicao int,
    qntd numeric(15,2)
);

CREATE TABLE Pertence (
    FK_Recebimento_id_receb int,
    qntd numeric(15,2)
);

CREATE TABLE Pertence (
    FK_OrdemProducao_id_ord int,
    FK_Lote_id_lote int,
    qntd numeric(15,2)
);

CREATE TABLE Utiliza (
    FK_Operacao_id_oper int,
    FK_Roteiro_id_rot int,
    seq int,
    tmp_setup time,
    tmp_limp time,
    tmp_prod time
);

CREATE TABLE Possui (
    FK_Lote_id_lote int,
    FK_Programacao_id_prog int
);

CREATE TABLE Pertence (
    FK_Lote_id_lote int,
    FK_Retirada_id_retr int,
    qntd numeric(15,2)
);
 
ALTER TABLE Produto ADD CONSTRAINT FK_Produto_1
    FOREIGN KEY (FK_UnidadeMedida_id_unid)
    REFERENCES UnidadeMedida (id_unid);
 
ALTER TABLE Operacao ADD CONSTRAINT FK_Operacao_1
    FOREIGN KEY (FK_CentroTrabalho_id_ct)
    REFERENCES CentroTrabalho (id_ct);
 
ALTER TABLE OrdemProducao ADD CONSTRAINT FK_OrdemProducao_1
    FOREIGN KEY (FK_OrdemProducao_id_ord)
    REFERENCES OrdemProducao (id_ord);
 
ALTER TABLE OrdemDetalhe_Contem ADD CONSTRAINT FK_OrdemDetalhe_Contem_0
    FOREIGN KEY (FK_Produto_id_prod)
    REFERENCES Produto (id_prod);
 
ALTER TABLE OrdemDetalhe_Contem ADD CONSTRAINT FK_OrdemDetalhe_Contem_1
    FOREIGN KEY (FK_OrdemProducao_id_ord)
    REFERENCES OrdemProducao (id_ord);
 
ALTER TABLE Apontamento ADD CONSTRAINT FK_Apontamento_1
    FOREIGN KEY (FK_Programacao_id_prog)
    REFERENCES Programacao (id_prog);
 
ALTER TABLE RequisicaoDetalhe_Contem ADD CONSTRAINT FK_RequisicaoDetalhe_Contem_0
    FOREIGN KEY (FK_Produto_id_prod)
    REFERENCES Produto (id_prod);
 
ALTER TABLE Recurso ADD CONSTRAINT FK_Recurso_1
    FOREIGN KEY (FK_CentroTrabalho_id_ct)
    REFERENCES CentroTrabalho (id_ct);
 
ALTER TABLE Lote ADD CONSTRAINT FK_Lote_1
    FOREIGN KEY (FK_Roteiro_id_rot)
    REFERENCES Roteiro (id_rot);
 
ALTER TABLE Programacao ADD CONSTRAINT FK_Programacao_1
    FOREIGN KEY (FK_Operacao_id_oper)
    REFERENCES Operacao (id_oper);
 
ALTER TABLE Programacao ADD CONSTRAINT FK_Programacao_2
    FOREIGN KEY (FK_Recurso_id_rec)
    REFERENCES Recurso (id_rec);
 
ALTER TABLE Roteiro ADD CONSTRAINT FK_Roteiro_1
    FOREIGN KEY (FK_Produto_id_prod)
    REFERENCES Produto (id_prod);
 
ALTER TABLE Composicao ADD CONSTRAINT FK_Composicao_0
    FOREIGN KEY (FK_Produto_id_prod)
    REFERENCES Produto (id_prod);
 
ALTER TABLE Composicao ADD CONSTRAINT FK_Composicao_1
    FOREIGN KEY (FK_Produto_id_prod_Composicao)
    REFERENCES Produto (id_prod);
 
ALTER TABLE Pertence ADD CONSTRAINT FK_Pertence_0
    FOREIGN KEY (FK_Recebimento_id_receb)
    REFERENCES Recebimento (id_receb);
 
ALTER TABLE Pertence ADD CONSTRAINT FK_Pertence_0
    FOREIGN KEY (FK_OrdemProducao_id_ord)
    REFERENCES OrdemProducao (id_ord);
 
ALTER TABLE Pertence ADD CONSTRAINT FK_Pertence_1
    FOREIGN KEY (FK_Lote_id_lote)
    REFERENCES Lote (id_lote);
 
ALTER TABLE Utiliza ADD CONSTRAINT FK_Utiliza_0
    FOREIGN KEY (FK_Operacao_id_oper)
    REFERENCES Operacao (id_oper);
 
ALTER TABLE Utiliza ADD CONSTRAINT FK_Utiliza_1
    FOREIGN KEY (FK_Roteiro_id_rot)
    REFERENCES Roteiro (id_rot);
 
ALTER TABLE Possui ADD CONSTRAINT FK_Possui_0
    FOREIGN KEY (FK_Lote_id_lote)
    REFERENCES Lote (id_lote);
 
ALTER TABLE Possui ADD CONSTRAINT FK_Possui_1
    FOREIGN KEY (FK_Programacao_id_prog)
    REFERENCES Programacao (id_prog);
 
ALTER TABLE Pertence ADD CONSTRAINT FK_Pertence_0
    FOREIGN KEY (FK_Lote_id_lote)
    REFERENCES Lote (id_lote);
 
ALTER TABLE Pertence ADD CONSTRAINT FK_Pertence_1
    FOREIGN KEY (FK_Retirada_id_retr)
    REFERENCES Retirada (id_retr);*/