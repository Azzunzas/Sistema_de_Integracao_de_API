import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TwitterAPI {
    public boolean authenticate(String apiKey, String apiSecret) {
        return apiKey != null && apiSecret != null && !apiKey.isEmpty();
    }

    public Map<String, Object> tweet(String text, List<String> mediaIds) {
        Map<String, Object> response = new HashMap<>();
        response.put("tweet_id", "tw_" + System.currentTimeMillis());
        response.put("created_at", LocalDateTime.now());
        response.put("text", text);
        response.put("url", "https://twitter.com/user/status/" + System.currentTimeMillis());
        return response;
    }

    public Map<String, Integer> getTweetMetrics(String tweetId) {
        Map<String, Integer> metrics = new HashMap<>();
        metrics.put("impressions", 1250);
        metrics.put("likes", 45);
        metrics.put("retweets", 12);
        metrics.put("replies", 8);
        return metrics;
    }
}

