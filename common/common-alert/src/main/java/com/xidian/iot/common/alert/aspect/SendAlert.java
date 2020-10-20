
package com.xidian.iot.common.alert.aspect;

import java.lang.annotation.*;

/**
 * @author bc
 * @date  2020-09-10 21:00
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited()
public @interface SendAlert {
    byte alertType();
    //AlertType alertType() default AlertType.SITE;
    String destination();
    String content();
}
