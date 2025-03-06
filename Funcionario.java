import java.sql.Date;

public class Funcionario extends Pessoa{
    public String crm_coren;

    //Construtores vazios e populados
    public Funcionario (){
        this.nome = "example";
        this.cpf = "111.111.111-11";
        this.endereco = "endereco";
        this.idade = null;
        this.email = "email@email.com";
        this.crm_coren = "crm_coren";
    }
    public Funcionario (String nome, String cpf, String endereco, Date idade, String email, String crm_coren){
        this.nome = nome;
        this.cpf = cpf;
        this.endereco = endereco;
        this.idade = idade;
        this.email = email;
        this.crm_coren = crm_coren;
    }

    public String getCrm_coren() {
        return crm_coren;
    }
    public void setCrm_coren(String crm_coren) {
        this.crm_coren = crm_coren;
    }

}
