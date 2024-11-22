import java.util.Date;

public class Task {
    
    private String name;
    private String description;
    private Date createDate;
    private int deadline;

    public Task(String name, String description, Date createDate, int deadline) {
        this.name = name;
        this.description = description;
        this.createDate = createDate;
        this.deadline = deadline;
    }

    public Task() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(int year, int month, int day) {
        this.createDate = new Date(year, month, day);
    }

    public int getDeadline() {
        return deadline;
    }

    public void setDeadline(int deadline) {
        this.deadline = deadline;
    }
    
    
}