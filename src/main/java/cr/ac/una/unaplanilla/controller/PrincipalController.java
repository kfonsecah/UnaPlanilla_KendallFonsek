package cr.ac.una.unaplanilla.controller;


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.event.ActionEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.AnchorPane;
import cr.ac.una.unaplanilla.util.FlowController;
import cr.ac.una.unaplanilla.controller.Controller;


/**
 * FXML Controller class
 *
 * @author kfonsecah
 */
public class PrincipalController extends Controller implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private MFXButton btnEmpleados;

    @FXML
    private MFXButton btnTiposDePlanilla;

    @FXML
    private BorderPane root;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    @Override
    public void initialize() {

    }
    public void onActionBtnEmpleados(ActionEvent event){
        FlowController.getInstance().goView("EmpleadosView");
    }
    public void onActionBtnTiposDePlanilla(ActionEvent event){
        FlowController.getInstance().goView("TiposPlanillaView");
    }
    
}
