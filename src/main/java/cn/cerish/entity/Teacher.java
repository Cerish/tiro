package cn.cerish.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;

public class Teacher implements UserDetails {
    private int id;
    private String username;
    private String password;
    private String IdCard;
    private int age;
    private int collegeId;
    private String collegeName;
    private String mobile;
    private String address;
    private Date birth;
    private int gender = -1;
    private int isLeader = -1;
    private int titleId;
    private String titleName;
    private String titleNameZh;
    private Boolean enabled;
    private int roleId;
    private String roleName;
    private String roleNameZh;
    private String userface;

    public Teacher() {
    }

    public Teacher(int id, String username, String password, String idCard, int age, int collegeId, String collegeName, String mobile, String address, Date birth, int gender, int isLeader, int titleId, String titleName, String titleNameZh, boolean enabled, int roleId, String roleName, String roleNameZh, String userface) {
        this.id = id;
        this.username = username;
        this.password = password;
        IdCard = idCard;
        this.age = age;
        this.collegeId = collegeId;
        this.collegeName = collegeName;
        this.mobile = mobile;
        this.address = address;
        this.birth = birth;
        this.gender = gender;
        this.isLeader = isLeader;
        this.titleId = titleId;
        this.titleName = titleName;
        this.titleNameZh = titleNameZh;
        this.enabled = enabled;
        this.roleId = roleId;
        this.roleName = roleName;
        this.roleNameZh = roleNameZh;
        this.userface = userface;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", IdCard='" + IdCard + '\'' +
                ", age=" + age +
                ", collegeId=" + collegeId +
                ", collegeName='" + collegeName + '\'' +
                ", mobile='" + mobile + '\'' +
                ", address='" + address + '\'' +
                ", birth=" + birth +
                ", gender=" + gender +
                ", isLeader=" + isLeader +
                ", titleId=" + titleId +
                ", titleName='" + titleName + '\'' +
                ", titleNameZh='" + titleNameZh + '\'' +
                ", enabled=" + enabled +
                ", roleId=" + roleId +
                ", roleName='" + roleName + '\'' +
                ", roleNameZh='" + roleNameZh + '\'' +
                ", userface='" + userface + '\'' +
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
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


    public boolean isEnabled() {
        return enabled;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
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

    public int getLeader() {
        return isLeader;
    }

    public void setLeader(int leader) {
        this.isLeader = leader;
    }

    public int getTitleId() {
        return titleId;
    }

    public void setTitleId(int titleId) {
        this.titleId = titleId;
    }

    public String getTitleName() {
        return titleName;
    }

    public void setTitleName(String titleName) {
        this.titleName = titleName;
    }

    public String getTitleNameZh() {
        return titleNameZh;
    }

    public void setTitleNameZh(String titleNameZh) {
        this.titleNameZh = titleNameZh;
    }

//    public boolean getEnabled() {
//        return enabled;
//    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getUserface() {
        return userface;
    }

    public void setUserface(String userface) {
        this.userface = userface;
    }
}
