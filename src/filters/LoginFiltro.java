/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 * @author RamsesMtnz
 */
@WebFilter(filterName = "LoginFiltro", urlPatterns = {"/pages/Login.xhtml"})
public class LoginFiltro implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest sRequest = (HttpServletRequest) request;
        HttpServletResponse sResponse = (HttpServletResponse) response;
        Object tokenCoordinacion = sRequest.getSession().getAttribute("tokenCoordinacion");
        Object tokenDocente = sRequest.getSession().getAttribute("tokenDocente");
        Object tokenFarmacia = sRequest.getSession().getAttribute("tokenFarmacia");
        

        if (tokenCoordinacion != null) {
            sResponse.sendRedirect("/FdO-3.0/pages/Coordinadora/Principal.xhtml");
        }else if (tokenDocente != null) {
            sResponse.sendRedirect("/FdO-3.0/pages/Maestro/Principal.xhtml");
        }else if (tokenFarmacia != null) {
            sResponse.sendRedirect("/FdO-3.0/pages/EncargadaDeFarmacia/Principal.xhtml");
        }
//if(sr.getRequestURI());
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }

}
