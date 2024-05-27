package cr.ac.una.unaplanilla.service;

import cr.ac.una.unaplanilla.model.Empleado;
import cr.ac.una.unaplanilla.model.EmpleadoDto;
import cr.ac.una.unaplanilla.util.EntityManagerHelper;
import cr.ac.una.unaplanilla.util.Respuesta;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import jakarta.persistence.NonUniqueResultException;
import jakarta.persistence.Query;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class EmpleadoService {

    EntityManager em = EntityManagerHelper.getInstance().getManager();
    private EntityTransaction et;

    public Respuesta getUsuario(String usuario, String clave) {
        try {
            Query qryUsuario = em.createNamedQuery("Empleado.findByUsuarioClave", Empleado.class);
            qryUsuario.setParameter("usuario", usuario);
            qryUsuario.setParameter("clave", clave);
            EmpleadoDto empleadoDto = new EmpleadoDto((Empleado) qryUsuario.getSingleResult());
            return new Respuesta(true, "", "", "Usuario", empleadoDto);
        } catch (NoResultException ex) {
            return new Respuesta(false, "No existe un usuario con las credenciales ingresadas.", "getUsuario NoResultException");
        } catch (NonUniqueResultException ex) {
            Logger.getLogger(EmpleadoService.class.getName()).log(Level.SEVERE, "Ocurrio un error al consultar el usuario.", ex);
            return new Respuesta(false, "Ocurrio un error al consultar el usuario.", "getUsuario NonUniqueResultException");
        } catch (Exception ex) {
            Logger.getLogger(EmpleadoService.class.getName()).log(Level.SEVERE, "Error obteniendo el usuario [" + usuario + "]", ex);
            return new Respuesta(false, "Error obteniendo el usuario.", "getUsuario " + ex.getMessage());
        }
    }

    public Respuesta getEmpleado(Long id) {
        try {
            Query qryEmpleado = em.createNamedQuery("Empleado.findByEmpId", Empleado.class);
            qryEmpleado.setParameter("id", id);
            EmpleadoDto empleadoDto = new EmpleadoDto((Empleado) qryEmpleado.getSingleResult());
            return new Respuesta(true, "", "", "Empleado", empleadoDto);
        } catch (NoResultException ex) {
            return new Respuesta(false, "No existe un empleado con el código ingresado.", "getEmpleado NoResultException");
        } catch (NonUniqueResultException ex) {
            Logger.getLogger(EmpleadoService.class.getName()).log(Level.SEVERE, "Ocurrio un error al consultar el usuario.", ex);
            return new Respuesta(false, "Ocurrio un error al consultar el empleado.", "getEmpleado NonUniqueResultException");
        } catch (Exception ex) {
            Logger.getLogger(EmpleadoService.class.getName()).log(Level.SEVERE, "Error obteniendo el empleado [" + id + "]", ex);
            return new Respuesta(false, "Error obteniendo el empleado.", "getEmpleado " + ex.getMessage());
        }
    }

    public Respuesta guardarEmpleado(EmpleadoDto empleadoDto) {
        try {
            et = em.getTransaction();
            et.begin();
            Empleado empleado;
            if (empleadoDto.getId() != null && empleadoDto.getId() > 0) {
                empleado = em.find(Empleado.class, empleadoDto.getId());
                if (empleado == null) {
                    et.rollback();
                    return new Respuesta(false, "No se encontró el empleado a modificar.", "guardarEmpleado NoResultException");
                }
                empleado.actualizar(empleadoDto);
                empleado = em.merge(empleado);
            }
            else {
                empleado = new Empleado(empleadoDto);
                em.persist(empleado);
            }
            et.commit();
            return new Respuesta(true, "", "", "Empleado", new EmpleadoDto(empleado));
        } catch (Exception ex) {
            et.rollback();
            Logger.getLogger(EmpleadoService.class.getName()).log(Level.SEVERE, "Error al guardar ");
            return new Respuesta(false, "Error al guardar el empleado.", "guardarEmpleado " + ex.getMessage());
        }
    }

    public Respuesta eliminarEmpleado(Long id) {
        try {
            et = em.getTransaction();
            et.begin();
            Empleado empleado;
            if (id != null && id > 0) {
                empleado = em.find(Empleado.class, id);
                if (empleado == null) {
                    et.rollback();
                    return new Respuesta(false, "No se encontró el empleado a eliminar.", "eliminarEmpleado NoResultException");
                }
                em.remove(empleado);
            } else {
                et.rollback();
                return new Respuesta(false, "Debe indicar el código del empleado a eliminar.", "eliminarEmpleado NoResultException");
            }
            et.commit();
            return new Respuesta(true, "", "");
        } catch (Exception ex) {
            et.rollback();
            Logger.getLogger(EmpleadoService.class.getName()).log(Level.SEVERE, "Error al eliminar ");
            return new Respuesta(false, "Error al eliminar el empleado.", "eliminarEmpleado " + ex.getMessage());
        }

    }


    public Respuesta buscarEmpleadosPorNombre(String nombre) {
        try {
            Query query = em.createNamedQuery("Empleado.findByNombre", Empleado.class);
            query.setParameter("empNombre", "%" + nombre + "%"); // Uso de comodines para la búsqueda parcial
            List<Empleado> empleados = query.getResultList();
            List<EmpleadoDto> empleadosDto = empleados.stream().map(EmpleadoDto::new).collect(Collectors.toList());
            return new Respuesta(true, "", "", "Empleados", empleadosDto);
        } catch (Exception ex) {
            Logger.getLogger(EmpleadoService.class.getName()).log(Level.SEVERE, "Error buscando empleados por nombre.", ex);
            return new Respuesta(false, "Error buscando empleados por nombre.", "buscarEmpleadosPorNombre " + ex.getMessage());
        }
    }


    public Respuesta getTodosLosEmpleados() {
        try {
            Query qryEmpleados = em.createNamedQuery("Empleado.findAll", Empleado.class);
            List<Empleado> empleados = qryEmpleados.getResultList();
            List<EmpleadoDto> empleadosDto = empleados.stream().map(EmpleadoDto::new).collect(Collectors.toList());
            return new Respuesta(true, "", "", "Empleados", empleadosDto);
        } catch (Exception ex) {
            Logger.getLogger(EmpleadoService.class.getName()).log(Level.SEVERE, "Error obteniendo todos los empleados.", ex);
            return new Respuesta(false, "Error obteniendo todos los empleados.", "getTodosLosEmpleados " + ex.getMessage());
        }
    }

    public String getCedulaporUser(String user) {
        try {
            Query qryUsuario = em.createNamedQuery("Empleado.findByUsuario", Empleado.class);
            qryUsuario.setParameter("usuario", user);
            EmpleadoDto empleadoDto = new EmpleadoDto((Empleado) qryUsuario.getSingleResult());
            return empleadoDto.getCedula();
        } catch (NoResultException ex) {
            return "No existe un usuario con las credenciales ingresadas.";
        } catch (NonUniqueResultException ex) {
            Logger.getLogger(EmpleadoService.class.getName()).log(Level.SEVERE, "Ocurrio un error al consultar el usuario.", ex);
            return "Ocurrio un error al consultar el usuario.";
        } catch (Exception ex) {
            Logger.getLogger(EmpleadoService.class.getName()).log(Level.SEVERE, "Error obteniendo el usuario [" + user + "]", ex);
            return "Error obteniendo el usuario.";
        }
    }
}