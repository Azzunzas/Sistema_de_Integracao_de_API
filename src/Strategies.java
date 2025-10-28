public class Strategies {

    private int visualizacoes;
    private int curtidas;
    private int compartilhamentos;
    private int comentarios;
    private int engajamentos;
    private double taxaEngajamento;

    //construtor
    public Strategies(int visualizacoes, int curtidas, int compartilhamentos, int comentarios, int engajamentos, double taxaEngajamento){
        this.visualizacoes = visualizacoes;
        this.curtidas = curtidas;
        this.compartilhamentos = compartilhamentos;
        this.comentarios = comentarios;
        this.engajamentos = engajamentos;
        this.taxaEngajamento = taxaEngajamento;
    }

    //getters

    public int getVisualizacoes() {return visualizacoes;}
    public int getCurtidas() {return curtidas;}
    public int getCompartilhamentos() {return compartilhamentos;}
    public int getComentarios() {return comentarios;}
    public int getEngajamentos() {return engajamentos;}
    public double getTaxaEngajamento() {return taxaEngajamento;}
}
