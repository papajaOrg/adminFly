package org.papaja.adminfly.shared.annotation.mvc;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
@RequestMapping("/admin")
public @interface AdminController {

    @AliasFor(annotation = Component.class)
    String value() default "";

}
