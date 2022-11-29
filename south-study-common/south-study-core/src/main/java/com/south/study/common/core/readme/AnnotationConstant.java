package com.south.study.common.core.readme;

import com.south.study.common.core.annotation.Chinese;

/**
 * 注解作用域
 *
 * @author YueJiaJun
 * @version 0.0.1
 * @date 2022年03月10日 20:27 星期四
 * @since JDK_1.8.0.271
 */
public class AnnotationConstant {

    @Chinese("允许被修饰的注解作用在类、接口和枚举上")
    public static final String ELEMENTTYPE_TYPE = "ElementType.TYPE";

    @Chinese("允许作用在属性字段上")
    public static final String ELEMENTTYPE_FIELD = "ElementType.FIELD";

    @Chinese("允许作用在方法上")
    public static final String ELEMENTTYPE_METHOD = "ElementType.METHOD";

    @Chinese("允许作用在方法参数上")
    public static final String ELEMENTTYPE_PARAMETER = "ElementType.PARAMETER";

    @Chinese("允许作用在构造器上")
    public static final String ELEMENTTYPE_CONSTRUCTOR = "ElementType.CONSTRUCTOR";

    @Chinese("允许作用在本地局部变量上")
    public static final String ELEMENTTYPE_LOCAL_VARIABLE = "ElementType.LOCAL_VARIABLE";

    @Chinese("允许作用在注解上")
    public static final String ELEMENTTYPE_ANNOTATION_TYPE = "ElementType.ANNOTATION_TYPE";

    @Chinese("允许作用在包上")
    public static final String ELEMENTTYPE_PACKAGE = "ElementType.PACKAGE";

    @Chinese("当前注解编译期可见，不会写入 class 文件")
    public static final String RETENTIONPOLICY_SOURCE = "RetentionPolicy.SOURCE";

    @Chinese("类加载阶段丢弃，会写入 class 文件")
    public static final String RETENTIONPOLICY_CLASS = "RetentionPolicy.CLASS";

    @Chinese("永久保存，可以反射获取")
    public static final String RETENTIONPOLICY_RUNTIME = "RetentionPolicy.RUNTIME";


}
