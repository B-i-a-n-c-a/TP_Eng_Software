package es1;

import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import javax.swing.table.DefaultTableModel;
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
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.formdev.flatlaf.*;

//Esta classe inicia a interface de usuário e interage com os demais recursos da aplicação

public class GUI extends JFrame {
    String fileRootDir;
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
    private JLabel pacienteConsultaVacinaNome;
    private JButton exportarButton;
    private JButton voltarHistoricoToUserButton;
    private JPanel buscaHistorico;
    private JTextField consultaHistoricoField;
    private JTable queryResultTable;
    private JTextField cadastraUserCPF;
    private JTextField cadastraUserEndereco;
    private JTextField cadastraUserNasc;
    private JTextField cadastraUserEmail;
    private JTextField cadastraUserCRM;
    private JPasswordField CadastraUserConfirmaPassword;
    private JButton botaoAlternaModo;
    private JLabel warningUser;
    private String password;
    private String username;
    private String adminSalt = "adminpbkdf2";
    private String adminHash = "fTcZzt8MdjGl0/Ce6BgPGPSSHAai3UPIp7JyKAJj+Uo=";
    private String userDB;
    private String passwordDB;
    private String addressDB;
    private String cadastraUserNome1;
    private String cadastraUserSenha1;
    private String cadastraUserSenha2;
    private String query;
    private Gestão_Vacinas vacina = new Gestão_Vacinas();
    private Connection connection;
    private Lote lote1 = new Lote();
    private paciente paciente1 = new paciente();
    private String IDVacina;
    private String CPF;
    private String IDLote;
    private ResultSet importRS;
    private Funcionario funcionario1 = new Funcionario();
    private String CPF_TEMP;
    private boolean modoEscuroAtivo = true;

