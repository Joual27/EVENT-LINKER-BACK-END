package org.youcode.EventLinkerAPI.config.chat;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.youcode.EventLinkerAPI.shared.utils.security.JwtService;
import org.youcode.EventLinkerAPI.shared.utils.ws.StompChannelInterceptor;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    private final String clientUrl;
    private final UserDetailsService userDetailsService;
    private final JwtService jwtService;

    public WebSocketConfig(@Value("${CLIENT_URL}") String clientUrl , UserDetailsService userDetailsService , JwtService jwtService ){
        this.clientUrl = clientUrl;
        this.userDetailsService = userDetailsService;
        this.jwtService = jwtService;
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry){
        registry.addEndpoint("/ws")
                .setAllowedOriginPatterns("*");
    }

    @Override
    public void configureClientInboundChannel(org.springframework.messaging.simp.config.ChannelRegistration registration) {
        registration.interceptors(new StompChannelInterceptor(jwtService, userDetailsService));
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/topic");
        registry.setApplicationDestinationPrefixes("/app");
    }

}
