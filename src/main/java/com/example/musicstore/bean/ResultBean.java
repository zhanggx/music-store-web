package com.example.musicstore.bean;

import com.fasterxml.jackson.annotation.JsonInclude;

public class ResultBean {

    private boolean success;

    private int errorCode;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String text;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Object data;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Object exData;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String exToken;

    public ResultBean(){
        success=true;
        this.data=null;
        this.text="操作成功";
    }
    public ResultBean(Object data){
        success=true;
        this.data=data;
        this.text="操作成功";
    }
    public ResultBean(int errorCode, String text){
        success=false;
        this.errorCode=errorCode;
        this.text=text;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }


    public static ResultBean success() {
        return new ResultBean(null);
    }
    public static ResultBean success(Object data) {
        /*if (data instanceof Page) {
            return ResultPageBean.success(new PageInfo((Page) data));
        }*/
        return new ResultBean(data);
    }
    public static ResultBean fail() {
        return new ResultBean(500, "操作失败");
    }
    public static ResultBean fail(int errorCode) {
        String errorMsg;
        if (errorCode==0){
            errorMsg="成功";
        }else if (errorCode==1){
            errorMsg="未知错误";
        }else if (errorCode==2){
            errorMsg="服务暂不可用";
        }else if (errorCode==3){
            errorMsg="未知的方法";
        }else if (errorCode==4){
            errorMsg="无权限访问该数据";
        }else if (errorCode==5){
            errorMsg="无效操作";
        }else if (errorCode==100){
            errorMsg="请求Header参数错误";
        }else if (errorCode==101){
            errorMsg="请求参数无效";
        }else if (errorCode==102){
            errorMsg="无效签名";
        }else if (errorCode==103){
            errorMsg="请求参数过多";
        }else if (errorCode==104){
            errorMsg="未知的签名方法";
        }else if (errorCode==105){
            errorMsg="无效的user id";
        }else if (errorCode==106){
            errorMsg="用户账号密码错误";
        }else if (errorCode==107){
            errorMsg="用户不存在";
        }else if (errorCode==108){
            errorMsg="token无效";
        }else if (errorCode==109){
            errorMsg="用户状态不正确";
        }else if (errorCode==110){
            errorMsg="用户已经存在";
        }else if (errorCode==120){
            errorMsg="请求的数据不存在";
        }else if (errorCode==121){
            errorMsg="请求的数据已存在";
        }else if (errorCode==122){
            errorMsg="数据关联错误";
        }else if (errorCode==403){
            errorMsg="token已过期";
        }else if (errorCode==500){
            errorMsg="操作失败";
        }else if (errorCode==800){
            errorMsg="未知的存储操作错误";
        }else if (errorCode==801){
            errorMsg="无效的操作方法";
        }else if (errorCode==803){
            errorMsg="指定的对象不存在";
        }else if (errorCode==804	){
            errorMsg="指定的对象已存在";
        }else if (errorCode==805){
            errorMsg="数据库操作出错，请重试";
        }else {
            errorMsg = "操作失败";
        }
        return new ResultBean(errorCode, errorMsg);
    }
    public static ResultBean fail(String errorMsg) {
        return new ResultBean(600, errorMsg);
    }
    public static ResultBean fail(int code, String errorMsg) {
        return new ResultBean(code, errorMsg);
    }

    public final static ResultBean PARAMETER_ERROR_RESULTBEAN= ResultBean.fail(ResultBean.ERROR_CODE_PARAMETERERROR);
    public final static ResultBean UNKNOWN_ERROR_RESULTBEAN= ResultBean.fail(ResultBean.ERROR_CODE_UNKNOWD,"操作错误，请重试");
    public final static ResultBean DB_ERROR_RESULTBEAN= ResultBean.fail(ResultBean.ERROR_CODE_DBERROR,"数据库操作错误，请重试");

