import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame {
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
    private JLabel SenhaBD;
    private JLabel UserBD;
    private JLabel AddressBD;
    private JButton alterarEndereçoBDButton;
    private JButton voltarConfToAdmin;
    private JButton alterarUsuárioBDButton;
    private JButton alterarSenhaBDButton;
    private JButton adminlogoutButton;
    private JButton userLogoutButton;
    private String password;
    private String username;
    private String adminSalt = "adminpbkdf2";
    private String adminHash = "fTcZzt8MdjGl0/Ce6BgPGPSSHAai3UPIp7JyKAJj+Uo=";
    private String userDB = "admin";
    private String adminDBSalt = "admindbpbkdf2";
    private String passwordDBHash = "boDhIfrQhSNItRt/9TdjfKJmZ8N/hpmLTpuLgUhO9qw=";
    private String addressDB = "endereco";

    public GUI() {
        setContentPane(background);
        setTitle("Sistema vacinação");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);
        setVisible(true);
        SenhaBD.setText("Senha + Salt: " + passwordDBHash + " | " + adminDBSalt);
        AddressBD.setText("Endereço: " + addressDB);
        UserBD.setText("Usuário: " + userDB);

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
                    landing_Jpanel.setVisible(false);
                    user_Jpanel.setVisible(true);
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
    }

    public String auth() {
        if(this.username.equals("admin") && this.adminHash.equals(Crypto.pbkdf2Hash(this.password, this.adminSalt))) {
            return "admin";
        }else if(this.username.equals("guest") && this.password.equals("guest")){
            return "user";
        }else{
            return "null";
        }
    }


    public void setPassword() {
        this.password = new String(passwordField1.getPassword());
    }
    public void setUsername() {
        this.username = new String(textField1.getText());
    }

    public static void main(String[] args) {
        new GUI();
    }
}
