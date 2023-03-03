package ibf2022ssf.assessment;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

import ibf2022ssf.assessment.repository.ItemRepo;

public class AppConfig {
    @Bean
    @Scope("singleton")
    public ItemRepo createCart() {
    return new ItemRepo();
    }
}
