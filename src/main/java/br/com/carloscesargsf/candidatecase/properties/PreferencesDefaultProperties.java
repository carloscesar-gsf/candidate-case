package br.com.carloscesargsf.candidatecase.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConfigurationProperties(prefix = "api.preferences.default")
@ConstructorBinding
public class PreferencesDefaultProperties {

    private final Integer pageSize;

    public PreferencesDefaultProperties(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageSize() {
        return pageSize;
    }

}
