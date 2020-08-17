package cn.cerish.entity;

public class Title {
    private int id;
    private String name;
    private String nameZh;

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

    public Title() {
    }

    public Title(int id, String name, String nameZh) {
        this.id = id;
        this.name = name;
        this.nameZh = nameZh;
    }
}
