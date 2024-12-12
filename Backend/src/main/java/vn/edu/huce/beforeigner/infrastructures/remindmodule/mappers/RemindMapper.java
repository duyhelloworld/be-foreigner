package vn.edu.huce.beforeigner.infrastructures.remindmodule.mappers;

import java.util.Optional;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import vn.edu.huce.beforeigner.commons.AppObjectMapper;
import vn.edu.huce.beforeigner.domains.remind.Remind;
import vn.edu.huce.beforeigner.infrastructures.remindmodule.dtos.RemindDto;

@Component
@RequiredArgsConstructor
public class RemindMapper {

    private final AppObjectMapper objectMapper;

    public RemindDto toDto(Remind remind) {
        return RemindDto.builder()
                .id(remind.getId())
                .title(remind.getTitle())
                .content(remind.getBody())
                .method(remind.getMethod())
                .data(Optional.ofNullable(remind.getData()).map(d -> objectMapper.readJson(d)).orElse(null))
                .sendAt(remind.getLastUpdatedAt())
                .isRead(remind.isRead())
                .build();
    }
}
