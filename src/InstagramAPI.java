import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InstagramAPI {
    public Map<String, String> login(String username, String accessToken) {
        Map<String, String> response = new HashMap<>();
        response.put("user_id", "ig_user_123");
        response.put("status", "authenticated");
        return response;
    }

    public Map<String, Object> createMedia(String caption, String imageUrl, List<String> hashtags) {
        Map<String, Object> response = new HashMap<>();
        response.put("media_id", "ig_" + System.currentTimeMillis());
        response.put("permalink", "https://instagram.com/p/" + System.currentTimeMillis());
        response.put("timestamp", LocalDateTime.now());
        return response;
    }

    public Map<String, Integer> getInsights(String mediaId) {
        Map<String, Integer> insights = new HashMap<>();
        insights.put("reach", 3400);
        insights.put("likes", 156);
        insights.put("comments", 23);
        insights.put("saves", 34);
        insights.put("shares", 12);
        return insights;
    }
}
