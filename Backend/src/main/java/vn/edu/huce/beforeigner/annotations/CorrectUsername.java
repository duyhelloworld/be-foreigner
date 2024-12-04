package vn.edu.huce.beforeigner.annotations;

import jakarta.validation.constraints.Pattern;

@Pattern(regexp = "^[a-z0-9]+$", message = "USERNAME_INVALID")
public @interface CorrectUsername {
    
}
