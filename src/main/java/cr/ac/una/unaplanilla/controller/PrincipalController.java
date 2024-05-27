package cr.ac.una.unaplanilla.controller;

import java.net.URL;
import java.util.ResourceBundle;

import cr.ac.una.unaplanilla.service.EmpleadoService;
import cr.ac.una.unaplanilla.util.AppContext;
import cr.ac.una.unaplanilla.util.FlowController;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class PrincipalController extends Controller implements Initializable{

    @FXML
    private MFXButton btnEmpleados;

    @FXML
    private MFXButton btnSalir;

    @FXML
    private MFXButton btnTiposPlanilla;

    @FXML
    private BorderPane root;

    @FXML
    private Label labelCedula;

    @FXML
    private Label labelUser;

    @FXML
    private MFXButton btnCerrarSesion;


    @Override
    public void initialize() {
        // TODO Auto-generated method stub
    }
    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO Auto-generated method stub
        AppContext.getInstance().get("Usuario");
        labelUser.setText(AppContext.getInstance().get("User").toString());

        EmpleadoService empleadoService = new EmpleadoService();

        labelCedula.setText(empleadoService.getCedulaporUser(AppContext.getInstance().get("User").toString()));
    }
    
    @FXML
    void onActionBtnEmpleados(ActionEvent event) {

        FlowController.getInstance().goView("EmpleadosView");

    }

    @FXML
    void onActionBtnSalir(ActionEvent event) {

        FlowController.getInstance().salir();

    }

    @FXML
    void onActionBtnTipoPlanilla(ActionEvent event) {
        FlowController.getInstance().goView("TipoPlanillaView");
    }

    @FXML
    void onActionBtnCerrarSesion(ActionEvent event) {
        FlowController.getInstance().goViewInWindow("LogInView");
        Stage stage = (Stage) this.root.getScene().getWindow();
        stage.close();

    }


}