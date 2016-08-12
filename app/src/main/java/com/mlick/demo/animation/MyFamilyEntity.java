package com.mlick.demo.animation;

import android.text.TextUtils;

import org.json.JSONObject;

import java.io.Serializable;

/**
 * 我的家人实体
 * Created by wtq on 2016/5/18.
 */
public class MyFamilyEntity implements Serializable {

    /** 更新 */
    public static final int UPDATE=2;
    /** 新增 */
    public static final int INSERT=1;

    private String personId;
    private String personName;
    /**
     * 体检次数
     */
    private String examinationTimes;
    private boolean isSelect;
    private String nickName;
    private String imgUrl;
    private String simpleName;
    private int sex;
    private long birthday;
    private float height;
    /** 是否可以删除该家人 */
    private boolean isDelete;
    /** 是否可以编辑该家人备注名 */
    private boolean isEditable;
    /** 数据类型 */
    private int type;

    private SerBean2 serBean2;

    public SerBean2 getSerBean2() {
        return serBean2;
    }

    public void setSerBean2(SerBean2 serBean2) {
        this.serBean2 = serBean2;
    }

    public MyFamilyEntity(String personId, String nickName, int sex, boolean isSelect) {
        this.personId = personId;
        this.nickName = nickName;
        this.sex = sex;
        this.isSelect = isSelect;
    }

    public MyFamilyEntity() {
    }


    public MyFamilyEntity(JSONObject json) {
//        this.personId = Tools.getJsonString(json, "PersonId");
//        this.personName = Tools.getJsonString(json, "PersonName");
//        this.examinationTimes = Tools.getJsonString(json, "ExaminationTimes");
//        this.isSelect = Utils.getBoolean(Tools.getJsonString(json, "IsSelect"));
//        this.nickName = Tools.getJsonString(json, "PersonNickname");
//        this.imgUrl = Tools.getJsonString(json, "PersonImgUrl");
//        this.simpleName = Utils.getSimple(this.nickName);
//        this.sex = Tools.getJsonNum(json,"Sex");
//        this.height = (float) Tools.getJsonDouble(json,"Height");
//        this.birthday = Tools.getJsonLong(json,"Birthday");

    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getExaminationTimes() {
        return examinationTimes;
    }

    public void setExaminationTimes(String examinationTimes) {
        this.examinationTimes = examinationTimes;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    public String getNickName() {
        return TextUtils.isEmpty(nickName) ? "无" : nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getSimpleName() {
        return simpleName;
    }

    public void setSimpleName(String simpleName) {
        this.simpleName = simpleName;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public long getBirthday() {
        return birthday;
    }

    public void setBirthday(long birthday) {
        this.birthday = birthday;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public boolean isDelete() {
        return isDelete;
    }

    public void setDelete(boolean delete) {
        isDelete = delete;
    }

    public boolean isEditable() {
        return isEditable;
    }

    public void setEditable(boolean editable) {
        isEditable = editable;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "MyFamilyEntity{" +
                "personId='" + personId + '\'' +
                ", personName='" + personName + '\'' +
                ", examinationTimes='" + examinationTimes + '\'' +
                ", isSelect=" + isSelect +
                ", nickName='" + nickName + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", simpleName='" + simpleName + '\'' +
                ", sex=" + sex +
                ", birthday=" + birthday +
                ", height=" + height +
                ", isDelete=" + isDelete +
                ", isEditable=" + isEditable +
                ", type=" + type +
                '}';
    }
}
