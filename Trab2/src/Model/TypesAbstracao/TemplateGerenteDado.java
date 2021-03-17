package Model.TypesAbstracao;

import java.util.ArrayList;

import Viewer.Excecoes;


public abstract class TemplateGerenteDado<T extends DadoDeRegistroIdentificadorUnico> {

	protected ArrayList<T> array;
	
	public TemplateGerenteDado(ArrayList<T> array) {
		super();
		if(setArrayList(array)!=Excecoes.OPERACAO_BEM_SUCEDIDA) {
			this.array = new ArrayList<T>();
		}
	}
	public TemplateGerenteDado() {
		this.array = new ArrayList<T>();
	}
	protected Excecoes inicializaDado(T dado) {
		return isDadoValido(dado);
	}
	public Excecoes adiciona(T dado) {
		if(inicializaDado(dado)==Excecoes.OPERACAO_BEM_SUCEDIDA&&!contemID(dado.getIDRegistros())){
			if(array.add(dado)) {
				return Excecoes.OPERACAO_BEM_SUCEDIDA;
			}else {
				return Excecoes.ERROR_ESCRITA;
			}
		}else {
			return inicializaDado(dado);
		}
	}
	public Excecoes remove(T dado) {
		if(array.remove(dado)) {
			return Excecoes.OPERACAO_BEM_SUCEDIDA;
		}return Excecoes.ERROR_ESCRITA;
	}
	public Excecoes remove(RegistroIdentificador ID) {
		for(T t:array) {
			if(comparaRegistros(t, ID)) {
				return remove(t);
			}
		}return Excecoes.SEM_CORRESPONDENCIAS;
	}
	public ArrayList<T> getArrayList(){return array;}
	public Excecoes setArrayList(ArrayList<T> array) {
		if(array!=null) {
			if(isArrayValido(array)==Excecoes.OPERACAO_BEM_SUCEDIDA) {
				this.array=array;
				return Excecoes.OPERACAO_BEM_SUCEDIDA;
			}else {
				return isArrayValido(array);
			}
		}else {
			return Excecoes.VALOR_NULO;
		}
	}
	public Excecoes setArrayList(T[] vetor) {
		if(isArrayValido(array)!=Excecoes.OPERACAO_BEM_SUCEDIDA) {
			return isArrayValido(array);
		}
		array=new ArrayList<T>();
		for(T t:vetor) {
			if(t!=null) {
				if(isDadoValido(t)!=Excecoes.OPERACAO_BEM_SUCEDIDA) {
					return isDadoValido(t);
				}else {
					if(adiciona(t)!=Excecoes.OPERACAO_BEM_SUCEDIDA) {
						return adiciona(t);
					}
				}
				
			}
		}
		return Excecoes.OPERACAO_BEM_SUCEDIDA;
	}
	public T pesquisa(RegistroIdentificador ID) {
		for(T t:array) {
			if(comparaRegistros(t,ID) ){
				return t;
			}
		}return null;
	}
	public boolean contem(T dado) {
		return array.contains(dado);
	}
	public boolean contemID(RegistroIdentificador ID) {
		for(T t:array) {
			if(comparaRegistros(t,ID)) {
				return true;
			}
		}return false;
	}
	protected Excecoes isDadoValido(T t) {
		if(t!=null) {
			return t.isDadosValidos();
		}return Excecoes.VALOR_NULO;
	}
	protected boolean comparaRegistros(T dado, RegistroIdentificador registro) {
		return dado.comparaRegistros(registro);
	}
	protected Excecoes isArrayValido(ArrayList<T> array) {
		if(array!=null) {
			for(T t:array) {
				if(inicializaDado(t)!=Excecoes.OPERACAO_BEM_SUCEDIDA) {
					return inicializaDado(t);
				}
				for(T t2:array) {
					if(comparaRegistros(t, t2.getIDRegistros())) {
						return Excecoes.CHAVES_DUPLICADAS;
					}
				}
			}
		}else {
			return Excecoes.VALOR_NULO;
		}
		return Excecoes.OPERACAO_BEM_SUCEDIDA;
	}
	public Excecoes alterarDado(T dado) {
		if(array.contains(dado)) {
			return Excecoes.DADO_EXISTENTE;
		}
		if(!contemID(dado.getIDRegistros())){
			return Excecoes.SEM_CORRESPONDENCIAS;
		}
		if(inicializaDado(dado)!=Excecoes.OPERACAO_BEM_SUCEDIDA) {
			return inicializaDado(dado);
		}
		if(remove(dado.getIDRegistros())!=Excecoes.OPERACAO_BEM_SUCEDIDA) {
			return remove(dado.getIDRegistros());
		}
		if(adiciona(dado)!=Excecoes.OPERACAO_BEM_SUCEDIDA) {
			return adiciona(dado);
		}
		return Excecoes.OPERACAO_BEM_SUCEDIDA;
	}
	public DadoDeRegistroIdentificadorUnico[] getVetor() {
		DadoDeRegistroIdentificadorUnico[] dados=new DadoDeRegistroIdentificadorUnico[array.size()];
		Object[] aux=array.toArray();
		for(int i=0,j=0;i<array.size();i++,j++) {
			if(aux[i]!=null) {
				dados[j]=(DadoDeRegistroIdentificadorUnico)aux[i];
				j++;
			}
		}
		return dados;
	}
	public void limpaArray() {
		array.clear();
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String aux="";
		for(T t:array) {
			aux+=t.toString()+System.lineSeparator();
		}
		return aux;
	}
}
