/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Modelos.UsuariosService;
import ServiciosWeb.Usuarios;
import java.io.IOException;
import java.io.PrintWriter;
import javafx.scene.control.Alert;
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
public class UsuariosServlet extends HttpServlet {

    String addUsuario = "Vistas/registroUsuarios.jsp";
    String editUsuario = "Vistas/editarUsuario.jsp";
    String listarUsuario = "Vistas/portalAdmin.jsp";
    String listarContacto = "Vistas/portalUsuario.jsp";
    String editMe = "Vistas/editarPerfil.jsp";
    String inicio = "index.jsp";
    String acceso = "";
    UsuariosService usuarioService = new UsuariosService();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String accion = request.getParameter("accion");
        if (accion.equals("portalAdmin")) {
            if ((Integer) session.getAttribute("logged") == 1 && (Integer) session.getAttribute("tipoUsuario") == 0) {
                acceso = listarUsuario;
            } else {
                acceso = inicio;
            }

        } else if (accion.equals("logIn")) {
            System.out.println("");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            Usuarios usu = usuarioService.logIn(email, password);

            if (usu != null) {
                if (usu.getID() > 0) {

                    session.setAttribute("logged", 1);
                    session.setAttribute("id", usu.getID());
                    session.setAttribute("nombres", usu.getNOMBRES());
                    session.setAttribute("apellidos", usu.getAPELLIDOS());
                    session.setAttribute("tipoUsuario", usu.getTIPOUSUARIO());
                    if (usu.getTIPOUSUARIO() == 0) {
                        acceso = listarUsuario;
                    } else {
                        acceso = listarContacto;
                    }
                } else {
                    acceso = inicio;
                }
            }
        } else if (accion.equals("add")) {

            acceso = addUsuario;

        } else if (accion.equals("Guardar")) {

            String nombres = request.getParameter("nombres");
            String apellidos = request.getParameter("apellidos");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            int userType = 1;
            usuarioService.agregarUsuario(nombres, apellidos, email, password, userType);
            if ((Integer) session.getAttribute("logged") == 1) {
                acceso = listarUsuario;
            } else {
                acceso = inicio;
            }

        } else if (accion.equals("Editar")) {

            acceso = editUsuario;
            request.setAttribute("idusu", request.getParameter("id"));

        } else if (accion.equals("Actualizar")) {
            int id = Integer.parseInt(request.getParameter("txtid"));
            String nombres = request.getParameter("nombres");
            String apellidos = request.getParameter("apellidos");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            int userType = (Integer) session.getAttribute("tipoUsuario");
            usuarioService.actualizar(id, nombres, apellidos, email, password, userType);
            if ((Integer) session.getAttribute("tipoUsuario") == 0) {
                acceso = listarUsuario;
            } else {
                session.invalidate();
                acceso = inicio;
            }
        } else if (accion.equals("Borrar")) {
            int id = Integer.parseInt(request.getParameter("id"));
            usuarioService.eliminarUsuario(id);
            acceso = listarUsuario;

        } else if (accion.equals("Perfil")) {
            acceso = editMe;
            request.setAttribute("idEdit", request.getParameter("id"));
        } else if (accion.equals("logOut")) {
            session.setAttribute("logged", 0);
            acceso = inicio;
        } else {
            acceso = inicio;
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
