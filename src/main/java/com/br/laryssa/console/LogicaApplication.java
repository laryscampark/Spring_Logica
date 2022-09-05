package com.br.laryssa.console;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LogicaApplication {

	public final static void clearConsole() {
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}

	public final static void espera(int segundos) throws InterruptedException {
		TimeUnit.SECONDS.sleep(segundos);
	}

	private static List<String> produtos = new ArrayList<>();
	private static List<List<String>> pedidos = new ArrayList<>();
	//private static List<String> clientes = new ArrayList<>();


	public static void main(String[] args) throws IOException, InterruptedException{
		//SpringApplication.run(LogicaApplication.class, args);
		System.out.println("Bem vindo, escolha a opção que deseja");

		BufferedReader reader = new BufferedReader (
			new InputStreamReader(System.in));

			while(true) {
				System.out.println("1 - Cadastrar produtos");
				System.out.println("2 - Cadastrar pedidos");
				System.out.println("3- Exibir relatório");
				System.out.println("4- Sair");

				int op = Integer.parseInt(reader.readLine());

				boolean sair = false;

				switch(op) {
					case 1: 
						System.out.println("Digite o nome do produto");
						String nome = reader.readLine();
						produtos.add(nome);
						clearConsole();
						System.out.println("Produtos cadastrados com sucesso");
						espera(2);
						clearConsole();
					break;
					case 2:
					System.out.println("Selecione um produto da lista");
					for(int i = 0; i< produtos.size(); i++) {
						System.out.println((i+1) + " - " + produtos.get(i));
					}

					int idProduto = Integer.parseInt(reader.readLine());

					var clientePedido = new ArrayList<String>();
					clientePedido.add(produtos.get(idProduto-1));

					System.out.println("Digite o nome do cliente");
					String nomeCliente = reader.readLine();
					clientePedido.add(nomeCliente);

					pedidos.add(clientePedido);

					clearConsole();
					System.out.println("Pedidos cadastrados com sucesso");
					espera(2);
					clearConsole();
					break;
					case 3:
					System.out.println("Relatório de pedidos");
					for(List<String> clientePed: pedidos){
						System.out.println("Produto: " + clientePed.get(0));
						System.out.println("Clientes: " + clientePed.get(1));
					}
					espera(4);
					clearConsole();
					break;
					case 4:
					sair = true;
					break;
				}

				if(sair) break; 
			}

	}

}


