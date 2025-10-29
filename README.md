Resumo Técnico

Sistema implementa padrões de design para resolver problema de integração com múltiplas APIs heterogêneas.

Adapter unifica interfaces diferentes das redes sociais (Twitter, Instagram, LinkedIn, TikTok)
em uma interface comum (RedeSocialInterface), isolando as diferenças entre APIs em camadas de adaptação específicas.

Strategy permite comportamentos intercambiáveis de publicação (sequencial, simultânea, condicional)
através de interface EstrategiaPublicacao, permitindo troca de algoritmo em tempo de execução sem modificar código cliente.

Factory Method centraliza criação e autenticação de adapters através de factories específicas 
(TwitterFactory, InstagramFactory, etc) que herdam de SocialMediaFactory, encapsulando lógica de instanciação complexa.

Arquitetura modular e desacoplada facilita manutenção, extensão e testes. Sistema orquestrado por GerenciadorMidiaSocial que coordena factories, 
adapters e strategies para processar publicações em múltiplas plataformas através de interface unificada.

<img width="9510" height="2956" alt="Diagrama" src="https://github.com/user-attachments/assets/97acf129-4936-4e21-92e6-bc569464846b" />
