package by.vasileuski.linkshortener;

import by.vasileuski.LoggingStarterApplication;
import by.vasileuski.linkshortener.modules.link.config.LinkProperties;
import by.vasileuski.loggingstartermaven.LoggingStarterMavenApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties({
        LinkProperties.class
})
@SpringBootApplication
public class LinkShortenerApplication {

    public static void main(String[] args) {
        LoggingStarterMavenApplication.println("Link Shortener");
        LoggingStarterApplication.println("Link Shortener");

        SpringApplication.run(LinkShortenerApplication.class, args);
    }

}
