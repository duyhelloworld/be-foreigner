package vn.edu.huce.beforeigner.infrastructures.remindmodule.dtos;

import java.time.LocalDateTime;
import java.util.Map;

import lombok.Builder;
import lombok.Data;
import vn.edu.huce.beforeigner.domains.remind.RemindMethod;

@Data
@Builder
public class RemindDto {

    private Integer id;
    
    private String title;

    private String content;

    private Map<String, String> data;

    private boolean isRead;

    private LocalDateTime sendAt;

    private RemindMethod method;
}
