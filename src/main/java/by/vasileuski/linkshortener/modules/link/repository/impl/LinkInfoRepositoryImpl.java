package by.vasileuski.linkshortener.modules.link.repository.impl;

import by.vasileuski.linkshortener.modules.link.model.LinkInfo;
import by.vasileuski.linkshortener.modules.link.repository.LinkInfoRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@RequiredArgsConstructor
public class LinkInfoRepositoryImpl implements LinkInfoRepository {

    private static final Map<String, LinkInfo> linkInfoMap = new ConcurrentHashMap<>();

    public Optional<LinkInfo> findByShortLink(final String shortLink) {
        return Optional.ofNullable(linkInfoMap.get(shortLink));
    }

    @Override
    public LinkInfo save(final LinkInfo linkInfo) {
        linkInfo.setId(UUID.randomUUID());

        linkInfoMap.put(linkInfo.getShortLink(), linkInfo);

        return linkInfo;
    }

    @Override
    public List<LinkInfo> findAll() {
        return linkInfoMap.values().stream()
                .toList();
    }

}
