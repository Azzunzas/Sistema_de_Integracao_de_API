import java.util.Map;

public interface RedeSocialInteface {
    boolean autenticar(Map<String,String> credenciais);
    Publicacao publicar(Conteudo conteudo);
    Strategies obterStrategies(String idPublicacao);
    String getNomePlataforma();
}
