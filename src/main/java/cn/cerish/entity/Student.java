package cn.cerish.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;

public class Student implements UserDetails {
    private int id;
    private String username;
    private String password;
    private String  IdCard;
    private int age;
    private int collegeId;
    private int majorId;
    private int classId;
    private String mobile;
    private String address;
    private Date birth;
    private Date admissionTime;
    private int gender;
    private Boolean enabled;
    private int roleId;
    private String roleName;
    private String roleNameZh;
    private String studentNo;
    private String className;
    private String majorName;
    private String collegeName;

    public Student() {
    }

    public Student(int id, String username, String password, String idCard, int age, int collegeId, int majorId, int classId, String mobile, String address, Date birth, Date admissionTime, int gender, Boolean enabled, int roleId, String roleName, String roleNameZh, String studentNo, String className, String majorName, String collegeName) {
        this.id = id;
        this.username = username;
        this.password = password;
        IdCard = idCard;
        this.age = age;
        this.collegeId = collegeId;
        this.majorId = majorId;
        this.classId = classId;
        this.mobile = mobile;
        this.address = address;
        this.birth = birth;
        this.admissionTime = admissionTime;
        this.gender = gender;
        this.enabled = enabled;
        this.roleId = roleId;
        this.roleName = roleName;
        this.roleNameZh = roleNameZh;
        this.studentNo = studentNo;
        this.className = className;
        this.majorName = majorName;
        this.collegeName = collegeName;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", IdCard='" + IdCard + '\'' +
                ", age=" + age +
                ", collegeId=" + collegeId +
                ", majorId=" + majorId +
                ", classId=" + classId +
                ", mobile='" + mobile + '\'' +
                ", address='" + address + '\'' +
                ", birth=" + birth +
                ", admissionTime=" + admissionTime +
                ", gender=" + gender +
                ", enabled=" + enabled +
                ", roleId=" + roleId +
                ", roleName='" + roleName + '\'' +
                ", roleNameZh='" + roleNameZh + '\'' +
                ", studentNo='" + studentNo + '\'' +
                ", className='" + className + '\'' +
                ", majorName='" + majorName + '\'' +
                ", collegeName='" + collegeName + '\'' +
                '}';
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleNameZh() {
        return roleNameZh;
    }

    public void setRoleNameZh(String roleNameZh) {
        this.roleNameZh = roleNameZh;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMajorName() {
        return majorName;
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName;
    }

    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getStudentNo() {
        return studentNo;
    }

    public void setStudentNo(String studentNo) {
        this.studentNo = studentNo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIdCard() {
        return IdCard;
    }

    public void setIdCard(String idCard) {
        IdCard = idCard;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
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

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public Date getAdmissionTime() {
        return admissionTime;
    }

    public void setAdmissionTime(Date admissionTime) {
        this.admissionTime = admissionTime;
    }




    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    //    public Boolean getEnabled() {
//        return enabled;
//    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

}
