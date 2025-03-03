package org.youcode.EventLinkerAPI.shared.utils.ws;


import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.youcode.EventLinkerAPI.user.User;

@Component
@AllArgsConstructor
public class WebSocketSessionManager {

    private final RedisTemplate<String , String> redisTemplate;

    public void trackUserSession(User user , String sessionId){
        String key = "user:" + user.getId() + ":sessions";
        redisTemplate.opsForSet().add(key , sessionId);
    }

    public void removeUserSession(User user , String sessionId){
        String key = "user:" + user.getId() + ":sessions";
        redisTemplate.opsForSet().remove(key, sessionId);
    }

    public boolean isUserOnline(Long userId){
        String key = "user:" + userId + ":sessions";
        return Boolean.TRUE.equals(redisTemplate.hasKey(key))
                && !redisTemplate.opsForSet().members(key).isEmpty();
    }


}
