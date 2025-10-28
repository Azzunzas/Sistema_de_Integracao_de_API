import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class TwitterAdapter implements RedeSocialInteface{

    private TwitterAPI api;
    private boolean autenticado;

    public TwitterAdapter(){
        this.api = new TwitterAPI();
        this.autenticado = false;
    }
    @Override
    public boolean autenticar(Map<String, String> credenciais){
        String apiKey = credenciais.get("api_key");
        String apiSecret = credenciais.get("api_secret");
        this.autenticado = api.authenticate(apiKey, apiSecret);
        return this.autenticado;
    }
    @Override
    public Publicacao publicar (Conteudo conteudo){
        if(!autenticado){
            return  new Publicacao("","Twitter",StatusPublicacao.FALHOU,null, LocalDateTime.now(),"Não Autenticado");

        }
        //Adapta o conteudo para o formato do Twitter
        StringBuilder textoCompleto = new StringBuilder(conteudo.getTexto());
        if (!conteudo.getHashtags().isEmpty()){
            textoCompleto.append(" ");
            for(String tag: conteudo.getHashtags()){
                textoCompleto.append("#").append(tag).append(" ");
            }
        }
        List<String> mediaIds = conteudo.getMidiaUrl() != null?
                Arrays.asList(conteudo.getMidiaUrl()) : null;

        Map<String, Object> resultado = api.tweet(textoCompleto.toString(),mediaIds);
        return new Publicacao((String) resultado.get("teet_id"), "Twitter",
                StatusPublicacao.PUBLICADO, (String) resultado.get("url"), (LocalDateTime) resultado.get("created_at"),
                "Publicado com sucesso"
        );
    }
    @Override
    public Strategies obterStrategies(String idPublicacao) {
        Map<String, Integer> metricas = api.getTweetMetrics(idPublicacao);

        int engajamentoTotal = metricas.get("likes") +
                metricas.get("retweets") +
                metricas.get("replies");

        double taxaEngajamento = metricas.get("impressions") > 0 ?
                (engajamentoTotal * 100.0 / metricas.get("impressions")) : 0;

        return new Strategies(
                metricas.get("impressions"),
                metricas.get("likes"),
                metricas.get("retweets"),
                metricas.get("replies"),
                engajamentoTotal,
                Math.round(taxaEngajamento * 100.0) / 100.0
        );
    }

    @Override
    public String getNomePlataforma() {
        return "Twitter";
    }
}
