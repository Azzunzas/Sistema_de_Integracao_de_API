import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GerenciadorMidiaSocial {
    private String ambiente;
    private Map<String, RedeSocialInteface> plataformas;
    private EstrategiaPublicacao estrategia;
    private Map<String, SocialMediaFactory> factories;

    public GerenciadorMidiaSocial(String ambiente) {
        this.ambiente = ambiente;
        this.plataformas = new HashMap<>();
        this.estrategia = new PublicacaoSequencial();
        this.factories = new HashMap<>();

        factories.put("twitter", new TwitterFactory());
        factories.put("instagram", new InstagramFactory());
        factories.put("linkedin", new LinkedInFactory());
        factories.put("tiktok", new TikTokFactory());
    }

    public boolean adicionarPlataforma(String nomePlataforma) {
        String nomeLower = nomePlataforma.toLowerCase();

        if (!factories.containsKey(nomeLower)) {
            System.out.println("✗ Plataforma " + nomePlataforma + " não suportada");
            return false;
        }

        SocialMediaFactory factory = factories.get(nomeLower);
        Map<String, String> credenciais = ConfiguracaoAmbiente.obterCredenciais(ambiente, nomeLower);

        RedeSocialInteface adapter = factory.criarEAutenticar(credenciais);
        plataformas.put(nomeLower, adapter);
        return true;
    }

    public void definirEstrategia(EstrategiaPublicacao estrategia) {
        this.estrategia = estrategia;
        System.out.println("Estratégia definida: " + estrategia.getClass().getSimpleName());
    }

    public List<Publicacao> publicarConteudo(Conteudo conteudo, List<String> plataformasAlvo) {
        List<RedeSocialInteface> plataformasParaPublicar = new ArrayList<>();

        if (plataformasAlvo != null && !plataformasAlvo.isEmpty()) {
            for (String p : plataformasAlvo) {
                if (plataformas.containsKey(p)) {
                    plataformasParaPublicar.add(plataformas.get(p));
                }
            }
        } else {
            plataformasParaPublicar.addAll(plataformas.values());
        }

        if (plataformasParaPublicar.isEmpty()) {
            System.out.println("✗ Nenhuma plataforma disponível para publicação");
            return new ArrayList<>();
        }

        return estrategia.executar(plataformasParaPublicar, conteudo);
    }

    public Map<String, Object> obterRelatorioEstatisticas(List<Publicacao> publicacoes) {
        Map<String, Object> relatorio = new HashMap<>();
        relatorio.put("total_publicacoes", publicacoes.size());

        int bemSucedidas = 0;
        int falhas = 0;
        Map<String, Map<String, Object>> estatisticasPorPlataforma = new HashMap<>();

        for (Publicacao pub : publicacoes) {
            if (pub.getStatus() == StatusPublicacao.PUBLICADO) {
                bemSucedidas++;

                String plataformaLower = pub.getPlataforma().toLowerCase();
                if (plataformas.containsKey(plataformaLower)) {
                    Strategies stats = plataformas.get(plataformaLower).obterStrategies(pub.getIdPublicacao());

                    Map<String, Object> statsMap = new HashMap<>();
                    statsMap.put("visualizacoes", stats.getVisualizacoes());
                    statsMap.put("curtidas", stats.getCurtidas());
                    statsMap.put("compartilhamentos", stats.getCompartilhamentos());
                    statsMap.put("comentarios", stats.getComentarios());
                    statsMap.put("engajamento_total", stats.getEngajamentos());
                    statsMap.put("taxa_engajamento", stats.getTaxaEngajamento() + "%");

                    estatisticasPorPlataforma.put(pub.getPlataforma(), statsMap);

                } else {
                    falhas++;
                }
            }
        }

        relatorio.put("publicacoes_bem_sucedidas", bemSucedidas);
        relatorio.put("publicacoes_com_falha", falhas);
        relatorio.put("estatisticas_detalhadas", estatisticasPorPlataforma);

        return relatorio; // <-- Adicionar esta linha
    }
}