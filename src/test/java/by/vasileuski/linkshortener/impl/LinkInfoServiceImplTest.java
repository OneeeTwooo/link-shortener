package by.vasileuski.linkshortener.impl;

import by.vasileuski.linkshortener.modules.link.config.LinkProperties;
import by.vasileuski.linkshortener.modules.link.dto.request.CreateLinkInfoRequest;
import by.vasileuski.linkshortener.modules.link.dto.response.LinkInfoResponse;
import by.vasileuski.linkshortener.modules.link.exception.NotFoundException;
import by.vasileuski.linkshortener.modules.link.model.LinkInfo;
import by.vasileuski.linkshortener.modules.link.repository.impl.LinkInfoRepositoryImpl;
import by.vasileuski.linkshortener.modules.link.service.impl.LinkInfoServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

class LinkInfoServiceImplTest {

    private LinkProperties linkProperties;
    private LinkInfoServiceImpl service;
    private LinkInfoRepositoryImpl infoRepository;

    @BeforeEach
    void setUpDependencies() {
        linkProperties = Mockito.mock(LinkProperties.class);
        infoRepository = Mockito.mock(LinkInfoRepositoryImpl.class);

        Mockito.when(linkProperties.getLength()).thenReturn(8);

        service = new LinkInfoServiceImpl(infoRepository, linkProperties);
    }

    @Test
    void createShortLink() {
        final var createLinkInfoRequestDto = new CreateLinkInfoRequest(
                "https://random.com/KJLGHDFjS123NBFJKHgs",
                LocalDateTime.now(),
                "Ссылка",
                true
        );

        Mockito.when(infoRepository.save(any(LinkInfo.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        final var shortLink = service.createShortLink(createLinkInfoRequestDto);

        System.out.println("Сгенерированная ссылка: " + shortLink);
        assertEquals(8, shortLink.getShortLink().length(), "Длина короткой ссылки должна соответствовать настройкам.");
    }

    @Test
    void when_createShortLink_then_success() {
        CreateLinkInfoRequest request = CreateLinkInfoRequest.builder()
                .link("http://google.com")
                .build();

        LinkInfoResponse response = service.createShortLink(request);
        assertEquals(8, response.getShortLink().length());

        LinkInfoResponse byShortLink = service.getByShortLink((response.getShortLink()));
        assertNotNull(byShortLink);
    }

    @Test
    void when_FindByFilter_then_success() {
        CreateLinkInfoRequest firstRequest = CreateLinkInfoRequest.builder()
                .link("http://google.com")
                .description("google start page")
                .endTime(LocalDateTime.now().plusDays(2))
                .build();
        CreateLinkInfoRequest secondRequest = CreateLinkInfoRequest.builder()
                .link("http://vk.com")
                .description("vk start page")
                .endTime(LocalDateTime.now().plusDays(1))
                .build();
        CreateLinkInfoRequest thirdRequest = CreateLinkInfoRequest.builder()
                .link("http://ya.ru")
                .description("yandex start page")
                .endTime(LocalDateTime.now().plusDays(3))
                .build();

        service.createShortLink(firstRequest);
        service.createShortLink(secondRequest);
        service.createShortLink(thirdRequest);

        List<LinkInfoResponse> listByFindAll = service.findByFilter();
        assertEquals(3, listByFindAll.size());
    }

    @Test
    void when_getByShortLink_then_fail() {
        String searchShortLink = "test";

        CreateLinkInfoRequest request = CreateLinkInfoRequest.builder()
                .link("http://google.com")
                .description("google start page")
                .endTime(LocalDateTime.now().plusDays(2))
                .build();

        service.createShortLink(request);

        NotFoundException thrown = assertThrows(NotFoundException.class, () -> service.getByShortLink(searchShortLink));

        assertEquals("Не удалось найти по короткой ссылке: test", thrown.getMessage());
    }
}