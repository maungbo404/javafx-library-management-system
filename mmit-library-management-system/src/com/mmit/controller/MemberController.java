package com.mmit.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class MemberController {

    @FXML
    private ComboBox<?> cbo_year;

    @FXML
    private TableColumn<?, ?> col_academic_year;

    @FXML
    private TableColumn<?, ?> col_card_id;

    @FXML
    private TableColumn<?, ?> col_created_date;

    @FXML
    private TableColumn<?, ?> col_expired_date;

    @FXML
    private TableColumn<?, ?> col_name;

    @FXML
    private TableColumn<?, ?> col_roll_no;

    @FXML
    private TableColumn<?, ?> col_year;

    @FXML
    private TableView<?> tbl_member;

    @FXML
    private TextField txt_academic_year;

    @FXML
    private TextField txt_card_id;

    @FXML
    private TextField txt_name;

    @FXML
    private TextField txt_roll_no;

    @FXML
    void btn_add_click(ActionEvent event) {

    }

    @FXML
    void btn_back_click(ActionEvent event) {

    }

    @FXML
    void btn_edit_click(ActionEvent event) {

    }

    @FXML
    void btn_logout_click(ActionEvent event) {

    }

    @FXML
    void btn_search_click(ActionEvent event) {

    }

}
