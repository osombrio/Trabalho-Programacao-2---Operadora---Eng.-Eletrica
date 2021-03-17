package Model.gerentes;


import java.util.ArrayList;
import java.util.Arrays;


import Model.TypesAbstracao.DadoDeRegistroIdentificadorUnico;
import Model.TypesAbstracao.TemplateGerenteDado;
import Model.TypesAbstracao.RegistroIdentificador;
import Model.types.Cliente;
import Model.types.PlanoTelefonico;
import Model.types.RegistroDeChamada;
import Model.types.Telefone;
import Viewer.Excecoes;

public class GerenteDados{
	private static TemplateGerenteDado<Cliente> clientes=new TemplateGerenteDado<Cliente>(){
		@Override
		public Excecoes remove(Cliente dado) {
			// TODO Auto-generated method stub
			if(dado==null) {return Excecoes.VALOR_NULO;}
			for(Telefone t:telefones.getArrayList()) {
				if(t.getCliente().comparaRegistros(dado.getIDRegistros())) {
					return Excecoes.DADO_ASSOCIADO;
				}
			}return super.remove(dado.getIDRegistros());
		}
	};
	private static TemplateGerenteDado<PlanoTelefonico> planosTelefonicos=new TemplateGerenteDado<PlanoTelefonico>(){
		@Override
		public Excecoes remove(PlanoTelefonico dado) {
			if(dado==null) {return Excecoes.VALOR_NULO;}
			for(Telefone t:telefones.getArrayList()) {
				if(t.getPlanoTelefone().comparaRegistros(dado.getIDRegistros())) {
					return Excecoes.DADO_ASSOCIADO;	
				}
			}return super.remove(dado.getIDRegistros());
		};
	};
	private static TemplateGerenteDado<RegistroDeChamada> registroDeChamadas=new TemplateGerenteDado<RegistroDeChamada>(){
		@Override
		public Excecoes adiciona(RegistroDeChamada dado) {
			if(dado!=null&&telefones.contem(dado.getTelefoneOrigem())){
				return super.adiciona(dado);
			}return Excecoes.DADO_EXISTENTE;
		};
	};
	private static TemplateGerenteDado<Telefone> telefones=new TemplateGerenteDado<Telefone>(){
		@Override
		public Excecoes adiciona(Telefone dado) {
			if(dado!=null&&clientes.contem(dado.getCliente())&&planosTelefonicos.contem(dado.getPlanoTelefone())) {
				return super.adiciona(dado);
			}return Excecoes.DADO_EXISTENTE;
		};
	};
	public GerenteDados() {
		// TODO Auto-generated constructor stub
	}
	/*
	 * ---Metodos delegados---
	 * Seriam melhor implementados com o padrao reflection
	 * */
	public static Excecoes adiciona(Cliente dado) {
		return clientes.adiciona(dado);
	}
	public static Excecoes remove(Cliente dado) {
		return clientes.remove(dado);
	}
	public static Excecoes setArrayList(Cliente[] vetor) {
		return clientes.setArrayList(vetor);
	}
	public static boolean contem(Cliente dado) {
		return clientes.contem(dado);
	}
	public static Excecoes alterarDado(Cliente dado) {
		return clientes.alterarDado(dado);
	}
	public static Excecoes adiciona(PlanoTelefonico dado) {
		return planosTelefonicos.adiciona(dado);
	}
	public static Excecoes remove(PlanoTelefonico dado) {
		return planosTelefonicos.remove(dado);
	}
	public static Excecoes setArrayList(PlanoTelefonico[] vetor) {
		return planosTelefonicos.setArrayList(vetor);
	}
	public static boolean contem(PlanoTelefonico dado) {
		return planosTelefonicos.contem(dado);
	}
	public static Excecoes alterarDado(PlanoTelefonico dado) {
		return planosTelefonicos.alterarDado(dado);
	}
	public static Excecoes adiciona(RegistroDeChamada dado) {
		return registroDeChamadas.adiciona(dado);
	}
	public static Excecoes remove(RegistroDeChamada dado) {
		return registroDeChamadas.remove(dado);
	}
	public static Excecoes setArrayList(RegistroDeChamada[] vetor) {
		return registroDeChamadas.setArrayList(vetor);
	}
	public static boolean contem(RegistroDeChamada dado) {
		return registroDeChamadas.contem(dado);
	}
	public static Excecoes alterarDado(RegistroDeChamada dado) {
		return registroDeChamadas.alterarDado(dado);
	}
	public static Excecoes adiciona(Telefone dado) {
		return telefones.adiciona(dado);
	}
	public static Excecoes remove(Telefone dado) {
		return telefones.remove(dado);
	}
	public static Excecoes setArrayList(Telefone[] vetor) {
		return telefones.setArrayList(vetor);
	}
	public static boolean contem(Telefone dado) {
		return telefones.contem(dado);
	}
	public static Excecoes alterarDado(Telefone dado) {
		return telefones.alterarDado(dado);
	}
	
	public static Excecoes remove(RegistroIdentificador ID,String nameClass) {
		if(nameClass.equals(Cliente.class.getName())) {
			return clientes.remove(ID);
		}
		if(nameClass.equals(PlanoTelefonico.class.getName())) {
			return planosTelefonicos.remove(ID);
		}
		if(nameClass.equals(Telefone.class.getName())) {
			return telefones.remove(ID);
		}
		if(nameClass.equals(RegistroDeChamada.class.getName())) {
			return registroDeChamadas.remove(ID);
		}return Excecoes.REFERENCIA_INCORRETA;
	}
	public static Excecoes setArrayList(ArrayList array,String nameClass) {
		if(nameClass.equals(Cliente.class.getName())) {
			return clientes.setArrayList(array);
		}
		if(nameClass.equals(PlanoTelefonico.class.getName())) {
			return planosTelefonicos.setArrayList(array);
		}
		if(nameClass.equals(Telefone.class.getName())) {
			return telefones.setArrayList(array);
		}
		if(nameClass.equals(RegistroDeChamada.class.getName())) {
			return registroDeChamadas.setArrayList(array);
		}return Excecoes.REFERENCIA_INCORRETA;
	}
	
	public static Excecoes limpaArray(String nameClass) {
		if(nameClass.equals(Cliente.class.getName())) {
			clientes.limpaArray();
			return Excecoes.OPERACAO_BEM_SUCEDIDA;
		}
		if(nameClass.equals(PlanoTelefonico.class.getName())) {
			planosTelefonicos.limpaArray();
			return Excecoes.OPERACAO_BEM_SUCEDIDA;
		}
		if(nameClass.equals(Telefone.class.getName())) {
			telefones.limpaArray();
			return Excecoes.OPERACAO_BEM_SUCEDIDA;
		}
		if(nameClass.equals(RegistroDeChamada.class.getName())) {
			registroDeChamadas.limpaArray();
			return Excecoes.OPERACAO_BEM_SUCEDIDA;
		}return Excecoes.REFERENCIA_INCORRETA;
	}
	
	public static TemplateGerenteDado<Cliente> getClientes() {
		return clientes;
	}
	
	public static TemplateGerenteDado<PlanoTelefonico> getPlanosTelefonicos() {
		return planosTelefonicos;
	}
	
	public static TemplateGerenteDado<RegistroDeChamada> getRegistroDeChamadas() {
		return registroDeChamadas;
	}
	
	public static TemplateGerenteDado<Telefone> getTelefones() {
		return telefones;
	}
	
}
