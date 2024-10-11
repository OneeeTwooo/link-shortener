package by.vasileuski.linkshortener.modules.link.service.impl;

import by.vasileuski.linkshortener.modules.link.config.LinkProperties;
import by.vasileuski.linkshortener.modules.link.dto.request.CreateLinkInfoRequestDto;
import by.vasileuski.linkshortener.modules.link.service.LinkInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

import static org.apache.commons.lang3.RandomStringUtils.secureStrong;

@Service
@RequiredArgsConstructor
public class LinkInfoServiceImpl implements LinkInfoService {

    private static final Map<String, CreateLinkInfoRequestDto> linkInfoMap = new HashMap<>();

    private final LinkProperties linkProperties;

    @Override
    public String getShortLink(final CreateLinkInfoRequestDto createLinkInfoRequestDto) {
        final var shortLink = secureStrong().nextAlphanumeric(linkProperties.getLength());

        linkInfoMap.put(shortLink, createLinkInfoRequestDto);

        return shortLink;
    }
}
