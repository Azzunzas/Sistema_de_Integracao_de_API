import java.util.List;

public interface EstrategiaPublicacao {
    List<Publicacao> executar(List<RedeSocialInteface> plataformas, Conteudo conteudo);
}
