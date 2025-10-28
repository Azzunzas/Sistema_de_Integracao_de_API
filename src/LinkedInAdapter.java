import java.time.LocalDateTime;
import java.util.Map;

public class LinkedInAdapter implements RedeSocialInteface{
    private LinkedInAPI api;
    private boolean autenticado;
    private String authorUrn;

    public LinkedInAdapter() {
        this.api = new LinkedInAPI();
        this.autenticado = false;
        this.authorUrn = "urn:li:person:123456";
    }

    @Override
    public boolean autenticar(Map<String, String> credenciais) {
        String clientId = credenciais.get("client_id");
        String clientSecret = credenciais.get("client_secret");
        String token = api.authorize(clientId, clientSecret);
        this.autenticado = token != null && !token.isEmpty();
        return this.autenticado;
    }

    @Override
    public Publicacao publicar(Conteudo conteudo) {
        if (!autenticado) {
            return new Publicacao("", "LinkedIn", StatusPublicacao.FALHOU,
                    null, LocalDateTime.now(), "Não autenticado");
        }

        StringBuilder texto = new StringBuilder(conteudo.getTexto());
        if (!conteudo.getHashtags().isEmpty()) {
            texto.append("\n\n");
            for (String tag : conteudo.getHashtags()) {
                texto.append("#").append(tag).append(" ");
            }
        }

        Map<String, Object> resultado = api.shareUpdate(
                authorUrn,
                texto.toString(),
                conteudo.getLink()
        );

        long timestamp = (Long) resultado.get("created_timestamp");

        return new Publicacao(
                (String) resultado.get("activity_id"),
                "LinkedIn",
                StatusPublicacao.PUBLICADO,
                (String) resultado.get("shareUrl"),
                LocalDateTime.now(),
                "Publicado com sucesso"
        );
    }

    @Override
    public Strategies obterStrategies(String idPublicacao) {
        Map<String, Integer> stats = api.getShareStatistics(idPublicacao);

        int engajamentoTotal = stats.get("likeCount") +
                stats.get("commentCount") +
                stats.get("shareCount") +
                stats.get("clickCount");

        double taxaEngajamento = stats.get("impressionCount") > 0 ?
                (engajamentoTotal * 100.0 / stats.get("impressionCount")) : 0;

        return new Strategies(
                stats.get("impressionCount"),
                stats.get("likeCount"),
                stats.get("shareCount"),
                stats.get("commentCount"),
                engajamentoTotal,
                Math.round(taxaEngajamento * 100.0) / 100.0
        );
    }

    @Override
    public String getNomePlataforma() {
        return "LinkedIn";
    }
}
