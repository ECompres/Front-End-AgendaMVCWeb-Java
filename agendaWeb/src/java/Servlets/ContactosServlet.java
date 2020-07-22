/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Modelos.ContactosService;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Emmanuel
 */
public class ContactosServlet extends HttpServlet {

    String addUsuario = "Vistas/registroContacto.jsp";
    String editUsuario = "Vistas/editarContacto.jsp";
    String listarUsuario = "Vistas/portalUsuario.jsp";
    String acceso = "";
    ContactosService contactoService = new ContactosService();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession sesion = request.getSession();

        String accion = request.getParameter("accion");
        if (accion.equals("portalUsuario")) {
            acceso = listarUsuario;
        } else if (accion.equals("add")) {

            acceso = addUsuario;

        } else if (accion.equals("Guardar")) {
            int idUsuario = Integer.parseInt(request.getParameter("id"));
            System.out.println(idUsuario );
            String nombres = request.getParameter("nombres");
            String apellidos = request.getParameter("apellidos");
            String email = request.getParameter("email");
            String direccion = request.getParameter("direccion");

            contactoService.agregarContacto(idUsuario, nombres, apellidos, email, direccion);
            acceso = listarUsuario;

        } else if (accion.equals("Editar")) {

            acceso = editUsuario;
            request.setAttribute("idusu", request.getParameter("id"));

        } else if (accion.equals("Actualizar")) {
            int id = Integer.parseInt(request.getParameter("txtid"));
            int idUsuario = Integer.parseInt(request.getParameter("id"));
            String nombres = request.getParameter("nombres");
            String apellidos = request.getParameter("apellidos");
            String email = request.getParameter("email");
            String direccion = request.getParameter("direccion");

            contactoService.actualizarContacto(id, idUsuario, nombres, apellidos, email, direccion);
            acceso = listarUsuario;
        } else if (accion.equals("Borrar")) {
            int id = Integer.parseInt(request.getParameter("id"));
            contactoService.eliminarContacto(id);
            acceso = listarUsuario;

        } else {
            acceso = listarUsuario;
        }
        RequestDispatcher req = request.getRequestDispatcher(acceso);
        req.forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
