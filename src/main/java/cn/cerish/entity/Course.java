package cn.cerish.entity;

public class Course {
    private int id;
    private String name;
    private  int classId;
    private  String className;
    private  int weekDay;
    private  int weekStart;
    private  int weekEnd;
    private  int lessonStart;
    private  int lessonEnd;
    private String address;
    private  int courseId;
    private  int semester;
    private  int credit;
    private  int teacherId;
    private  String teacherName;
    private  Major major;

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

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public int getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(int weekDay) {
        this.weekDay = weekDay;
    }

    public int getWeekStart() {
        return weekStart;
    }

    public void setWeekStart(int weekStart) {
        this.weekStart = weekStart;
    }

    public int getWeekEnd() {
        return weekEnd;
    }

    public void setWeekEnd(int weekEnd) {
        this.weekEnd = weekEnd;
    }

    public int getLessonStart() {
        return lessonStart;
    }

    public void setLessonStart(int lessonStart) {
        this.lessonStart = lessonStart;
    }

    public int getLessonEnd() {
        return lessonEnd;
    }

    public void setLessonEnd(int lessonEnd) {
        this.lessonEnd = lessonEnd;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public Major getMajor() {
        return major;
    }

    public void setMajor(Major major) {
        this.major = major;
    }

    public Course() {
    }

    public Course(int id, String name, int classId, String className, int weekDay, int weekStart, int weekEnd, int lessonStart, int lessonEnd, String address, int courseId, int semester, int credit, int teacherId, String teacherName, Major major) {
        this.id = id;
        this.name = name;
        this.classId = classId;
        this.className = className;
        this.weekDay = weekDay;
        this.weekStart = weekStart;
        this.weekEnd = weekEnd;
        this.lessonStart = lessonStart;
        this.lessonEnd = lessonEnd;
        this.address = address;
        this.courseId = courseId;
        this.semester = semester;
        this.credit = credit;
        this.teacherId = teacherId;
        this.teacherName = teacherName;
        this.major = major;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", classId=" + classId +
                ", className='" + className + '\'' +
                ", weekDay=" + weekDay +
                ", weekStart=" + weekStart +
                ", weekEnd=" + weekEnd +
                ", lessonStart=" + lessonStart +
                ", lessonEnd=" + lessonEnd +
                ", address='" + address + '\'' +
                ", courseId=" + courseId +
                ", semester=" + semester +
                ", credit=" + credit +
                ", teacherId=" + teacherId +
                ", teacherName='" + teacherName + '\'' +
                ", major=" + major +
                '}';
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

}
