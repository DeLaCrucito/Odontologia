/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sys.bean.Bita;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author RamsesMtnz
 */
@ManagedBean
@RequestScoped
public class beanReportes implements Serializable {

    private final String myDatePattern1 = "yyyy-MM-dd";
    private final SimpleDateFormat df = new SimpleDateFormat(myDatePattern1);

    /**
     * @return the clinica
     */
    public String getClinica() {
        return clinica;
    }

    /**
     * @param clinica the clinica to set
     */
    public void setClinica(String clinica) {
        this.clinica = clinica;
    }

    /**
     * @return the fecha1
     */
    public Date getFecha1() {
        return fecha1;
    }

    /**
     * @param fecha1 the fecha1 to set
     */
    public void setFecha1(Date fecha1) {
        this.fecha1 = fecha1;
    }

    /**
     * @return the fecha2
     */
    public Date getFecha2() {
        return fecha2;
    }

    /**
     * @param fecha2 the fecha2 to set
     */
    public void setFecha2(Date fecha2) {
        this.fecha2 = fecha2;
    }

    private String clinica;
    private Date fecha1;
    private Date fecha2;

    public void reporteGeneral1() throws SQLException, IOException, JRException {
        exportarPDF("/resources/ReportesPDF/generalNew.jasper", "General-de-todas-las-clinicas-desde-la-implementación", null);
    }

    public void reportePorClinica2() throws SQLException, IOException, JRException {
        System.out.print("+++++");
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("clinica", clinica);
        System.out.print("+++++" + clinica);
        exportarPDF("/resources/ReportesPDF/soloClinica.jasper", "Clinica-" + clinica + "-desde-la-implementación", parametros);
    }

    public void reporteClinicasDentroDeFechaDet3() throws IOException, SQLException, JRException {
        System.out.print("+++++");
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("fecha1", df.format(fecha1));
        parametros.put("fecha2", df.format(fecha2));
        System.out.print("+++++ " + df.format(fecha1) + " +++++ " + df.format(fecha2));
        exportarPDF("/resources/ReportesPDF/fechas.jasper", "Todas-las-clinicas-desde-el-" + df.format(fecha1) + "-hasta-el-" + df.format(fecha2), parametros);
    }

    public void reportePorClinicaPorFechas4() throws SQLException, IOException, JRException {
        System.out.print("+++++");
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("clinica", clinica);
        parametros.put("fecha1", df.format(fecha1));
        parametros.put("fecha2", df.format(fecha2));
        System.out.print("+++++ " + fecha1 + " +++++ " + fecha2 + " ++++++ " + clinica);
        exportarPDF("/resources/ReportesPDF/porClinica.jasper", "Clinica " + clinica + " desde el " + df.format(fecha1) + " hasta el " + df.format(fecha2), parametros);
    }

    public void alumnosDestacados5() throws SQLException, IOException, JRException {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("fecha1", df.format(fecha1));
        parametros.put("fecha2", df.format(fecha2));
        System.out.print("+++++ " + fecha1 + " +++++ " + fecha2 + " ++++++ ");
        exportarPDF("/resources/ReportesPDF/alumno.jasper", "Alumnos-destacados-de-todas-las-clinicas", parametros);
    }

    public void exportarPDF(String rutaArchivo, String nombreArchivo, Map parametros) throws SQLException, IOException, JRException {
        //Map <String,Object> parametros = new HashMap<String, Object>();
        //parametros.put("", "");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/facultad_odontologia", "root", "root");
        File jasperReport = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath(rutaArchivo));
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport.getPath(), parametros, connection);
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-disposition", "attachment; filename=" + nombreArchivo + ".pdf");
        ServletOutputStream stream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
        stream.flush();
        stream.close();
        FacesContext.getCurrentInstance().responseComplete();
    }

      public void consentimientoPDF(String paciente,String maestro) throws SQLException, IOException, JRException  {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        String nombreAlum = (String) session.getAttribute("nombreCompletoAlumno");
        Map<String, Object> parametros = new HashMap<String, Object>();
        
        String profe = maestro;
        parametros.put("paciente", paciente);
        parametros.put("maestro", profe);
        parametros.put("alumno", nombreAlum);
        exportarPDF("/resources/ReportesPDF/Consentimiento.jasper", "Consentimiento_del_paciente", parametros);
    }

}
