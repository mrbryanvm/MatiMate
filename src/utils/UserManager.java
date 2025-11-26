package utils;

import models.User;
import java.io.*;
import java.util.*;

public class UserManager {
    private static final String USERS_FILE = "users.txt";
    private static UserManager instance;
    private Map<String, User> users;
    
    private UserManager() {
        users = new HashMap<>();
        loadUsers();
    }
    
    public static UserManager getInstance() {
        if (instance == null) {
            instance = new UserManager();
        }
        return instance;
    }
    
    // Cargar usuarios desde archivo
    private void loadUsers() {
        File file = new File(USERS_FILE);
        if (!file.exists()) {
            // Crear usuarios por defecto
            createDefaultUsers();
            saveUsers();
            return;
        }
        
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 5) {
                    User user = new User(
                        parts[0], // email
                        parts[1], // password
                        parts[2], // nombre
                        parts[3], // telefono
                        Integer.parseInt(parts[4]) // score
                    );
                    users.put(user.getEmail(), user);
                }
            }
        } catch (IOException e) {
            System.err.println("Error al cargar usuarios: " + e.getMessage());
            createDefaultUsers();
        }
    }
    
    // Guardar usuarios en archivo
    private void saveUsers() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(USERS_FILE))) {
            for (User user : users.values()) {
                writer.write(String.format("%s|%s|%s|%s|%d%n",
                    user.getEmail(),
                    user.getPassword(),
                    user.getNombre(),
                    user.getTelefono(),
                    user.getScore()
                ));
            }
        } catch (IOException e) {
            System.err.println("Error al guardar usuarios: " + e.getMessage());
        }
    }
    
    // Crear usuarios predeterminados
    private void createDefaultUsers() {
        users.clear();
        
        // Usuario de prueba 1
        users.put("juan.perez@ejemplo.com", new User(
            "juan.perez@ejemplo.com",
            "123456",
            "Juan Pérez",
            "+1234567890",
            80
        ));
        
        // Usuario de prueba 2
        users.put("maria.garcia@ejemplo.com", new User(
            "maria.garcia@ejemplo.com",
            "password",
            "María García",
            "+9876543210",
            95
        ));
        
        // Usuario de prueba 3
        users.put("admin@matimate.com", new User(
            "admin@matimate.com",
            "admin123",
            "Administrador",
            "+1111111111",
            100
        ));
    }
    
    // Autenticar usuario
    public User authenticate(String email, String password) {
        User user = users.get(email.toLowerCase().trim());
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }
    
    // Registrar nuevo usuario
    public boolean register(String email, String password, String nombre, String telefono) {
        email = email.toLowerCase().trim();
        
        // Validaciones
        if (email.isEmpty() || password.isEmpty() || nombre.isEmpty()) {
            return false;
        }
        
        if (users.containsKey(email)) {
            return false; // Usuario ya existe
        }
        
        if (!isValidEmail(email)) {
            return false;
        }
        
        if (password.length() < 6) {
            return false; // Contraseña muy corta
        }
        
        // Crear nuevo usuario
        User newUser = new User(email, password, nombre, telefono, 0);
        users.put(email, newUser);
        saveUsers();
        
        return true;
    }
    
    // Actualizar usuario
    public boolean updateUser(User user) {
        if (users.containsKey(user.getEmail())) {
            users.put(user.getEmail(), user);
            saveUsers();
            return true;
        }
        return false;
    }
    
    // Cambiar contraseña
    public boolean changePassword(String email, String oldPassword, String newPassword) {
        User user = authenticate(email, oldPassword);
        if (user != null && newPassword.length() >= 6) {
            user.setPassword(newPassword);
            updateUser(user);
            return true;
        }
        return false;
    }
    
    // Validar email
    private boolean isValidEmail(String email) {
        return email.contains("@") && email.contains(".");
    }
    
    // Obtener usuario por email
    public User getUser(String email) {
        return users.get(email.toLowerCase().trim());
    }
    
    // Verificar si existe un usuario
    public boolean userExists(String email) {
        return users.containsKey(email.toLowerCase().trim());
    }
    
    // Obtener todos los usuarios (para debugging)
    public List<User> getAllUsers() {
        return new ArrayList<>(users.values());
    }
    
    // Resetear a usuarios por defecto
    public void resetToDefaults() {
        createDefaultUsers();
        saveUsers();
    }
}