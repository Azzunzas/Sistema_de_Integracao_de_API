import java.time.LocalDateTime;

public class Publicacao {
    private String idPublicacao;
    private String plataforma;
    private StatusPublicacao status;
    private String url;
    private LocalDateTime dataPublicacao;
    private String mensagem;
    //construtor
    public Publicacao(String idPublicacao, String plataforma, StatusPublicacao status,
                      String url, LocalDateTime dataPublicacao, String mensagem){
        this.idPublicacao = idPublicacao;
        this.plataforma = plataforma;
        this.status = status;
        this.url = url;
        this.dataPublicacao = dataPublicacao;
        this.mensagem = mensagem;
    }
    //getters
    public String getIdPublicacao(){return idPublicacao;}
    public String getPlataforma() {return plataforma;}
    public StatusPublicacao getStatus() {return status;}
    public String getUrl() {return url;}
    public LocalDateTime getDataPublicacao() {return dataPublicacao;}
    public String getMensagem(){return mensagem;}
}
