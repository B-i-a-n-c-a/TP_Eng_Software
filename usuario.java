public class usuario extends Pessoa{
    private String vacinas;
    private String proxDose;
    
    public usuario (String nome, String cpf, String endereco, int idade, String email, String vacinas, String proxDose) {
        this.nome = nome;
        this.cpf = cpf;
        this.endereco = endereco;
        this.email = email;
        this.idade = idade;
        this.vacinas = vacinas;
    }

    public usuario (){
        this.nome = "exemplo";
        this.cpf = "111.111.111-11";
        this.endereco = "endereco";
        this.idade = 0;
        this.email = "exemplo@exemplo.com";
        this.vacinas = "vacinas";
    }



    public String getVacinas() {
        return vacinas;
    }
    public void setVacinas(String vacinas) {
        this.vacinas = vacinas;
    }

    public String getProxDose() {
        return proxDose;
    }

    public void setProxDose(String proxDose) {
        this.proxDose = proxDose;
    }
}