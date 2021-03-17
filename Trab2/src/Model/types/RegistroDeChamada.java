package Model.types;

import java.util.Calendar;
import java.util.Date;

import Model.TypesAbstracao.DadoDeRegistroIdentificadorUnico;
import Model.TypesAbstracao.RegistroIdentificador;
import Viewer.Excecoes;

public class RegistroDeChamada implements DadoDeRegistroIdentificadorUnico{

	private Telefone telefoneOrigem;
	private int numeroDestino;
	private Date tempoDeInicioDaChamada;
	private Date tempoDeDeTerminoDaChamada;
	private RegistroDeChamada(Telefone telefoneOrigem, int numeroDestino, Date tempoDeInicioDaChamada,
			Date tempoDeDeTerminoDaChamada) {
		super();
		this.telefoneOrigem = telefoneOrigem;
		this.numeroDestino = numeroDestino;
		this.tempoDeInicioDaChamada = tempoDeInicioDaChamada;
		this.tempoDeDeTerminoDaChamada = tempoDeDeTerminoDaChamada;
	}
	public Telefone getTelefoneOrigem() {
		return telefoneOrigem;
	}
	public boolean setTelefoneOrigem(Telefone telefoneOrigem) {
		if(telefoneOrigem!=null&&isTelefoneValido(telefoneOrigem)) {
			this.telefoneOrigem = telefoneOrigem;
			return true;
		}return false;
	}
	public int getNumeroDestino() {
		return numeroDestino;
	}
	public boolean setNumeroDestino(int numeroDestino) {
		if(Telefone.isNumeroValido(numeroDestino)) {
			this.numeroDestino = numeroDestino;
			return true;
		}return false;
	}
	public Date getTempoDeInicioDaChamada() {
		return tempoDeInicioDaChamada;
	}
	public boolean setTempoDeInicioDaChamada(Date tempoDeInicioDaChamada) {
		if(isDataValida(tempoDeInicioDaChamada)&&tempoDeInicioDaChamada.before(tempoDeDeTerminoDaChamada)) {
			this.tempoDeInicioDaChamada = tempoDeInicioDaChamada;
			return true;
		}return false;
	}
	public Date getTempoDeDeTerminoDaChamada() {
		return tempoDeDeTerminoDaChamada;
	}
	public boolean setTempoDeDeTerminoDaChamada(Date tempoDeDeTerminoDaChamada) {
		if(tempoDeDeTerminoDaChamada==null||(isDataValida(tempoDeDeTerminoDaChamada)&&tempoDeDeTerminoDaChamada.after(tempoDeInicioDaChamada))) {
			this.tempoDeDeTerminoDaChamada = tempoDeDeTerminoDaChamada;
			return true;
		}return false;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return new String(telefoneOrigem+" "+numeroDestino+" "+tempoDeInicioDaChamada+" "+tempoDeDeTerminoDaChamada);
	}
	public static RegistroDeChamada getRequestInstance(Telefone telefoneOrigem, int numeroDestino, Date tempoDeInicioDaChamada,
			Date tempoDeDeTerminoDaChamada){
		RegistroDeChamada r=new RegistroDeChamada(telefoneOrigem, numeroDestino, tempoDeInicioDaChamada, tempoDeDeTerminoDaChamada);
		if(isDadosValidos(r)==Excecoes.OPERACAO_BEM_SUCEDIDA) {
			return r;
		}return null;
	}
	public static Excecoes isDadosValidos(RegistroDeChamada r) {
		if(Telefone.isNumeroValido(r.getNumeroDestino())
				&&isTelefoneValido(r.getTelefoneOrigem())
				&&isDataValida(r.getTempoDeDeTerminoDaChamada())
				&&(r.getTempoDeDeTerminoDaChamada()==null||isDataValida(r.getTempoDeDeTerminoDaChamada()))
				&&(r.getTempoDeDeTerminoDaChamada()==null||r.getTempoDeInicioDaChamada().before(r.getTempoDeDeTerminoDaChamada()))
				&&(r.getTempoDeInicioDaChamada().after(r.getTelefoneOrigem().getTempoDeAtivacao()))
				&&(r.getTempoDeDeTerminoDaChamada()==null||r.getTempoDeDeTerminoDaChamada().after(r.getTelefoneOrigem().getTempoDeAtivacao()))){
			return Excecoes.OPERACAO_BEM_SUCEDIDA;
		}return Excecoes.VALOR_FORMATO_INVALIDO;
	}
	private static boolean isDataValida(Date data) {
		if(data!=null&&data.before(new Date(Calendar.YEAR, Calendar.MONTH, Calendar.DATE, Calendar.HOUR, Calendar.MINUTE))){
			return true;
		}return false;
	}
	private static boolean isTelefoneValido(Telefone t) {
		if(t!=null&&t.isDadosValidos()==Excecoes.OPERACAO_BEM_SUCEDIDA&&!t.isContaCancelada()) {
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
		Object[] o=new Object[2];
		o[0]=telefoneOrigem;
		o[1]=tempoDeInicioDaChamada;
		return new RegistroIdentificador(o);
	}
	@Override
	public Excecoes isDadosValidos() {
		// TODO Auto-generated method stub
		return isDadosValidos(this);
	}
	
	
	
}
