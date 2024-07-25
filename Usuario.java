package ATV1;

// Classe principal do "usuario"
public class Usuario {
    private String username;
    private String senha;
    private boolean isAdmin;
    
    // Cria um construtor para o Usuario atrelando-o um username, senha e (caso seja adm, verificando a veracidade)
    public Usuario(String username, String senha, boolean isAdmin) {
        this.username = username;
        this.senha = senha;
        this.isAdmin = isAdmin;
    }
    // Método para verificar se o usuário é um administrador
    public boolean isAdmin(){
    	return isAdmin;
    }
    // Método para obter o nome de usuário
    public String getUsername() {
    	return username;
    }
    // Método para obter a senha
    public String getSenha() {
    	return senha;
    }
}