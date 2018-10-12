package br.com.aibetesda.util;

import java.io.ByteArrayOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class RelatorioUtils {
	
	private static Connection con;
	private static String usuario = "postgres"; // Usuario do banco
	private static String senha = "adminadmin"; // Senha
	
	public static final String FICHAALUNO = "/relatorios/matriculaAluno.jasper";
	public static final String ALUNOSMATRICULADOS = "/relatorios/alunosMatriculados.jasper"; 

	
	@SuppressWarnings("unchecked")
	public static void gerarRelatorio(String nomeArq, String tipo, Map<?,?> parametros, List<?> objetos) throws Exception {
		try {
			FacesContext facesContext = FacesContext.getCurrentInstance();
			ServletContext servletContext = (ServletContext) facesContext.getExternalContext().getContext();
	
			String pathRel = servletContext.getRealPath(tipo);
			
			HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
			ServletOutputStream outputStream = response.getOutputStream();
			
			JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(objetos);
			
			String pathSub = servletContext.getRealPath("/relatorios/") + "/";
			pathSub = pathSub.replace("\\", "/");			
			
			JasperPrint print = null;		
			print = JasperFillManager.fillReport(pathRel, parametros, ds);
			
			ByteArrayOutputStream output = new ByteArrayOutputStream();
			JasperExportManager.exportReportToPdfStream(print, output);
		
			response.setContentType("application/pdf");  
			response.setHeader("Content-Disposition", "attachment; filename=" + nomeArq + ".pdf");  	
			
			output.writeTo(outputStream);
			outputStream.flush();  
			outputStream.close();
			facesContext.responseComplete();
			output.close();
			output = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void gerarRelatorio(String nomeArq, String tipo, Map<?,?> parametros) throws Exception {
		try {
			FacesContext facesContext = FacesContext.getCurrentInstance();
			ServletContext servletContext = (ServletContext) facesContext.getExternalContext().getContext();
	
			String pathRel = servletContext.getRealPath(tipo);
			
			HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
			ServletOutputStream outputStream = response.getOutputStream();
			String pathSub = servletContext.getRealPath("/relatorios/") + "/";
			pathSub = pathSub.replace("\\", "/");			
			
			JasperPrint print = null;
			print = JasperFillManager.fillReport(pathRel, parametros, conexao());			
			
			ByteArrayOutputStream output = new ByteArrayOutputStream();
			JasperExportManager.exportReportToPdfStream(print, output);
		
			response.setContentType("application/pdf");  
			response.setHeader("Content-Disposition", "attachment; filename=" + nomeArq + ".pdf");  	
			output.writeTo(outputStream);
			outputStream.flush();  
			outputStream.close();
			facesContext.responseComplete();
			output.close();
			output = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// Cria a conexão
	private static Connection conexao() {
		try {
			if (con == null || con.isClosed()) {
				Class.forName("org.postgresql.Driver");
				con = DriverManager.getConnection(
						"jdbc:postgresql://localhost:5432/aibetesda", usuario, senha);
			}
		} catch (Exception e) {
			System.out.println("não foi possível conectar ao banco ->");
			e.printStackTrace();
		}
		return con;
	}	
	
}
