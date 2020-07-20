/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

import ServiciosWeb.Usuarios;

/**
 *
 * @author Emmanuel
 */
public class UsuariosService {

    public UsuariosService() {
    }

    public java.util.List<ServiciosWeb.Usuarios> listarUsuarios() {
        ServiciosWeb.UsuariosService_Service service = new ServiciosWeb.UsuariosService_Service();
        ServiciosWeb.UsuariosService port = service.getUsuariosServicePort();
        return port.listarUsuarios();
    }

    public String agregarUsuario(java.lang.String nombres, java.lang.String apellidos, java.lang.String email, java.lang.String password, int tipoUSUARIO) {
        ServiciosWeb.UsuariosService_Service service = new ServiciosWeb.UsuariosService_Service();
        ServiciosWeb.UsuariosService port = service.getUsuariosServicePort();
        return port.agregarUsuario(nombres, apellidos, email, password, tipoUSUARIO);
    }

    public String actualizar(int id, java.lang.String nombres, java.lang.String apellidos, java.lang.String email, java.lang.String password, int tipoUSUARIO) {
        ServiciosWeb.UsuariosService_Service service = new ServiciosWeb.UsuariosService_Service();
        ServiciosWeb.UsuariosService port = service.getUsuariosServicePort();
        return port.actualizar(id, nombres, apellidos, email, password, tipoUSUARIO);
    }

    public Usuarios listarID(int id) {
        ServiciosWeb.UsuariosService_Service service = new ServiciosWeb.UsuariosService_Service();
        ServiciosWeb.UsuariosService port = service.getUsuariosServicePort();
        return port.listarID(id);
    }

    public Usuarios eliminarUsuario(int id) {
        ServiciosWeb.UsuariosService_Service service = new ServiciosWeb.UsuariosService_Service();
        ServiciosWeb.UsuariosService port = service.getUsuariosServicePort();
        return port.eliminarUsuario(id);
    }

}
