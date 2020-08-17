package cn.cerish.entity;

public class MyUserDetail {
    private int userId;
    private String username;
    private int roleId;
    private String roleName;
    private String roleNameZh;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
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

    public MyUserDetail() {
    }

    public MyUserDetail(int userId, String username, int roleId, String roleName, String roleNameZh) {
        this.userId = userId;
        this.username = username;
        this.roleId = roleId;
        this.roleName = roleName;
        this.roleNameZh = roleNameZh;
    }

    @Override
    public String toString() {
        return "MyUserDetail{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", roleId=" + roleId +
                ", roleName='" + roleName + '\'' +
                ", roleNameZh='" + roleNameZh + '\'' +
                '}';
    }
}
