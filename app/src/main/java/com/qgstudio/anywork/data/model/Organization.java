package com.qgstudio.anywork.data.model;

/**
 * 组织实体
 * Created by FunriLy on 2017/7/10.
 * From small beginnings comes great things.
 */
public class Organization {

    private int organizationId;            //ID
    private int teacherId;               //教师ID
    private String teacherName;          //教师名称
    private String organName;       //组织名
    private String description;     //描述
    private long token;             //口令
    private int count;              //组织人数
    private int isJoin;             //判断字段，标志学生是否是该组织成员

    //TODO:考虑老师登录后是否要带上学生列表或者试卷列表

    public Organization() {
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public int getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(int organizationId) {
        this.organizationId = organizationId;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public String getOrganName() {
        return organName;
    }

    public void setOrganName(String organName) {
        this.organName = organName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getToken() {
        return token;
    }

    public void setToken(long token) {
        this.token = token;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getIsJoin() {
        return isJoin;
    }

    public void setIsJoin(int isJoin) {
        this.isJoin = isJoin;
    }
}
