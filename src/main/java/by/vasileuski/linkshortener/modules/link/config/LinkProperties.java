package by.vasileuski.linkshortener.modules.link.config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "link")
@RequiredArgsConstructor
public class LinkProperties {

    private Integer length;

}
