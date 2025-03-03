package org.youcode.EventLinkerAPI.shared.utils.ws;


import lombok.AllArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;
import org.youcode.EventLinkerAPI.user.User;

@AllArgsConstructor
@Component
public class WebSocketEventListener {

    private final WebSocketSessionManager webSocketSessionManager;

    @EventListener
    public void handleConnection(SessionConnectedEvent event) {
        Object principalObj = event.getUser();
        if (principalObj instanceof UsernamePasswordAuthenticationToken) {
            UsernamePasswordAuthenticationToken auth = (UsernamePasswordAuthenticationToken) principalObj;
            Object principal = auth.getPrincipal();
            if (principal instanceof User) {
                User authenticatedUser = (User) principal;
                SimpMessageHeaderAccessor headerAccessor = SimpMessageHeaderAccessor.wrap(event.getMessage());
                String sessionId = headerAccessor.getSessionId();
                webSocketSessionManager.trackUserSession(authenticatedUser, sessionId);
            }
        }
    }

    @EventListener
    public void handleDisconnection(SessionDisconnectEvent event) {
        Object principalObj = event.getUser();
        if (principalObj instanceof UsernamePasswordAuthenticationToken) {
            UsernamePasswordAuthenticationToken auth = (UsernamePasswordAuthenticationToken) principalObj;
            Object principal = auth.getPrincipal();
            if (principal instanceof User) {
                User authenticatedUser = (User) principal;
                SimpMessageHeaderAccessor headerAccessor = SimpMessageHeaderAccessor.wrap(event.getMessage());
                String sessionId = headerAccessor.getSessionId();
                webSocketSessionManager.removeUserSession(authenticatedUser, sessionId);
            }
        }
    }

}
