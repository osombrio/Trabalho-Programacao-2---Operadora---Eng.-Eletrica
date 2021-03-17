package Main;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.Scanner;

import Model.TypesAbstracao.DadoDeRegistroIdentificadorUnico;
import Model.classesUtildadesAuxiliares.Endereco;
import Model.gerentes.GerenteDados;
import Model.types.Cliente;
import Model.types.PlanoTelefonico;
import Model.types.RegistroDeChamada;
import Model.types.Telefone;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(GerenteDados.adiciona(Cliente.getResquestInstance(11999130650l, "Igor", new Endereco(145135))).getDescricao());
		System.out.println(GerenteDados.adiciona(Cliente.getResquestInstance(11999130650l, "Igor", new Endereco(145135))).getDescricao());
		System.out.println(GerenteDados.getClientes().getArrayList().size());
		System.out.println(GerenteDados.getClientes().getArrayList().get(0));
	}

}
