import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.sql.*;
import java.util.*;

//Esta classe inicia a interface de usuário e interage com os demais recursos da aplicação

public class GUI extends JFrame {
    String fileRootDir = System.getProperty("user.dir") + File.separator + "database_config.bin";
    private JButton loginButton;
    private JPasswordField passwordField1;
    private JTextField textField1;
    private JPanel background;
    private JPanel landing_Jpanel;
    private JPanel admin_Jpanel;
    private JPanel user_Jpanel;
    private JPanel consulta_user;
    private JPanel cadastra_user;
    private JPanel consulta_vacina;
    private JPanel insere_vacina;
    private JButton consultaUserButton;
    private JButton cadastraVacinaButton;
    private JButton cadastraUserButton;
    private JButton consultaVacinaButton;
    private JButton históricoDeVacinasButton;
    private JButton próximasDosesButton;
    private JButton imprimirCartãoButton;
    private JButton consultaCartãoUsuárioButton;
    private JButton consultarEAlterarDadosButton;
    private JButton AdminConfigButton;
    private JPanel adminConfig;
    private JLabel JLabelUserBD;
    private JLabel AddressBD;
    private JButton voltarConfToAdmin;
    private JButton alterarDadosBDButton;
    private JButton adminlogoutButton;
    private JButton userLogoutButton;
    private JPanel adminConfigChangeDB;
    private JTextField newDBuserTextField;
    private JPasswordField newDBpassword;
    private JTextField newDBaddress;
    private JButton confirmaMudançasButton;
    private JButton confirmaCadastroUserButton;
    private JTextField CadastraUserNome;
    private JButton cadastraToAdminButton;
    private JPasswordField CadastraUserSenha;
    private JButton testarConexãoButton;
    private String password;
    private String username;
    private String adminSalt = "adminpbkdf2";
    private String adminHash = "fTcZzt8MdjGl0/Ce6BgPGPSSHAai3UPIp7JyKAJj+Uo=";
    private String userDB;
    private String passwordDB;
    private String addressDB;
    private String cadastraUserNome1;
    private String cadastraUserSenha1;
    private String query;
    Connection connection;

