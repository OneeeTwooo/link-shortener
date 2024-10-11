package by.vasileuski.linkshortener.impl;

import by.vasileuski.linkshortener.modules.link.config.LinkProperties;
import by.vasileuski.linkshortener.modules.link.dto.request.CreateLinkInfoRequestDto;
import by.vasileuski.linkshortener.modules.link.service.impl.LinkInfoServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;

class LinkInfoServiceImplTest {

    private LinkProperties linkProperties;
    private LinkInfoServiceImpl service;

    @BeforeEach
    void setUpDependencies() {
        linkProperties = Mockito.mock(LinkProperties.class);

        Mockito.when(linkProperties.getLength()).thenReturn(8);

        service = new LinkInfoServiceImpl(linkProperties);
    }

    @Test
    void getShortLink() {
        final var createLinkInfoRequestDto = new CreateLinkInfoRequestDto(
                "https://random.com/KJLGHDFjS123NBFJKHgs",
                LocalDateTime.now(),
                "Ссылка",
                true
        );

        final var shortLink = service.getShortLink(createLinkInfoRequestDto);

        System.out.println("Сгенерированная ссылка: " + shortLink);
    }

}