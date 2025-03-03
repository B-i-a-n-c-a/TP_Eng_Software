

public class paciente extends Pessoa{
    public String nome;
    public String cpf;
    public String sexo;
    public String endereco;
    public String email;
    public String dataNascimento;

    //Construtores vazios e populados
    public paciente (String nome, String cpf, String endereco, String dataNascimento, String email, String sexo) {
        this.nome = nome;
        this.cpf = cpf;
        this.endereco = endereco;
        this.dataNascimento = dataNascimento;
        this.email = email;
        this.sexo = sexo;
    }

    public paciente (){
        this.nome = "";
        this.cpf = "";
        this.endereco = "";
        this.dataNascimento = null;
        this.email = "";
        this.sexo = "";
    }


}