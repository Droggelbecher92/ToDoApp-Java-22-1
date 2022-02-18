package de.kittlaus.backend.model;

public enum Status {

    DONE("DONE"),
    IN_PROGRESS("IN_PROGRESS"),
    OPEN("OPEN");

    private String status;

    Status(String status) {
        this.status = status;
    }

    public void advance(){
        if (this.status.equals(OPEN.status)){
            status = IN_PROGRESS.status;
        } else if (status.equals(IN_PROGRESS.status)){
            status = DONE.status;
        }
    }
}
