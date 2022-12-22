package com.jasper.demo.control;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JsonDataSource;

@RestController
public class JasJsonControl {

	@GetMapping("/akhiledits")
	public void getDocument(@RequestBody String master,HttpServletResponse response) throws IOException, JRException {
	
	
		         JasperReport jasperMasterReport = JasperCompileManager
		            .compileReport("aaa/front.jrxml");
		       
		         Map<String, Object> parameters = new HashMap<String, Object>();
		         
		          ByteArrayInputStream jsonDSBais = new  ByteArrayInputStream(master.getBytes());
		        // System.err.println(jsonDSBais);
		         JsonDataSource jsonDs = new  JsonDataSource(jsonDSBais); 
		        // System.out.println(jsonDs);
		         JasperPrint pdfFile= JasperFillManager.fillReport(jasperMasterReport, parameters, jsonDs);
			
		       
		        JasperExportManager.exportReportToPdfStream(pdfFile, response.getOutputStream());
		 		response.setContentType("application/pdf");
		 		response.addHeader("Content-Disposition", "inline; filename=jasper.pdf;");
		 		// System.err.println("called 3");
		   }	

}

