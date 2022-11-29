package com.south.study.common.core.constant;

import com.south.study.common.core.domain.AbstractBaseEnum;

import java.util.Arrays;
import java.util.List;

/**
 * 基础响应码
 *
 * @author Juner
 * @version 0.0.1
 * @description
 * @date 2022年11月27日 9:49 星期日
 * @since JDK_1.8.0.271
 */
public enum BasicResponseCode implements AbstractBaseEnum<Integer, String> {

    // 2xxx 操作成功
    SUCCESS(200, "操作成功"),

    // 5xxx 服务器异常（与程序相关的异常）
    SYSTEM_BUSY(50_00_01, "系统繁忙，请稍候再试"),
    OPERATION_ERROR(50_00_02, "操作失败"),
    REMOTE_CALL_EXCEPTION(50_00_03, "系统繁忙", "远程调用异常"),
    SPRING_BIND_EXCEPTION(50_10_01, "系统异常，请联系开发人员", "Spring Bind 异常"),

    // 4xxx 操作异常（与程序无关的异常）
    ACCOUNT_EXISTED(40_00_01, "账号已存在"),
    PHONE_ALREADY_IN_USE(40_00_02, "手机号已被使用"),
    ACCOUNT_NOT_EXSIT(40_00_03, "账号不存在"),
    PASSWORD_ERROR(40_00_04, "用户名或密码错误"),
    ACCOUNT_LOCK(40_00_05, "该用户已被锁定，请联系运营人员"),
    CAPTCHA_INVALID(40_00_06, "验证码无效"),
    CAPTCHA_EXPIRED(40_00_07, "验证码已失效"),
    CAPTCHA_ERROR(40_00_08, "验证码不正确"),
    TOKEN_ERROR(40_00_09, "登录凭证已过期，请重新登录"),

    REQUEST_METHOD_DISABLE(40_10_01, "请求无效，请更换请求方式"),

    FILE_SIZE_ERROR(40_40_01, "文件大小超出限制"),
    HTTP_MEDIA_TYPE_NOT_SUPPORTED(40_40_02, "HTTP媒体类型不支持"),

    METHOD_ARGUMENT_NOT_VALID_EXCEPTION(401007, "方法参数校验异常"),
    UNAUTHORIZED_ERROR(401008, "权鉴校验不通过"),
    ROLE_PERMISSION_RELATION(401009, "该菜单权限存在子集关联，不允许删除"),
    OLD_PASSWORD_ERROR(401010, "旧密码不正确"),
    NOT_PERMISSION_DELETED_DEPT(401011, "该组织机构下还关联着用户，不允许删除"),
    OPERATION_MENU_PERMISSION_CATALOG_ERROR(401012, "操作后的菜单类型是目录，所属菜单必须为默认顶级菜单或者目录"),
    OPERATION_MENU_PERMISSION_MENU_ERROR(401013, "操作后的菜单类型是菜单，所属菜单必须为目录类型"),
    OPERATION_MENU_PERMISSION_BTN_ERROR(401013, "操作后的菜单类型是按钮，所属菜单必须为菜单类型"),
    OPERATION_MENU_PERMISSION_URL_NOT_NULL(401015, "菜单权限的url不能为空"),
    OPERATION_MENU_PERMISSION_URL_PERMS_NULL(401016, "菜单权限的标识符不能为空")
            ;

    /** 响应代码 */
    private final Integer key;

    /** 响应消息 */
    private final String value;

    /** 备注 */
    private final List<String> remark;

    BasicResponseCode(Integer key, String value, String... remark) {
        this.key = key;
        this.value = value;
        this.remark = Arrays.asList(remark);
    }

    @Override
    public Integer key() {
        return key;
    }

    @Override
    public String value() {
        return value;
    }

}
