package es1;
public class Lote {
    public int id_lote;
    public int id_vacina;
    public String data_fab;
    public String data_val;
    public int quantidade;


    public Lote() {
        this.id_lote = 0;
        this.id_vacina = 0;
        this.data_fab = null;
        this.data_val = null;
        this.quantidade = 0;
    }

    public Lote(int idl, int idv, String dateF, String dateV, int qu) {
        this.id_lote = idl;
        this.id_vacina = idv;
        this.data_fab = dateF;
        this.data_val = dateV;
        this.quantidade = qu;
    }
}