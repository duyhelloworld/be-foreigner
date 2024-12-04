package vn.edu.huce.beforeigner.annotations;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@NotBlank(message = "CODE_MISSING")
@Pattern(regexp = "\\d{6}", message = "CODE_INVALID")
@Size(message = "CODE_INVALID")
public @interface ValidCode {
    
}
