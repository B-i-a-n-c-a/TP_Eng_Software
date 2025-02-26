public class paciente extends Pessoa{

    //Construtores vazios e populados
    public paciente (String nome, String cpf, String endereco, int idade, String email) {
        this.nome = nome;
        this.cpf = cpf;
        this.endereco = endereco;
        this.email = email;
        this.idade = idade;
    }

    public paciente (){
        this.nome = "exemplo";
        this.cpf = "111.111.111-11";
        this.endereco = "endereco";
        this.idade = 0;
        this.email = "exemplo@exemplo.com";
    }


}