package cr.ac.una.unaplanilla.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

import jakarta.persistence.*;


@Entity
@Table(name = "PLAM_TIPOPLANILLAS")
@NamedQueries({
    @NamedQuery(name = "TipoPlanilla.findAll", query = "SELECT t FROM TipoPlanilla t"),
    @NamedQuery(name = "TipoPlanilla.findByTplaId", query = "SELECT t FROM TipoPlanilla t WHERE t.tplaId = :tplaId"),
    @NamedQuery(name = "TipoPlanilla.findByTplaCodigo", query = "SELECT t FROM TipoPlanilla t WHERE t.tplaCodigo = :tplaCodigo"),
    @NamedQuery(name = "TipoPlanilla.findByTplaDescripcion", query = "SELECT t FROM TipoPlanilla t WHERE t.tplaDescripcion = :tplaDescripcion"),
        @NamedQuery(name = "TipoPlanilla.findByCedulaIDEmp", query = "SELECT t FROM TipoPlanilla t Join t.empleados e WHERE e.cedula like :cedula  and e.id like :id"),
        @NamedQuery(name = "TipoPlanilla.findByCodigoDescripcionPlanillasPorMes", query = "SELECT t FROM TipoPlanilla t  WHERE UPPER(t.tplaCodigo) like :codigo and UPPER(t.tplaDescripcion) like :descripcion and UPPER(t.tplaPlaxmes) like :planillasMes", hints = @QueryHint(name = "eclipselink.refresh", value = "true")),
    @NamedQuery(name = "TipoPlanilla.findByTplaPlaxmes", query = "SELECT t FROM TipoPlanilla t WHERE t.tplaPlaxmes = :tplaPlaxmes"),
    @NamedQuery(name = "TipoPlanilla.findByTplaAnoultpla", query = "SELECT t FROM TipoPlanilla t WHERE t.tplaAnoultpla = :tplaAnoultpla"),
    @NamedQuery(name = "TipoPlanilla.findByTplaMesultpla", query = "SELECT t FROM TipoPlanilla t WHERE t.tplaMesultpla = :tplaMesultpla"),
    @NamedQuery(name = "TipoPlanilla.findByTplaNumultpla", query = "SELECT t FROM TipoPlanilla t WHERE t.tplaNumultpla = :tplaNumultpla"),
    @NamedQuery(name = "TipoPlanilla.findByTplaEstado", query = "SELECT t FROM TipoPlanilla t WHERE t.tplaEstado = :tplaEstado"),
    @NamedQuery(name = "TipoPlanilla.findByTplaVersion", query = "SELECT t FROM TipoPlanilla t WHERE t.tplaVersion = :tplaVersion")})
