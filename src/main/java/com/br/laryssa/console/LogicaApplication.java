package com.br.laryssa.console;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.br.laryssa.console.models.Cliente;
import com.br.laryssa.console.models.Pedido;
import com.br.laryssa.console.models.Produto;

@SpringBootApplication
public class LogicaApplication {

	public final static void clearConsole() {
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}

	public final static void espera(int segundos) throws InterruptedException {
		TimeUnit.SECONDS.sleep(segundos);
	}

	private static BufferedReader reader = new BufferedReader (new InputStreamReader(System.in));

	private static List<Produto> produtos = new ArrayList<>();
	private static List<Pedido> pedidos = new ArrayList<>();


	/**
	 * @param args
	 * @throws IOException
	 * @throws InterruptedException
	 */
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
						var produto = new Produto();

						System.out.println("Digite o nome do produto");
						produto.setNome(reader.readLine());

						System.out.println("Digite a descrição do produto");
						produto.setDescricao(reader.readLine());

						System.out.println("Digite o valor do produto");
						produto.setValor(Float.parseFloat(reader.readLine()));


						produtos.add(produto);
						clearConsole();
						System.out.println("Produtos cadastrados com sucesso");
						espera(2);
						clearConsole();
					break;
					case 2:
					
					var pedido = new Pedido();

					var cliente = new Cliente();
					System.out.println("Digite o nome do cliente");
					cliente.setNome(reader.readLine());

					System.out.println("Digite o e-mail do cliente");
					cliente.setEmail(reader.readLine());

					pedido.setCliente(cliente);

					clearConsole();
					var produtosAPreencher = new ArrayList<Produto>();
					var produtosDoPedido = getProdutosDoPedido(produtosAPreencher);

					pedido.setProdutos(produtos);
					pedidos.add(pedido);

					clearConsole();
					System.out.println("Pedidos cadastrados com sucesso");
					espera(2);
					clearConsole();
					break;
					case 3:
					System.out.println("Relatório de pedidos");
					for(Pedido ped: pedidos){
						System.out.println("Clientes: " + ped.getCliente().getNome());
						System.out.println("E-mail: "+ ped.getCliente().getEmail());
						System.out.println("_______________________________________");
						for(Produto prod : ped.getProdutos()) {
							System.out.println("Nome: " + prod.getNome());
							System.out.println("Descrição: " + prod.getDescricao());
							System.out.println("Valor unidade: " + prod.getValor());
						}
						System.out.println("__________________________________________");
						System.out.println("Valor total: " + ped.valorTotal());
						System.out.println("_____________________________________________");
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

	private static List<Produto> getProdutosDoPedido(ArrayList<Produto> produtosAPreencher) throws NumberFormatException, IOException, InterruptedException {
					System.out.println("Selecione um produto da lista");
					for(int i = 0; i< produtos.size(); i++) {
						System.out.println((i+1) + " - " + produtos.get(i).getNome());
					}
					int idProduto = Integer.parseInt(reader.readLine());
					try {
						var produtoSelecionado = produtos.get(idProduto-1);
						produtosAPreencher.add(produtoSelecionado);
					} catch(Exception e) {
						getProdutosDoPedido(produtosAPreencher);
					}


					clearConsole();
					System.out.println("Produto adicionado com sucesso!");
					espera(1);

					System.out.println("Para adicionar mais produtos, digite");
					System.out.println("1- Para adicionar mais");
					System.out.println("2- Para fechar o pedido");

					int op = Integer.parseInt(reader.readLine());

					if(op == 1){
						getProdutosDoPedido(produtosAPreencher);
					}

					
					return produtos;
	}

}


