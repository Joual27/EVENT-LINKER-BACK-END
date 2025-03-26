package org.youcode.EventLinkerAPI.config.cloud;


import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CloudinaryConfig {

    private final String cloudName;
    private final String apiKey;
    private final String apiSecret;



    public CloudinaryConfig(@Value("${CLOUDINARY_CLOUD_NAME}") String cloudName , @Value("${CLOUDINARY_API_KEY}") String apiKey , @Value("${CLOUDINARY_API_SECRET}") String apiSecret){
        this.cloudName = cloudName;
        this.apiKey = apiKey;
        this.apiSecret = apiSecret;
    }

    @Bean
    public Cloudinary cloudinary(){
        return new Cloudinary(ObjectUtils.asMap(
                "cloud_name" , cloudName,
                 "api_key" , apiKey,
                "api_secret" , apiSecret ,
                "secure" , true
        ));
    }
}
