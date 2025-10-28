import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class TikTokAdapter implements RedeSocialInteface{
    private TikTokAPI api;
    private boolean autenticado;

    public TikTokAdapter() {
        this.api = new TikTokAPI();
        this.autenticado = false;
    }

    @Override
    public boolean autenticar(Map<String, String> credenciais) {
        String appId = credenciais.get("app_id");
        String appSecret = credenciais.get("app_secret");
        this.autenticado = api.initAuth(appId, appSecret);
        return this.autenticado;
    }

    @Override
    public Publicacao publicar(Conteudo conteudo) {
        if (!autenticado) {
            return new Publicacao("", "TikTok", StatusPublicacao.FALHOU,
                    null, LocalDateTime.now(), "Não autenticado");
        }

        if (conteudo.getTipo() != TipoConteudo.VIDEO) {
            return new Publicacao("", "TikTok", StatusPublicacao.FALHOU,
                    null, LocalDateTime.now(), "TikTok requer conteúdo de vídeo");
        }

        Map<String, Object> videoData = new HashMap<>();
        videoData.put("video_url", conteudo.getMidiaUrl());
        videoData.put("description", conteudo.getTexto());
        videoData.put("hashtags", conteudo.getHashtags());

        Map<String, Object> resultado = api.uploadVideo(videoData);

        return new Publicacao(
                (String) resultado.get("video_id"),
                "TikTok",
                StatusPublicacao.PUBLICADO,
                (String) resultado.get("share_url"),
                LocalDateTime.now(),
                "Publicado com sucesso"
        );
    }

    @Override
    public Strategies obterStrategies(String idPublicacao) {
        Map<String, Integer> data = api.queryVideoData(idPublicacao);

        int engajamentoTotal = data.get("like_count") +
                data.get("comment_count") +
                data.get("share_count") +
                data.get("download_count");

        double taxaEngajamento = data.get("play_count") > 0 ?
                (engajamentoTotal * 100.0 / data.get("play_count")) : 0;

        return new Strategies(
                data.get("play_count"),
                data.get("like_count"),
                data.get("share_count"),
                data.get("comment_count"),
                engajamentoTotal,
                Math.round(taxaEngajamento * 100.0) / 100.0
        );
    }

    @Override
    public String getNomePlataforma() {
        return "TikTok";
    }
}
