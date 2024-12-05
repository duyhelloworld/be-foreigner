package vn.edu.huce.beforeigner.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Retention(RetentionPolicy.RUNTIME)
@NotBlank(message = "PASSWORD_MISSING")
@Pattern(regexp = "^[^\s]{8,}$'", message = "PASSWORD_INVALID")
public @interface ValidPass {
    
}
