
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
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * FXML Controller class
 *
 * @author ANTHONY
 */
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
    private TableColumn<EmpleadoDto, String> tbcCodigo;
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
        txtId.setTextFormatter(Formato.getInstance().integerFormat());
        txtCodigo.setTextFormatter(Formato.getInstance().maxLengthFormat(4));
        txtDescripcion.setTextFormatter(Formato.getInstance().letrasFormat(40));
        txtPlanillasMes.setTextFormatter(Formato.getInstance().integerFormat());
        tipoPlanillaDto = new TipoPlanillaDto();
        empleadoDto = new EmpleadoDto();
        nuevoTipoPlanilla();
        indicarRequeridos();

        // bindEmpleado(true);

        tbcCodigo.setCellValueFactory(new PropertyValueFactory<>("id"));
        tbcNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        tbcEliminar.setCellValueFactory(cd -> new SimpleObjectProperty(cd.getValue() != null));
        tbcEliminar.setCellFactory(cd -> new ButtonCell());

        tbvEmpleados.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                unbindEmpleado();
                empleadoDto = newValue;
                bindEmpleado(false);
            } else {
                nuevoEmpleado();

            }
        });

    }

    @Override
    public void initialize() {
    }

    private void selectionChangeTabEmp(Event event) {
        if (tbpInclusionEmpleados.isSelected()) {
            if (tipoPlanillaDto.getId() == null) {
                new Mensaje().showModal(Alert.AlertType.ERROR, "Tipo planilla", getStage(), "Debe cargar el tipo de planilla al que desea agregar empleados.");
                tbpTipoPlanilla.getSelectionModel().select(tbpTipoPlanillas);
            }
        }
    }

    private void nuevoRegistro(ActionEvent event) {
        if (tbpInclusionEmpleados.isSelected()) {
            nuevoEmpleado();
        } else if (tbpTipoPlanillas.isSelected()) {
            if (new Mensaje().showConfirmation("Limpiar tipo planilla", getStage(), "¿Esta seguro que desea limpiar el registro?")) {
                nuevoTipoPlanilla();
            }
        }
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
    }

    private void unbindTipoPlanilla() {
        txtId.textProperty().unbind();
        txtCodigo.textProperty().unbindBidirectional(tipoPlanillaDto.codigo);
        txtDescripcion.textProperty().unbindBidirectional(tipoPlanillaDto.descripcion);
        txtPlanillasMes.textProperty().unbindBidirectional(tipoPlanillaDto.planillasPorMes);
        chkActivo.selectedProperty().unbindBidirectional(tipoPlanillaDto.estado);
    }

    private void bindEmpleado(Boolean nuevo) {
        if (!nuevo) {
            txtIdEmpleado.textProperty().bind(this.empleadoDto.id);
        }

        txtNombreEmpleado.textProperty().bindBidirectional(this.empleadoDto.nombre);
    }

    private void unbindEmpleado() {
        txtIdEmpleado.textProperty().unbind();
        txtNombreEmpleado.textProperty().unbindBidirectional(this.empleadoDto.nombre);
    }


    private void nuevoTipoPlanilla() {
        unbindTipoPlanilla();
        tipoPlanillaDto = new TipoPlanillaDto();
        bindTipoPlanilla(true);
        nuevoEmpleado();
        cargarEmpleados();
        txtId.clear();
        txtId.requestFocus();
    }

    private void nuevoEmpleado() {
        unbindEmpleado();
        empleadoDto = new EmpleadoDto();
        bindEmpleado(true);
        txtIdEmpleado.clear();
        txtIdEmpleado.requestFocus();
    }

    private void cargarTipoPlanilla(Long id) {
        TipoPlanillaService service = new TipoPlanillaService();
        Respuesta respuesta = service.getTipoPlanilla(id);

        if (respuesta.getEstado()) {
            unbindTipoPlanilla();
            tipoPlanillaDto = (TipoPlanillaDto) respuesta.getResultado("TipoPlanilla");
            bindTipoPlanilla(false);
            validarRequeridos();
            cargarEmpleados();
        } else {
            new Mensaje().showModal(Alert.AlertType.ERROR, "Cargar tipo planilla", getStage(), respuesta.getMensaje());
        }
    }

    private void cargarEmpleados() {
        tbvEmpleados.getItems().clear();
        tbvEmpleados.setItems(tipoPlanillaDto.getEmpleados());
        tbvEmpleados.refresh();
    }


    private void cargarEmpleado(Long id) {
        EmpleadoService service = new EmpleadoService();
        Respuesta respuesta = service.getEmpleado(id);

        if (respuesta.getEstado()) {
            unbindEmpleado();
            empleadoDto = (EmpleadoDto) respuesta.getResultado("Empleado");
            bindEmpleado(false);
        } else {
            new Mensaje().showModal(Alert.AlertType.ERROR, "Cargar empleado", getStage(), respuesta.getMensaje());
        }
    }


    @FXML
    private void keyPressIdTipoPlanilla(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER && txtId.getText() != null && !txtId.getText().isEmpty()) {
            cargarTipoPlanilla(Long.valueOf(txtId.getText()));
        }
    }

    @FXML
    private void keyPressIdEmpleado(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER && !txtIdEmpleado.getText().isEmpty()) {
            cargarEmpleado(Long.valueOf(txtIdEmpleado.getText()));
        } else if (event.getCode() == KeyCode.ENTER) {
            new Mensaje().showModal(Alert.AlertType.ERROR, "Agregar empleado", getStage(), "Es necesario agregar un id");
        }
    }

    @FXML
    private void agregarEmpleado(ActionEvent event) {
        if (empleadoDto.getId() == null || empleadoDto.getNombre().isEmpty()) {
            new Mensaje().showModal(Alert.AlertType.ERROR, "Agregar empleado", getStage(), "Es necesario cargar un empleado para agregarlo a la lista.");
        } else if (tbvEmpleados.getItems() == null || !tbvEmpleados.getItems().stream().anyMatch(a -> a.equals(empleadoDto))) {
            empleadoDto.setModificado(true);
            tbvEmpleados.getItems().add(empleadoDto);
            tbvEmpleados.refresh();
        }
        nuevoEmpleado();
    }

    @FXML
    private void onSelectionChangedTbpInclusionEmpleados(Event event) {
        if (tbpInclusionEmpleados.isSelected()) {
            if (tipoPlanillaDto.getId() == null) {
                new Mensaje().showModal(Alert.AlertType.ERROR, "Tipo planilla", getStage(), "Debe cargar el tipo de planilla al que desea agregar empleados.");
                tbpTipoPlanilla.getSelectionModel().select(tbpTipoPlanillas);
            }
        }
    }

    @FXML
    private void onActionBtnNuevo(ActionEvent event) {
        if (tbpInclusionEmpleados.isSelected()) {
            nuevoEmpleado();
        } else if (tbpTipoPlanillas.isSelected()) {
            if (new Mensaje().showConfirmation("Limpiar tipo planilla", getStage(), "¿Esta seguro que desea limpiar el registro?")) {
                nuevoTipoPlanilla();
            }
        }
    }