    public final static ResultBean SUCCESS_RESULTBEAN= ResultBean.success();
    public final static ResultBean FAILED_RESULTBEAN= ResultBean.fail();
    public final static ResultBean TOKENEXPRIED_RESULTBEAN= ResultBean.fail(ResultBean.ERROR_CODE_TOKENEXPRIED);
    public final static ResultBean UNAUTHORIZED_RESULTBEAN= ResultBean.fail(ResultBean.ERROR_CODE_UNAUTHORIZED);
    public static final ResultBean NOFOUND_RESULTBEAN = ResultBean.fail(404,"请求资源不存在");
    public final static ResultBean LOADINGREQUEST_RESULTBEAN= ResultBean.fail(ResultBean.ERROR_CODE_TOKENEXPRIED,"请登录后查看");
    /*
0	成功
1	未知错误
2	服务暂不可用
3	未知的方法
4	无权限访问该数据
5	无效操作
100	请求Header参数无效
101	请求参数无效
102	无效签名
103	请求参数过多
104	未知的签名方法
105	无效的user id
106	用户账号密码错误
107	用户不存在
108	token无效
109	状态错误
110	用户已经存在
120 请求的数据不存在
121 请求的数据已存在
403	token过期
500	操作失败
600-699	自定义错误
800	未知的存储操作错误
801	无效的操作方法
803	指定的对象不存在
804	指定的对象已存在
805	数据库操作出错，请重试

    /**
     * 成功
     */
    public static final int ERROR_CODE_SUCCESS=0;
    /**
     * 未知
     */
    public static final int ERROR_CODE_UNKNOWD=1;
    /**
     * 服务暂不可用
     */
    public static final int ERROR_CODE_SERVICEUNAVAILABLE=2;
    /**
     * 未知的方法
     */
    public static final int ERROR_CODE_UNKOWNMETHOD=3;
    /**
     * 无权限访问该数据
     */
    public static final int ERROR_CODE_UNAUTHORIZED=4;
    /**
     * 无效操作
     */
    public static final int ERROR_CODE_OPERERROR=5;
    /**
     * 请求Header参数错误
     */
    public static final int ERROR_CODE_PARAMETERHEADERERROR=100;
    /**
     * 请求参数无效
     */
    public static final int ERROR_CODE_PARAMETERERROR=101;
    /**
     * 无效签名
     */
    public static final int ERROR_CODE_SIGNERROR=102;
    /**
     * 请求参数过多
     */
    public static final int ERROR_CODE_PARAMETEROVERLOAD=103;

    /**
     * 未知的签名方法
     */
    public static final int ERROR_CODE_UNKNOWDSIGN=104;

    /**
     * 无效的user id
     */
    public static final int ERROR_CODE_USERIDERROR=105;
    /**
     * 用户账号密码错误
     */
    public static final int ERROR_CODE_PASSWORDERROR=106;

    /**
     * 用户不存在
     */
    public static final int ERROR_CODE_USERNOEXISTS=107;
    /**
     * token无效
     */
    public static final int ERROR_CODE_INVALIDTOKEN=108;
    /**
     * 用户状态错误
     */
    public static final int ERROR_CODE_USERSTATUSERROR=109;
    /**
     * 用户已经存在
     */
    public static final int ERROR_CODE_USEREXISTED=110;
    /**
     * 数据不存在
     */
    public static final int ERROR_CODE_RECORDNOEXISTS=120;
    /**
     * 数据已存在
     */
    public static final int ERROR_CODE_RECORDEXISTED=121;
    /**
     * 数据已关联
     */
    public static final int ERROR_CODE_DATARELATION=122;
    /**
     * token过期
     */
    public static final int ERROR_CODE_TOKENEXPRIED=403;
    /**
     * 未知的存储操作错误
     */
    public static final int ERROR_CODE_STORGEERROR=800;
    /**
     * 无效的操作方法
     */
    public static final int ERROR_CODE_INVALIDMOTHOD=801;
    /**
     * 指定的对象不存在
     */
    public static final int ERROR_CODE_OBJECTNOEXITS=803;
    /**
     * 指定的对象已存在
     */
    public static final int ERROR_CODE_OBJECTEXITED=804;
    /**
     * 数据库操作出错，请重试
     */
    public static final int ERROR_CODE_DBERROR=805;

    public String getExToken() {
        return exToken;
    }

    public void setExToken(String exToken) {
        this.exToken = exToken;
    }

    public Object getExData() {
        return exData;
    }

    public void setExData(Object exData) {
        this.exData = exData;
    }
}
