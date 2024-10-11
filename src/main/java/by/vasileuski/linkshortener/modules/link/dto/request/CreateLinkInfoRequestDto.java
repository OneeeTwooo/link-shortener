package by.vasileuski.linkshortener.modules.link.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateLinkInfoRequestDto {

    private String link;

    private LocalDateTime endTime;

    private String description;

    private Boolean isActive;

}
