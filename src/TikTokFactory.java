public class TikTokFactory extends SocialMediaFactory{
    @Override
    public RedeSocialInteface criarAdapter(){
        return new TikTokAdapter();
    }
}
