import java.util.ArrayList;
import java.util.List;

public class PublicacaoSequencial implements EstrategiaPublicacao{
    @Override
    public List<Publicacao> executar(List<RedeSocialInteface> plataformas, Conteudo conteudo) {
        List<Publicacao> resultados = new ArrayList<>();
        for (RedeSocialInteface plataforma : plataformas) {
            Publicacao resultado = plataforma.publicar(conteudo);
            resultados.add(resultado);
            System.out.println("✓ Publicado em " + plataforma.getNomePlataforma() +
                    ": " + resultado.getStatus());
        }
        return resultados;
    }
}
