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
import java.util.Date;

/**
 *
 * @author Kendall Fonseca
 */
@Entity
@Table(name = "PLAM_EMPLEADOS", schema="UNA")
@NamedQueries({
    @NamedQuery(name = "Empleados.findAll", query = "SELECT e FROM Empleados e"),
    @NamedQuery(name = "Empleados.findByEmpId", query = "SELECT e FROM Empleados e WHERE e.empId = :empId"),
//    @NamedQuery(name = "Empleados.findByEmpNombre", query = "SELECT e FROM Empleados e WHERE e.empNombre = :empNombre"),
//    @NamedQuery(name = "Empleados.findByEmpPapellido", query = "SELECT e FROM Empleados e WHERE e.empPapellido = :empPapellido"),
//    @NamedQuery(name = "Empleados.findByEmpSapellido", query = "SELECT e FROM Empleados e WHERE e.empSapellido = :empSapellido"),
//    @NamedQuery(name = "Empleados.findByEmpCedula", query = "SELECT e FROM Empleados e WHERE e.empCedula = :empCedula"),
//    @NamedQuery(name = "Empleados.findByEmpGenero", query = "SELECT e FROM Empleados e WHERE e.empGenero = :empGenero"),
//    @NamedQuery(name = "Empleados.findByEmpCorreo", query = "SELECT e FROM Empleados e WHERE e.empCorreo = :empCorreo"),
//    @NamedQuery(name = "Empleados.findByEmpAdministrador", query = "SELECT e FROM Empleados e WHERE e.empAdministrador = :empAdministrador"),
//    @NamedQuery(name = "Empleados.findByEmpUsuario", query = "SELECT e FROM Empleados e WHERE e.empUsuario = :empUsuario"),
//    @NamedQuery(name = "Empleados.findByEmpClave", query = "SELECT e FROM Empleados e WHERE e.empClave = :empClave"),
//    @NamedQuery(name = "Empleados.findByEmpFingreso", query = "SELECT e FROM Empleados e WHERE e.empFingreso = :empFingreso"),
//    @NamedQuery(name = "Empleados.findByEmpFsalida", query = "SELECT e FROM Empleados e WHERE e.empFsalida = :empFsalida"),
//    @NamedQuery(name = "Empleados.findByEmpEstado", query = "SELECT e FROM Empleados e WHERE e.empEstado = :empEstado"),
//    @NamedQuery(name = "Empleados.findByEmpVersion", query = "SELECT e FROM Empleados e WHERE e.empVersion = :empVersion")
})
public class Empleados implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @SequenceGenerator(name= "PLAM_EMPLEADOS_EMP_ID_GENERATOR",sequenceName="una.PLAM_EMPLEADOS_SEQ01",allocationSize=1)
    @GeneratedValue( strategy = GenerationType.SEQUENCE,generator="PLAM_EMPLEADOS_EMP_ID_GENERATOR")
    @Basic(optional = false)
    @Column(name = "EMP_ID")
    private BigDecimal empId;
    @Basic(optional = false)
    @Column(name = "EMP_NOMBRE")
    private String empNombre;
    @Basic(optional = false)
    @Column(name = "EMP_PAPELLIDO")
    private String empPapellido;
    @Basic(optional = false)
    @Column(name = "EMP_SAPELLIDO")
    private String empSapellido;
    @Basic(optional = false)
    @Column(name = "EMP_CEDULA")
    private String empCedula;
    @Basic(optional = false)
    @Column(name = "EMP_GENERO")
    private String empGenero;
    @Column(name = "EMP_CORREO")
    private String empCorreo;
    @Basic(optional = false)
    @Column(name = "EMP_ADMINISTRADOR")
    private String empAdministrador;
    @Column(name = "EMP_USUARIO")
    private String empUsuario;
    @Column(name = "EMP_CLAVE")
    private String empClave;
    @Basic(optional = false)
    @Column(name = "EMP_FINGRESO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date empFingreso;
    @Column(name = "EMP_FSALIDA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date empFsalida;
    @Basic(optional = false)
    @Column(name = "EMP_ESTADO")
    private String empEstado;
    @Basic(optional = false)
    @Column(name = "EMP_VERSION")
    private BigInteger empVersion;

    public Empleados() {
    }

    public Empleados(BigDecimal empId) {
        this.empId = empId;
    }

    public Empleados(BigDecimal empId, String empNombre, String empPapellido, String empSapellido, String empCedula, String empGenero, String empAdministrador, Date empFingreso, String empEstado, BigInteger empVersion) {
        this.empId = empId;
        this.empNombre = empNombre;
        this.empPapellido = empPapellido;
        this.empSapellido = empSapellido;
        this.empCedula = empCedula;
        this.empGenero = empGenero;
        this.empAdministrador = empAdministrador;
        this.empFingreso = empFingreso;
        this.empEstado = empEstado;
        this.empVersion = empVersion;
    }

    public BigDecimal getEmpId() {
        return empId;
    }

    public void setEmpId(BigDecimal empId) {
        this.empId = empId;
    }

    public String getEmpNombre() {
        return empNombre;
    }

    public void setEmpNombre(String empNombre) {
        this.empNombre = empNombre;
    }

    public String getEmpPapellido() {
        return empPapellido;
    }

    public void setEmpPapellido(String empPapellido) {
        this.empPapellido = empPapellido;
    }

    public String getEmpSapellido() {
        return empSapellido;
    }

    public void setEmpSapellido(String empSapellido) {
        this.empSapellido = empSapellido;
    }

    public String getEmpCedula() {
        return empCedula;
    }

    public void setEmpCedula(String empCedula) {
        this.empCedula = empCedula;
    }

    public String getEmpGenero() {
        return empGenero;
    }

    public void setEmpGenero(String empGenero) {
        this.empGenero = empGenero;
    }

    public String getEmpCorreo() {
        return empCorreo;
    }

    public void setEmpCorreo(String empCorreo) {
        this.empCorreo = empCorreo;
    }

    public String getEmpAdministrador() {
        return empAdministrador;
    }

    public void setEmpAdministrador(String empAdministrador) {
        this.empAdministrador = empAdministrador;
    }

    public String getEmpUsuario() {
        return empUsuario;
    }

    public void setEmpUsuario(String empUsuario) {
        this.empUsuario = empUsuario;
    }

    public String getEmpClave() {
        return empClave;
    }

    public void setEmpClave(String empClave) {
        this.empClave = empClave;
    }

    public Date getEmpFingreso() {
        return empFingreso;
    }

    public void setEmpFingreso(Date empFingreso) {
        this.empFingreso = empFingreso;
    }

    public Date getEmpFsalida() {
        return empFsalida;
    }

    public void setEmpFsalida(Date empFsalida) {
        this.empFsalida = empFsalida;
    }

    public String getEmpEstado() {
        return empEstado;
    }

    public void setEmpEstado(String empEstado) {
        this.empEstado = empEstado;
    }

    public BigInteger getEmpVersion() {
        return empVersion;
    }

    public void setEmpVersion(BigInteger empVersion) {
        this.empVersion = empVersion;
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
        if (!(object instanceof Empleados)) {
            return false;
        }
        Empleados other = (Empleados) object;
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