//    @FXML
//    private void onActionBtnBuscar(ActionEvent event) {
//        BusquedaViewController busquedaController = (BusquedaViewController) FlowController.getInstance().getController("BusquedaView");
//        busquedaController.busquedaPlanillas();
//        FlowController.getInstance().goViewInWindowModal("BusquedaView", getStage(),true);
//        TipoPlanillaDto tipoPlanillaDto = (TipoPlanillaDto) busquedaController.getResultado();
//        if (tipoPlanillaDto != null) {
//            cargarTipoPlanilla(tipoPlanillaDto.getId());
//        }
//    }
@FXML
private void onActionBtnGuardar(ActionEvent event) {
    try {
        String invalidos = validarRequeridos();
        if (!invalidos.isEmpty()) {
            new Mensaje().showModal(Alert.AlertType.ERROR, "Guardar tipo planilla", getStage(), invalidos);
        } else {

            TipoPlanillaService service = new TipoPlanillaService();
            Respuesta respuesta = service.guardarTipoPlanilla(tipoPlanillaDto);
            if (!respuesta.getEstado()) {
                new Mensaje().showModal(Alert.AlertType.ERROR, "Guardar tipo planilla", getStage(), respuesta.getMensaje());
            } else {
                unbindTipoPlanilla();
                tipoPlanillaDto = (TipoPlanillaDto) respuesta.getResultado("TipoPlanilla");
                bindTipoPlanilla(false);
                nuevoEmpleado();
                cargarEmpleados();
                new Mensaje().showModal(Alert.AlertType.INFORMATION, "Guardar tipo planilla", getStage(), "Tipo planilla actualizado correctamente.");
            }
        }
    } catch (Exception ex) {
        Logger.getLogger(TipoPlanillaController.class.getName()).log(Level.SEVERE, "Error guardando el tipo de planilla.", ex);
        new Mensaje().showModal(Alert.AlertType.ERROR, "Guardar tipo planilla", getStage(), "Ocurrio un error guardando el tipo de planilla.");
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

    private class ButtonCell extends TableCell<EmpleadoDto, Boolean> {

            final MFXButton cellButton = new MFXButton("Eliminar");

            ButtonCell() {
                cellButton.setOnAction(t -> {
                    EmpleadoDto empleado = (EmpleadoDto) ButtonCell.this.getTableView().getItems().get(ButtonCell.this.getIndex());
                    if (empleado.getId() != null) {
                        tipoPlanillaDto.getEmpleadosEliminados().add(empleado);
                    }
                    tbvEmpleados.getItems().remove(empleado);
                    tbvEmpleados.refresh();
                });
            }

            @Override
            protected void updateItem(Boolean t, boolean empty) {
                super.updateItem(t, empty);
                if (!empty) {
                    setGraphic(cellButton);
                } else {
                    setGraphic(null);
                }
            }
    }
}