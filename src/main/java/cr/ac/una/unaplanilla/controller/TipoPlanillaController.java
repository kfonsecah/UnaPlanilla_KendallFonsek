
package cr.ac.una.unaplanilla.controller;

import io.github.palexdev.materialfx.controls.*;
import io.github.palexdev.materialfx.controls.MFXTextField;
import cr.ac.una.unaplanilla.model.EmpleadoDto;
import cr.ac.una.unaplanilla.model.TipoPlanillaDto;
import cr.ac.una.unaplanilla.service.EmpleadoService;
import cr.ac.una.unaplanilla.service.TipoPlanillaService;
import cr.ac.una.unaplanilla.util.FlowController;
import cr.ac.una.unaplanilla.util.Formato;
import cr.ac.una.unaplanilla.util.Mensaje;
import cr.ac.una.unaplanilla.util.Respuesta;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class TipoPlanillaController extends Controller implements Initializable {

    @FXML
    private TabPane tbpTipoPlanilla;
    @FXML
    private Tab tbpTipoPlanillas;
    @FXML
    private MFXTextField txtId;
    @FXML
    private MFXCheckbox chkActivo;
    @FXML
    private MFXTextField txtCodigo;
    @FXML
    private MFXTextField txtDescripcion;
    @FXML
    private MFXTextField txtPlanillasMes;
    @FXML
    private Tab tbpInclusionEmpleados;
    @FXML
    private MFXTextField txtIdEmpleado;
    @FXML
    private MFXTextField txtNombreEmpleado;
    @FXML
    private MFXButton btnAgregar;
    @FXML
    private TableView<EmpleadoDto> tbvEmpleados;
    @FXML
    private TableColumn<EmpleadoDto, String> tbcId;
    @FXML
    private TableColumn<EmpleadoDto, String> tbcNombre;
    @FXML
    private TableColumn<EmpleadoDto, Boolean> tbcEliminar;
    @FXML
    private MFXButton btnNuevo;
    @FXML
    private MFXButton btnBuscar;
    @FXML
    private MFXButton btnEliminar;
    @FXML
    private MFXButton btnGuardar;

    private TipoPlanillaDto tipoPlanillaDto;
    private EmpleadoDto empleadoDto;
    List<Node> requeridos = new ArrayList<>();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txtId.delegateSetTextFormatter(Formato.getInstance().integerFormat());
        txtCodigo.delegateSetTextFormatter(Formato.getInstance().maxLengthFormat(4));
        txtDescripcion.delegateSetTextFormatter(Formato.getInstance().letrasFormat(40));
        txtPlanillasMes.delegateSetTextFormatter(Formato.getInstance().integerFormat());
        txtNombreEmpleado.delegateSetTextFormatter(Formato.getInstance().letrasFormat(20));
        tipoPlanillaDto = new TipoPlanillaDto();
        empleadoDto = new EmpleadoDto();
        nuevoTipoPlanilla();
        indicarRequeridos();

        tbvEmpleados.refresh();

        tbcId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tbcNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));


        tbcEliminar.setCellFactory(param -> new TableCell<EmpleadoDto, Boolean>() {
            private final MFXButton btnEliminar = new MFXButton();

            @Override
            protected void updateItem(Boolean item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                    setText(null);
                } else {
                    ImageView imageView = new ImageView(new Image(getClass().getResourceAsStream("/cr/ac/una/unaplanilla/resources/Eliminar-48-red.png")));
                    imageView.setFitHeight(40);
                    imageView.setFitWidth(40);
                    btnEliminar.setGraphic(imageView);
                    btnEliminar.setText("\u200E");
                    btnEliminar.rippleColorProperty().set(null);
                    btnEliminar.setOnAction(event -> {
                        EmpleadoService empleadoService = new EmpleadoService();
                        Respuesta respuesta = empleadoService.eliminarEmpleado(getTableView().getItems().get(getIndex()).getId());
                        //eliminar de la tabal
                        if (respuesta.getEstado()) {
                        getTableView().getItems().remove(getIndex());
                        getTableView().refresh();
    } else {
        new Mensaje().showModal(Alert.AlertType.ERROR, "", getStage(), respuesta.getMensaje());
    }
                    });
                    setGraphic(btnEliminar);
                }
            }
        });

        cargarTodosLosEmpleados();
    }

