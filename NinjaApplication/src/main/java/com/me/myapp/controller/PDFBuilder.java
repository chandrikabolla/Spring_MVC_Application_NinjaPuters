package com.me.myapp.controller;

import java.awt.Color;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.me.myapp.pojo.Employee;





public class PDFBuilder extends AbstractPdfView{

	
	@SuppressWarnings("unchecked")
	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document doc,
			PdfWriter writer, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// get data model which is passed by the Spring container
		List<Employee> employees = (List<Employee>) model.get("employees");
		
		doc.add(new Paragraph("Employees in the organization"));
		
		PdfPTable table = new PdfPTable(4);
		table.setWidthPercentage(100.0f);
		table.setWidths(new float[] {3.0f, 2.0f, 2.0f, 2.0f});
		table.setSpacingBefore(10);
		
		// define font for table header row
		Font font = FontFactory.getFont(FontFactory.HELVETICA);
		font.setColor(Color.WHITE);
		
		
		// define table header cell
		PdfPCell cell = new PdfPCell();
		cell.setBackgroundColor(Color.BLUE);
		cell.setPadding(5);
		
		// write table header 
		cell.setPhrase(new Phrase("Employee ID", font));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("Role", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Level", font));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("Organization", font));
		table.addCell(cell);
		
		
		
		// write table row data
		for (Employee emp: employees) {
			table.addCell(emp.getEmpid());
			if(emp.getEmpRole()!=null){
				table.addCell(emp.getEmpRole().getRoleName());
			}
			else{
				table.addCell("Not assigned");
			}
			if(emp.getLevel()!=null){
			table.addCell(emp.getLevel().name());
			}
			else{
				table.addCell("Not assigned");
			}
			table.addCell(emp.getOrg().getOrgName());
			
		}
		
		doc.add(table);
		
	}
	protected void buildPdfDocument1(Map<String, Object> arg0, Document arg1, PdfWriter arg2, HttpServletRequest arg3,
			HttpServletResponse arg4) throws Exception {
		// TODO Auto-generated method stub
		
	}
}
