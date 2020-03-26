package org.easymis.easyicc.common.result;

/**
 * 返回结果常量定义
 *
 * @author tanyujie
 */
public class ResultCode {

    /**
     * 成功
     */
    public final static Integer SUCCESS = 0;

    /**
     * 失败
     */
    public final static Integer FAIL = 1;

    /**
     * 执行异常
     */
    public final static Integer ERROR = 2;

    /**
     * FALLBACK异常
     */
    public final static Integer FALLBACK_ERROR = 3;

    /**
     * OAUTHFAIL 认证失败
     */
    public final static Integer OAUTHFAIL = 2000;


    /**
     * 增值服务异常 :比如余额不足 ,vip到期
     */
    public final static Integer VALUEADDEDFAIL = 5;

    /**
     *
     *  资源禁止访问
     */
    public final static Integer SC_FORBIDDEN = 403;


    /**
 　　 * 跳转到图片
 　　 */
    public final static Integer PRECISE = 404;


}
