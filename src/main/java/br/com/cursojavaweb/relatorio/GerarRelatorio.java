package br.com.cursojavaweb.relatorio;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import br.com.cursojavaweb.conexao.HibernateUtil;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperRunManager;

public class GerarRelatorio {
	
	public static void gerar(Long valor, String parametro, String nomePdf, String caminho) throws IOException, SQLException {
		Connection conexao = HibernateUtil.getConexao();
		Map<String, Object> parametros = new HashMap<String, Object>();

			parametros.put(parametro, valor);
			parametros.put("SUBREPORT_DIR", "/reports/"); 
		try {
			FacesContext context = FacesContext.getCurrentInstance();
			HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
			ServletOutputStream responseStream = response.getOutputStream();

			response.setContentType("application/pdf");
			response.setHeader("Content-Disposition", nomePdf);
			response.setHeader("Pragma", "");
			response.setHeader("Cache-Control", "");
			response.setHeader("Expires", "");

			InputStream reportStream = context.getExternalContext().getResourceAsStream(caminho);
			JasperRunManager.runReportToPdfStream(reportStream, responseStream, parametros, conexao);
			responseStream.flush();
			responseStream.close();
			context.responseComplete();
			conexao.close();

		} catch (JRException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
