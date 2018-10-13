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
@WebFilter(filterName = "CoordinacionFilter", urlPatterns = {"/pages/Coordinadora/*"}) 
public class coordinacionFiltro implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
       HttpServletRequest sRequest = (HttpServletRequest)request;
       HttpServletResponse sResponse = (HttpServletResponse)response;
       Object token = sRequest.getSession().getAttribute("tokenCoordinacion");
       
       if(token == null){
           sResponse.sendRedirect("/FdO-3.0/pages/Login.xhtml");
       }
//if(sr.getRequestURI());
       chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        
    }

   
}
