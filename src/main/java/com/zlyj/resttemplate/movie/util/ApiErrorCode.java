package com.zlyj.resttemplate.movie.util;

import com.digidite.core.exception.ErrorCode;

public enum ApiErrorCode implements ErrorCode {

    MEETING_ROOM_EXIST("10001", "该会议室已经存在"),
    TIME_ERROR("10002", "输入时间有误"),
    ROOM_ORDER_EXIST("10003","该时段会议室已被预订"),
    ROOM_ORDER_ENPTY("10014","可用的会议室预约信息为空"),
    DEL_ERROR("10004", "删除失败"),
    QUERY_ERROR("10005", "查询出错"),
    STATUS_ERROR("10006","状态类型错误"),
    PARAMETERS_ERROR("10007", "参数错误"),
    AUTHORITY_ERROR("10006", "没有该操作的权限"),
    USER_LOGIN_ERROR("10007","用户名密码错误"),
    TOKEN_ERROR("10009", "token验证失败"),
    USER_INFO_ERROR("10010", "用户信息错误"),
    OPERATION_ERROR("10011","操作失败"),
    ORDER_APP_STATUS_ERROR("10012", "会议登记未登记或者会议室未预约"),
    MEETING_ROOM_ORDER_EMPTY("10013", "没有该会议室预订信息"),
    IMG_DECODE_ERROR("10010","图片转码失败"),
    ROOM_ORDER_TIME_CONFLICT("10011","会议室预订时间冲突，请重新选择会议室预订时间"),
    ORDER_APP_DELETE_ERROR("10012","请先到会议查询中取消会议"),
    ACK_ORDER_APP_ERROR("10013","该会议已经取消，不能进行报名操作"),
    VERSION_ERROR("10014","数据正在更新，请刷新页面再试"),
    NOTIFY_HAS_EXIST("10015","会议通知已经发送了，请不要重复发送通知"),
    NOTIFY_EMPTY("10016","会议通知为空"),
    COMMON_SERVICE_ERROR("10017","用户管理系统查询出错"),
    MEETING_ORDER_EXIST("10018","该会议室已被其他会议登记使用"),
    UNITS_ERROR("10019","单位信息入参错误"),
    FILE_EMPTY("10020","文件为空"),
    FILE_UPLOAD_ERROR("10021","文件上传失败"),
    FILE_SUFFIX_ERROR("10022","文件格式不正确"),
    DEADLINE_HAS_PAST("10023","报名时间已过，不能报名"),
    LEADERS_INFO_EXIST("10024","该领导信息已经存在"),
    SEND_NOTIFY_TIME_ERROR("10025","会议开始时间已过，不能发送通知"),
    ROOM_DELETE_ERROR("10026","该会议室存在登记的会议不能删除"),
    PROPERITIES_EMPTY("10027","输入参数为空");

    private String code;
    private String message;

    ApiErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
