package com.creativelabs.projectmanager.tasks;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import static java.time.temporal.ChronoUnit.DAYS;

public final class Task {
    private final String id;
    private final String title;
    private final String description;
    private final String type;
    private final String status;
    private final String assignedUser;
    private final String creator;
    private final LocalDate created;
    private final LocalDate deadline;

    public Task(final String id, final String title, final String description,
                final String type, final String status,
                final String assignedUser, final String creator,
                final LocalDate created, final LocalDate deadline) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.type = type;
        this.status = status;
        this.assignedUser = assignedUser;
        this.creator = creator;
        this.created = created;
        this.deadline = deadline;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getType() {
        return type;
    }

    public String getStatus() {
        return status;
    }

    public String getAssignedUser() {
        return assignedUser;
    }

    public String getCreator() {
        return creator;
    }

    public LocalDate getCreated() {
        return created;
    }
    public int getHowManyDays(){
        return (int) DAYS.between(created, LocalDate.now());
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", type='" + type + '\'' +
                ", status='" + status + '\'' +
                ", assignedUser=" + assignedUser +
                ", creator=" + creator +
                ", created=" + created +
                ", deadline=" + deadline +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Task task = (Task) o;

        return id != null ? id.equals(task.id) : task.id == null;
    }

}