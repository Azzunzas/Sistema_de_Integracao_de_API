public class LinkedInFactory extends SocialMediaFactory{
    @Override
    public RedeSocialInteface criarAdapter(){
        return new LinkedInAdapter();
    }
}
