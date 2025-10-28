public class InstagramFactory extends SocialMediaFactory{
    @Override
    public RedeSocialInteface criarAdapter(){
        return new InstagramAdapter();
    }
}
