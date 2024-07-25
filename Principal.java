package ATV1;
import java.util.Scanner;

// Classe principal do "principal"
public class Principal {
	public static void main(String[] args) {
  // Incicializando scanner, objetos e variáveis
  Scanner scanner = new Scanner(System.in);
  Diario diario = new Diario();
  // Variavel responsável por manter o usuário logado, ou não logado
  Usuario usuarioLogado = null;
    // Variavel que confirma informações de login e controla o looo do menu 1
	boolean autenticado_usuario = false;
    // Variavel que controla o loop dos menus
	boolean rodando = true;
   
	// loop geral
	while(rodando){
		// loop do menu 1
	    while (!autenticado_usuario) {
	
	    	// Opções do menu 1        	
	    	System.out.println("Olá, seja bem vindo ao Diário Virtual!");
			System.out.println("1. Registrar novo usuário");
			System.out.println("2. Login");
			System.out.println("3. Sair do Diário");
			System.out.print("Escolha uma opção: ");
			String opcao1 = scanner.nextLine();
			switch (opcao1) {
				case "1":
						
				// Sistema que coleta e compara dados de login
				System.out.print("Informe o nome de usuário: ");
				String username = scanner.nextLine();
				System.out.print("Informe a senha: ");
				String senha = scanner.nextLine();
	
				// Nesta função as informações fornecidas pelo usuário são verificadas, e caso sejam confimadas o menu 1 retorna para que o usuario possa logar	
				diario.registrarUsuario(username, senha, false);
				
				boolean rodando2=false;
				while (!rodando2)
				{
					System.out.println("Deseja logar com a conta criada?");
					System.out.print("Digite Sim ou Não: ");
					// Iniciando variável para controlar o while
					String answer = scanner.nextLine();
					switch (answer.toLowerCase())
					{
						case "sim":
						// Alterando o valor de autenticado_usuario para sair do menu 1 
						autenticado_usuario = true;
						// Autenticando e logando o usuário
						usuarioLogado = diario.autenticarUsuario(username, senha);
						System.out.println("\nLogin bem-sucedido!");
						// Método responsável por efetuar o login						
						Diario.login(usuarioLogado);
						// Alterando o valor de rodando2 para sair do loop
						rodando2=true;
						break;
						
						case "não":
						System.out.println("Retornando ao menu...\n");
						// Alterando o valor de rodando2 para sair do loop
						rodando2=true;
						break;
						
						default:
						System.out.println("Opção Inválida. Tente Novamente\n");
					}
				}
				break;
				
				case "2":
				// Sistema usado para logar o usuário 					
				System.out.print("Nome de usuário: ");
				username = scanner.nextLine();
				System.out.print("Senha: ");
				senha = scanner.nextLine();
				// Usando a variavel usuarioLogado, é possível verificar se o processo de autenticação de username e senha sucedeu
				usuarioLogado = diario.autenticarUsuario(username, senha);
				// Caso usuarioLogado não retorne null, o processo foi um sucesso
				if (usuarioLogado != null) {
					// Alterando o valor de autenticado_usuario para sair do menu 1						
					autenticado_usuario = true;
					System.out.println("\nLogin bem-sucedido!");
					// Método responsável por efetuar o login						
					Diario.login(usuarioLogado);
					}
					// Caso usuárioLogado retorne null, o usuário é notificado e o menu 1 volta a ser repetido
					else {
						System.out.println("\nLogin falhou. Tente novamente.\n");
					}
				break;
				
				case "3":
				// Opção para fechamento direto do diario					
				System.out.println("Volte sempre :)");
				System.out.println("Saindo do Diário de Bordo...\n");
				
				// Com essas alterações em "autenticado_usuario" e "rodando" o loop acaba e o código encerra               	
				autenticado_usuario = false;
	           	rodando = false;					
	          	return;
				default: 
				// Caso o usuário fuja das entradas válidas nesse menu, uma mensagem	é exibida e o menu 1 volta a ser exibido	
				System.out.print("\nOpção inválida. Tente novamente.\n\n");
				break;
			}
  }

  // Uma vez que o usuário está devidamente autenticado e logado, e a variável autenticado_usuario == true, o menu 2 é exibido 
  while(autenticado_usuario) {
	    System.out.println("\nDiário de Bordo");
	    // Caso o usuário tenha acesso aos dados de login como adimin, e faça login como, opções extras são exibidas
	    if (usuarioLogado.isAdmin()) {
		    // Opções extras ao ser admin
		    // Admin pode visualizar qualquer entrada salva posteriormente por qualquer usuário          	
		    System.out.println("001. ADM Visualizar Entradas");
		
		    // Admins podem substituir a ultima entrada do diário de qualquer usuário por uma nova entrada           	
		    System.out.println("002. ADM Substituir Entradas");
		
		    // Admins podem continuar a escrever sob a última entrada de qualquer usuário        	
		    System.out.println("003. ADM Sobrescrever Entradas");
		
		    // Admins podem excluir a última entrada escrita posteriormente por qualquer usuário     	
		    System.out.println("004. ADM Deletar Entradas");
	    }
	
	    // Opções disponíveis para usuarios comuns e admins            
	    // Apenas adiciona uma entrada 
	    System.out.println("1. Adicionar Entrada");
	
	    // Vizualiza as entradas escritas pelo próprio usuário             
	    System.out.println("2. Visualizar Entradas");
	
	    // Usando uma palavra-chave, realiza-se uma pesquisa nas entradas escritas pelo usuário posteriormente    
	    System.out.println("3. Pesquisar Entradas");
	
	    // Conta-se o número de palavras escritas pelo usuário         
	    System.out.println("4. Contar Palavras nas Entradas");
	
	    // Realiza o logout do usuário e retorna ao menu 1            
	    System.out.println("5. Logout");
	
	    // Fecha o diario diretamente            
	    System.out.println("6. Sair do Diário");
	    System.out.print("Escolha uma opção: ");
	          
	    String opcao2 = scanner.nextLine();
	
	    switch (opcao2) {
		    case "001":
		    // Verificação de segurança 
		    if (usuarioLogado.isAdmin()) {
		    	// Função responsável por exibir entradas de usuários diversos          			
		    	diario.visualizarEntradasAdmin();
		        } else {
		        	System.out.println("Opção inválida. Tente novamente.\n");
		        }
		    break;
		    case "002":
		    // Verificação de segurança 
		    if (usuarioLogado.isAdmin()) {
			    // Exibe entradas de usuários diversos
			    diario.visualizarEntradasAdmin();
			    System.out.print("\nDigite o usuario que deseja modificar a entrada: ");
		        String username = scanner.nextLine();
		     	System.out.print("Digite uma nova entrada: ");
		   		String novaEntrada = scanner.nextLine();
		
		   		// Método que, usando o usuário e entrada fornecidos, adiona-a ao diário escolhido         			
		   		diario.substituirEntradasAdmin(username, novaEntrada);
		    } else {
		    	System.out.println("Opção inválida. Tente novamente.");
		    }
		    break;
		    case "003":
		    // Verificação de segurança 
		    if (usuarioLogado.isAdmin()) {
		    	// Exibe entradas de usuários diversos            			
		    	diario.visualizarEntradasAdmin();
		        System.out.print("\nDigite o usuario que deseja modificar a entrada: ");
		   		String username = scanner.nextLine();
		        System.out.print("Adicione uma nova entrada sobre a do usuário: ");
		 		String novaEntrada = scanner.nextLine();
		
		 		// Método responsável por substituir a entrada do usuário escolhido pela entrada escrita pelo admin
		 		diario.sobrescreverEntradasAdmin(username, novaEntrada);
		        } else {
		        System.out.println("Opção inválida. Tente novamente.");
		    }
		    break;
		    case "004":
		    // Verificação de segurança 
        	if (usuarioLogado.isAdmin()) {
        		diario.visualizarEntradasAdmin();
        		System.out.print("\nDigite o nome do usuario: ");
        		String usuario = scanner.nextLine();
        		// Método resposável por apagar todas entradas de um usuário
       		diario.deletarEntradasAdmin(usuario);
        		diario.visualizarEntradasAdmin();
        		} else {
        			System.out.println("Opção inválida. Tente novamente.");
        		}
        	break;
          case "1":
                System.out.println("\nEscreva sua entrada (linha em branco para terminar):");
                // Inicializando o StringBuilder
                StringBuilder entrada = new StringBuilder();
                String linha;
                // While responsável por terminar uma entrada
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
                // Método responsável por pesquisar a palavra-chave nas entradas do usuário
                diario.pesquisarEntradas(palavraChave);
            break;
            case "4":
          	// Método por contar quantas palavras existem na entrada
            	int Qntd_Palavras = diario.contarPalavrasTotais();
            	if(Qntd_Palavras == 0){
            		System.out.print("\nNão há entradas escritas no diário\n");
            	}
            	else {
            		System.out.print("\nHá " + Qntd_Palavras + " palavra(s) escrita(s) no diário.\n");
            	}
            break;
            case "5":
          	  // Método que realiza o logout do usuário e retorna ao menu 1
                Diario.logout();
                // Redefinindo autenticado_usuario para que o loop do menu 1 possa recomeçar
                autenticado_usuario = false;
            break;
            case "6":
          	  // Realizando logout do usuário
            	Diario.logout();
            	// Saindo do loop do menu 2
            	autenticado_usuario = false;
            	// Saiindo do loop geral
            	rodando = false;
            	System.out.println("\nSaindo do Diário de Bordo...\n");
            break;
            default:
          	  System.out.println("\nOpção inválida. Tente novamente.");
          	  break;
	    			}
  		}
		}
		scanner.close();
	}
}