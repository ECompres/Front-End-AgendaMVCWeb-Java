/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

import ServiciosContacto.Contactos;

/**
 *
 * @author Emmanuel
 */
public class ContactosService {

    public ContactosService() {
    }

    public java.util.List<ServiciosContacto.Contactos> listarContactos(int idUSUARIO) {
        ServiciosContacto.ContactosService_Service service = new ServiciosContacto.ContactosService_Service();
        ServiciosContacto.ContactosService port = service.getContactosServicePort();
        return port.listarContactos(idUSUARIO);
    }

    public String agregarContacto(int idUSUARIO, java.lang.String nombres, java.lang.String apellidos, java.lang.String email, java.lang.String direccion) {
        ServiciosContacto.ContactosService_Service service = new ServiciosContacto.ContactosService_Service();
        ServiciosContacto.ContactosService port = service.getContactosServicePort();
        return port.agregarContacto(idUSUARIO, nombres, apellidos, email, direccion);
    }

    public String actualizarContacto(int id, int idUSUARIO, java.lang.String nombres, java.lang.String apellidos, java.lang.String email, java.lang.String direccion) {
        ServiciosContacto.ContactosService_Service service = new ServiciosContacto.ContactosService_Service();
        ServiciosContacto.ContactosService port = service.getContactosServicePort();
        return port.actualizarContacto(id, idUSUARIO, nombres, apellidos, email, direccion);
    }

    public Contactos eliminarContacto(int id) {
        ServiciosContacto.ContactosService_Service service = new ServiciosContacto.ContactosService_Service();
        ServiciosContacto.ContactosService port = service.getContactosServicePort();
        return port.eliminarContacto(id);
    }

    public Contactos listarContactoID(int id) {
        ServiciosContacto.ContactosService_Service service = new ServiciosContacto.ContactosService_Service();
        ServiciosContacto.ContactosService port = service.getContactosServicePort();
        return port.listarContactoID(id);
    }

}
