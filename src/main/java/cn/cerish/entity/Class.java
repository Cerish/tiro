package cn.cerish.entity;


import java.io.Serializable;

public class Class {
    private int id;
    private String name;
    private int teacherId;
    private String teacherName;
    private int studentId;
    private String studentName;
    private int collegeId;
    private int majorId;
    private Major major;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(int collegeId) {
        this.collegeId = collegeId;
    }

    public int getMajorId() {
        return majorId;
    }

    public void setMajorId(int majorId) {
        this.majorId = majorId;
    }

    public Major getMajor() {
        return major;
    }

    public void setMajor(Major major) {
        this.major = major;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public Class() {
    }

    public Class(int id, String name, int teacherId, String teacherName, int studentId, String studentName, int collegeId, int majorId, Major major) {
        this.id = id;
        this.name = name;
        this.teacherId = teacherId;
        this.teacherName = teacherName;
        this.studentId = studentId;
        this.studentName = studentName;
        this.collegeId = collegeId;
        this.majorId = majorId;
        this.major = major;
    }

    @Override
    public String toString() {
        return "Class{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", teacherId=" + teacherId +
                ", teacherName='" + teacherName + '\'' +
                ", studentId=" + studentId +
                ", studentName='" + studentName + '\'' +
                ", collegeId=" + collegeId +
                ", majorId=" + majorId +
                ", major=" + major +
                '}';
    }
}