//    EmpleadoService empleadoService = new EmpleadoService();
//    Respuesta respuesta = empleadoService.eliminarEmpleado(getTableView().getItems().get(getIndex()).getId());
//    //eliminar de la tabal
//                        if (respuesta.getEstado()) {
//        getTableView().getItems().remove(getIndex());
//        getTableView().refresh();
//    } else {
//        new Mensaje().showModal(Alert.AlertType.ERROR, "Eliminar Empleado", getStage(), respuesta.getMensaje());
//    }

    @Override
    public void initialize() {
    }



    public void indicarRequeridos() {
        requeridos.clear();
        requeridos.addAll(Arrays.asList(txtCodigo, txtDescripcion, txtPlanillasMes));
    }

    public String validarRequeridos() {
        Boolean validos = true;
        String invalidos = "";
        for (Node node : requeridos) {
            if (node instanceof MFXTextField && (((MFXTextField) node).getText() == null || ((MFXTextField) node).getText().isBlank())) {
                if (validos) {
                    invalidos += ((MFXTextField) node).getFloatingText();
                } else {
                    invalidos += "," + ((MFXTextField) node).getFloatingText();
                }
                validos = false;
            } else if (node instanceof MFXPasswordField && (((MFXPasswordField) node).getText() == null || ((MFXPasswordField) node).getText().isBlank())) {
                if (validos) {
                    invalidos += ((MFXPasswordField) node).getFloatingText();
                } else {
                    invalidos += "," + ((MFXPasswordField) node).getFloatingText();
                }
                validos = false;
            } else if (node instanceof MFXDatePicker && ((MFXDatePicker) node).getValue() == null) {
                if (validos) {
                    invalidos += ((MFXDatePicker) node).getFloatingText();
                } else {
                    invalidos += "," + ((MFXDatePicker) node).getFloatingText();
                }
                validos = false;
            } else if (node instanceof MFXComboBox && ((MFXComboBox) node).getSelectionModel().getSelectedIndex() < 0) {
                if (validos) {
                    invalidos += ((MFXComboBox) node).getFloatingText();
                } else {
                    invalidos += "," + ((MFXComboBox) node).getFloatingText();
                }
                validos = false;
            }
        }
        if (validos) {
            return "";
        } else {
            return "Campos requeridos o con problemas de formato [" + invalidos + "].";
        }
    }

    private void bindTipoPlanilla(Boolean nuevo) {
        if (!nuevo) {
            txtId.textProperty().bindBidirectional(tipoPlanillaDto.id);
        }
        txtCodigo.textProperty().bindBidirectional(tipoPlanillaDto.codigo);
        txtDescripcion.textProperty().bindBidirectional(tipoPlanillaDto.descripcion);
        txtPlanillasMes.textProperty().bindBidirectional(tipoPlanillaDto.planillasPorMes);
        chkActivo.selectedProperty().bindBidirectional(tipoPlanillaDto.estado);

        cargarEmpleados();
    }

    private void unbindTipoPlanilla() {
        txtId.textProperty().unbind();
        txtCodigo.textProperty().unbindBidirectional(tipoPlanillaDto.codigo);
        txtDescripcion.textProperty().unbindBidirectional(tipoPlanillaDto.descripcion);
        txtPlanillasMes.textProperty().unbindBidirectional(tipoPlanillaDto.planillasPorMes);
        chkActivo.selectedProperty().unbindBidirectional(tipoPlanillaDto.estado);
    }




    private void nuevoTipoPlanilla() {
        unbindTipoPlanilla();
        tipoPlanillaDto = new TipoPlanillaDto();
        bindTipoPlanilla(true);
        txtId.clear();
        txtId.requestFocus();
    }


    private void cargarTipoPlanilla(Long id) {
        try {
            TipoPlanillaService tipoPlanillaService = new TipoPlanillaService();
            Respuesta respuesta = tipoPlanillaService.getTipoPlanilla(id);

            if (respuesta.getEstado()) {
                unbindTipoPlanilla();
                this.tipoPlanillaDto = (TipoPlanillaDto) respuesta.getResultado("TipoPlanilla");
                bindTipoPlanilla(false);
                validarRequeridos();
            } else {
                new Mensaje().showModal(Alert.AlertType.ERROR, "Cargar Tipo Planilla", getStage(), respuesta.getMensaje());
            }
        } catch (Exception ex) {
            Logger.getLogger(TipoPlanillaController.class.getName()).log(Level.SEVERE, "Error consultando el tipo de planilla.", ex);
            new Mensaje().showModal(Alert.AlertType.ERROR, "Cargar Tipo Planilla", getStage(), "Ocurrio un error consultando el tipo de planilla.");
        }
    }


    private void cargarEmpleados() {
        tbvEmpleados.getItems().clear();
        tbvEmpleados.setItems(tipoPlanillaDto.getEmpleados());
        tbvEmpleados.refresh();
    }





    @FXML
    private void onKeyPressedTxtId(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER && !txtId.getText().isBlank())
        {
            cargarTipoPlanilla(Long.valueOf(txtId.getText()));
        }
    }


    @FXML
    private void onActionBtnNuevo(ActionEvent event) {
        if (new Mensaje().showConfirmation("Limpiar Tipo Planilla", getStage(), "¿Está seguro que desea limpiar el registro?")) {
            nuevoTipoPlanilla();
        }
    }



    @FXML
    private void onActionBtnBuscar(ActionEvent event) {
        String nombre = txtNombreEmpleado.getText();
        EmpleadoService empleadoService = new EmpleadoService();
        Respuesta respuesta = empleadoService.buscarEmpleadosPorNombre(nombre);

        if (respuesta.getEstado()) {
            List<EmpleadoDto> empleados = (List<EmpleadoDto>) respuesta.getResultado("Empleados");
            tbvEmpleados.getItems().setAll(empleados);
        } else {
            new Mensaje().showModal(Alert.AlertType.ERROR, "Buscar Empleados", getStage(), respuesta.getMensaje());
        }
    }

    private void refreshEmpleado() {
        EmpleadoService empleadoService = new EmpleadoService();

        empleadoService.getTodosLosEmpleados();
        List<EmpleadoDto> empleados = (List<EmpleadoDto>) empleadoService.getTodosLosEmpleados().getResultado("Empleados");
        tbvEmpleados.getItems().setAll(empleados);
    }


    @FXML
    private void onActionBtnGuardar(ActionEvent event) {
        try {
            String invalidos = validarRequeridos();
            if (!invalidos.isBlank()) {
                new Mensaje().showModal(Alert.AlertType.ERROR, "Guardar Tipo Planilla", getStage(), invalidos);
            } else {
                TipoPlanillaService tipoPlanillaService = new TipoPlanillaService();
                Respuesta respuesta = tipoPlanillaService.guardarTipoPlanilla(tipoPlanillaDto);
                if (respuesta.getEstado()) {
                    unbindTipoPlanilla();
                    this.tipoPlanillaDto = (TipoPlanillaDto) respuesta.getResultado("TipoPlanilla");
                    bindTipoPlanilla(false);
                    cargarEmpleados();
                    new Mensaje().showModal(Alert.AlertType.INFORMATION, "Guardar Tipo Planilla", getStage(), "Tipo planilla guardada exitosamente.");
                } else {
                    new Mensaje().showModal(Alert.AlertType.ERROR, "Guardar Tipo Planilla", getStage(), respuesta.getMensaje());
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(TipoPlanillaController.class.getName()).log(Level.SEVERE, "Error guardando el tipo de planilla.", ex);
            new Mensaje().showModal(Alert.AlertType.ERROR, "Guardar Tipo Planilla", getStage(), "Ocurrio un error guardando el tipo de planilla.");
        }
    }


    @FXML
    private void onActionBtnEliminar(ActionEvent event) {
        try {
            if (tipoPlanillaDto.getId() == null) {
                new Mensaje().showModal(Alert.AlertType.ERROR, "Eliminar tipo planilla", getStage(), "Debe cargar el tipo de planilla que desea eliminar.");
            } else {

                TipoPlanillaService service = new TipoPlanillaService();
                Respuesta respuesta = service.eliminarTipoPlanilla(tipoPlanillaDto.getId());
                if (!respuesta.getEstado()) {
                    new Mensaje().showModal(Alert.AlertType.ERROR, "Eliminar tipo planilla", getStage(), respuesta.getMensaje());
                } else {
                    new Mensaje().showModal(Alert.AlertType.INFORMATION, "Eliminar tipo planilla", getStage(), "Tipo planilla eliminado correctamente.");
                    nuevoTipoPlanilla();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            new Mensaje().showModal(Alert.AlertType.ERROR, "Eliminar tipo planilla", getStage(), "Se ha producido un error, contacte al administrador del sistema.");
        }
    }

    private void cargarTodosLosEmpleados() {
        EmpleadoService empleadoService = new EmpleadoService();
        Respuesta respuesta = empleadoService.getTodosLosEmpleados();
        if (respuesta.getEstado()) {
            List<EmpleadoDto> empleados = (List<EmpleadoDto>) respuesta.getResultado("Empleados");
            tbvEmpleados.getItems().setAll(empleados);
        } else {
            new Mensaje().showModal(Alert.AlertType.ERROR, "Cargar Empleados", getStage(), respuesta.getMensaje());
        }
    }

    @FXML
    void onActionTab(ActionEvent event) {
        if (tbpInclusionEmpleados.isSelected()) {
            cargarTodosLosEmpleados();
        }
    }


}