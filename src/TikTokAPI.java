import java.util.HashMap;
import java.util.Map;

public class TikTokAPI {
    public boolean initAuth(String appId, String appSecret) {
        return appId != null && appSecret != null && !appId.isEmpty();
    }

    public Map<String, Object> uploadVideo(Map<String, Object> videoData) {
        Map<String, Object> response = new HashMap<>();
        response.put("video_id", "tk_" + System.currentTimeMillis());
        response.put("share_url", "https://tiktok.com/@user/video/" + System.currentTimeMillis());
        response.put("create_time", System.currentTimeMillis());
        response.put("status", "published");
        return response;
    }

    public Map<String, Integer> queryVideoData(String videoId) {
        Map<String, Integer> data = new HashMap<>();
        data.put("play_count", 8900);
        data.put("like_count", 234);
        data.put("share_count", 45);
        data.put("comment_count", 67);
        data.put("download_count", 23);
        return data;
    }
}
