import java.util.HashMap;
import java.util.Map;

public class LinkedInAPI {
    public String authorize(String clientId, String clientSecret) {
        return "token_" + clientId;
    }

    public Map<String, Object> shareUpdate(String authorUrn, String text, String contentUrl) {
        Map<String, Object> response = new HashMap<>();
        response.put("activity_id", "ln_activity_" + System.currentTimeMillis());
        response.put("created_timestamp", System.currentTimeMillis());
        response.put("shareUrl", "https://linkedin.com/feed/update/urn:li:activity:" + System.currentTimeMillis());
        return response;
    }

    public Map<String, Integer> getShareStatistics(String activityId) {
        Map<String, Integer> stats = new HashMap<>();
        stats.put("impressionCount", 2100);
        stats.put("likeCount", 67);
        stats.put("commentCount", 15);
        stats.put("shareCount", 8);
        stats.put("clickCount", 89);
        return stats;
    }
}
