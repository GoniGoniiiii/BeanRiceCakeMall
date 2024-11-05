package com.example.beanricecakemall.oauth2;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import com.example.beanricecakemall.oauth2.SocialClientRegistration;

@Configuration
public class CustomClientRegistrationRepository {

    private final SocialClientRegistration socialClientRegistration;

    public CustomClientRegistrationRepository(SocialClientRegistration socialClientRegistration){
        this.socialClientRegistration=socialClientRegistration;
    }

    public ClientRegistrationRepository clientRegistrationRepository(){
        return new InMemoryClientRegistrationRepository(socialClientRegistration.naverClientRegistration(),socialClientRegistration.googleClientRegistration());
    }
}
