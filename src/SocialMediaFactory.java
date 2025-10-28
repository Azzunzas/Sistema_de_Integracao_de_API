import java.util.Map;

abstract class SocialMediaFactory {
    public abstract RedeSocialInteface criarAdapter();

    public RedeSocialInteface criarEAutenticar(Map<String, String> credenciais) {
        RedeSocialInteface adapter = criarAdapter();
        if (adapter.autenticar(credenciais)) {
            System.out.println("✓ Autenticado com sucesso em " + adapter.getNomePlataforma());
        } else {
            System.out.println("✗ Falha na autenticação em " + adapter.getNomePlataforma());
        }
        return adapter;
    }
}