    public GUI() {
        retrieveDatabaseConfig();
        try {
            makeConnection(addressDB, userDB, passwordDB);
        } catch (SQLException g) {
            JOptionPane.showMessageDialog(null, "Database connection failed: " + g.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            g.printStackTrace();
        }
        setContentPane(background);
        setTitle("Sistema vacinação");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);
        setVisible(true);
        AddressBD.setText("Endereço: " + addressDB);
        JLabelUserBD.setText("Usuário: " + userDB);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                closeConnection(); // Fecha conexão quando a janela da aplicação encerra
            }
        });

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setPassword();
                setUsername();
                if(auth().equals("admin")){
                    landing_Jpanel.setVisible(false);
                    admin_Jpanel.setVisible(true);
                }
                if(auth().equals("user")){
                    try {
                        String query = "SELECT password, salt FROM users WHERE username = ?";
                        PreparedStatement s = connection.prepareStatement(query);
                        s.setString(1, username);
                        ResultSet rs = s.executeQuery();

                        if (rs.next()) { // Check if a row was returned
                            String passwordHashFromDB = rs.getString("password"); // Use column names
                            System.out.println(passwordHashFromDB);
                            String salt = rs.getString("salt");

                            if (salt != null) { // Handle the case where the salt is null
                                String enteredPasswordHash = Crypto.pbkdf2Hash(password, salt); // Hash the entered password with the retrieved salt.
                                if (enteredPasswordHash.equals(passwordHashFromDB)) {
                                    landing_Jpanel.setVisible(false);
                                    user_Jpanel.setVisible(true);
                                } else {
                                    // Incorrect password - provide feedback to the user
                                    JOptionPane.showMessageDialog(null, "Incorrect password.", "Error", JOptionPane.ERROR_MESSAGE);
                                }
                            } else {
                                // Handle the case where the user doesn't have a salt in the database.
                                JOptionPane.showMessageDialog(null, "User record is missing salt.", "Error", JOptionPane.ERROR_MESSAGE);
                            }

                        } else {
                            // User not found - provide feedback to the user
                            JOptionPane.showMessageDialog(null, "User not found.", "Error", JOptionPane.ERROR_MESSAGE);
                        }

                        rs.close(); // Close the ResultSet
                        s.close(); // Close the PreparedStatement

                    } catch (SQLException f) {
                        f.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Database error: " + f.getMessage(), "Error", JOptionPane.ERROR_MESSAGE); // Inform user of database error
                    }
                    Gestão_Vacinas.test();
                }
            }
        });


        AdminConfigButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                admin_Jpanel.setVisible(false);
                adminConfig.setVisible(true);
            }
        });
        voltarConfToAdmin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adminConfig.setVisible(false);
                admin_Jpanel.setVisible(true);
            }
        });
        adminlogoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                admin_Jpanel.setVisible(false);
                landing_Jpanel.setVisible(true);
            }
        });
        userLogoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                user_Jpanel.setVisible(false);
                landing_Jpanel.setVisible(true);
            }
        });
        alterarDadosBDButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adminConfig.setVisible(false);
                adminConfigChangeDB.setVisible(true);
            }
        });
        confirmaMudançasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adminConfigChangeDB.setVisible(false);
                adminConfig.setVisible(true);
                setUserDB();
                setPasswordDB();
                setPathDB();
                storeDatabaseConfig(addressDB, userDB, passwordDB);
            }
        });
        cadastraUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                admin_Jpanel.setVisible(false);
                cadastra_user.setVisible(true);
            }
        });
        cadastraToAdminButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cadastra_user.setVisible(false);
                admin_Jpanel.setVisible(true);
            }
        });
        confirmaCadastroUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setCadastraUserNome();
                setCadastraUserSenha();
                String salt = Crypto.generateSalt(16);
                String hash = Crypto.pbkdf2Hash(getCadastraUserSenha(), salt);
                registerUser(getCadastraUsernome(), hash, salt);
                //isto apenas registra o usuário. Necessário fazer buscador de login no sistema com conexão ao bd
            }
        });
        testarConexãoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    makeConnection(addressDB, userDB, passwordDB);
                    JOptionPane.showMessageDialog(null, "Database connection succeeded","Success", JOptionPane.PLAIN_MESSAGE);
                } catch (SQLException g) {
                    JOptionPane.showMessageDialog(null, "Database connection failed: " + g.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    g.printStackTrace();
                }
            }
        });
    }
    public String auth() {
        if(this.username.equals("admin") && this.adminHash.equals(Crypto.pbkdf2Hash(this.password, this.adminSalt))) {
            return "admin";
        }else{
            return "user";
        }
    }


    public void setPassword() {this.password = new String(passwordField1.getPassword());}
    public void setUsername() {this.username = new String(textField1.getText());}
    public void setUserDB(){this.userDB = new String(newDBuserTextField.getText());}
    public void setPasswordDB(){this.passwordDB = new String(newDBpassword.getPassword());}
    public void setPathDB(){this.addressDB = new String(newDBaddress.getText());}
    public void setCadastraUserNome(){this.cadastraUserNome1 = new String(CadastraUserNome.getText());}
    public void setCadastraUserSenha(){this.cadastraUserSenha1 = new String(CadastraUserSenha.getPassword());}
    public String getCadastraUserSenha(){return this.cadastraUserSenha1;}
    public String getCadastraUsernome(){return this.cadastraUserNome1;}


    public void storeDatabaseConfig(String address, String user, String password) {
        Map<String, String> config = new HashMap<>();
        config.put("address", address);
        config.put("user", user);
        config.put("password", password);

        // Ensure the file exists
        File file = new File(fileRootDir);
        if (!file.exists()) {
            try {
                file.createNewFile(); // Create the file if it doesn't exist
            } catch (IOException e) {
                System.err.println("Failed to create file: " + e.getMessage());
                return;
            }
        }

        // Write the configuration to the file
        try (FileOutputStream fileOut = new FileOutputStream(file);
             ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)) {
            objectOut.writeObject(config); // Write the map to the file
            System.out.println("Database configuration saved to " + file.getAbsolutePath());
        } catch (IOException e) {
            System.err.println("Failed to save database configuration: " + e.getMessage());
        }
    }

    public void retrieveDatabaseConfig() {
        File file = new File(fileRootDir);

        // Check if the file exists
        if (!file.exists()) {
            System.err.println("Configuration file not found: " + file.getAbsolutePath());
        }

        // Read the configuration from the file
        try (FileInputStream fileIn = new FileInputStream(file);
             ObjectInputStream objectIn = new ObjectInputStream(fileIn)) {
            Map<String, String> config = (Map<String, String>) objectIn.readObject(); // Read the map from the file
            System.out.println("Database configuration loaded from " + file.getAbsolutePath());
            this.addressDB= config.get("address");
            this.userDB = config.get("user");
            this.passwordDB = config.get("password");
            System.out.println("Database Address: " + config.get("address"));
            System.out.println("Database User: " + config.get("user"));
            System.out.println("Database Password: " + config.get("password"));

        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Failed to retrieve database configuration: " + e.getMessage());
        }

    }


        public void makeConnection(String jdbcurl, String username, String password) throws SQLException{
                this.connection = DriverManager.getConnection(jdbcurl, username, password);
        }

        public void closeConnection(){
            try{
                this.connection.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        public void registerUser(String username, String hashpassword, String salt){
            try{
                PreparedStatement insertState = connection.prepareStatement("INSERT INTO users(username, password, salt) VALUES (?,?,?)");
                insertState.setString(1, username);
                insertState.setString(2, hashpassword);
                insertState.setString(3, salt);
                insertState.execute();
                System.out.println("Usuário registrado");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }



    public static void main(String[] args) {
        new GUI();

    }
}
