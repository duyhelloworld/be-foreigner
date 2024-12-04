package vn.edu.huce.beforeigner.annotations;

import jakarta.validation.constraints.Pattern;

@Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "EMAIL_INVALID")
public @interface CorrectEmail {
    
}