    public GUI() {
        SwingUtilities.invokeLater(() -> {
            warningUser.setText("");
        });
        DatabaseConfigManager();
        retrieveDatabaseConfig();
        try {
            makeConnection(addressDB, userDB, passwordDB);
        } catch (SQLException g) {
            JOptionPane.showMessageDialog(null, "Erro ao finalizar conexão: " + g.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            g.printStackTrace();
        }

        setContentPane(background);
        setTitle("Sistema vacinação");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 400);
        setLocationRelativeTo(null);
        setVisible(true);
        SwingUtilities.invokeLater(() -> {
            AddressBD.setText(addressDB);
            AddressBD.setForeground(new Color(50, 150, 50));
            JLabelUserBD.setText(userDB);
            JLabelUserBD.setForeground(new Color(50, 150, 50));
            vaxSearchID.setForeground(new Color(50, 150, 50));
            vaxSearchbatch.setForeground(new Color(50, 150, 50));
            vaxSearchExpire.setForeground(new Color(50, 150, 50));
            vaxSearchFab.setForeground(new Color(50, 150, 50));
            vaxSearchName.setForeground(new Color(50, 150, 50));
            vaxSearchType.setForeground(new Color(50, 150, 50));
            vaxBatchAmount.setForeground(new Color(50, 150, 50));
            pacienteNomeLabel.setForeground(new Color(50, 150, 50));
            pacienteCPFLabel.setForeground(new Color(50, 150, 50));
            pacienteNascLabel.setForeground(new Color(50, 150, 50));
            pacienteEmailLabel.setForeground(new Color(50, 150, 50));
            pacienteSexoLabel.setForeground(new Color(50, 150, 50));
            pacienteEndereçoLabel.setForeground(new Color(50, 150, 50));

            if (!modoEscuroAtivo) {
                botaoAlternaModo.setText("Modo Escuro");
                cadastraPacienteButton.setBackground(new Color(210, 230, 250));
                criarVacinaUserButton.setBackground(new Color(190, 220, 245));
                realizarAplicaçãoButton.setBackground(new Color(180, 210, 240));
                buscaPacienteButton.setBackground(new Color(220, 235, 220));
                buscarVacinaButton.setBackground(new Color(200, 235, 200));
                consultaCartãoPacienteButton.setBackground(new Color(180, 235, 180));
                queryResultTable.setBackground(new Color(220, 220, 220));
            } else {
                botaoAlternaModo.setText("Modo Claro");
                cadastraPacienteButton.setBackground(new Color(50, 70, 90)); // Azul escuro
                criarVacinaUserButton.setBackground(new Color(60, 80, 100)); // Azul escuro um pouco mais claro
                realizarAplicaçãoButton.setBackground(new Color(70, 90, 110)); // Azul escuro um pouco mais claro
                buscaPacienteButton.setBackground(new Color(70, 90, 70)); // Verde escuro
                buscarVacinaButton.setBackground(new Color(80, 100, 80)); // Verde escuro um pouco mais claro
                consultaCartãoPacienteButton.setBackground(new Color(90, 110, 90)); // Verde escuro um pouco mais claro
                queryResultTable.setBackground(new Color(50, 50, 50)); // Cinza escuro
            }
            });


        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if(connection != null){
                    closeConnection(); // Fecha conexão quando a janela da aplicação encerra
                }else{
                    System.out.println("Connection is null");
                }
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
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, "Erro ao finalizar conexão.", "Erro", JOptionPane.ERROR_MESSAGE);
                        ex.printStackTrace();
                    }
                } else {
                    System.out.println("Database connection was null.");
                }
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
                    setUserDB();
                    setPasswordDB();
                    setPathDB();
                    storeDatabaseConfig(addressDB, userDB, passwordDB);
                    if (connection != null) {
                        try {
                            connection.close();
                        } catch (SQLException ex) {
                            System.err.println("Error closing database connection: " + ex.getMessage());
                            JOptionPane.showMessageDialog(null, "Erro ao finalizar conexão.", "Erro", JOptionPane.ERROR_MESSAGE);
                            ex.printStackTrace();
                        }
                    } else {
                        System.out.println("Database connection was null.");
                    }
                    System.out.flush();
                    System.exit(0);

                }
            }
        });
        cadastraUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(() -> {
                    admin_Jpanel.setVisible(false);
                    cadastra_user.setVisible(true);
                });
            }
        });
        cadastraToAdminButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(() -> {
                    cadastra_user.setVisible(false);
                    admin_Jpanel.setVisible(true);
                });
            }
        });
        confirmaCadastroUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(() -> {
                    if(CadastraUserNome.getText().isBlank() || cadastraUserCPF.getText().isBlank() || cadastraUserEmail.getText().isBlank() || cadastraUserCRM.getText().isBlank() || cadastraUserNasc.getText().isBlank() || cadastraUserEndereco.getText().isBlank()){
                        JOptionPane.showMessageDialog(null, "Todos os campos devem estar preenchidos!", "Erro", JOptionPane.ERROR_MESSAGE);
                    }else {
                        try {
                            setCadastraUserNome();
                            setCadastraUserSenha();
                            setCadastraUserConfirmaSenha();
                            if(comparePasswords(CadastraUserSenha, CadastraUserConfirmaPassword)) {
                                setFuncionario();
                                String salt = Crypto.generateSalt(16);
                                String hash = Crypto.pbkdf2Hash(getCadastraUserSenha(), salt);
                                registerUser(getCadastraUsernome(), hash, salt, funcionario1);
                                JOptionPane.showMessageDialog(null, "Funcionário registrado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                                cadastra_user.setVisible(false);
                                admin_Jpanel.setVisible(true);
                                CadastraUserNome.setText("");
                                CadastraUserSenha.setText("");
                                CadastraUserConfirmaPassword.setText("");
                                cadastraUserEndereco.setText("");
                                cadastraUserNasc.setText("");
                                cadastraUserEmail.setText("");
                                cadastraUserCRM.setText("");
                                cadastraUserCPF.setText("");
                            }else{
                                JOptionPane.showMessageDialog(null, "As senhas devem ser iguais!", "Erro", JOptionPane.ERROR_MESSAGE);
                                CadastraUserSenha.setText("");
                                CadastraUserConfirmaPassword.setText("");
                            }
                        } catch (SQLException m) {
                            JOptionPane.showMessageDialog(null, m.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                            m.printStackTrace();
                            CadastraUserNome.setText("");
                            CadastraUserSenha.setText("");
                            CadastraUserConfirmaPassword.setText("");
                            cadastraUserEndereco.setText("");
                            cadastraUserNasc.setText("");
                            cadastraUserEmail.setText("");
                            cadastraUserCRM.setText("");
                            cadastraUserCPF.setText("");
                        } catch (NumberFormatException n) {
                            JOptionPane.showMessageDialog(null, "Erro no formato da entrada", "Erro", JOptionPane.ERROR_MESSAGE);
                            n.printStackTrace();
                            CadastraUserNome.setText("");
                            CadastraUserSenha.setText("");
                            CadastraUserConfirmaPassword.setText("");
                            cadastraUserEndereco.setText("");
                            cadastraUserNasc.setText("");
                            cadastraUserEmail.setText("");
                            cadastraUserCRM.setText("");
                            cadastraUserCPF.setText("");
                        } catch (IllegalArgumentException h) {
                            JOptionPane.showMessageDialog(null, h.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                            h.printStackTrace();
                            CadastraUserNome.setText("");
                            CadastraUserSenha.setText("");
                            CadastraUserConfirmaPassword.setText("");
                            cadastraUserEndereco.setText("");
                            cadastraUserNasc.setText("");
                            cadastraUserEmail.setText("");
                            cadastraUserCRM.setText("");
                            cadastraUserCPF.setText("");
                        } catch (DateTimeParseException i) {
                            JOptionPane.showMessageDialog(null, i.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                            i.printStackTrace();
                            CadastraUserNome.setText("");
                            CadastraUserSenha.setText("");
                            CadastraUserConfirmaPassword.setText("");
                            cadastraUserEndereco.setText("");
                            cadastraUserNasc.setText("");
                            cadastraUserEmail.setText("");
                            cadastraUserCRM.setText("");
                            cadastraUserCPF.setText("");
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(null, "Erro desconhecido" + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                            ex.printStackTrace();
                            CadastraUserNome.setText("");
                            CadastraUserSenha.setText("");
                            CadastraUserConfirmaPassword.setText("");
                            cadastraUserEndereco.setText("");
                            cadastraUserNasc.setText("");
                            cadastraUserEmail.setText("");
                            cadastraUserCRM.setText("");
                            cadastraUserCPF.setText("");
                        }
                    }
                });
            }
        });
        configuraçõesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(()-> {
                    user_Jpanel.setVisible(false);
                    adminConfigChangeDB.setVisible(true);
                });
            }
        });
        sairAdminConfigButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(()-> {
                    adminConfigChangeDB.setVisible(false);
                    user_Jpanel.setVisible(true);
                });
            }
        });
        criarVacinaUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(()-> {
                    user_Jpanel.setVisible(false);
                    insere_vacina_user.setVisible(true);
                });
            }
        });
        ConfirmaVacinaUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(()-> {
                    if (idvacinaUSER.getText().isBlank() || tipovacinaUSER.getText().isBlank() || fabricantevacinaUSER.getText().isBlank() || nomevacinaUSER.getText().isBlank()) {
                        JOptionPane.showMessageDialog(null, "Todos os campos devem estar preenchidos!", "Erro", JOptionPane.ERROR_MESSAGE);
                    } else {
                        try {
                            setVacinaUser();
                            Gestão_Vacinas.registraVacina(vacina, connection);
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
            }
        });
        voltarVacinaToConfigUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(()-> {
                    insere_vacina_user.setVisible(false);
                    user_Jpanel.setVisible(true);
                });
            }
        });
        testarConexãoAdminConfigButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    makeConnection(addressDB, userDB, passwordDB);
                    JOptionPane.showMessageDialog(null, "Conexão bem-sucedida","Atenção", JOptionPane.PLAIN_MESSAGE);
                } catch (SQLException g) {
                    JOptionPane.showMessageDialog(null, "Erro ao finalizar conexão: " + g.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
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
                            j.printStackTrace();
                        } catch (NullPointerException k){
                            JOptionPane.showMessageDialog(null, "Erro: Lote ou conexão nula. Verifique se os dados estão corretos.", "Erro", JOptionPane.ERROR_MESSAGE);
                            k.printStackTrace();
                        } catch(Exception l) {
                            JOptionPane.showMessageDialog(null, "Ocorreu um erro inesperado: " + l.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                            l.printStackTrace();
                        }
                    } catch (NumberFormatException g) {
                        JOptionPane.showMessageDialog(null, g.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                        g.printStackTrace();
                    } catch (IllegalArgumentException h) {
                        JOptionPane.showMessageDialog(null, h.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                        h.printStackTrace();
                    } catch (DateTimeParseException i) {
                        JOptionPane.showMessageDialog(null, i.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
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
                            buscarVacinaUser.setVisible(true);
                            user_Jpanel.setVisible(false);
                        }
                    } catch (SQLException m) {
                        JOptionPane.showMessageDialog(null, "Erro ao consultar o banco de dados: " + m.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                        searchVaxField1.setText("");
                        m.printStackTrace();
                    } catch (NumberFormatException n) {
                        JOptionPane.showMessageDialog(null, "Erro no formato da entrada", "Erro", JOptionPane.ERROR_MESSAGE);
                        searchVaxField1.setText("");
                        n.printStackTrace();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                        searchVaxField1.setText("");
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
                        JOptionPane.showMessageDialog(null, h.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                        h.printStackTrace();
                    } catch (DateTimeParseException i) {
                        JOptionPane.showMessageDialog(null, i.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
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
                        searchPacienteField1.setText("");
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
        consultaCartãoPacienteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(() -> {
                    if(consultaHistoricoField.getText().isBlank()){
                        JOptionPane.showMessageDialog(null, "Campo CPF não preenchido!", "Erro", JOptionPane.ERROR_MESSAGE);
                    }else {
                        try {
                            searchHistorico();
                            user_Jpanel.setVisible(false);
                            buscaHistorico.setVisible(true);
                            consultaHistoricoField.setText("");
                        }catch (SQLException ex) {
                            JOptionPane.showMessageDialog(null, "Erro no banco de dados:" + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                            ex.printStackTrace();
                            consultaHistoricoField.setText("");
                        }catch (IllegalArgumentException ey){
                            JOptionPane.showMessageDialog(null, ey.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                            ey.printStackTrace();
                            consultaHistoricoField.setText("");
                        }catch (Exception ez){
                            JOptionPane.showMessageDialog(null, ez.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                            ez.printStackTrace();
                            consultaHistoricoField.setText("");
                        }
                    }
                });
            }
        });
        voltarHistoricoToUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(() -> {
                   buscaHistorico.setVisible(false);
                   user_Jpanel.setVisible(true);
                });
            }
        });
        exportarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    PreparedStatement statement = connection.prepareStatement("SELECT nome, data_nasc FROM paciente WHERE cpf = ?");
                    statement.setString(1, CPF_TEMP);
                    ResultSet rs = statement.executeQuery();
                    String nome = new String();
                    Date datanasc = null;
                    if(rs.next()){
                        nome = rs.getString("nome");
                        datanasc = rs.getDate("data_nasc");
                    }
                    // Reset the ResultSet
                    importRS.beforeFirst();
                    // Export to PDF
                    exportResultSetToPdf(importRS, (JFrame) SwingUtilities.getWindowAncestor(buscaHistorico), nome, datanasc);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(buscaHistorico, "Erro ao exportar o PDF: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        botaoAlternaModo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(()-> {
                    alternarModo();
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
    private void setCadastraUserConfirmaSenha() {this.cadastraUserSenha2 = new String(CadastraUserConfirmaPassword.getPassword());}
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
    public void setFuncionario() throws IllegalArgumentException{
        this.funcionario1.nome = CadastraUserNome.getText();
        this.funcionario1.cpf = cadastraUserCPF.getText();
        this.funcionario1.email = cadastraUserEmail.getText();
        this.funcionario1.idade = parseAndFormatDate(cadastraUserNasc.getText());
        this.funcionario1.endereco = cadastraUserEndereco.getText();
        this.funcionario1.crm_coren = cadastraUserCRM.getText();
    }

    private void performLogin(){
        setPassword();
        setUsername();
        SwingUtilities.invokeLater(() -> {
            textField1.setText("");
            passwordField1.setText("");
            searchPacienteField1.setText("");
            searchVaxField1.setText("");
            consultaHistoricoField.setText("");
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
                            // Incorrect password
                            JOptionPane.showMessageDialog(null, "Senha incorreta.", "Erro", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        // Handle the case where the user has no salt in the database.
                        JOptionPane.showMessageDialog(null, "Ocorreu um erro ao recuperar a senha da base de dados.", "Erro", JOptionPane.ERROR_MESSAGE);
                    }

                } else {
                    // User not found
                    JOptionPane.showMessageDialog(null, "Usuário não encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
                }

                rs.close();
                s.close();

            } catch (SQLException f) {
                f.printStackTrace();
                JOptionPane.showMessageDialog(null, "Erro na base de dados: " + f.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE); // Inform user of database error
            }
        }
    }

    public void DatabaseConfigManager() {
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("win")) {
            fileRootDir = System.getenv("APPDATA") + "/SysVac/temp/database_config.bin";
        } else {
            fileRootDir = System.getProperty("user.home") + "/.SysVac/temp/database_config.bin";
        }
    }

    public void storeDatabaseConfig(String address, String user, String password) {
        Map<String, String> config = new HashMap<>();
        config.put("address", address);
        config.put("user", user);
        config.put("password", password);

        File file = new File(fileRootDir);
        File parentDir = file.getParentFile();
        if(parentDir != null && !parentDir.exists()){
            parentDir.mkdirs();
        }

        // Write the configuration to the file, overwriting if it exists.
        try (FileOutputStream fileOut = new FileOutputStream(file);
             ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)) {
            objectOut.writeObject(config); // Write the map to the file
            JOptionPane.showMessageDialog(null, "Configuração da base de dados salva em: " + file.getAbsolutePath(), "Atenção", JOptionPane.WARNING_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Configuração da base de dados falhou !", "Atenção", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void retrieveDatabaseConfig() {
        File file = new File(fileRootDir);

        // Check if the file exists
        if (!file.exists()) {
            JOptionPane.showMessageDialog(null, "Configuração falhou. Mudando para configuração default", "Error", JOptionPane.WARNING_MESSAGE);
            warningUser.setText("Apenas consultas são permitidas no momento");
            warningUser.setForeground(Color.RED);
            this.addressDB = "jdbc:postgresql://localhost:5432/postgres";
            this.userDB = "defaultexception";
            this.passwordDB = "12345";
            return;
        }

        // Read the configuration from the file
        try (FileInputStream fileIn = new FileInputStream(file);
             ObjectInputStream objectIn = new ObjectInputStream(fileIn)) {
            Map<String, String> config = (Map<String, String>) objectIn.readObject(); // Read the map from the file
            this.addressDB = ("jdbc:postgresql://" + config.get("address") + "/postgres");
            this.userDB = config.get("user");
            this.passwordDB = config.get("password");

        } catch (IOException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Configuração falhou. Mudando para configuração default", "Error", JOptionPane.ERROR_MESSAGE);
            this.addressDB = "jdbc:postgresql://localhost:5432/postgres";
            this.userDB = "defaultexception";
            this.passwordDB = "12345";
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

    public void registerUser(String username, String hashpassword, String salt, Funcionario func1)throws SQLException, DateTimeParseException, Exception{
                PreparedStatement checkStatement = connection.prepareStatement("SELECT username FROM users WHERE username = ?");
                checkStatement.setString(1, username);
                ResultSet resultSetTest = checkStatement.executeQuery();
                if(resultSetTest.next()){
                    JOptionPane.showMessageDialog(null, "Este usuário já existe. Registrando apenas o funcionário.", "Atenção", JOptionPane.INFORMATION_MESSAGE);
                }else {
                    PreparedStatement insertState = connection.prepareStatement("INSERT INTO users(username, password, salt) VALUES (?,?,?)");
                    insertState.setString(1, username);
                    insertState.setString(2, hashpassword);
                    insertState.setString(3, salt);
                    insertState.execute();
                }

                PreparedStatement insertState = connection.prepareStatement("INSERT INTO funcionarios(nome, cpf, endereco, data_nasc, email, crm_coren) VALUES (?,?,?,?,?,?)");
                insertState.setString(1, func1.getNome());
                insertState.setString(2, func1.getCpf());
                insertState.setString(3, func1.getEndereco());
                insertState.setDate(4, func1.idade);
                insertState.setString(5, func1.getEmail());
                insertState.setString(6, func1.crm_coren);
                insertState.execute();
                JOptionPane.showMessageDialog(null, "Funcionário registrado", "Atenção", JOptionPane.INFORMATION_MESSAGE);
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

    public void searchHistorico() throws SQLException, IllegalArgumentException, Exception{
        CPF_TEMP = consultaHistoricoField.getText();
        pacienteConsultaVacinaNome.setText(consultaHistoricoField.getText());
        query = "SELECT vacina_padronizada.nome_vacina AS \"Nome da Vacina\", vacina_padronizada.tipo_vacina AS \"Tipo da Vacina\", historico_vacinacao.data_aplicacao AS \"Data de Aplicação\", historico_vacinacao.nome_funcionario AS \"Profissional Responsável\" FROM historico_vacinacao JOIN vacina_padronizada ON historico_vacinacao.id_vacina_aplicacao = vacina_padronizada.id_vacina JOIN paciente ON historico_vacinacao.cpf_aplicacao = paciente.cpf WHERE paciente.cpf = ? AND historico_vacinacao.nome_funcionario IS NOT NULL ORDER BY historico_vacinacao.data_aplicacao DESC";
        PreparedStatement ps = connection.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ps.setString(1, CPF_TEMP);
        importRS = ps.executeQuery();
        if (!importRS.isBeforeFirst()) {
            throw new Exception("Paciente não encontrado com o CPF: " + CPF_TEMP);
        }else{
            resultSetToTableModel(importRS, queryResultTable);
        }
    }

    public void registerLote(Lote lote) throws SQLException, NullPointerException, DateTimeParseException, Exception{
            PreparedStatement insertState = connection.prepareStatement("INSERT INTO lote(id_lote, id_vacina, data_fabricacao, data_validade, quantidade) VALUES (?,?,?,?,?)");
            insertState.setInt(1, lote.id_lote);
            insertState.setInt(2, lote.id_vacina);
            insertState.setDate(3, parseAndFormatDate(lote.data_fab));
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
                updatePs.setInt(3, idVacina);
                updatePs.executeUpdate();

                PreparedStatement insertState = connection.prepareStatement("INSERT INTO historico_vacinacao(cpf_aplicacao, id_vacina_aplicacao, data_aplicacao, nome_funcionario) VALUES (?,?,?,?)");
                insertState.setString(1, CPF);
                insertState.setInt(2, idVacina);
                LocalDate currentDate = LocalDate.now();
                Date sqlDate = Date.valueOf(currentDate);
                insertState.setDate(3, sqlDate);
                insertState.setString(4, username);
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

    private boolean comparePasswords(JPasswordField passwordField1, JPasswordField passwordField2) {
        char[] passwordArray1 = passwordField1.getPassword();
        char[] passwordArray2 = passwordField2.getPassword();

        boolean passwordsMatch = Arrays.equals(passwordArray1, passwordArray2);

        Arrays.fill(passwordArray1, '0');
        Arrays.fill(passwordArray2, '0');

        return passwordsMatch;
    }

    private Date parseAndFormatDate(String dateString) throws DateTimeParseException {
        if (dateString == null || dateString.isEmpty()) {
            return null; // Handle null or empty date
        }

        DateTimeFormatter[] formatters = {
                DateTimeFormatter.ofPattern("dd/MM/yyyy"),
                DateTimeFormatter.ofPattern("dd-MM-yyyy")
        };

        LocalDate localDate = null;
        for (DateTimeFormatter formatter : formatters) {
            try {
                localDate = LocalDate.parse(dateString, formatter);
                break; // Found matching format
            } catch (DateTimeParseException e) {
                // Try next formatter
            }
        }

        if (localDate == null) {
            throw new DateTimeParseException("Invalid date format", dateString, 0); //Throw exception if no format match.
        }

        return Date.valueOf(localDate);
    }

    //Adaptado de: https://stackoverflow.com/questions/10620448/how-to-populate-jtable-from-resultset
    public void resultSetToTableModel(ResultSet rs, JTable table) throws SQLException{

        DefaultTableModel tableModel = new DefaultTableModel();
        ResultSetMetaData metaData = rs.getMetaData();
        int columnCount = metaData.getColumnCount();
        for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++){
            tableModel.addColumn(metaData.getColumnLabel(columnIndex));
        }
        Object[] row = new Object[columnCount];
        while (rs.next()){
            for (int i = 0; i < columnCount; i++){
                row[i] = rs.getObject(i+1);
            }
            tableModel.addRow(row);
        }
        table.setModel(tableModel);
    }

    public static void exportResultSetToPdf(ResultSet resultSet, JFrame parentFrame, String nome_paciente, Date nasc_pacinte) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Salvar PDF");
        fileChooser.setFileFilter(new FileNameExtensionFilter("PDF files (*.pdf)", "pdf"));

        int userSelection = fileChooser.showSaveDialog(parentFrame);
        if (userSelection != JFileChooser.APPROVE_OPTION) return;

        File fileToSave = fileChooser.getSelectedFile();
        String filePath = fileToSave.getAbsolutePath();
        if (!filePath.toLowerCase().endsWith(".pdf")) filePath += ".pdf";

        try {
            PdfWriter writer = new PdfWriter(filePath);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            // Cabeçalho estilizado
            Paragraph title = new Paragraph("CARTÃO DE VACINAÇÃO")
                    .setBold()
                    .setFontSize(16)
                    .setTextAlignment(TextAlignment.CENTER)
                    .setMarginBottom(10);

            Paragraph patientInfo = new Paragraph("Nome: " + nome_paciente)
                    .setFontSize(12)
                    .setMarginBottom(10);

            document.add(title);
            document.add(patientInfo);

            // Criando tabela
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            Table table = new Table(UnitValue.createPercentArray(columnCount)).useAllAvailableWidth();

            // Adicionando cabeçalhos da tabela
            for (int i = 1; i <= columnCount; i++) {
                Cell headerCell = new Cell()
                        .add(new Paragraph(metaData.getColumnLabel(i)).setBold())
                        .setBackgroundColor(ColorConstants.LIGHT_GRAY)
                        .setTextAlignment(TextAlignment.CENTER);
                table.addHeaderCell(headerCell);
            }

            // Adicionando dados à tabela
            while (resultSet.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    Cell cell = new Cell()
                            .add(new Paragraph(resultSet.getString(i)))
                            .setTextAlignment(TextAlignment.CENTER);
                    table.addCell(cell);
                }
            }

            document.add(table);
            try {
                int idade = calcularIdade(nasc_pacinte);
                if (idade < 1) {
                    Paragraph vacbebe = new Paragraph("Vacinas recomendadas: BCG, Hepatite B, Penta, VIP, Rotavírus, Pneumocócica 10-valente, Meningocócica C, Febre amarela (se aplicável).");
                    document.add(vacbebe);
                } else if (idade >= 1 && idade <= 9) {
                    Paragraph vaccrianca = new Paragraph("Vacinas recomendadas: Tríplice viral, Varicela, DTPa, VIP, HPV (a partir de 9 anos), Meningocócica ACWY (a partir de 11 anos).");
                    document.add(vaccrianca);
                } else if (idade >= 10 && idade <= 19) {
                    Paragraph adolescente = new Paragraph("Vacinas recomendadas: HPV, dTpa, Meningocócica ACWY.");
                    document.add(adolescente);
                } else if (idade >= 20 && idade <= 59) {
                    Paragraph adulto = new Paragraph("Vacinas recomendadas: Hepatite B (se não vacinado), Tríplice viral (se não vacinado), Febre amarela (se aplicável), dT, HPV (mulheres até 45 anos, homens até 26 anos), Gripe.");
                    document.add(adulto);
                } else if (idade >= 60) {
                    Paragraph idoso = new Paragraph("Vacinas recomendadas: Gripe, Pneumocócica 23-valente, dT, Herpes zóster, Covid-19.");
                    document.add(idoso);
                }
            }catch (IllegalArgumentException e){
            }

            String dataAtual = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            Paragraph footer = new Paragraph("Relatório gerado em: " + dataAtual)
                    .setFontSize(10)
                    .setTextAlignment(TextAlignment.RIGHT)
                    .setMarginTop(20);
            document.add(footer);
            document.close();
            JOptionPane.showMessageDialog(parentFrame, "PDF exportado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException | SQLException e) {
            JOptionPane.showMessageDialog(parentFrame, "Erro ao exportar PDF: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private void alternarModo() {
        try {
            if (modoEscuroAtivo) {
                UIManager.setLookAndFeel(new FlatIntelliJLaf());
                botaoAlternaModo.setText("Modo Escuro");
                cadastraPacienteButton.setBackground(new Color(210, 230, 250));
                criarVacinaUserButton.setBackground(new Color(190, 220, 245));
                realizarAplicaçãoButton.setBackground(new Color(180, 210, 240));
                buscaPacienteButton.setBackground(new Color(220, 235, 220));
                buscarVacinaButton.setBackground(new Color(200, 235, 200));
                consultaCartãoPacienteButton.setBackground(new Color(180, 235, 180));
                queryResultTable.setBackground(new Color(220, 220, 220));
            } else {
                UIManager.setLookAndFeel(new FlatDarkLaf());
                botaoAlternaModo.setText("Modo Claro");
                cadastraPacienteButton.setBackground(new Color(50, 70, 90)); // Azul escuro
                criarVacinaUserButton.setBackground(new Color(60, 80, 100)); // Azul escuro um pouco mais claro
                realizarAplicaçãoButton.setBackground(new Color(70, 90, 110)); // Azul escuro um pouco mais claro
                buscaPacienteButton.setBackground(new Color(70, 90, 70)); // Verde escuro
                buscarVacinaButton.setBackground(new Color(80, 100, 80)); // Verde escuro um pouco mais claro
                consultaCartãoPacienteButton.setBackground(new Color(90, 110, 90)); // Verde escuro um pouco mais claro
                queryResultTable.setBackground(new Color(50, 50, 50)); // Cinza escuro
            }
            modoEscuroAtivo = !modoEscuroAtivo;
            SwingUtilities.updateComponentTreeUI(this);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static int calcularIdade(Date dataNascimento) {
        if (dataNascimento == null) {
            throw new IllegalArgumentException("A data de nascimento não pode ser nula.");
        }

        LocalDate dataNascimentoLocal = dataNascimento.toLocalDate();
        LocalDate dataAtual = LocalDate.now();

        Period periodo = Period.between(dataNascimentoLocal, dataAtual);
        return periodo.getYears();
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
        }catch (Exception e) {
            e.printStackTrace();
        }
        new GUI();
    }
}
