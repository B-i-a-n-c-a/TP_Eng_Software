import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;
import java.sql.*;
import java.sql.Date;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
    private JPanel insere_vacina_user;
    private JButton consultaUserButton;
    private JButton cadastraUserButton;
    private JButton consultarEAlterarDadosButton;
    private JLabel JLabelUserBD;
    private JLabel AddressBD;
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
    private JButton cadastraPacienteButton;
    private JButton alterarMeusDadosButton;
    private JButton configuraçõesButton;
    private JButton sairAdminConfigButton;
    private JTextField idvacinaUSER;
    private JTextField nomevacinaUSER;
    private JTextField tipovacinaUSER;
    private JTextField fabricantevacinaUSER;
    private JButton ConfirmaVacinaUser;
    private JButton voltarVacinaToConfigUser;
    private JButton criarVacinaUserButton;
    private JButton criarLoteUserButton;
    private JButton buscarVacinaButton;
    private JPanel criarLoteUser;
    private JTextField batchIDfield;
    private JTextField vaxIDfield;
    private JTextField batchFabField;
    private JTextField batchExpiration;
    private JTextField batchQuantity;
    private JButton realizarAplicaçãoButton;
    private JButton buscaPacienteButton;
    private JLabel usernameLabel1;
    private JButton consultaCartãoPacienteButton;
    private JButton testarConexãoAdminConfigButton;
    private JLabel adminDBconfigLabel1;
    private JButton adminToUserJbutton;
    private JButton batchConfirmaButton;
    private JButton batchToUserButton;
    private JLabel batchLabel1;
    private JButton fecharButton;
    private JPanel buscarVacinaUser;
    private JButton voltarVacinaToUserButton;
    private JLabel vaxSearchID;
    private JLabel vaxSearchName;
    private JLabel vaxSearchType;
    private JLabel vaxSearchFab;
    private JLabel vaxSearchbatch;
    private JLabel vaxBatchAmount;
    private JTextField searchVaxField1;
    private JTextField searchPacienteField1;
    private JLabel vaxSearchExpire;
    private JPanel cadastraPacienteUser;
    private JTextField cadastroNomePaciente;
    private JTextField cadastroCPFPaciente;
    private JTextField cadastroEnderecoPaciente;
    private JTextField cadastroNascPaciente;
    private JTextField cadastroEmailPaciente;
    private JButton confirmaCadastroPacienteButton;
    private JButton voltarPacienteToUserButton;
    private JTextField cadastroSexoPaciente;
    private JPanel buscarPacienteUser;
    private JLabel pacienteNomeLabel;
    private JLabel pacienteCPFLabel;
    private JLabel pacienteEndereçoLabel;
    private JLabel pacienteEmailLabel;
    private JLabel pacienteNascLabel;
    private JLabel pacienteSexoLabel;
    private JButton voltarBuscaPacienteToUserButton;
    private JPanel vacinar_Jpanel;
    private JTextField aplicacaoCPFfield;
    private JTextField aplicacaoIDvacinaField;
    private JButton confirmarAplicaçãoButton;
    private JButton voltarButton;
    private JTextField aplicacaoIDloteField;
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
    private Gestão_Vacinas vacina = new Gestão_Vacinas();
    private Connection connection;
    private Lote lote1 = new Lote();
    private paciente paciente1 = new paciente();
    private String IDVacina;
    private String CPF;
    private String IDLote;

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

            AddressBD.setText(addressDB);
            JLabelUserBD.setText(userDB);
            AddressBD.setForeground(new Color(100, 180, 120));
            JLabelUserBD.setForeground(new Color(100, 180, 120));
            cadastraPacienteButton.setBackground(new Color(100, 150, 200));
            criarVacinaUserButton.setBackground(new Color(100, 150, 200));
            criarLoteUserButton.setBackground(new Color(100, 150, 200));
            realizarAplicaçãoButton.setBackground(new Color(100, 150, 200));
            buscaPacienteButton.setBackground(new Color(100, 180, 120));
            buscarVacinaButton.setBackground(new Color(100, 180, 120));
            consultaCartãoPacienteButton.setBackground(new Color(100, 180, 120));

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                closeConnection(); // Fecha conexão quando a janela da aplicação encerra
            }
        });

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performLogin();
            }
        });

        passwordField1.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    performLogin();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {}
        });

        fecharButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (connection != null) {
                    try {
                        connection.close();
                        System.out.println("\nDatabase connection closed.");
                    } catch (SQLException ex) {
                        System.err.println("Error closing database connection: " + ex.getMessage());
                        JOptionPane.showMessageDialog(null, "Error closing database connection.", "Error", JOptionPane.ERROR_MESSAGE);
                        ex.printStackTrace(); // Log the exception for debugging
                    }
                } else {
                    System.out.println("Database connection was null.");
                }
                System.out.println("Exiting the application...");
                System.out.flush();
                System.exit(0);
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
        confirmaMudançasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(newDBuserTextField.getText().isBlank() || newDBpassword.getText().isBlank() || newDBaddress.getText().isBlank()){
                    SwingUtilities.invokeLater(() -> {
                        adminDBconfigLabel1.setText("Todos os campos devem ser preenchidos");
                        adminDBconfigLabel1.setForeground(Color.RED);
                    });
                }else{
                    adminDBconfigLabel1.setText("Insira abaixo novas configurações do Banco de dados:");
                    SwingUtilities.invokeLater(() -> {
                                adminDBconfigLabel1.setForeground(Color.BLACK);
                                adminConfigChangeDB.setVisible(false);
                                landing_Jpanel.setVisible(true);
                            });
                    setUserDB();
                    setPasswordDB();
                    setPathDB();
                    storeDatabaseConfig(addressDB, userDB, passwordDB);
                }
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
        configuraçõesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                user_Jpanel.setVisible(false);
                adminConfigChangeDB.setVisible(true);
            }
        });
        sairAdminConfigButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adminConfigChangeDB.setVisible(false);
                user_Jpanel.setVisible(true);
            }
        });
        criarVacinaUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                user_Jpanel.setVisible(false);
                insere_vacina_user.setVisible(true);
            }
        });
        ConfirmaVacinaUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVacinaUser();
                try{
                    Gestão_Vacinas.registraVacina(vacina,connection);
                    insere_vacina_user.setVisible(false);
                    user_Jpanel.setVisible(true);
                    setVacinaUser();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao registrar vacina: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE); // Feedback visual com mensagem de erro
                    ex.printStackTrace(); // Imprime o stack trace para debugging
                } catch (Exception ex) { // Captura outras exceções inesperadas
                    JOptionPane.showMessageDialog(null, "Ocorreu um erro inesperado: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }
            }
        });
        voltarVacinaToConfigUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                insere_vacina_user.setVisible(false);
                user_Jpanel.setVisible(true);
            }
        });
        testarConexãoAdminConfigButton.addActionListener(new ActionListener() {
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
        adminToUserJbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(() -> {
                    admin_Jpanel.setVisible(false);
                    user_Jpanel.setVisible(true);
                    usernameLabel1.setText("Olá admin");
                });
            }
        });
        criarLoteUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(() -> {
                    criarLoteUser.setVisible(true);
                    user_Jpanel.setVisible(false);
                    batchLabel1.setVisible(false);
                });
            }
        });
        batchToUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(() -> {
                    user_Jpanel.setVisible(true);
                    criarLoteUser.setVisible(false);
                    batchLabel1.setVisible(false);
                });
            }
        });

        //Lembrete: Ainda não adicionado verificação do id da vacina
        batchConfirmaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(batchIDfield.getText().isBlank() || vaxIDfield.getText().isBlank() || batchExpiration.getText().isBlank() || batchFabField.getText().isBlank()){
                    SwingUtilities.invokeLater(() -> {
                        batchLabel1.setVisible(true);
                        batchLabel1.setForeground(Color.RED);
                        batchLabel1.setText("Todos os campos devem ser preenchidos");
                    });
                }else if(Integer.parseInt(batchQuantity.getText()) == 0){
                    SwingUtilities.invokeLater(() -> {
                        batchLabel1.setVisible(true);
                        batchLabel1.setForeground(Color.RED);
                        batchLabel1.setText("A quantidade do lote não pode ser 0");
                    });
                }else{
                    try{
                        try{
                            setBatch();
                            registerLote(lote1);
                            JOptionPane.showMessageDialog(null, "Configurações aplicadas com sucesso!", "Success", JOptionPane.INFORMATION_MESSAGE);
                            SwingUtilities.invokeLater(() -> {
                                user_Jpanel.setVisible(true);
                                criarLoteUser.setVisible(false);
                            });
                        } catch (SQLException j) {
                            JOptionPane.showMessageDialog(null, "Erro ao registrar lote: " + j.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                            j.printStackTrace(); // Optional: Print stack trace for debugging
                        } catch (NullPointerException k){
                            JOptionPane.showMessageDialog(null, "Erro: Lote ou conexão nula. Verifique se os dados estão corretos.", "Erro", JOptionPane.ERROR_MESSAGE);
                            k.printStackTrace();
                        } catch(Exception l) {
                            JOptionPane.showMessageDialog(null, "Ocorreu um erro inesperado: " + l.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                            l.printStackTrace();
                        }
                    } catch (NumberFormatException g) {
                        JOptionPane.showMessageDialog(null, g.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                        g.printStackTrace(); // Optional: Print stack trace for debugging
                    } catch (IllegalArgumentException h) {
                        JOptionPane.showMessageDialog(null, h.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                        h.printStackTrace();
                    } catch (DateTimeParseException i) {
                        JOptionPane.showMessageDialog(null, i.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                        i.printStackTrace();
                    }
                }


            }
        });
        buscarVacinaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(() -> {
                    try {
                        if (searchVaxField1.getText().isBlank()) {
                            JOptionPane.showMessageDialog(null, "O campo ID está vazio", "Erro", JOptionPane.ERROR_MESSAGE);
                        } else {
                            searchVax();
                            searchVaxField1.setText("");
                            SwingUtilities.invokeLater(() -> {
                                buscarVacinaUser.setVisible(true);
                                user_Jpanel.setVisible(false);
                            });
                        }
                    } catch (SQLException m) {
                        JOptionPane.showMessageDialog(null, "Erro ao consultar o banco de dados: " + m.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                        SwingUtilities.invokeLater(() -> {
                            searchVaxField1.setText("");
                        });
                        m.printStackTrace();
                    } catch (NumberFormatException n) {
                        JOptionPane.showMessageDialog(null, "Erro no formato da entrada", "Erro", JOptionPane.ERROR_MESSAGE);
                        SwingUtilities.invokeLater(() -> {
                            searchVaxField1.setText("");
                        });
                        n.printStackTrace();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                        ex.printStackTrace();
                    }
                });
            }
        });
        voltarVacinaToUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(() -> {
                    buscarVacinaUser.setVisible(false);
                    user_Jpanel.setVisible(true);
                });
            }
        });
        voltarPacienteToUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(() -> {
                    cadastraPacienteUser.setVisible(false);
                    user_Jpanel.setVisible(true);
                });
            }
        });
        cadastraPacienteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(() -> {
                    user_Jpanel.setVisible(false);
                    cadastraPacienteUser.setVisible(true);
                });
            }
        });
        confirmaCadastroPacienteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(cadastroNomePaciente.getText().isBlank() || cadastroCPFPaciente.getText().isBlank() || cadastroEmailPaciente.getText().isBlank() || cadastroEnderecoPaciente.getText().isBlank() || cadastroNascPaciente.getText().isBlank()){
                    JOptionPane.showMessageDialog(null, "Todos os campos devem estar preenchidos!", "Erro", JOptionPane.ERROR_MESSAGE);
                }else {
                    try {
                        setPaciente();
                        registerPaciente(paciente1);
                        SwingUtilities.invokeLater(() -> {
                            cadastraPacienteUser.setVisible(false);
                            user_Jpanel.setVisible(true);
                        });

                    } catch (SQLException m) {
                        JOptionPane.showMessageDialog(null, "Erro ao consultar o banco de dados: " + m.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                        SwingUtilities.invokeLater(() -> { searchPacienteField1.setText(""); });
                        m.printStackTrace();
                    } catch (NumberFormatException n) {
                        JOptionPane.showMessageDialog(null, "Erro no formato da entrada", "Erro", JOptionPane.ERROR_MESSAGE);
                        SwingUtilities.invokeLater(() -> { searchPacienteField1.setText(""); });
                        n.printStackTrace();
                    } catch (IllegalArgumentException h) {
                        JOptionPane.showMessageDialog(null, h.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                        h.printStackTrace();
                    } catch (DateTimeParseException i) {
                        JOptionPane.showMessageDialog(null, i.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                        i.printStackTrace();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Erro desconhecido" + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                        ex.printStackTrace();
                    }
                }
            }
        });
        voltarBuscaPacienteToUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(() -> {
                    buscarPacienteUser.setVisible(false);
                    user_Jpanel.setVisible(true);
                });
            }
        });
        buscaPacienteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(() -> {
                    try {
                        if (searchPacienteField1.getText().isBlank()) {
                            JOptionPane.showMessageDialog(null, "O campo nome está vazio", "Erro", JOptionPane.ERROR_MESSAGE);
                        } else {
                            searchPaciente();

                                searchPacienteField1.setText("");
                                buscarPacienteUser.setVisible(true);
                                user_Jpanel.setVisible(false);

                        }
                    } catch (SQLException m) {
                        JOptionPane.showMessageDialog(null, "Erro ao consultar o banco de dados: " + m.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                        searchPacienteField1.setText("");
                        m.printStackTrace();
                    } catch (NumberFormatException n) {
                        JOptionPane.showMessageDialog(null, "Erro no formato da entrada", "Erro", JOptionPane.ERROR_MESSAGE);
                        searchPacienteField1.setText("");
                        n.printStackTrace();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                        ex.printStackTrace();
                    }
                });
            }

        });
        realizarAplicaçãoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(() -> {
                    user_Jpanel.setVisible(false);
                    vacinar_Jpanel.setVisible(true);
                });
            }
        });
        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(() -> {
                   vacinar_Jpanel.setVisible(false);
                   user_Jpanel.setVisible(true);
                });
            }
        });
        confirmarAplicaçãoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(() -> {
                    if (aplicacaoCPFfield.getText().isBlank() || aplicacaoIDvacinaField.getText().isBlank() || aplicacaoIDloteField.getText().isBlank()) {
                        JOptionPane.showMessageDialog(null, "Todos os campos devem estar preenchidos", "Erro", JOptionPane.ERROR_MESSAGE);
                    }else{
                        try {
                            setIDVacina();
                            setCPFvacina();
                            setIDLote();
                            if(checkVacinaPaciente(IDVacina, IDLote, CPF, connection)) {
                                registerAplicacao(CPF, IDVacina, IDLote);
                                aplicacaoCPFfield.setText("");
                                aplicacaoIDvacinaField.setText("");
                                aplicacaoIDloteField.setText("");
                            }else{
                                JOptionPane.showMessageDialog(null, "Vacina ou paciente não encontrados", "Erro", JOptionPane.ERROR_MESSAGE);
                                aplicacaoCPFfield.setText("");
                                aplicacaoIDvacinaField.setText("");
                                aplicacaoIDloteField.setText("");
                            }
                        } catch (SQLException n) {
                            aplicacaoCPFfield.setText("");
                            aplicacaoIDvacinaField.setText("");
                            aplicacaoIDloteField.setText("");
                            JOptionPane.showMessageDialog(null, "Erro ao registrar aplicação: " + n.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                            n.printStackTrace();
                        } catch (NullPointerException m) {
                            aplicacaoCPFfield.setText("");
                            aplicacaoIDvacinaField.setText("");
                            aplicacaoIDloteField.setText("");
                            JOptionPane.showMessageDialog(null, "Erro: CPF ou ID da Vacina nulo.", "Erro", JOptionPane.ERROR_MESSAGE);
                            m.printStackTrace();
                        } catch (Exception o) {
                            aplicacaoCPFfield.setText("");
                            aplicacaoIDvacinaField.setText("");
                            aplicacaoIDloteField.setText("");
                            JOptionPane.showMessageDialog(null, o.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                            o.printStackTrace();
                        }
                    }

                });
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
    public void setIDVacina(){this.IDVacina = aplicacaoIDvacinaField.getText();}
    public void setCPFvacina(){this.CPF = aplicacaoCPFfield.getText();}
    public void setIDLote(){this.IDLote = aplicacaoIDloteField.getText();}
    public void setVacinaUser(){
        this.vacina.id_vacina = Integer.parseInt(new String(idvacinaUSER.getText()));
        this.vacina.nome_vacina = new String(nomevacinaUSER.getText());
        this.vacina.tipo_vacina = new String(tipovacinaUSER.getText());
        this.vacina.fabricante = new String(fabricantevacinaUSER.getText());
    }
    public void setBatch() throws IllegalArgumentException{
        this.lote1.id_lote = Integer.parseInt(batchIDfield.getText());
        this.lote1.id_vacina = Integer.parseInt(vaxIDfield.getText());
        this.lote1.data_fab = batchFabField.getText();
        this.lote1.data_val = batchExpiration.getText();
        this.lote1.quantidade = Integer.parseInt(batchQuantity.getText());
    }
    public void setPaciente() throws IllegalArgumentException{
        this.paciente1.nome = cadastroNomePaciente.getText();
        this.paciente1.email = cadastroEmailPaciente.getText();
        this.paciente1.cpf = cadastroCPFPaciente.getText();
        this.paciente1.dataNascimento = cadastroNascPaciente.getText();
        this.paciente1.endereco = cadastroEnderecoPaciente.getText();
        this.paciente1.sexo = cadastroSexoPaciente.getText();
    }

    private void performLogin(){
        setPassword();
        setUsername();
        SwingUtilities.invokeLater(() -> {
            textField1.setText("");
            passwordField1.setText("");
        });
        if(auth().equals("admin")){
            SwingUtilities.invokeLater(() -> {
                landing_Jpanel.setVisible(false);
                admin_Jpanel.setVisible(true);
            });
        }
        if(auth().equals("user")){
            try {
                String query = "SELECT password, salt FROM users WHERE username = ?";
                PreparedStatement s = connection.prepareStatement(query);
                s.setString(1, username);
                ResultSet rs = s.executeQuery();

                if (rs.next()) { // Check if a row was returned
                    String passwordHashFromDB = rs.getString("password"); // Use column names
                    //System.out.println(passwordHashFromDB);
                    String salt = rs.getString("salt");

                    if (salt != null) { // Handle the case where the salt is null
                        String enteredPasswordHash = Crypto.pbkdf2Hash(password, salt); // Hash the entered password with the retrieved salt.
                        if (enteredPasswordHash.equals(passwordHashFromDB)) {
                            SwingUtilities.invokeLater(() -> {
                                landing_Jpanel.setVisible(false);
                                user_Jpanel.setVisible(true);
                                usernameLabel1.setText("Olá, " + username);
                            });
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
            //Gestão_Vacinas.test();
        }
    }

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

    public boolean checkVacinaPaciente(String idVacina, String idlote, String cpf, Connection connection) throws SQLException {
        boolean vacinaExists = false;
        boolean pacienteExists = false;
        boolean loteExists = false;

        // Check if vacina exists
        String queryVacina = "SELECT 1 FROM vacina_padronizada WHERE id_vacina = ?";
        try (PreparedStatement psVacina = connection.prepareStatement(queryVacina)) {
            psVacina.setInt(1, Integer.parseInt(idVacina));
            try (ResultSet rsVacina = psVacina.executeQuery()) {
                vacinaExists = rsVacina.next(); // True if a row is found
            }
        }

        // Check if paciente exists
        String queryPaciente = "SELECT 1 FROM paciente WHERE cpf = ?";
        try (PreparedStatement psPaciente = connection.prepareStatement(queryPaciente)) {
            psPaciente.setString(1, cpf);
            try (ResultSet rsPaciente = psPaciente.executeQuery()) {
                pacienteExists = rsPaciente.next(); // True if a row is found
            }
        }

        // Check if lote exists
        String queryLote = "SELECT 1 FROM lote WHERE id_lote = ?";
        try (PreparedStatement psLote = connection.prepareStatement(queryLote)) {
            psLote.setInt(1, Integer.parseInt(idlote));
            try (ResultSet rsLote = psLote.executeQuery()) {
                loteExists = rsLote.next(); // True if a row is found
            }
        }

        return vacinaExists && pacienteExists && loteExists;
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
    public void searchVax() throws SQLException, NumberFormatException, Exception{
        int id = Integer.parseInt(searchVaxField1.getText());
        query = "SELECT vp.id_vacina, vp.nome_vacina, vp.tipo_vacina, vp.fabricante, l.id_lote, l.data_validade, l.quantidade FROM vacina_padronizada vp INNER JOIN lote l ON vp.id_vacina = l.id_vacina WHERE vp.id_vacina = ?;";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if (!rs.isBeforeFirst()) {
            throw new Exception("Vacina não encontrada com o ID: " + id);
        }else {
            while(rs.next()) {
                vaxSearchID.setText(String.valueOf(rs.getInt("id_vacina")));
                vaxSearchName.setText(rs.getString("nome_vacina"));
                vaxSearchType.setText(rs.getString("tipo_vacina"));
                vaxSearchFab.setText(rs.getString("fabricante"));
                vaxSearchbatch.setText(String.valueOf(rs.getInt("id_lote")));
                vaxSearchExpire.setText(String.valueOf(rs.getDate("data_validade")));
                vaxBatchAmount.setText(String.valueOf(rs.getInt("quantidade")));
            }
        }

    }
    public void registerLote(Lote lote) throws SQLException, NullPointerException, DateTimeParseException, Exception{
            PreparedStatement insertState = connection.prepareStatement("INSERT INTO lote(id_lote, id_vacina, data_fabricacao, data_validade, quantidade) VALUES (?,?,?,?,?)");
            insertState.setInt(1, lote.id_lote);
            insertState.setInt(2, lote.id_vacina);
            // Parse and format data_fab
            insertState.setDate(3, parseAndFormatDate(lote.data_fab));
            // Parse and format data_val
            insertState.setDate(4, parseAndFormatDate(lote.data_val));
            insertState.setInt(5, lote.quantidade);

            insertState.executeUpdate();
            JOptionPane.showMessageDialog(null, "Lote registrado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
    }

    public void registerPaciente(paciente paciente) throws SQLException, NullPointerException, DateTimeParseException, Exception{
            PreparedStatement insertState = connection.prepareStatement("INSERT INTO paciente(nome, cpf, endereco, email, data_nasc, sexo) VALUES (?,?,?,?,?,?)");
            insertState.setString(1, paciente.nome);
            insertState.setString(2, paciente.cpf);
            insertState.setString(3, paciente.endereco);
            insertState.setString(4, paciente.email);
            insertState.setDate(5, parseAndFormatDate(paciente.dataNascimento));
            insertState.setString(6, paciente.sexo);
            insertState.executeUpdate();
        JOptionPane.showMessageDialog(null, "Paciente registrado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);

    }

    public void registerAplicacao(String CPF, String IDVaxx, String idLote) throws SQLException, NullPointerException, DateTimeParseException, Exception{
        int idVacina = Integer.parseInt(IDVaxx);
        int loteId = Integer.parseInt(idLote);

        String queryLote = "SELECT quantidade FROM lote WHERE id_lote = ? AND id_vacina = ?";
        PreparedStatement psLote = connection.prepareStatement(queryLote);
        psLote.setInt(1, loteId);
        psLote.setInt(2, idVacina);
        ResultSet rsLote = psLote.executeQuery();
        if (rsLote.next()) {
            int quantidadeAtual = rsLote.getInt("quantidade");
            if (quantidadeAtual > 0) {
                String updateQuery = "UPDATE lote SET quantidade = ? WHERE id_lote = ? AND id_vacina = ?";
                PreparedStatement updatePs = connection.prepareStatement(updateQuery);
                updatePs.setInt(1, quantidadeAtual - 1);
                updatePs.setInt(2, loteId);
                System.out.println(loteId);
                System.out.println(idVacina);
                updatePs.setInt(3, idVacina);
                updatePs.executeUpdate();

                PreparedStatement insertState = connection.prepareStatement("INSERT INTO historico_vacinacao(cpf_aplicacao, id_vacina_aplicacao, data_aplicacao) VALUES (?,?,?)");
                insertState.setString(1, CPF);
                insertState.setInt(2, idVacina);
                LocalDate currentDate = LocalDate.now();
                Date sqlDate = Date.valueOf(currentDate);
                insertState.setDate(3, sqlDate);
                insertState.executeUpdate();
                JOptionPane.showMessageDialog(null, "Aplicação registrada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            }else{
                throw new Exception("Lote sem vacinas disponíveis.");
            }
        }else{
            throw new Exception("Lote não encontrado.");
        }
    }

    public void searchPaciente() throws SQLException, NumberFormatException, Exception {
        String name = searchPacienteField1.getText();
        query = "SELECT nome, cpf, endereco, email, data_nasc, sexo FROM paciente WHERE nome = ?;";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, name);
        ResultSet rs = ps.executeQuery();
        if (!rs.isBeforeFirst()) {
            throw new Exception("Pessoa não encontrada com o Nome: " + name);
        } else {
            while (rs.next()) {
                pacienteNomeLabel.setText(rs.getString("nome"));
                pacienteCPFLabel.setText(rs.getString("cpf"));
                pacienteEmailLabel.setText(rs.getString("email"));
                pacienteEndereçoLabel.setText(rs.getString("endereco"));
                pacienteNascLabel.setText(String.valueOf(rs.getString("data_nasc")));
                pacienteSexoLabel.setText(rs.getString("sexo"));
            }
        }
    }

    private Date parseAndFormatDate(String dateString) throws DateTimeParseException {
        if (dateString == null || dateString.isEmpty()) {
            return null; // Handle null or empty date strings as needed
        }

        DateTimeFormatter[] formatters = {
                DateTimeFormatter.ofPattern("dd/MM/yyyy"),
                DateTimeFormatter.ofPattern("dd-MM-yyyy")
        };

        LocalDate localDate = null;
        for (DateTimeFormatter formatter : formatters) {
            try {
                localDate = LocalDate.parse(dateString, formatter);
                break; // Found a matching format
            } catch (DateTimeParseException e) {
                // Try the next formatter
            }
        }

        if (localDate == null) {
            throw new DateTimeParseException("Invalid date format", dateString, 0); //Throw exception if no format matches.
        }

        return Date.valueOf(localDate);
    }

    public static void main(String[] args) {
        new GUI();
    }
}
