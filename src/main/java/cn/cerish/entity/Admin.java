package cn.cerish.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class Admin implements UserDetails {
    private int id;
    private String username;
    private String password;
    private String mobile;
    private int gender;
    private String email;
    private int roleId;
    private String roleName;
    private String roleNameZh;
    private boolean enabled;
    private int isLeader = -1;
    private String userface = "G:\\huajieli\\project\\backend\\tiro\\uploads\\tiro.ico";

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserface() {
        return userface;
    }

    public void setUserface(String userface) {
        this.userface = userface;
    }

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

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
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

    public int getIsLeader() {
        return isLeader;
    }

    public void setIsLeader(int isLeader) {
        this.isLeader = isLeader;
    }

    public Admin() {
    }

    public Admin(int id, String username, String password, String mobile, int gender, String email, int roleId, String roleName, String roleNameZh, boolean enabled, int isLeader, String userface) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.mobile = mobile;
        this.gender = gender;
        this.email = email;
        this.roleId = roleId;
        this.roleName = roleName;
        this.roleNameZh = roleNameZh;
        this.enabled = enabled;
        this.isLeader = isLeader;
        this.userface = userface;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", mobile='" + mobile + '\'' +
                ", gender=" + gender +
                ", email='" + email + '\'' +
                ", roleId=" + roleId +
                ", roleName='" + roleName + '\'' +
                ", roleNameZh='" + roleNameZh + '\'' +
                ", enabled=" + enabled +
                ", isLeader=" + isLeader +
                ", userface='" + userface + '\'' +
                '}';
    }

}
