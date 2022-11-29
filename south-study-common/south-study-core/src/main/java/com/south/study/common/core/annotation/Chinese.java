package com.south.study.common.core.annotation;

import java.lang.annotation.*;

/**
 * 中文
 *
 * @author YueJiaJun
 * @version 0.0.1
 * @date 2022年03月10日 20:28 星期四
 * @since JDK_1.8.0.271
 */
// Target 作用目标，参见 AnnotationConstant
@Target({
        ElementType.FIELD,
        ElementType.LOCAL_VARIABLE,
        ElementType.TYPE,
        ElementType.METHOD
})
// Retention 保留，保留到哪个阶段
@Retention(RetentionPolicy.RUNTIME)
// Inherited 允许子类继承，可以不加
@Inherited
// Documented 应该被 javadoc工具记录，可以不加
@Documented
// Constraint(validatedBy=IdCardValidator.class) 指定了当前注解使用哪个类来进行校验。
public @interface Chinese {

    // default 关键字 接口中被default修饰的方法，在类实现这个接口时不必必须实现这个方法
    String[] value() default "";

}
