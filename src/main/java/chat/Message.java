package chat;


public class Message  {
	private String name;
    private String classRoom;
    private String msg;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getClassroom() {
        return classRoom;
    }

    public void setClassroom(String classRoom) {
        this.classRoom = classRoom;
    }
}
