package by.vasileuski.linkshortener.modules.link.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateLinkInfoRequest {

    private String link;

    private LocalDateTime endTime;

    private String description;

    private Boolean isActive;

}
