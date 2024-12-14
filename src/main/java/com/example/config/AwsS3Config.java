package com.example.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.Resource;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.context.annotation.Bean;

import java.util.Properties;

@Configuration
public class AwsS3Config {

    @Value("${cloud.aws.credentials.access-key}")
    private String accessKey;

    @Value("${cloud.aws.credentials.secret-key}")
    private String secretKey;

    @Value("${cloud.aws.region.static}")
    private String region;

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();

        try {
            // YAML 파일 로드
            YamlPropertiesFactoryBean yaml = new YamlPropertiesFactoryBean();
            Resource resource = new PathMatchingResourcePatternResolver()
                    .getResource("classpath:application.yaml");
            yaml.setResources(resource);
            Properties properties = yaml.getObject();

            // YAML 프로퍼티 설정
            configurer.setProperties(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return configurer;
    }
}
