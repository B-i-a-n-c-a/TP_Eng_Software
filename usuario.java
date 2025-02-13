public class usuario {
    private String nome;
    private int idade;
    private String cpf;
    private String endereco;
    private String vacinas;
    private String proxDose;
    
    public usuario (){
        this.nome = nome;
        this.cpf = cpf;
        this.endereco = endereco;
        this.idade = idade;
        this.vacinas = vacinas;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public int getIdade() {
        return idade;
    }
    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getCpf() {
        return cpf;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEndereco() {
        return endereco;
    }
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getVacinas() {
        return vacinas;
    }
    public void setVacinas(String vacinas) {
        this.vacinas = vacinas;
    }
}