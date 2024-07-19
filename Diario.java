package ATV1;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Diario {
	private List<EntradaDiario> entradas;
	private List<Usuario> usuarios;
	private static Usuario usuarioAtual = null;
	
    public Diario() {
        this.entradas = new ArrayList<>();
        this.usuarios = new ArrayList<>();
        Usuario admin = new Usuario("adm", "adm", true);
        usuarios.add(admin);
    }
    
    public class EntradaDiario{
    	private String texto;
    	private Usuario autor;
    	private String dataHora;
    	
    	public EntradaDiario(String entradas, Usuario autor) {
    		this.texto = entradas;
    		this.autor = autor;
    		LocalDateTime agora = LocalDateTime.now();
    	    DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    	    this.dataHora = agora.format(formatador);
    		
    	}
    	public void setTexto(String texto) {
			this.texto = texto;
		}
    	public String getTexto() {
    		return texto;
    	}
		public Usuario getAutor() {
    		return autor;
    	}
    	public String toString() {
    		return dataHora + " - " + autor.getUsername() + ": " + texto;
    	}
    }
    
    public void registrarUsuario(String username, String senha, boolean isAdmin) {
        Usuario usuario = new Usuario(username, senha, isAdmin);
        usuarios.add(usuario);
    }
    
    public Usuario autenticarUsuario(String username, String senha) {
        for (Usuario usuario : usuarios) {
            if (usuario.getUsername().equals(username) && usuario.getSenha().equals(senha)) {
                return usuario;
            }
        }
        return null;
    }
    
    public static void login(Usuario usuario) {
    	usuarioAtual = usuario;
    	System.out.println("Usuário " + usuario.getUsername() + " foi logado com sucesso");
    }
    
    public static void logout() {
    	System.out.println("Usuário " + usuarioAtual.getUsername() + " se deslogou do Diário\n");
    }

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

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void visualizarEntradasAdmin() {
    	if (entradas.isEmpty()) {
            System.out.println("\nO diário está vazio.");
        } else {
            System.out.println("\nEntradas do Diário:");
            entradas.forEach(System.out::println);
        }
    }
	
	public void editarEntradasAdmin (int indice, String novaEntrada) {
		if (indice < 0 || indice >= entradas.size()) {
			System.out.println("Índice de entrada inválido ou não há entradas para sobrescrever");
		} else {
			EntradaDiario entrada = entradas.get(indice);
			entrada.setTexto(novaEntrada);
			System.out.println("Entrada editada com sucesso.");
		}
		
	}
	
	public void deletarEntradasAdmin (int indice) {
		if (indice < 0 || indice >= entradas.size()) {
			System.out.println("\nÍndice de entrada inválido.");
		} else {
			entradas.remove(indice);
			System.out.println("Entrada removida com sucesso.");
		}
		
	}
	
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
    
    public int contarPalavrasDeEntrada(String entrada) {
    	if(entrada == null || entrada.isEmpty()) {
    		return 0;
    	}
    	String[] palavras = entrada.split("\\s+");
        return palavras.length;
    }
    
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