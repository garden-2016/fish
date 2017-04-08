package com.fish.learn.model;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

/**
 * @author noName
 * @since 简历模型
 */
@Data
public class Resume extends BaseModel{

    /**
     * 姓名图片
     */
    private String nameImg;

    /**
     * 性别
     */
    private String gender;

    /**
     * 生日
     */
    private String birthday;

    /**
     * 入学时间
     */
    private String joinTime;

    /**
     * 学历类型
     */
    private String educationType;

    /**
     * 学历等级
     */
    private String educationLevel;

    /**
     * 学校
     */
    private String school;

    /**
     * 学校地址
     */
    private String schoolAddr;

    /**
     * 专业
     */
    private String major;

    /**
     * 学习形式
     */
    private String learnType;

    /**
     * 制表时间
     */
    private String createTime;

    /**
     * 校验时间
     */
    private String verifyTime;

    /**
     * 证书编号
     */
    private String cardNo;

    /**
     * 验证码
     */
    private String verifyNo;

    /**
     * 二维码
     */
    private String verifyImg;

    /**
     * 民族
     */
    private String nations;

    /**
     * 班级
     */
    private String classNo;

    /**
     * 学号
     */
    private String studentId;

    /**
     * 学制
     */
    private String entrance;

    /**
     * 毕业日期
     */
    private String graduationTime;

}
