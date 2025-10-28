import java.time.LocalDateTime;
import java.util.Map;

public class InstagramAdapter implements  RedeSocialInteface{
    private InstagramAPI api;
    private boolean autenticado;
    private String userId;

    public InstagramAdapter() {
        this.api = new InstagramAPI();
        this.autenticado = false;
    }

    @Override
    public boolean autenticar(Map<String, String> credenciais) {
        String username = credenciais.get("username");
        String accessToken = credenciais.get("access_token");
        Map<String, String> resultado = api.login(username, accessToken);
        this.autenticado = "authenticated".equals(resultado.get("status"));
        this.userId = resultado.get("user_id");
        return this.autenticado;
    }

    @Override
    public Publicacao publicar(Conteudo conteudo) {
        if (!autenticado) {
            return new Publicacao("", "Instagram", StatusPublicacao.FALHOU,
                    null, LocalDateTime.now(), "Não autenticado");
        }

        if (conteudo.getMidiaUrl() == null) {
            return new Publicacao("", "Instagram", StatusPublicacao.FALHOU,
                    null, LocalDateTime.now(), "Instagram requer mídia visual");
        }

        Map<String, Object> resultado = api.createMedia(
                conteudo.getTexto(),
                conteudo.getMidiaUrl(),
                conteudo.getHashtags()
        );

        return new Publicacao(
                (String) resultado.get("media_id"),
                "Instagram",
                StatusPublicacao.PUBLICADO,
                (String) resultado.get("permalink"),
                (LocalDateTime) resultado.get("timestamp"),
                "Publicado com sucesso"
        );
    }

    @Override
    public Strategies obterStrategies(String idPublicacao) {
        Map<String, Integer> insights = api.getInsights(idPublicacao);

        int engajamentoTotal = insights.get("likes") +
                insights.get("comments") +
                insights.get("saves") +
                insights.get("shares");

        double taxaEngajamento = insights.get("reach") > 0 ?
                (engajamentoTotal * 100.0 / insights.get("reach")) : 0;

        return new Strategies(
                insights.get("reach"),
                insights.get("likes"),
                insights.get("shares"),
                insights.get("comments"),
                engajamentoTotal,
                Math.round(taxaEngajamento * 100.0) / 100.0
        );
    }

    @Override
    public String getNomePlataforma() {
        return "Instagram";
    }
}
