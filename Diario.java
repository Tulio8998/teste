package ATV1;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

// Classe principal do "diário"
public class Diario {
	// Lista de entradas do diário
	private List<EntradaDiario> entradas;
	private List<Usuario> usuarios;
	// Usuário atualmente logado
	private static Usuario usuarioAtual = null;
	
	// Construtor da classe Diario
    public Diario() {
        this.entradas = new ArrayList<>();
        this.usuarios = new ArrayList<>();
    // Criação de um usuário administrador padrão
        Usuario admin = new Usuario("adm", "adm", true);
        usuarios.add(admin);
    }
    
    // Classe interna representando uma entrada no diário
    public class EntradaDiario{
    	private String texto;
    	private Usuario autor;
    	private String dataHora;
    	
    	// Construtor da classe EntradaDiario
    	public EntradaDiario(String entradas, Usuario autor) {
    		this.texto = entradas;
    		this.autor = autor;
    		// Captura a data e hora atual em um formato especificado
    		LocalDateTime agora = LocalDateTime.now();
    	    DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    	    this.dataHora = agora.format(formatador);
    		
    	}
    	// Define o texto da entrada
    	public void setTexto(String texto) {
			this.texto = texto;
		}
    	// Retorna o texto da entrada
    	public String getTexto() {
    		return texto;
    	}
    	// Retorna o autor da entrada
		public Usuario getAutor() {
    		return autor;
    	}
		// Retorna uma representação em string da entrada
    	public String toString() {
    		return dataHora + " - " + autor.getUsername() + ": " + texto;
    	}
    }
    
    // Registra um novo usuário no sistema
    public void registrarUsuario(String username, String senha, boolean isAdmin) {
    	boolean nomeDoUsuarioExiste = false;
    	// Verifica se o nome de usuário já existe
        for (Usuario u : usuarios) {
	        if (u.getUsername().equals(username)) {
	        	nomeDoUsuarioExiste = true;
	        	break;
	        	}
	        }
        	// Se o nome de usuário já existe, exibe uma mensagem de erro
        	if (nomeDoUsuarioExiste) {
        		System.out.println("\nEste nome de usuario já esta sendo utilizado, por favor escolha outro.\n");
        	} else {
        		 // Caso contrário, adiciona o novo usuário a lista
	    			Usuario usuario = new Usuario(username, senha, isAdmin);
		        	usuarios.add(usuario);
		        	System.out.println("\nUsuário registrado com sucesso!\n");
		        	}
   }
    
    // Autentica um usuário com base em nome de usuário e senha
    public Usuario autenticarUsuario(String username, String senha) {
        for (Usuario usuario : usuarios) {
            if (usuario.getUsername().equals(username) && usuario.getSenha().equals(senha)) {
                return usuario;
            }
        }
        return null;
    }
    
    // Realiza o login de um usuário
    public static void login(Usuario usuario) {
    	usuarioAtual = usuario;
    	System.out.println("Usuário " + usuario.getUsername() + " foi logado com sucesso");
    }
    
    // Realiza o logout do usuário atual
    public static void logout() {
    	System.out.println("\nUsuário " + usuarioAtual.getUsername() + " deslogou do Diário\n");
    }
 
    // Adiciona uma nova entrada ao diário
    public void adicionarEntrada(String texto) {
    	if(usuarioAtual != null) {
    		EntradaDiario entrada = new EntradaDiario(texto, usuarioAtual);
    		entradas.add(entrada);
    		System.out.println("Entrada adiciona pelo perfil " + usuarioAtual.getUsername());
    	}
    	else {
    		System.out.println("Nenhum usuário está logado, tente novamente mais tarde");
    	}
    }	
    
    // Visualiza todas as entradas no diário (acessível apenas pelo administrador)
	public void visualizarEntradasAdmin() {
    	if (entradas.isEmpty()) {
            System.out.println("\nO diário está vazio.");
        } else {
            System.out.println("\nEntradas do Diário:");
            entradas.forEach(System.out::println);
        }
    }
	
