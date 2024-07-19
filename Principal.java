package ATV1;

import java.util.Scanner;

public class Principal {
	public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Diario diario = new Diario();
        Usuario usuarioLogado = null;
        boolean autenticado_usuario = false;
        boolean rodando = true;
       
    while(rodando){
        while (!autenticado_usuario) {
        	System.out.println("Olá, seja bem vindo ao Diário Virtual!");
			System.out.println("1. Registrar novo usuário");
			System.out.println("2. Login");
			System.out.print("Escolha uma opção: ");
			int opcao = scanner.nextInt();
			scanner.nextLine();
			if (opcao == 1) {
				System.out.print("Informe o nome de usuário: ");
				String username = scanner.nextLine();
				System.out.print("Informe a senha: ");
				String senha = scanner.nextLine();
				diario.registrarUsuario(username, senha, false);
				System.out.println("\nUsuário registrado com sucesso!\n");
			} 
			else if (opcao == 2) {
				System.out.print("\nNome de usuário: ");
				String username = scanner.nextLine();
				System.out.print("Senha: ");
				String senha = scanner.nextLine();
				usuarioLogado = diario.autenticarUsuario(username, senha);
				if (usuarioLogado != null) {
					autenticado_usuario = true;
					System.out.println("\nLogin bem-sucedido!");
					Diario.login(usuarioLogado);
				} 
				else {
					System.out.println("\nLogin falhou. Tente novamente.\n");
				}
			}
				else {
					System.out.print("\nDesculpa, não entendemos o que você digitou, TENTE NOVAMENTE!\n\n");
				}
        }
        
        while(autenticado_usuario){
            System.out.println("\nDiário de Bordo");
            if (usuarioLogado.isAdmin()) {
            	System.out.println("001. ADM Visualizar Entradas");
            	System.out.println("002. ADM Sobreescrever Entradas");
            	System.out.println("003. ADM Deletar Entradas");
            	System.out.println("004. ADM Continuar Escrever Entradas");
            }
            System.out.println("1. Adicionar Entrada");
            System.out.println("2. Visualizar Entradas");
            System.out.println("3. Pesquisar Entradas");
            System.out.println("4. Contar Palavras nas Entradas");
            System.out.println("5. Logout");
            System.out.println("6. Sair do Diário");
            System.out.print("Escolha uma opção: ");
            
            String opcao = scanner.nextLine();

            switch (opcao) {
            	case "001":
            		if (usuarioLogado.isAdmin()) {
            			diario.visualizarEntradasAdmin();
            		} else {
            			System.out.println("\nOpção inválida. Tente novamente.");
            		}
                break;
            	case "002":
            		if (usuarioLogado.isAdmin()) {
            			diario.visualizarEntradasAdmin();
            			System.out.print("\nDigite o indice do usuario: ");
            			int indice = scanner.nextInt();
            			scanner.nextLine();
            			System.out.print("Digite uma nova entrada: ");
            			String novaEntrada = scanner.nextLine();
            			diario.editarEntradasAdmin(indice, novaEntrada);
            		} else {
            			System.out.println("Opção inválida. Tente novamente.");
            		}
                break;
            	case "003":
            		if (usuarioLogado.isAdmin()) {
            			diario.visualizarEntradasAdmin();
            			System.out.print("\nDigite o indice do usuario: ");
            			int indice = scanner.nextInt();
            			scanner.nextLine();
            			diario.deletarEntradasAdmin(indice);
            			diario.visualizarEntradasAdmin();
            		} else {
            			System.out.println("Opção inválida. Tente novamente.");
            		}
                break;
                case "1":
                    System.out.println("\nEscreva sua entrada (linha em branco para terminar):");
                    StringBuilder entrada = new StringBuilder();
                    String linha;
                    while (!(linha = scanner.nextLine()).isEmpty()) {
                        entrada.append(linha).append("\n");
                    }
                    diario.adicionarEntrada(entrada.toString().trim());
                    break;
                case "2":
                	diario.visualizarEntradas();
                    break;
                case "3":
                    System.out.print("\nDigite a palavra-chave para pesquisar: ");
                    String palavraChave = scanner.nextLine();
                    diario.pesquisarEntradas(palavraChave);
                    break;
                case "4":
                	int Qntd_Palavras = diario.contarPalavrasTotais();
                	if(Qntd_Palavras == 0){
                		System.out.print("\nNão há entradas escritas no diário\n");
                	}
                	else {
                		System.out.print("\nHá " + Qntd_Palavras + " palavra(s) escrita(s) no diário.\n");
                	}
                	break;
                case "5":
                    Diario.logout();
                    autenticado_usuario = false;
                    break;
                case "6":
                	Diario.logout();
                	autenticado_usuario = false;
                	rodando = false;
                	System.out.println("Saindo do Diário de Bordo...\n");
                	break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
        		}
        	}
		}
    scanner.close();
	}
}