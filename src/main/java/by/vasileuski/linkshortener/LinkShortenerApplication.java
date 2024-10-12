package by.vasileuski.linkshortener;

import by.vasileuski.linkshortener.modules.link.config.LinkProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties({
        LinkProperties.class
})
@SpringBootApplication
public class LinkShortenerApplication {

    public static void main(String[] args) {
        SpringApplication.run(LinkShortenerApplication.class, args);
    }

}
