package cn.cerish.entity;

public class Major {
    private int id;
    private String name;
    private String nameZh;
    private int campusId;
    private String campusName;
    private String campusNameZh;
    private int collegeId;
    private String collegeName;
    private String collegeNameZh;

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

    public String getNameZh() {
        return nameZh;
    }

    public void setNameZh(String nameZh) {
        this.nameZh = nameZh;
    }

    public int getCampusId() {
        return campusId;
    }

    public void setCampusId(int campusId) {
        this.campusId = campusId;
    }

    public String getCampusName() {
        return campusName;
    }

    public void setCampusName(String campusName) {
        this.campusName = campusName;
    }

    public String getCampusNameZh() {
        return campusNameZh;
    }

    public void setCampusNameZh(String campusNameZh) {
        this.campusNameZh = campusNameZh;
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

    public String getCollegeNameZh() {
        return collegeNameZh;
    }

    public void setCollegeNameZh(String collegeNameZh) {
        this.collegeNameZh = collegeNameZh;
    }

    public Major() {
    }

    public Major(int id, String name, String nameZh, int campusId, String campusName, String campusNameZh, int collegeId, String collegeName, String collegeNameZh) {
        this.id = id;
        this.name = name;
        this.nameZh = nameZh;
        this.campusId = campusId;
        this.campusName = campusName;
        this.campusNameZh = campusNameZh;
        this.collegeId = collegeId;
        this.collegeName = collegeName;
        this.collegeNameZh = collegeNameZh;
    }

    @Override
    public String toString() {
        return "Major{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", nameZh='" + nameZh + '\'' +
                ", campusId=" + campusId +
                ", campusName='" + campusName + '\'' +
                ", campusNameZh=" + campusNameZh +
                ", collegeId=" + collegeId +
                ", collegeName='" + collegeName + '\'' +
                ", collegeNameZh=" + collegeNameZh +
                '}';
    }
}
