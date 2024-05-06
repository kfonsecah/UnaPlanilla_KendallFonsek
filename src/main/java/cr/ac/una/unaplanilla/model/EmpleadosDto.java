/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.unaplanilla.model;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Kendall Fonseca
 */
public class EmpleadosDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private SimpleStringProperty empId;
    private SimpleStringProperty empNombre;
    private SimpleStringProperty empPapellido;
    private SimpleStringProperty empSapellido;
    private SimpleStringProperty empCedula;
    private ObjectProperty<String> empGenero;
    private SimpleStringProperty empCorreo;
    private SimpleBooleanProperty empAdministrador;
    private SimpleStringProperty empUsuario;
    private SimpleStringProperty empClave;
    private ObjectProperty<LocalDate> empFingreso;
    private ObjectProperty<LocalDate> empFsalida;
    private SimpleBooleanProperty empEstado;
    private Long empVersion;
    private Boolean modificado;
    //private List<Tipoplanillas> tipoPlanillas;
    
    public EmpleadosDto() {
    }

    public EmpleadosDto(Long empId, SimpleStringProperty empNombre, SimpleStringProperty empPapellido, SimpleStringProperty empSapellido, SimpleStringProperty empCedula, ObjectProperty<String> empGenero, SimpleStringProperty empCorreo, SimpleStringProperty empAdministrador, SimpleStringProperty empUsuario, SimpleStringProperty empClave, ObjectProperty<LocalDate> empFingreso, ObjectProperty<LocalDate> empFsalida, SimpleStringProperty empEstado) {
        this.empId = new SimpleStringProperty("");
        this.empNombre = new SimpleStringProperty("");
        this.empPapellido = new SimpleStringProperty("");
        this.empSapellido = new SimpleStringProperty("");
        this.empCedula = new SimpleStringProperty("");
        this.empGenero = new SimpleObjectProperty("M");
        this.empCorreo = new SimpleStringProperty("");
        this.empAdministrador = new SimpleBooleanProperty(false);
        this.empUsuario = new SimpleStringProperty("");
        this.empClave = new SimpleStringProperty("");
        this.empFingreso = new SimpleObjectProperty(LocalDate.now());
        this.empFsalida = new SimpleObjectProperty();
        this.empEstado = new SimpleBooleanProperty(true);
        this.modificado=false;
    }

    public EmpleadosDto(Empleados empleado) {
        this.empId.set(empleado.getEmpId().toString());
        this.empNombre = empNombre;
        this.empPapellido = empPapellido;
        this.empSapellido = empSapellido;
        this.empCedula = empCedula;
        this.empGenero = empGenero;
        this.empCorreo = empCorreo;
        this.empAdministrador = empAdministrador;
        this.empUsuario = empUsuario;
        this.empClave = empClave;
        this.empFingreso = empFingreso;
        this.empFsalida = empFsalida;
        this.empEstado = empEstado;
        this.empVersion = empVersion;
        this.modificado = modificado;
    }
    
    


    

    public Long getEmpId() {
        if(!this.empId.get().isBlank() && this.empId.get() !=null){
            return Long.valueOf(this.empId.get());
        }
        return null;
    }

    public void setEmpId(BigDecimal empId) {
        this.empId.set(empId.toString());
    }

    public String getEmpNombre() {
        return empNombre.get();
    }

    public void setEmpNombre(String empNombre) {
        this.empNombre.set(empNombre);
    }

    public String getEmpPapellido() {
        return empPapellido.get();
    }

    public void setEmpPapellido(String empPapellido) {
        this.empPapellido.set(empPapellido);
    }

    public String getEmpSapellido() {
        return empSapellido.get();
    }

    public void setEmpSapellido(String empSapellido) {
        this.empSapellido.set(empSapellido);
    }

    public String getEmpCedula() {
        return empCedula.get();
    }

    public void setEmpCedula(String empCedula) {
        this.empCedula.set(empCedula);
    }

    public String getEmpGenero() {
        return empGenero.get();
    }

    public void setEmpGenero(String empGenero) {
        this.empGenero.set(empGenero);
    }

    public String getEmpCorreo() {
        return empCorreo.get();
    }

    public void setEmpCorreo(String empCorreo) {
        this.empCorreo.set(empCorreo);
    }

    public String getEmpAdministrador() {
        return empAdministrador.get()?"S":"N";
    }

    public void setEmpAdministrador(String empAdministrador) {
        this.empAdministrador.set(empAdministrador.equalsIgnoreCase(empAdministrador));
    } 

    public String getEmpUsuario() {
        return empUsuario.get();
    }

    public void setEmpUsuario(String empUsuario) {
        this.empUsuario.set(empUsuario);
    }

    public String getEmpClave() {
        return empClave.get();
    }

    public void setEmpClave(String empClave) {
        this.empClave.set(empClave);
    }

    public LocalDate getEmpFingreso() {
        return empFingreso.get(); 
    }

    public void setEmpFingreso(LocalDate empFingreso) {
        this.empFingreso.set(empFingreso);
    }

    public LocalDate getEmpFsalida() {
        return empFsalida.get();
    }

    public void setEmpFsalida(LocalDate empFsalida) {
        this.empFsalida.set(empFsalida);
    }

    public String getEmpEstado() {
        return empEstado.get()?"A":"I";
    }

    public void setEmpEstado(String empEstado) {
        this.empEstado.set(empEstado.equalsIgnoreCase("A"));
    }

    public Long getEmpVersion() {
        return empVersion;
    }

    public void setEmpVersion(Long empVersion) {
        this.empVersion = empVersion;
    }
 
    public Boolean getModificado() {
        return modificado;
    }

    public void setModificado(Boolean modificado) {
        this.modificado = modificado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (empId != null ? empId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EmpleadosDto)) {
            return false;
        }
        EmpleadosDto other = (EmpleadosDto) object;
        if ((this.empId == null && other.empId != null) || (this.empId != null && !this.empId.equals(other.empId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cr.ac.una.unaplanilla.model.Empleados[ empId=" + empId + " ]";
    }
    
}
