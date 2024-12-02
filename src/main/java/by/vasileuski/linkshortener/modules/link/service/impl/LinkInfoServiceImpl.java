package by.vasileuski.linkshortener.modules.link.service.impl;

import by.vasileuski.linkshortener.modules.link.config.LinkProperties;
import by.vasileuski.linkshortener.modules.link.dto.request.CreateLinkInfoRequest;
import by.vasileuski.linkshortener.modules.link.dto.response.LinkInfoResponse;
import by.vasileuski.linkshortener.modules.link.exception.NotFoundException;
import by.vasileuski.linkshortener.modules.link.model.LinkInfo;
import by.vasileuski.linkshortener.modules.link.repository.LinkInfoRepository;
import by.vasileuski.linkshortener.modules.link.service.LinkInfoService;

import java.util.List;

import static org.apache.commons.lang3.RandomStringUtils.secureStrong;


public class LinkInfoServiceImpl implements LinkInfoService {

    private final LinkInfoRepository linkInfoRepository;
    private final LinkProperties linkProperties;

    public LinkInfoServiceImpl(
            final LinkInfoRepository linkInfoRepository,
            final LinkProperties linkProperties
    ) {
        this.linkInfoRepository = linkInfoRepository;
        this.linkProperties = linkProperties;
    }

    @Override
    public LinkInfoResponse createShortLink(final CreateLinkInfoRequest createLinkInfoRequest) {
        final var shortLink = secureStrong().nextAlphanumeric(linkProperties.getLength());

        final var linkInfo = LinkInfo.builder()
                .shortLink(shortLink)
                .link(createLinkInfoRequest.getLink())
                .description(createLinkInfoRequest.getDescription())
                .endTime(createLinkInfoRequest.getEndTime())
                .isActive(createLinkInfoRequest.getIsActive())
                .openingCount(0L)
                .build();

        final var saveLinkInfo = linkInfoRepository.save(linkInfo);

        return mapEntityToResponse(saveLinkInfo);
    }

    @Override
    public LinkInfoResponse getByShortLink(final String shortLink) {
        return linkInfoRepository.findByShortLink(shortLink).map(this::mapEntityToResponse)
                .orElseThrow(() -> new NotFoundException("%s not found".formatted(shortLink)));
    }

    @Override
    public List<LinkInfoResponse> findByFilter() {
        return linkInfoRepository.findAll().stream().map(this::mapEntityToResponse).toList();
    }

    private LinkInfoResponse mapEntityToResponse(final LinkInfo linkInfo) {
        return LinkInfoResponse.builder()
                .id(linkInfo.getId())
                .shortLink(linkInfo.getShortLink())
                .openingCount(linkInfo.getOpeningCount())
                .link(linkInfo.getLink())
                .endTime(linkInfo.getEndTime())
                .description(linkInfo.getDescription())
                .isActive(linkInfo.getIsActive())
                .build();
    }
}
