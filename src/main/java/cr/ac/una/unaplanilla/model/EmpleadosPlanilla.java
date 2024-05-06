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
import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

/**
 *
 * @author Kendall Fonseca
 */
@Entity
@Table(name = "PLAM_EMPLEADOSPLANILLA")
@NamedQueries({
    @NamedQuery(name = "EmpleadosPlanilla.findAll", query = "SELECT e FROM EmpleadosPlanilla e"),
    @NamedQuery(name = "EmpleadosPlanilla.findByExId", query = "SELECT e FROM EmpleadosPlanilla e WHERE e.exId = :exId"),
    @NamedQuery(name = "EmpleadosPlanilla.findByExpIdtpla", query = "SELECT e FROM EmpleadosPlanilla e WHERE e.expIdtpla = :expIdtpla"),
    @NamedQuery(name = "EmpleadosPlanilla.findByExpIdemp", query = "SELECT e FROM EmpleadosPlanilla e WHERE e.expIdemp = :expIdemp"),
    @NamedQuery(name = "EmpleadosPlanilla.findByExpFechadeingreso", query = "SELECT e FROM EmpleadosPlanilla e WHERE e.expFechadeingreso = :expFechadeingreso"),
    @NamedQuery(name = "EmpleadosPlanilla.findByExpVersion", query = "SELECT e FROM EmpleadosPlanilla e WHERE e.expVersion = :expVersion")})
public class EmpleadosPlanilla implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "EX_ID")
    private String exId;
    @Basic(optional = false)
    @Column(name = "EXP_IDTPLA")
    private BigInteger expIdtpla;
    @Basic(optional = false)
    @Column(name = "EXP_IDEMP")
    private BigInteger expIdemp;
    @Column(name = "EXP_FECHADEINGRESO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date expFechadeingreso;
    @Column(name = "EXP_VERSION")
    private String expVersion;

    public EmpleadosPlanilla() {
    }

    public EmpleadosPlanilla(String exId) {
        this.exId = exId;
    }

    public EmpleadosPlanilla(String exId, BigInteger expIdtpla, BigInteger expIdemp) {
        this.exId = exId;
        this.expIdtpla = expIdtpla;
        this.expIdemp = expIdemp;
    }

    public String getExId() {
        return exId;
    }

    public void setExId(String exId) {
        this.exId = exId;
    }

    public BigInteger getExpIdtpla() {
        return expIdtpla;
    }

    public void setExpIdtpla(BigInteger expIdtpla) {
        this.expIdtpla = expIdtpla;
    }

    public BigInteger getExpIdemp() {
        return expIdemp;
    }

    public void setExpIdemp(BigInteger expIdemp) {
        this.expIdemp = expIdemp;
    }

    public Date getExpFechadeingreso() {
        return expFechadeingreso;
    }

    public void setExpFechadeingreso(Date expFechadeingreso) {
        this.expFechadeingreso = expFechadeingreso;
    }

    public String getExpVersion() {
        return expVersion;
    }

    public void setExpVersion(String expVersion) {
        this.expVersion = expVersion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (exId != null ? exId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EmpleadosPlanilla)) {
            return false;
        }
        EmpleadosPlanilla other = (EmpleadosPlanilla) object;
        if ((this.exId == null && other.exId != null) || (this.exId != null && !this.exId.equals(other.exId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cr.ac.una.unaplanilla.model.EmpleadosPlanilla[ exId=" + exId + " ]";
    }
    
}
