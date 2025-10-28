public class TwitterFactory extends SocialMediaFactory{
    @Override
    public RedeSocialInteface criarAdapter(){
        return new TwitterAdapter();
    }
}
