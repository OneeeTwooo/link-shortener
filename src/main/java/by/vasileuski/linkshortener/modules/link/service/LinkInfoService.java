package by.vasileuski.linkshortener.modules.link.service;

import by.vasileuski.linkshortener.modules.link.dto.request.CreateLinkInfoRequest;

public interface LinkInfoService {

    String getShortLink(CreateLinkInfoRequest createLinkInfoRequest);

}
