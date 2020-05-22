package com.ibm.dip.samplemicrosvc.config;

import io.micrometer.core.instrument.Tag;
import org.springframework.boot.actuate.metrics.web.client.RestTemplateExchangeTags;
import org.springframework.boot.actuate.metrics.web.client.RestTemplateExchangeTagsProvider;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Configuration
public class RestConfig {

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder templateBuilder)
    {
        templateBuilder = templateBuilder.messageConverters(new MappingJackson2HttpMessageConverter())
                .requestFactory(this::getClientHttpRequestFactory);
        return templateBuilder.build();
    }

    @Bean
    public RestTemplateExchangeTagsProvider restTemplateExchangeTagsProvider()
    {
        return new RestTemplateExchangeTagsProvider() {
            @Override
            public Iterable<Tag> getTags(String urlTemplate, HttpRequest request, ClientHttpResponse response) {
                Tag uriTag = (StringUtils.hasText(urlTemplate) ? RestTemplateExchangeTags.uri(urlTemplate)
                        : RestTemplateExchangeTags.uri(request));
                return Arrays.asList(RestTemplateExchangeTags.method(request), uriTag,
                        RestTemplateExchangeTags.status(response), RestTemplateExchangeTags.clientName(request),
                        Tag.of("componentClass", "httpClient"),
                        Tag.of("componentType", "integration"),
                        Tag.of("methodName", uriTag.getValue()));
            }
        };
    }

    @Bean
    public ClientHttpRequestFactory getClientHttpRequestFactory()
    {
        HttpComponentsClientHttpRequestFactory httpComponentsClientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory();
        httpComponentsClientHttpRequestFactory.setConnectTimeout(30000);
        httpComponentsClientHttpRequestFactory.setReadTimeout(30000);
        return httpComponentsClientHttpRequestFactory;
    }


}
