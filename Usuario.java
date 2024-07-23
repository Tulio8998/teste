package ATV1;

public class Usuario {
    private String username;
    private String senha;
    private boolean isAdmin;
 
    public Usuario(String username, String senha, boolean isAdmin) {
        this.username = username;
        this.senha = senha;
        this.isAdmin = isAdmin;
    }
    public boolean isAdmin(){
    	return isAdmin;
    }
    public void setADM(boolean isAdmin){
    	this.isAdmin = isAdmin;
    }
    public String getUsername() {
    	return username;
    }
    public String getSenha() {
    	return senha;
    }
}