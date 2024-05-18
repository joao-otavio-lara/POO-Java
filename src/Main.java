import java.util.Scanner;
import java.util.*;

public class Main {

	public static void main(String[] args) {
		System.out.println("===============================================");
		System.out.println("Bem Vindo ao ontrole de estoque!");
		System.out.println("===============================================");

		List<Produto> estoque = new ArrayList<Produto>();
		boolean loop = true;
		while(loop){
			estoque = menu(estoque);
		}
	}

	public static List<Produto> menu(List<Produto> estoque){
		Scanner ler = new Scanner(System.in);
		
		
		System.out.println("===============================================");
		System.out.println("MENU");
		System.out.println("1 - Cadastro de Produto");
		System.out.println("2 - Estoque");
		System.out.println("3 - Entrada de Produto");
		System.out.println("4 - Saida de Produto");
		System.out.println("===============================================");
		
		int option = ler.nextInt();

		switch(option){
			case 1: 
				var produto = Cadastro();
				estoque.add(produto);
				break;
			
			case 2: 
				Estoque(estoque);
			
				break;
				
			case 3: 
				estoque = Entrada(estoque);
				
				break;
						
			case 4: 
				estoque = Saida(estoque);
				
				break;
		}
		return estoque;

	}

	public static Produto Cadastro(){
		Scanner ler = new Scanner(System.in);
		
		System.out.println("Digite a descrição do produto");
		String descricao = ler.nextLine();
		
		System.out.println("Digite o codigo do produto");
		int codigo = ler.nextInt();
		
		return new Produto(descricao, codigo);
	}

	public static void Estoque(List<Produto> estoque){
		estoque.forEach(item -> {
			System.out.println("Descrição: "+item.getDescricao()+
								", Codigo: "+item.getCodigo()+
								", Quantidade: "+item.getQuantidade());
		});
	}

	public static List<Produto> Entrada(List<Produto> estoque){
		Scanner ler = new Scanner(System.in);
		
		System.out.println("Digite o codigo do produto que quer dar entrada.");
		int codigo = ler.nextInt();

		System.out.println("Digite quantidade do produto para entrada.");
    	int quantidade = ler.nextInt();	
		
		estoque.stream()
      		.filter(produto -> produto.getCodigo() == codigo)
      		.findFirst()
      		.ifPresent(produto -> {produto.setQuantidade(quantidade);});
		
		System.out.println("Entrada realizada com sucesso!");
		return estoque;
	}

	public static List<Produto> Saida(List<Produto> estoque){
		Scanner ler = new Scanner(System.in);
		
		System.out.println("Digite o codigo do produto que quer dar saida.");
		int codigo = ler.nextInt();

		System.out.println("Digite quantidade do produto para saida.");
    	int quantidade = ler.nextInt();	
		
		estoque.stream()
      		.filter(produto -> produto.getCodigo() == codigo)
      		.findFirst()
      		.ifPresent(produto -> {
				if(produto.getQuantidade() < quantidade){
					System.out.println("Não tem estoque suficiente para dar saida nesta quantidade!");
				}else{
					int quantidadeAtual = produto.getQuantidade() - quantidade;
					produto.setQuantidade(quantidadeAtual);
					System.out.println("Saída realizada com sucesso!");
				}
			});
		return estoque;
	}
}
