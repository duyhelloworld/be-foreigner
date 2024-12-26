package vn.edu.huce.beforeigner.annotations.validation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.constraints.Pattern;

import java.lang.annotation.Documented;

@Documented
@Pattern(regexp = "^[a-zA-Z0-9]+$", message = "USERNAME_INVALID")
@Target({ ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidUsername {
    
}
