import java.util.HashMap;
import java.util.Map;

public class ConfiguracaoAmbiente {
    private static final Map<String, Map<String, Map<String, String>>> AMBIENTES = new HashMap<>();

    static {
        // Desenvolvimento
        Map<String, Map<String, String>> dev = new HashMap<>();

        Map<String, String> twDev = new HashMap<>();
        twDev.put("api_key", "dev_tw_key");
        twDev.put("api_secret", "dev_tw_secret");
        dev.put("twitter", twDev);

        Map<String, String> igDev = new HashMap<>();
        igDev.put("username", "dev_user");
        igDev.put("access_token", "dev_ig_token");
        dev.put("instagram", igDev);

        Map<String, String> lnDev = new HashMap<>();
        lnDev.put("client_id", "dev_ln_client");
        lnDev.put("client_secret", "dev_ln_secret");
        dev.put("linkedin", lnDev);

        Map<String, String> tkDev = new HashMap<>();
        tkDev.put("app_id", "dev_tk_app");
        tkDev.put("app_secret", "dev_tk_secret");
        dev.put("tiktok", tkDev);

        AMBIENTES.put("desenvolvimento", dev);

        // Produção
        Map<String, Map<String, String>> prod = new HashMap<>();

        Map<String, String> twProd = new HashMap<>();
        twProd.put("api_key", "prod_tw_key");
        twProd.put("api_secret", "prod_tw_secret");
        prod.put("twitter", twProd);

        Map<String, String> igProd = new HashMap<>();
        igProd.put("username", "prod_user");
        igProd.put("access_token", "prod_ig_token");
        prod.put("instagram", igProd);

        Map<String, String> lnProd = new HashMap<>();
        lnProd.put("client_id", "prod_ln_client");
        lnProd.put("client_secret", "prod_ln_secret");
        prod.put("linkedin", lnProd);

        Map<String, String> tkProd = new HashMap<>();
        tkProd.put("app_id", "prod_tk_app");
        tkProd.put("app_secret", "prod_tk_secret");
        prod.put("tiktok", tkProd);

        AMBIENTES.put("producao", prod);
    }

    public static Map<String, String> obterCredenciais(String ambiente, String plataforma) {
        Map<String, Map<String, String>> amb = AMBIENTES.get(ambiente);
        return amb != null ? amb.get(plataforma) : new HashMap<>();
    }

}
