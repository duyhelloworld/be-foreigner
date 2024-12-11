package vn.edu.huce.beforeigner.infrastructures.remindmodule.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import vn.edu.huce.beforeigner.domains.remind.RemindFrequence;
import vn.edu.huce.beforeigner.domains.remind.RemindMethod;
import vn.edu.huce.beforeigner.domains.remind.RemindType;

@Data
@AllArgsConstructor
public class TemplateDto {
    
    private String title;

    private String body;

    private RemindMethod method;

    private RemindType type;

    private RemindFrequence frequence;
}
