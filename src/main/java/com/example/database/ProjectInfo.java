package com.example.database;

public class ProjectInfo {
    private int projectId;
    private int unitermId;
    private String projectTitle;
    private String description;

    public ProjectInfo(){

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

    public int getUnitermId() {
        return unitermId;
    }

    public void setUnitermId(int unitermId) {
        this.unitermId = unitermId;
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
}
