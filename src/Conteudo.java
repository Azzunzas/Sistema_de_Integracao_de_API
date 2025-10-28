import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Conteudo {
    private String texto;
    private TipoConteudo tipo;
    private String midiaUrl;
    private String link;
    private List<String> hashtags;
    private List<String> mencoes;
    //construtor
    public Conteudo(String texto, TipoConteudo tipo){
        this.texto = texto;
        this.tipo = tipo;
        this.hashtags = new ArrayList<>();
        this.mencoes = new ArrayList<>();
    }
    //getters
    public String getTexto(){return texto;}
    public TipoConteudo getTipo(){return tipo;}
    public String getMidiaUrl(){return midiaUrl;}
    public String getLink(){return link;}
    public List<String> getHashtags() {return hashtags;}
    public List<String> getMencoes() {return mencoes;}
    //setters

    public void setMidiaUrl(String midiaUrl) {this.midiaUrl = midiaUrl;}
    public void setLink(String link) {this.link = link;}
    public void setHashtags(List<String> hashtags) {this.hashtags = hashtags;}
    public void setMencoes(List<String> mencoes) {this.mencoes = mencoes;}
}
