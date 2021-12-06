package task.manager.model;

public class Task {
    private long id;
    private String name;
    private String description;
    private long taskListId;
    private boolean done;


    public long getId() {
        return id;
    }

    public void setId(final long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public long getTaskListId() {
        return taskListId;
    }

    public void setTaskListId(final long taskListId) {
        this.taskListId = taskListId;
    }

    public boolean getDone() {
        return done;
    }

    public void setDone(final boolean done) {
        this.done = done;
    }
}
