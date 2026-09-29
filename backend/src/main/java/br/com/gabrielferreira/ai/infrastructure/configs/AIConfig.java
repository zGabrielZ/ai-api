package br.com.gabrielferreira.ai.infrastructure.configs;

import br.com.gabrielferreira.ai.infrastructure.configs.properties.AIProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(AIProperties.class)
public class AIConfig {

}
