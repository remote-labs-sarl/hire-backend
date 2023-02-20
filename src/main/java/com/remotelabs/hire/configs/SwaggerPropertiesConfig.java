package com.remotelabs.hire.configs;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
@Setter
@ConfigurationProperties(prefix = "swagger")
public class SwaggerPropertiesConfig {

    private String title;
    private String description;
    private String version;
}
