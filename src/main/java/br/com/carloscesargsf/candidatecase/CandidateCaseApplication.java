package br.com.carloscesargsf.candidatecase;

import br.com.carloscesargsf.candidatecase.properties.PreferencesDefaultProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EntityScan(basePackageClasses = {CandidateCaseApplication.class, Jsr310JpaConverters.class})
@EnableConfigurationProperties({PreferencesDefaultProperties.class})
@EnableAsync
public class CandidateCaseApplication {

    public static void main(String[] args) {
        SpringApplication.run(CandidateCaseApplication.class, args);
    }

}
