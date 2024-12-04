package vn.edu.huce.beforeigner.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Retention(RetentionPolicy.RUNTIME)
@NotBlank(message = "PASSWORD_MISSING")
@Min(value = 8, message = "PASSWORD_LENGTH_NOT_ENOUGH")
@Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$'", message = "PASSWORD_INVALID")
public @interface ValidPass {
    
}
