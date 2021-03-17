package Viewer;

public enum Excecoes {

	ERROR_DESCONHECIDO("ERROR: Ocorreu um erro desconhecido"),
	VALOR_NULO("INFORMACAO_INVALIDA: Valor nulo de variavel"),
	OPERACAO_BEM_SUCEDIDA("CONCLUIDA: Operacao realizada com sucesso"),
	CHAVES_DUPLICADAS("INFORMACAO_INVALIDA: Detectado excecao de chaves de registro duplicadas!"),
	DADO_NAO_INICIALIZADO("INFORMACAO_INVALIDA: Ocorreu uma excecao ao inicilizar o dado"),
	SEM_CORRESPONDENCIAS("CONCLUIDA_PESQUISA: Nao foi encontrado alguma correspondencia para sua solicitacao"),
	ERROR_ESCRITA("ERROR: Nao foi possivel registrar!"),
	ERROR_LEITURA("ERROR: Nao foi possivel ler!"),
	DADO_EXISTENTE("Impossivel fazer alteracoes, pois esse dado ja existe no sistema"),
	DADO_ASSOCIADO("Impossivel completar a operacao, pois o dado esta associado a outro no sistema"),
	DADO_INEXISTENTE("Impossivel completar a operacao, pois algum dado nao existe no sistema"),
	REFERENCIA_INCORRETA("Informacao invalida: voce referenciou um dado no sistema que nao possui operacoes"),
	VALOR_FORMATO_INVALIDO("O formato do valor e invalido"),
	CLIENTE_CPF_INVALIDO("O CPF informado e invalido!"),
	ENDERECO_INVALIDO("O endereco informado e invalido!"),
	ERROR_ACESSO_BANCODEDADOS("ERROR: O armazenamento nao pode ser acessado");
	private String descricao;
	private Excecoes(String descricao) {
		// TODO Auto-generated constructor stub
		this.descricao=descricao;
	}
	public String getDescricao() {
		return descricao;
	}
}
