package com.example.database;

import com.example.simpledrawingproject.Uniterm;

import java.util.ArrayList;
import java.util.List;

public class ProjectInfo {
    private int projectId;
    private String projectTitle;
    private String description;
    public ProjectInfo() {
    }
    public ProjectInfo(int projectId, String projectTitle, String description) {
        this.projectId = projectId;
        this.projectTitle = projectTitle;
        this.description = description;
    }
    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getProjectTitle() {
        return projectTitle;
    }

    public void setProjectTitle(String projectTitle) {
        this.projectTitle = projectTitle;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "ProjectInfo{" +
                "projectId=" + projectId +
                ", projectTitle='" + projectTitle + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
