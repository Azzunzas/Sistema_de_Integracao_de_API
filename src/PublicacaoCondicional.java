import java.util.ArrayList;
import java.util.List;

public class PublicacaoCondicional implements EstrategiaPublicacao{
    @Override
    public List<Publicacao> executar(List<RedeSocialInteface> plataformas, Conteudo conteudo) {
        List<Publicacao> resultados = new ArrayList<>();
        for (RedeSocialInteface plataforma : plataformas) {
            String nome = plataforma.getNomePlataforma();

            // Regras condicionais
            if (nome.equals("Instagram") && conteudo.getTipo() == TipoConteudo.TEXTO) {
                System.out.println("⊘ Pulando " + nome + ": conteúdo texto-apenas não é ideal");
                continue;
            }

            if (nome.equals("TikTok") && conteudo.getTipo() != TipoConteudo.VIDEO) {
                System.out.println("⊘ Pulando " + nome + ": requer vídeo");
                continue;
            }

            Publicacao resultado = plataforma.publicar(conteudo);
            resultados.add(resultado);
            System.out.println("✓ Publicado em " + nome + ": " + resultado.getStatus());
        }
        return resultados;
    }
}
