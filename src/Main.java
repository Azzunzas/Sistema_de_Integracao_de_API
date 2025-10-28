import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        System.out.println("--- INICIANDO GERENCIADOR DE MÍDIA SOCIAL (AMBIENTE DE DESENVOLVIMENTO) ---");
        // 1. Inicializa o gerenciador no ambiente de desenvolvimento
        GerenciadorMidiaSocial gerenciador = new GerenciadorMidiaSocial("desenvolvimento");

        // 2. Adiciona as plataformas desejadas
        System.out.println("\n--- ADICIONANDO E AUTENTICANDO PLATAFORMAS ---");
        gerenciador.adicionarPlataforma("twitter");
        gerenciador.adicionarPlataforma("instagram");
        gerenciador.adicionarPlataforma("linkedin");
        gerenciador.adicionarPlataforma("tiktok");

        // 3. Cenário 1: Publicação de uma imagem com estratégia sequencial
        System.out.println("\n--- CENÁRIO 1: PUBLICAÇÃO SEQUENCIAL DE IMAGEM ---");
        Conteudo postagemImagem = new Conteudo(
                "Explorando o poder dos Design Patterns em Java. #Java #SoftwareEngineering",
                TipoConteudo.IMAGEM
        );
        postagemImagem.setMidiaUrl("path/to/diagrama_strategy.png");
        postagemImagem.setHashtags(Arrays.asList("Java", "DesignPatterns", "Developer"));

        // Define a estratégia e publica o conteúdo
        gerenciador.definirEstrategia(new PublicacaoSequencial());
        List<Publicacao> resultados1 = gerenciador.publicarConteudo(
                postagemImagem,
                Arrays.asList("twitter", "instagram", "linkedin") // Alvo específico
        );

        // 4. Cenário 2: Publicação de um vídeo com estratégia condicional
        System.out.println("\n--- CENÁRIO 2: PUBLICAÇÃO CONDICIONAL DE VÍDEO ---");
        Conteudo postagemVideo = new Conteudo(
                "Tutorial rápido sobre o padrão Factory! Veja como simplificar a criação de objetos.",
                TipoConteudo.VIDEO
        );
        postagemVideo.setMidiaUrl("path/to/tutorial_factory.mp4");
        postagemVideo.setHashtags(Arrays.asList("OOP", "CleanCode", "Tutorial"));

        // Define uma nova estratégia que avalia se a plataforma é adequada para o conteúdo
        gerenciador.definirEstrategia(new PublicacaoCondicional());
        // Tenta publicar em todas as plataformas disponíveis (null como alvo)
        List<Publicacao> resultados2 = gerenciador.publicarConteudo(postagemVideo, null);


        // 5. Consolida todos os resultados de publicação
        List<Publicacao> todasAsPublicacoes = new ArrayList<>();
        todasAsPublicacoes.addAll(resultados1);
        todasAsPublicacoes.addAll(resultados2);

        // 6. Gera e exibe o relatório de estatísticas
        System.out.println("\n--- GERANDO RELATÓRIO DE ESTATÍSTICAS ---");
        Map<String, Object> relatorio = gerenciador.obterRelatorioEstatisticas(todasAsPublicacoes);

        System.out.println("\n================= RELATÓRIO FINAL ==================");
        System.out.println("Total de Publicações Tentadas: " + relatorio.get("total_publicacoes"));
        System.out.println("Publicações Bem-sucedidas: " + relatorio.get("publicacoes_bem_sucedidas"));
        System.out.println("Publicações com Falha: " + relatorio.get("publicacoes_com_falha"));
        System.out.println("----------------------------------------------------");

        @SuppressWarnings("unchecked")
        Map<String, Map<String, Object>> detalhado = (Map<String, Map<String, Object>>) relatorio.get("estatisticas_detalhadas");

        if (detalhado.isEmpty()) {
            System.out.println("Nenhuma estatística detalhada disponível.");
        } else {
            System.out.println("Estatísticas Detalhadas por Plataforma:");
            for (Map.Entry<String, Map<String, Object>> entry : detalhado.entrySet()) {
                System.out.println("\n Plataforma: " + entry.getKey());
                Map<String, Object> stats = entry.getValue();
                System.out.println("   - Visualizações: " + stats.get("visualizacoes"));
                System.out.println("   - Curtidas: " + stats.get("curtidas"));
                System.out.println("   - Compartilhamentos: " + stats.get("compartilhamentos"));
                System.out.println("   - Comentários: " + stats.get("comentarios"));
                System.out.println("   - Engajamento Total: " + stats.get("engajamento_total"));
                System.out.println("   - Taxa de Engajamento: " + stats.get("taxa_engajamento"));
            }
        }
        System.out.println("====================================================");
    }
}