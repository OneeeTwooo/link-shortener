package by.vasileuski.linkshortener.modules.link.repository;

import by.vasileuski.linkshortener.modules.link.model.LinkInfo;

import java.util.List;
import java.util.Optional;

public interface LinkInfoRepository {

    Optional<LinkInfo> findByShortLink(String shortLink);

    LinkInfo save(LinkInfo linkInfo);

    List<LinkInfo> findAll();

}
