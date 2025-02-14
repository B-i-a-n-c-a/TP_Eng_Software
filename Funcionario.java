import javax.lang.model.type.NullType;

public class Funcionario extends Pessoa{
    private String crm_coren;
    private String hospitalClinica;

    public Funcionario (String nome, String cpf,String endereco, int idade, String email, String crm_coren, String hospitalClinica){
        this.nome = nome;
        this.cpf = cpf;
        this.endereco = endereco;
        this.idade = idade;
        this.email = email;
        this.crm_coren = crm_coren;
        this.hospitalClinica = hospitalClinica;
    }

    public Funcionario (){
        this.nome = "example";
        this.cpf = "111.111.111-11";
        this.endereco = "endereco";
        this.idade = 0;
        this.email = "email@email.com";
        this.crm_coren = "crm_coren";
        this.hospitalClinica = "hospitalClinica";
    }

    public String getCrm_coren() {
        return crm_coren;
    }
    public void setCrm_coren(String vacinas) {
        this.crm_coren = crm_coren;
    }

    public String getHospitalClinica() {
        return hospitalClinica;
    }

    public void setHospitalClinica(String hospitalClinica) {
        this.hospitalClinica = hospitalClinica;
    }
}
