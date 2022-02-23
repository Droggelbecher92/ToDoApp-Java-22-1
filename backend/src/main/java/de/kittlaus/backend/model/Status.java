package de.kittlaus.backend.model;

public enum Status {

    DONE("DONE"),
    IN_PROGRESS("IN_PROGRESS"),
    OPEN("OPEN");

    private String status;

    Status(String status) {
        this.status = status;
    }

    Status() {
        status = "OPEN";
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Status advance(){
        if (this.status.equals(OPEN.status)){
            return IN_PROGRESS;
        } else if (status.equals(IN_PROGRESS.status)){
            return DONE;
        }
        return null;
    }
}
