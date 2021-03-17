package Model.types;

import Model.TypesAbstracao.DadoDeRegistroIdentificadorUnico;
import Model.TypesAbstracao.RegistroIdentificador;
import Viewer.Excecoes;

public class PlanoTelefonico implements DadoDeRegistroIdentificadorUnico {

	private int id;
	private String descricao;
	private float valorMensal;
	private int minutosDeFranquia;
	private float valorDaChamada;
	private PlanoTelefonico(int id, String descricao, float valorMensal, int minutosDeFranquia, float valorDaChamada) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.valorMensal = valorMensal;
		this.minutosDeFranquia = minutosDeFranquia;
		this.valorDaChamada = valorDaChamada;
	}
	public int getId() {
		return id;
	}
	public String getDescricao() {
		return descricao;
	}
	public boolean setDescricao(String descricao) {
		if(descricao!=null) {
			this.descricao = descricao;
			return true;
		}return false;
	}
	public float getValorMensal() {
		return valorMensal;
	}
	public boolean setValorMensal(float valorMensal) {
		if(isValorMensalValido(valorMensal)) {
			this.valorMensal = valorMensal;
			return true;
		}return false;
	}
	public int getMinutosDeFranquia() {
			return minutosDeFranquia;
	}
	public boolean setMinutosDeFranquia(int minutosDeFranquia) {
		if(isMinutosChamadaValido(minutosDeFranquia)) {
			this.minutosDeFranquia = minutosDeFranquia;
			return true;
		}return false;
	}
	public float getValorDaChamada() {
		return valorDaChamada;
	}
	public boolean setValorDaChamada(float valorDaChamada) {
		if(isValorChamadaValido(valorDaChamada)) {
			this.valorDaChamada = valorDaChamada;
			return true;
		}return false;
	}
	public PlanoTelefonico getRequestInstance(int id, String descricao, float valorMensal, int minutosDeFranquia, float valorDaChamada) {
		PlanoTelefonico p=new PlanoTelefonico(id, descricao, valorMensal, minutosDeFranquia, valorDaChamada);
		if(isDadosValidos(p)==Excecoes.OPERACAO_BEM_SUCEDIDA) {
			return p;
		}return null;
	}
	public static Excecoes isDadosValidos(PlanoTelefonico p) {
		if(p!=null&&p.getDescricao()!=null) {
			if(p.getId()>=0&&p.getMinutosDeFranquia()>=0&&p.getValorDaChamada()>=0&&p.getValorMensal()>=0) {
				return Excecoes.OPERACAO_BEM_SUCEDIDA;
			}else {
				return Excecoes.VALOR_FORMATO_INVALIDO;
			}
		}else {
			return Excecoes.VALOR_NULO;
		}
	}
	private boolean isValorChamadaValido(float valor) {
		if(valor>=0) {
			return true;
		}return false;
	}
	private boolean isMinutosChamadaValido(int minutos) {
		if(minutos>=0) {
			return true;
		}return false;
	}
	private boolean isValorMensalValido(float valor) {
		if(valor>=0) {
			return true;
		}return false;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return new String(id+" "+descricao+" "+valorMensal+" "+minutosDeFranquia+" "+valorDaChamada);
	}
	@Override
	public boolean comparaRegistros(RegistroIdentificador registro) {
		// TODO Auto-generated method stub
		return getIDRegistros().comparaRegistro(registro);
	}
	@Override
	public RegistroIdentificador getIDRegistros() {
		// TODO Auto-generated method stub
		return new RegistroIdentificador(id);
	}
	@Override
	public Excecoes isDadosValidos() {
		// TODO Auto-generated method stub
		return isDadosValidos(this);
	}
}