public class TipoPlanilla implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name = "PLAM_TIPOPLANILLAS_TPLA_ID_GENERATOR", sequenceName = "UNA.PLAM_TIPOPLANILLAS_SEQ01", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PLAM_TIPOPLANILLAS_TPLA_ID_GENERATOR")
    @Basic(optional = false)
    @Column(name = "TPLA_ID")
    private Long tplaId;
    @Basic(optional = false)
    @Column(name = "TPLA_CODIGO")
    private String tplaCodigo;
    @Basic(optional = false)
    @Column(name = "TPLA_DESCRIPCION")
    private String tplaDescripcion;
    @Basic(optional = false)
    @Column(name = "TPLA_PLAXMES")
    private Integer tplaPlaxmes;
    @Column(name = "TPLA_ANOULTPLA")
    private Integer tplaAnoultpla;
    @Column(name = "TPLA_MESULTPLA")
    private Integer tplaMesultpla;
    @Column(name = "TPLA_NUMULTPLA")
    private Integer tplaNumultpla;
    @Basic(optional = false)
    @Column(name = "TPLA_ESTADO")
    private String tplaEstado;
    @Version
    @Column(name = "TPLA_VERSION")
    private Long tplaVersion;
    @JoinTable(name = "PLAM_EMPLEADOSPLANILLA", joinColumns = {
        @JoinColumn(name = "EXP_IDTPLA", referencedColumnName = "TPLA_ID")}, inverseJoinColumns = {
        @JoinColumn(name = "EXP_IDEMP", referencedColumnName = "EMP_ID")})
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Empleado> empleados;

    public TipoPlanilla() {
    }

    public TipoPlanilla(Long tplaId) {
        this.tplaId = tplaId;
    }
    public TipoPlanilla(Long tplaId, String tplaCodigo, String tplaDescripcion, Integer tplaPlaxmes, String tplaEstado, Long tplaVersion) {
        this.tplaId = tplaId;
        this.tplaCodigo = tplaCodigo;
        this.tplaDescripcion = tplaDescripcion;
        this.tplaPlaxmes = tplaPlaxmes;
        this.tplaEstado = tplaEstado;
        this.tplaVersion = tplaVersion;
    }


    public TipoPlanilla(TipoPlanillaDto tipoPlanillaDto) {
        this.tplaId = tipoPlanillaDto.getId();
        actualizarTipoPlanilla(tipoPlanillaDto);
    }

    public void actualizarTipoPlanilla(TipoPlanillaDto tipoPlanillaDto){
        this.tplaCodigo = tipoPlanillaDto.getCodigo();
        this.tplaDescripcion = tipoPlanillaDto.getDescripcion();
        this.tplaPlaxmes = tipoPlanillaDto.gettplaPlaxmes();
        this.tplaAnoultpla = tipoPlanillaDto.getAnoUltimaPlanilla();
        this.tplaMesultpla = tipoPlanillaDto.getMesUltimaPlanilla();
        this.tplaNumultpla = tipoPlanillaDto.getNumeroUltimaPlanilla();
        this.tplaEstado = tipoPlanillaDto.getEstado();
    }

    public Long getTplaId() {
        return tplaId;
    }

    public void setTplaId(Long tplaId) {
        this.tplaId = tplaId;
    }

    public String getTplaCodigo() {
        return tplaCodigo;
    }

    public void setTplaCodigo(String tplaCodigo) {
        this.tplaCodigo = tplaCodigo;
    }

    public String getTplaDescripcion() {
        return tplaDescripcion;
    }

    public void setTplaDescripcion(String tplaDescripcion) {
        this.tplaDescripcion = tplaDescripcion;
    }

    public Integer getTplaPlaxmes() {
        return tplaPlaxmes;
    }

    public void setTplaPlaxmes(Integer tplaPlaxmes) {
        this.tplaPlaxmes = tplaPlaxmes;
    }

    public Integer getTplaAnoultpla() {
        return tplaAnoultpla;
    }

    public void setTplaAnoultpla(Integer tplaAnoultpla) {
        this.tplaAnoultpla = tplaAnoultpla;
    }

    public Integer getTplaMesultpla() {
        return tplaMesultpla;
    }

    public void setTplaMesultpla(Integer tplaMesultpla) {
        this.tplaMesultpla = tplaMesultpla;
    }

    public Integer getTplaNumultpla() {
        return tplaNumultpla;
    }

    public void setTplaNumultpla(Integer tplaNumultpla) {
        this.tplaNumultpla = tplaNumultpla;
    }

    public String getTplaEstado() {
        return tplaEstado;
    }

    public void setTplaEstado(String tplaEstado) {
        this.tplaEstado = tplaEstado;
    }

    public Long getTplaVersion() {
        return tplaVersion;
    }

    public void setTplaVersion(Long tplaVersion) {
        this.tplaVersion = tplaVersion;
    }

    public List<Empleado> getEmpleadoList() {
        return empleados;
    }

    public void setEmpleadoList(List<Empleado> empleadoList) {
        this.empleados = empleadoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tplaId != null ? tplaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof TipoPlanilla)) {
            return false;
        }
        TipoPlanilla other = (TipoPlanilla) object;
        if ((this.tplaId == null && other.tplaId != null) || (this.tplaId != null && !this.tplaId.equals(other.tplaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cr.ac.una.unaplanilla.model.TipoPlanilla[ tplaId=" + tplaId + " ]";
    }
    
}