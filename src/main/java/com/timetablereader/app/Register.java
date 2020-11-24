package com.timetablereader.app;

import com.jfoenix.controls.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;


import java.io.IOException;


public class Register {

    @FXML
    private JFXTextField firstName;

    @FXML
    private JFXTextField lastName;

    @FXML
    private JFXTextField IdNumber;

    @FXML
    private JFXDatePicker dateOfBirth;

    @FXML
    private JFXTextField email;

    @FXML
    private JFXTextField address;

    @FXML
    private JFXPasswordField password;

    public JFXPasswordField getPassword() {
        return password;
    }

    public void setPassword(JFXPasswordField password) {
        this.password = password;
    }

    public JFXPasswordField getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(JFXPasswordField confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    @FXML
    private JFXPasswordField confirmPassword;
    @FXML
    private JFXButton createAccount;

    @FXML
    private JFXRadioButton genderMale;

    @FXML
    private JFXRadioButton genderFemale;

    @FXML
    private JFXButton login;


    public JFXTextField getFirstName() {
        return firstName;
    }

    public void setFirstName(JFXTextField firstName) {
        this.firstName = firstName;
    }

    public JFXTextField getLastName() {
        return lastName;
    }

    public void setLastName(JFXTextField lastName) {
        this.lastName = lastName;
    }

    public JFXTextField getIdNumber() {
        return IdNumber;
    }

    public void setIdNumber(JFXTextField idNumber) {
        IdNumber = idNumber;
    }

    public JFXDatePicker getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(JFXDatePicker dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public JFXTextField getEmail() {
        return email;
    }

    public void setEmail(JFXTextField email) {
        this.email = email;
    }

    public JFXTextField getAddress() {
        return address;
    }

    public void setAddress(JFXTextField address) {
        this.address = address;
    }

    public JFXButton getCreateAccount() {
        return createAccount;
    }

    public void setCreateAccount(JFXButton createAccount) {
        this.createAccount = createAccount;
    }

    public JFXRadioButton getGenderMale() {
        return genderMale;
    }

    public void setGenderMale(JFXRadioButton genderMale) {
        this.genderMale = genderMale;
    }

    public JFXRadioButton getGenderFemale() {
        return genderFemale;
    }

    public void setGenderFemale(JFXRadioButton genderFemale) {
        this.genderFemale = genderFemale;
    }

    public JFXButton getLogin() {
        return login;
    }

    public void setLogin(JFXButton login) {
        this.login = login;
    }

    @FXML
    void getLogin(ActionEvent event) throws IOException {

    }


    @FXML
    void makeRegistration(ActionEvent event) {

    }

}
