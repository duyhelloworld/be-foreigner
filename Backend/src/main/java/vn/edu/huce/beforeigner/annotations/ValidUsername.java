package vn.edu.huce.beforeigner.annotations;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@NotBlank(message = "USERNAME_MISSING")
@Pattern(regexp = "^[a-z0-9]+$", message = "USERNAME_INVALID")
public @interface ValidUsername {
    
}