	// Substitui a última entrada de um usuário específico (acessível apenas pelo administrador)
	public void substituirEntradasAdmin (String usuario, String novaEntrada) {
		boolean nomeDoUsuarioExiste = false;
		for (int i = entradas.size() - 1; i >= 0; i--) {
			EntradaDiario entrada = entradas.get(i);
        	if (entrada.getAutor().getUsername().equals(usuario)) {
        		entrada.setTexto(novaEntrada);
        		nomeDoUsuarioExiste = true;
        		System.out.println("Entrada editada com sucesso.");
        		break;
        	}
        }
        	if (!nomeDoUsuarioExiste) {
        		System.out.println("Usuario inválido ou não há entradas para sobrescrever");
        	}   
        	
	}
	
	// Deleta todas as entradas de um usuário específico (acessível apenas pelo administrador)
	public void deletarEntradasAdmin (String usuario) {
		boolean nomeDoUsuarioExiste = false;
		List<EntradaDiario> removerEntradas =  new ArrayList<>();
		 for (int i = entradas.size() - 1; i >= 0; i--) {
		    EntradaDiario entrada = entradas.get(i);
        	if (entrada.getAutor().getUsername().equals(usuario)) {
        		removerEntradas.add(entrada);
        		nomeDoUsuarioExiste = true;
        		break;
        	}
        }
        entradas.removeAll(removerEntradas);
        
        	if (nomeDoUsuarioExiste) {
        		System.out.println("Entradas removidas.");
        	} else {
        		System.out.println("Usuario inválido ou o diario esta vazio.");
        	}

	}
	
	// Sobrescreve a última entrada de um usuário específico (acessível apenas pelo administrador)
	public void sobrescreverEntradasAdmin (String usuario, String novaEntrada) {
		boolean nomeDoUsuarioExiste = false;
		 for (int i = entradas.size() - 1; i >= 0; i--) {
		    EntradaDiario entrada = entradas.get(i);
        	if (entrada.getAutor().getUsername().equals(usuario)) {
        		entrada.setTexto(entrada.getTexto() + " " + novaEntrada);
        		nomeDoUsuarioExiste = true;
        		System.out.println("Entrada editada com sucesso.");
        		break;
        	}
        }
        	if (!nomeDoUsuarioExiste) {
        		System.out.println("Usuario inválido ou o diario esta vazio.");
        	}   
        	
	}
	
	// Visualiza as entradas do usuário atualmente logado
    public void visualizarEntradas() {
        boolean entradaEcontrada = false;
        for (EntradaDiario entrada : entradas) {
            if (entrada.getAutor().equals(usuarioAtual)) {
                if (!entradaEcontrada) {
                    System.out.println("\nEntradas do Diário:");
                    entradaEcontrada = true;
                }
                System.out.println(entrada);
            }
        }
        if (!entradaEcontrada) {
            System.out.println("\nVocê não possui entradas no diário.");
        }
        
    }

    // Pesquisa entradas do usuário atual por palavra-chave
    public void pesquisarEntradas(String palavraChave) {
        boolean encontrou = false;
        for (EntradaDiario entrada : entradas) {
        	if (entrada.getAutor().equals(usuarioAtual)) {
	            if (entrada.getTexto().toLowerCase().contains(palavraChave.toLowerCase())) {
	                if (!encontrou) {
	                    System.out.println("Entradas encontradas:");
	                    encontrou = true;
	                }
	                System.out.println(entrada);
	            }
        	}
        }
        if (!encontrou) {
            System.out.println("Nenhuma entrada encontrada com a palavra-chave: " + palavraChave);
        }
    }
    
    // Conta o número de palavras em uma entrada
    public int contarPalavrasDeEntrada(String entrada) {
    	if(entrada == null || entrada.isEmpty()) {
    		return 0;
    	}
    	String[] palavras = entrada.split("\\s+");
        return palavras.length;
    }
    
    // Conta o número total de palavras nas entradas do usuário atual
    public int contarPalavrasTotais() {
        int totalDePalavras = 0;
        for (EntradaDiario entrada : entradas) {
        	if (entrada.getAutor().equals(usuarioAtual)) {
        		totalDePalavras += contarPalavrasDeEntrada(entrada.getTexto());
        	}
        }
        return totalDePalavras;
    }
    
    
}