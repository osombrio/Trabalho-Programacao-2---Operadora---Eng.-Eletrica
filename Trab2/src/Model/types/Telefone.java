package Model.types;
import java.util.Date;

import Model.TypesAbstracao.DadoDeRegistroIdentificadorUnico;
import Model.TypesAbstracao.RegistroIdentificador;
import Viewer.Excecoes;

import java.util.ArrayList;
import java.util.Calendar;


public class Telefone implements DadoDeRegistroIdentificadorUnico{

	private int numero;
	private Cliente cliente;
	private PlanoTelefonico planoTelefone;
	private Date tempoDeAtivacao;
	private Date tempoDeCancelamento;
	private ArrayList<Integer> diasDePagamento;
	private Telefone(int numero, Cliente cliente, PlanoTelefonico plano,
		 ArrayList<Integer> diasDePagamento) {
		super();
		this.numero = numero;
		this.cliente = cliente;
		this.planoTelefone = plano;
		this.diasDePagamento = diasDePagamento;
		tempoDeAtivacao=getDateTempoAtual();
	}
	public boolean isDiasDePagamentosValidos(ArrayList<Integer> diasDePagamento) {
		for(Integer i:diasDePagamento) {
			if(!(i>0&&i<28)) {
				return false;
			}
		}return true;
	}
	public ArrayList<Integer> getDiasDePagamento() {
		return diasDePagamento;
	}
	public boolean setDiasDePagamento(ArrayList<Integer> diasDePagamento) {
		if(isDiasDePagamentosValidos(diasDePagamento)) {
			this.diasDePagamento = diasDePagamento;
			return true;
		}return false;
	}
	@SuppressWarnings("deprecation")
	private Date getDateTempoAtual() {
		return new Date(Calendar.YEAR, Calendar.MONTH, Calendar.DATE, Calendar.HOUR, Calendar.MINUTE);
	}
	public int getNumero() {
		return numero;
	}
	public Cliente getCliente() {
		return cliente;
	}
	private static boolean isDataValida(Date data) {
		if(data!=null&&data.before(new Date(Calendar.YEAR, Calendar.MONTH, Calendar.DATE, Calendar.HOUR, Calendar.MINUTE))){
			return true;
		}return false;
	}
	public Date getTempoDeAtivacao() {
		return tempoDeAtivacao;
	}
	public Date getTempoDeCancelamento() {
		return tempoDeCancelamento;
	}
	public boolean setTempoDeCancelamento(Date tempoDeCancelamento) {
		if(tempoDeCancelamento==null||isDataValida(tempoDeCancelamento)) {
			this.tempoDeCancelamento = tempoDeCancelamento;
			return true;
		}return false;
	}
	public boolean setCliente(Cliente cliente) {
		if(Cliente.isDadosValidos(cliente)==Excecoes.OPERACAO_BEM_SUCEDIDA) {
			this.cliente = cliente;
			return true;
		}return false;
	}
	public PlanoTelefonico getPlanoTelefone() {
		return planoTelefone;
	}
	public boolean setPlanoTelefone(PlanoTelefonico plano) {
		if(PlanoTelefonico.isDadosValidos(plano)==Excecoes.OPERACAO_BEM_SUCEDIDA) {
			this.planoTelefone = plano;
			return true;
		}return false;
	}
	public static Excecoes isDadosValidos(Telefone t) {
		if(t!=null&&isNumeroValido(t.getNumero())
				&&PlanoTelefonico.isDadosValidos(t.getPlanoTelefone())==Excecoes.OPERACAO_BEM_SUCEDIDA
				&&Cliente.isDadosValidos(t.getCliente())==Excecoes.OPERACAO_BEM_SUCEDIDA
				&&isDataValida(t.getTempoDeAtivacao())
				&&(t.getTempoDeCancelamento()==null||(isDataValida(t.getTempoDeCancelamento())))
				&&(t.getTempoDeCancelamento()==null||(t.getTempoDeAtivacao().before(t.getTempoDeCancelamento())))
				) {
			return Excecoes.OPERACAO_BEM_SUCEDIDA;
		}return Excecoes.VALOR_FORMATO_INVALIDO;
	}
	public Telefone getRequestInstance(int numero, Cliente cliente, PlanoTelefonico plano,
			 ArrayList<Integer> diasDePagamento) {
		Telefone t=new Telefone(numero, cliente, plano, diasDePagamento);
		if(isDadosValidos(t)==Excecoes.OPERACAO_BEM_SUCEDIDA) {
			return t;
		}return null;
	}
	public boolean isContaCancelada() {
		if(tempoDeCancelamento!=null) {
			return true;
		}
		return false;
	}
	public boolean ativaConta() {
		if(isContaCancelada()) {
			tempoDeAtivacao=getDateTempoAtual();
			tempoDeCancelamento=null;
			return true;
		}
		return false;
	}
	public boolean cancelaConta() {
		if(!isContaCancelada()) {
			tempoDeCancelamento=getDateTempoAtual();
			return true;
		}
		return false;
	}
	public static boolean isNumeroValido(int numero) {
		if(numero==1||numero==5||numero==10||numero==15) {
			return true;
		}return false;
	}
	@Override
	public boolean comparaRegistros(RegistroIdentificador registro) {
		// TODO Auto-generated method stub
		RegistroIdentificador r=getIDRegistros();
		if(r==registro||r.getRegistro()==registro.getRegistro()) {
			return true;
		}if(!(registro!=null&&registro.getRegistro()!=null)||registro.getRegistro().length!=r.getRegistro().length) {
				return false;
		}
		for(int i=0;i<registro.getRegistro().length;i++) {
			if(registro.getRegistro()[i]==null) {
				return false;
			}
			if(registro.getRegistro()[i] instanceof Integer) {
				if(r.getRegistro()[i]!=registro.getRegistro()[i]) {
					return false;
				}
			}else {
				if(registro.getRegistro()[i] instanceof Date) {
					Date d1=(Date)r.getRegistro()[i];
					Date d2=(Date)registro.getRegistro()[i];
					if(!d1.equals(d2)) {
						return false;
					}
				}else {
					return false;
				}
			}
		}
		return true;
	}
	@Override
	public RegistroIdentificador getIDRegistros() {
		// TODO Auto-generated method stub
		Object o[]=new Object[2];
		o[0]=numero;
		o[1]=getTempoDeCancelamento();
		return new RegistroIdentificador(o);
	}
	@Override
	public Excecoes isDadosValidos() {
		// TODO Auto-generated method stub
		return isDadosValidos(this);
	}
}
