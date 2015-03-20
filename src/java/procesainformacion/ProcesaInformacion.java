package procesainformacion;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name="ProcesaInformacion",
            urlPatterns="/procesainformacion",
            initParams={@WebInitParam(name="parametro1", value="Valor1"),
                        @WebInitParam(name="parametro2", value="Valor2")})

public class ProcesaInformacion extends HttpServlet{
    
    private Map<String, String> datos;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException{
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try{
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ProcesaInformacion</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Procesamiento de Inicializacion</h1>");
            out.println("<ul>");
            Set<String> keys = datos.keySet();
            for(String h: keys){
                out.println("<li>"+h+": "+datos.get(h)+"</li>");  
            }
            out.println("</ul>");
            out.println("</body>");
            out.println("</html>");
        } finally{
            out.close();
        }
    }
    
    @Override
    public void init(ServletConfig config){
        Enumeration<String> parametros = config.getInitParameterNames();
        String nombre, dato;
        datos = new ConcurrentHashMap<String, String>();
        while(parametros.hasMoreElements()){
           nombre = parametros.nextElement();
           dato = config.getInitParameter(nombre);
           datos.put(nombre, dato);
        }
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException{
            processRequest(request, response);
    }
    
}