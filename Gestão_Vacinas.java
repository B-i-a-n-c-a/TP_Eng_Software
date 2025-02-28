import javax.swing.*;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Gestão_Vacinas {
    SecureRandom random = new SecureRandom();
    public int id_vacina;
    public String nome_vacina;
    public String tipo_vacina;
    public String fabricante;

    public Gestão_Vacinas(){
        this.id_vacina = random.nextInt(9999);
        this.nome_vacina = "exemplo";
        this.tipo_vacina = "EXEMPLO";
        this.fabricante = "exemplo";
    }
    public Gestão_Vacinas(int id, String nome, String tipo, String fabricante){
        this.id_vacina = id;
        this.nome_vacina = nome;
        this.tipo_vacina = tipo;
        this.fabricante = fabricante;
    }

    public static void registraVacina(Gestão_Vacinas vacina, Connection connection) throws SQLException {
        PreparedStatement insertState = connection.prepareStatement("INSERT INTO vacina_padronizada(id_vacina, nome_vacina, tipo_vacina, fabricante) VALUES (?,?,?,?)");
        insertState.setInt(1, vacina.id_vacina);
        insertState.setString(2, vacina.nome_vacina);
        insertState.setString(3, vacina.tipo_vacina);
        insertState.setString(4,vacina.fabricante);
        insertState.execute();
        System.out.println("Vacina registrada");
        JOptionPane.showMessageDialog(null, vacina.nome_vacina + " registrada com sucesso");
    }

    public static void test(){
        System.out.println("Isto é um teste");
    }
}
