package com.example.hometask_03_contacts;

class ContactClass {
    String name;
    String numberOrEmail;
    boolean isEmail = false ;

    String getName() {
        return this.name;
    }

    String getNumberOrEmail() {
        return numberOrEmail;
    }

    boolean isItEmail() {return isEmail; }

    void setNumberOrEmail(String numberOrEmail) {
        this.numberOrEmail = numberOrEmail;
    }

    void setName(String name) {
        this.name = name;
    }

    void itIsEmail() {this.isEmail = true ;}


}
