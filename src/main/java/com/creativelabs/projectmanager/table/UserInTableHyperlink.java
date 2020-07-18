package com.creativelabs.projectmanager.table;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Hyperlink;

public class UserInTableHyperlink {


    private final Hyperlink hyperlink;
    private final SimpleStringProperty lastName;
    private final SimpleStringProperty email;

    public UserInTableHyperlink(Hyperlink hyperlink, String lName, String email) {
        this.hyperlink = new Hyperlink(lName);
        this.lastName = new SimpleStringProperty(lName);
        this.email = new SimpleStringProperty(email);
    }

    public Hyperlink getHyperlink() {
        return hyperlink;
    }

    public String getLastName() {
        return lastName.get();
    }

    public void setLastName(String fName) {
        lastName.set(fName);
    }

    public String getEmail() {
        return email.get();
    }

    public void setEmail(String fName) {
        email.set(fName);
    }
}

