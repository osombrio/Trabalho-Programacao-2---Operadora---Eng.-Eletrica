package Model.TypesAbstracao;

import java.util.ArrayList;

import Viewer.Excecoes;

public interface DadoDeRegistroIdentificadorUnico {

	public boolean comparaRegistros(RegistroIdentificador registro);
	public RegistroIdentificador getIDRegistros();
	public Excecoes isDadosValidos();
}
