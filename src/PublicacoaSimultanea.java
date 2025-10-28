import java.util.ArrayList;
import java.util.List;

public class PublicacoaSimultanea implements EstrategiaPublicacao{
    @Override
    public List<Publicacao> executar(List<RedeSocialInteface> plataformas, Conteudo conteudo) {
        System.out.println("Iniciando publicação simultânea...");
        List<Publicacao> resultados = new ArrayList<>();
        for (RedeSocialInteface plataforma : plataformas) {
            Publicacao resultado = plataforma.publicar(conteudo);
            resultados.add(resultado);
        }
        System.out.println("✓ Publicado simultaneamente em " + plataformas.size() + " plataformas");
        return resultados;
    }
}
