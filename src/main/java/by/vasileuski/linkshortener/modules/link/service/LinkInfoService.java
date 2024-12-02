package by.vasileuski.linkshortener.modules.link.service;

import by.vasileuski.linkshortener.modules.link.dto.request.CreateLinkInfoRequest;
import by.vasileuski.linkshortener.modules.link.dto.response.LinkInfoResponse;

import java.util.List;

public interface LinkInfoService {

    LinkInfoResponse createShortLink(CreateLinkInfoRequest createLinkInfoRequest);

    LinkInfoResponse getByShortLink(String shortLink);

    List<LinkInfoResponse> findByFilter();

}
