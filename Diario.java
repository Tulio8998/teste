import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Diario {
	private List<String> entradas;
	private List<Usuario> usuarios;
	
    public Diario() {
        this.entradas = new ArrayList<>();
        this.usuarios = new ArrayList<>();
        Usuario admin = new Usuario("adm", "adm", true);
        usuarios.add(admin);
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

    public void adicionarEntrada(String entrada) {
        LocalDateTime agora = LocalDateTime.now();
        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String dataFormatada = agora.format(formatador);
        entradas.add(dataFormatada + " - " + entrada);
    }	

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void visualizarEntradasAdmin() {
    	if (entradas.isEmpty()) {
            System.out.println("O diário está vazio.");
        } else {
            System.out.println("Entradas do Diário:");
            entradas.forEach(System.out::println);
        }
    }
    
    public void visualizarEntradas() {
        if (entradas.isEmpty()) {
            System.out.println("O diário está vazio.");
        } else {
            System.out.println("Entradas do Diário:");
            entradas.forEach(System.out::println);
        }
    }

    public void pesquisarEntradas(String palavraChave) {
        boolean encontrou = false;
        for (String entrada : entradas) {
            if (entrada.toLowerCase().contains(palavraChave.toLowerCase())) {
                if (!encontrou) {
                    System.out.println("Entradas encontradas:");
                    encontrou = true;
                }
                System.out.println(entrada);
            }
        }
        if (!encontrou) {
            System.out.println("Nenhuma entrada encontrada com a palavra-chave: " + palavraChave);
        }
    }
    public int contarPalavrasDeEntrada(String entrada) {
        if (entrada == null || entrada.isEmpty()) {
            return 0;
        }
        String[] palavras = entrada.split("\\s+");
        return palavras.length;
    }

    public int contarPalavrasTotais() {
        int totalDePalavras = -3;
        for (String entrada : entradas) {
            totalDePalavras += contarPalavrasDeEntrada(entrada);
        }
        return totalDePalavras;
    }
}
