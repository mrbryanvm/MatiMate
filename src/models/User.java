package models;

public class User {
    private String email;
    private String password;
    private String nombre;
    private String telefono;
    private int score;
    
    public User(String email, String password, String nombre, String telefono, int score) {
        this.email = email;
        this.password = password;
        this.nombre = nombre;
        this.telefono = telefono;
        this.score = score;
    }
    
    // Getters
    public String getEmail() {
        return email;
    }
    
    public String getPassword() {
        return password;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public String getTelefono() {
        return telefono;
    }
    
    public int getScore() {
        return score;
    }
    
    // Setters
    public void setEmail(String email) {
        this.email = email;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    
    public void setScore(int score) {
        this.score = score;
    }
    
    @Override
    public String toString() {
        return String.format("User{email='%s', nombre='%s', score=%d}", email, nombre, score);
    }
}