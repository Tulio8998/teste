import java.util.Scanner;

public class Principal {
	public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Diario diario = new Diario();
        Usuario usuarioLogado = null;
        boolean autenticado_usuario = false;
        
        while (!autenticado_usuario) {
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
				System.out.println("Usuário registrado com sucesso!\n");
			} 
			else if (opcao == 2) {
				System.out.print("Nome de usuário: ");
				String username = scanner.nextLine();
				System.out.print("Senha: ");
				String senha = scanner.nextLine();
				usuarioLogado = diario.autenticarUsuario(username, senha);
				if (usuarioLogado != null) {
					autenticado_usuario = true;
					System.out.println("Login bem-sucedido!\n");
				} 
				else {
					System.out.println("Login falhou. Tente novamente.\n");
				}
			}
		}
        
        while (true) {
            System.out.println("\nDiário de Bordo");
            System.out.println("1. Adicionar Entrada");
            System.out.println("2. Visualizar Entradas");
            System.out.println("3. Pesquisar Entradas");
            System.out.println("4. Contar Palavras nas Entradas");
            System.out.println("5. Sair");
            if (usuarioLogado.isAdmin()) {
            	System.out.println("6. ADM Visualizar Entradas");
            }
            System.out.print("Escolha uma opção: ");
            
            String opcao = scanner.nextLine();

            switch (opcao) {
                case "1":
                    System.out.println("Escreva sua entrada (linha em branco para terminar):");
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
                    System.out.print("Digite a palavra-chave para pesquisar: ");
                    String palavraChave = scanner.nextLine();
                    diario.pesquisarEntradas(palavraChave);
                    break;
                case "4":
                	int Qntd_Palavras = diario.contarPalavrasTotais();
                	if(Qntd_Palavras == -3) {
                		Qntd_Palavras = 0;
                		System.out.print("O total de palavras escritas na entrada são: " + Qntd_Palavras);
                	}
                	else if(Qntd_Palavras == -2) {
                		Qntd_Palavras = 1;
                		System.out.print("O total de palavras escritas na entrada são: " + Qntd_Palavras);
                	}
                	else if(Qntd_Palavras == -1) {
                		Qntd_Palavras = 2;
                		System.out.print("O total de palavras escritas na entrada são: " + Qntd_Palavras);
                	}
                	else{
                		System.out.print("O total de palavras escritas na entrada são: " + Qntd_Palavras);
                	}
                	break;
                case "5":
                    System.out.println("Saindo do Diário de Bordo...");
                    return;
                case "6":
                	diario.visualizarEntradasAdmin();
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        }
    }
}