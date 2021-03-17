package Model.types;

import java.util.ArrayList;
import java.util.LinkedList;

import Model.TypesAbstracao.DadoDeRegistroIdentificadorUnico;
import Model.TypesAbstracao.TemplateGerenteDado;
import Model.TypesAbstracao.RegistroIdentificador;
import Model.classesUtildadesAuxiliares.Endereco;
import Viewer.Excecoes;

public class Cliente implements DadoDeRegistroIdentificadorUnico{

	private long CPF;
	private String nome;
	private Endereco endereco;
	private Cliente(long CPF, String nome, Endereco endereco) {
		super();
		this.CPF = CPF;
		this.nome = nome;
		this.endereco = endereco;
	}
	public long getCPF() {
		return CPF;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		if(nome!=null) {
			this.nome = nome;
		}
	}
	public Endereco getEndereco() {
			return this.endereco;
	}
	public void setEndereco(Endereco endereco) {
		if(endereco!=null&&endereco.isEnderecoValido()) {
			this.endereco = endereco;
		}
	}
	public static Cliente getResquestInstance(long CPF, String nome, Endereco endereco) {
		Cliente c=new Cliente(CPF, nome, endereco);
		if(isDadosValidos(c)==Excecoes.OPERACAO_BEM_SUCEDIDA){
			return c;
		}return null;
	}
	public static Excecoes isDadosValidos(Cliente c) {
		if(c!=null&&c.getEndereco()!=null&&c.getNome()!=null) {
			if(c.getEndereco().isEnderecoValido()) {
				if(isCPFValido(c.getCPF())){
					return Excecoes.OPERACAO_BEM_SUCEDIDA;
				}else {
					return Excecoes.CLIENTE_CPF_INVALIDO;
				}
			}else {
				return Excecoes.ENDERECO_INVALIDO;
			}
		}else {
			return Excecoes.VALOR_NULO;
		}
	}
	@Override
	public Excecoes isDadosValidos() {
		// TODO Auto-generated method stub
		return isDadosValidos(this);
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return new String(CPF+" "+nome+" "+endereco);
	}
	@Override
	public RegistroIdentificador getIDRegistros() {
		// TODO Auto-generated method stub
		return new RegistroIdentificador(CPF);
	}
	@Override
	public boolean comparaRegistros(RegistroIdentificador registro) {
		// TODO Auto-generated method stub
		return getIDRegistros().comparaRegistro(registro);
	}
	private static boolean isCPFValido(long CPF) {
		if(CPF>Math.pow(10,9)&&CPF<Math.pow(10,11)) {
			LinkedList<Integer> stack = new LinkedList<Integer>();
	        while(CPF>0){
	            stack.push((int)(CPF%10));
	            CPF/=10;
	        }int aux=stack.get(0);
	        for(Integer i:stack) {
	        	if(i!=aux) {
	        		aux=i;
	        		break;
	        	}
	        }if(aux==stack.get(0)) {
	        	return false;
	        }
	        int soma=0;
	        for(int i=0,j=10;i<9;i++,j--) {
	        	soma+=stack.get(i)*j;
	        }int resto=11-soma%11;
	        if (resto==10||resto==11) {
	        	resto=0;
	        }
	        soma=0;
	        for(int i=0,j=11;i<10;i++,j--) {
	        	soma+=stack.get(i)*j;
	        }int resto2=11-soma%11;
            if (resto2==10||resto2==11) {
            	resto2=0;
            }if (resto==stack.get(9)&&resto2==stack.get(10)) {
                 return true;
            }
        }return false;
	}

}
