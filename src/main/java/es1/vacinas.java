package es1;
public class vacinas {
    private String nome;
    private String frequencia;
    
    public vacinas(String nome, String frequencia){
        this.nome = nome;
        this.frequencia = frequencia; 
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getFrequencia() {
        return frequencia;
    }
    public void setFrequencia(String frequencia) {
        this.frequencia = frequencia;
    }
    
}