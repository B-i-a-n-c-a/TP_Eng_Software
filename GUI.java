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
    private String password;
    private String username;

    public GUI() {
        setContentPane(background);
        setTitle("Sistema vacinação");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        setLocationRelativeTo(null);
        setVisible(true);
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



    }

    public String auth() {
        if(this.username.equals("admin") && this.password.equals("admin")){
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
