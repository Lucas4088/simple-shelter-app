package my;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import au.com.bytecode.opencsv.CSVReader;

public class PDFManaging {
	
	public static void CSVToPDF(String CSVFile){
		String[] nextLine;
		CSVReader reader = null;
		int lnNum = 0;
		try {
			reader = new CSVReader(new FileReader(CSVFile));
		} catch (FileNotFoundException e) {
			new ErrorDialog(e.getMessage());
		}
		
		Document myPDFData = new Document();
		
		try {
			PdfWriter.getInstance(myPDFData, new FileOutputStream("Shelter.pdf"));
		} catch (FileNotFoundException | DocumentException e) {
			new ErrorDialog(e.getMessage());
		}
		
		myPDFData.open();
		PdfPTable myTable = new PdfPTable(3);
		PdfPCell tableCell;
		
		try {
			while((nextLine = reader.readNext()) != null){
				lnNum++;
				tableCell = new PdfPCell(new Phrase(nextLine[0]));
				myTable.addCell(tableCell);
				tableCell = new PdfPCell(new Phrase(nextLine[1]));
				myTable.addCell(tableCell);
				tableCell = new PdfPCell(new Phrase(nextLine[2]));
				myTable.addCell(tableCell);
			}
		} catch (IOException e) {
			new ErrorDialog(e.getMessage());
		}
		
		try {
			myPDFData.add(myTable);
		} catch (DocumentException e) {
			new ErrorDialog(e.getMessage());
		}
		myPDFData.close();
	}
}
