package cn.cerish.entity;

public class Score {
    private int id;
    private String courseName;
    private int courseId;
    private String studentNo;
    private String studentName;
    private int teacherId;
    private String teacherName;
    private int credit;
    private int score;
    private int classId;
    private String className;
    private int semester;
    private int majorId;
    private String majorName;
    private int collegeId;
    private String collegeName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getStudentNo() {
        return studentNo;
    }

    public void setStudentNo(String studentNo) {
        this.studentNo = studentNo;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public int getMajorId() {
        return majorId;
    }

    public void setMajorId(int majorId) {
        this.majorId = majorId;
    }

    public String getMajorName() {
        return majorName;
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName;
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

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Score() {
    }

    public Score(int id, String courseName, int courseId, String studentNo, String studentName, int teacherId, String teacherName, int credit, int score, int classId, String className, int semester, int majorId, String majorName, int collegeId, String collegeName) {
        this.id = id;
        this.courseName = courseName;
        this.courseId = courseId;
        this.studentNo = studentNo;
        this.studentName = studentName;
        this.teacherId = teacherId;
        this.teacherName = teacherName;
        this.credit = credit;
        this.score = score;
        this.classId = classId;
        this.className = className;
        this.semester = semester;
        this.majorId = majorId;
        this.majorName = majorName;
        this.collegeId = collegeId;
        this.collegeName = collegeName;
    }

    @Override
    public String toString() {
        return "Score{" +
                "id=" + id +
                ", courseName='" + courseName + '\'' +
                ", courseId=" + courseId +
                ", studentNo=" + studentNo +
                ", studentName='" + studentName + '\'' +
                ", teacherId=" + teacherId +
                ", teacherName='" + teacherName + '\'' +
                ", credit=" + credit +
                ", score=" + score +
                ", classId=" + classId +
                ", className='" + className + '\'' +
                ", semester=" + semester +
                ", majorId=" + majorId +
                ", majorName='" + majorName + '\'' +
                ", collegeId=" + collegeId +
                ", collegeName='" + collegeName + '\'' +
                '}';
    }
}
