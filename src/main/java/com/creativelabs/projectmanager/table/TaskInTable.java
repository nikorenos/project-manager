package com.creativelabs.projectmanager.table;

import javafx.beans.property.SimpleStringProperty;

public class TaskInTable {

    private final SimpleStringProperty id;
    private final SimpleStringProperty title;
    private final SimpleStringProperty type;
    private final SimpleStringProperty status;
    private final SimpleStringProperty assignee;
    private final SimpleStringProperty creator;
    private final SimpleStringProperty created;
    private final SimpleStringProperty deadline;

    public TaskInTable(String id, String title, String type, String status, String assignee, String creator, String created, String deadline) {
        this.id = new SimpleStringProperty(id);
        this.title = new SimpleStringProperty(title);
        this.type = new SimpleStringProperty(type);
        this.status = new SimpleStringProperty(status);
        this.assignee = new SimpleStringProperty(assignee);
        this.creator = new SimpleStringProperty(creator);
        this.created = new SimpleStringProperty(created);
        this.deadline = new SimpleStringProperty(deadline);
    }

    public String getId() {
        return id.get();
    }

    public void setId(String id) {
        this.id.set(id);
    }

    public String getTitle() {
        return title.get();
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    public String getType() {
        return type.get();
    }

    public void setType(String type) {
        this.type.set(type);
    }

    public String getStatus() {
        return status.get();
    }

    public void setStatus(String status) {
        this.status.set(status);
    }

    public String getAssignee() {
        return assignee.get();
    }

    public SimpleStringProperty assigneeProperty() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee.set(assignee);
    }

    public String getCreator() {
        return creator.get();
    }

    public SimpleStringProperty creatorProperty() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator.set(creator);
    }

    public String getCreated() {
        return created.get();
    }

    public SimpleStringProperty createdProperty() {
        return created;
    }

    public void setCreated(String created) {
        this.created.set(created);
    }

    public String getDeadline() {
        return deadline.get();
    }

    public SimpleStringProperty deadlineProperty() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline.set(deadline);
    }
}
