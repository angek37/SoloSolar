package com.SoloSolar.interfaces;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;

import javax.swing.JOptionPane;

import com.SoloSolar.DB.Consulta;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPCellEvent;
import com.itextpdf.text.pdf.PdfPRow;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPTableEvent;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;

public class GenerarPDF {
	private Font fuenteBold = new Font(Font.FontFamily.COURIER, 10, Font.BOLD);
	private Font fuenteNormal = new Font(Font.FontFamily.COURIER, 8, Font.NORMAL);
	private Font fuenteItalic = new Font(Font.FontFamily.COURIER, 8, Font.BOLDITALIC);
	
	public GenerarPDF(String ruta, String dataPDF[][], String buscar) {
		try {
			FileOutputStream archivo = new FileOutputStream(ruta + ".pdf");
			Document doc = new Document();
			PdfWriter writer = PdfWriter.getInstance(doc, archivo);
			MyFooter m = new MyFooter();
			writer.setPageEvent(m);
			doc.setPageSize(new Rectangle(612, 791));
			doc.open();
			PdfPTable t = new PdfPTable(2);
			doc.add(addHeaderInformation(t));
			doc.add(getFooter("BLVD. JUAN ALONSO DE TORRES OTE. #202 B COL. VIBAR TEL.: (477)"
					+ "114 56 37 CEL.: 044 477 136 5097, LEÓN, GTO."));
			doc.add(new Paragraph("\n"));
			int celdas = Consulta.cantidadDatosPDF(buscar);
			PdfPTable table = new PdfPTable(1);
			doc.add(addTableInformation(table));
			doc.add(new Paragraph("\n"));
			PdfPTable tab = new PdfPTable(5);
			PdfPCell cellClave = new PdfPCell(getHeader("Clave")),
					 cellNombre = new PdfPCell(getHeader("Nombre")),
					 cellCat = new PdfPCell(getHeader("Categoria")),
					 cellPre1 = new PdfPCell(getHeader("Precio 1")),
					 cellPre2 = new PdfPCell(getHeader("Precio 2"));
			cellClave.setBorderWidthBottom(1f);
			cellClave.setBorderWidthTop(1f);
			cellClave.setBorder(Rectangle.BOTTOM | Rectangle.TOP);
			cellNombre.setBorderWidthBottom(1f);
			cellNombre.setBorderWidthTop(1f);
			cellNombre.setBorder(Rectangle.BOTTOM | Rectangle.TOP);
			cellCat.setBorderWidthBottom(1f);
			cellCat.setBorderWidthTop(1f);
			cellCat.setBorder(Rectangle.BOTTOM | Rectangle.TOP);
			cellPre1.setBorderWidthBottom(1f);
			cellPre1.setBorderWidthTop(1f);
			cellPre1.setBorder(Rectangle.BOTTOM | Rectangle.TOP);
			cellPre2.setBorderWidthBottom(1f);
			cellPre2.setBorderWidthTop(1f);
			cellPre2.setBorder(Rectangle.BOTTOM | Rectangle.TOP);
			tab.addCell(cellClave);
			tab.addCell(cellNombre);
			tab.addCell(cellCat);
			tab.addCell(cellPre1);
			tab.addCell(cellPre2);
			tab.setTotalWidth(510f);
			tab.setLockedWidth(true);
			tab.setHorizontalAlignment(0);
			/*tab.getDefaultCell().setBorder(Rectangle.BOTTOM);
			tab.getDefaultCell().setCellEvent(new RoundedBorder());*/
	        tab.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			tab.setTableEvent(new BorderEvent());
			for(int i = 0; i < Consulta.cantidadDatosPDF(buscar); i++) {
				for(int j = 0; j < 5; j++) {
					tab.addCell(getInfo(dataPDF[i][j]));
				}
			}
			doc.add(tab);
			doc.close();
			JOptionPane.showMessageDialog(null, "Se ha guardado correctamente", "¡Exito!", JOptionPane.INFORMATION_MESSAGE);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	
	class RoundedBorder implements PdfPCellEvent {
		@Override
		public void cellLayout(PdfPCell cell, Rectangle rect, PdfContentByte[] canvas) {
			PdfContentByte cb = canvas[PdfPTable.BACKGROUNDCANVAS];
			cb.roundRectangle(
				rect.getLeft() + 1.5f, 
				rect.getBottom() + 1.5f, 
				rect.getWidth() - 3,
				rect.getHeight() - 3, 4
		    );
		    cb.stroke();
		}
	}
	
	public PdfPTable addHeaderInformation(PdfPTable t) {
		Image imagen;
		try {
			t.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			t.setHorizontalAlignment(0);
			t.setLockedWidth(true);
			t.setTotalWidth(510f);
			imagen = Image.getInstance("assets/logo.png");
			imagen.scaleAbsolute(150, 30);
			t.addCell(imagen);
			t.addCell(new Paragraph("aqui va algo :V"));
			PdfPTable t2 = new PdfPTable(1);
			t2.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			t2.addCell(getHeader("FELIX ALBERTO RODRIGUEZ ALVAREZ"));
			t2.addCell(getHeader("R.F.C. ROAF6504089G0"));
			t2.addCell(getHeader("alberto-426@hotmail.com"));
			t.addCell(t2);
			t.addCell(new Paragraph("aqui tambien va algo :v"));
			return t;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return t;
	}
	
	public PdfPTable addTableInformation (PdfPTable tab) {
		tab.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		//tab.setTableEvent(new BorderEvent());
		tab.getDefaultCell().setCellEvent(new RoundedBorder());
		tab.setHorizontalAlignment(0);
		tab.setLockedWidth(true);
		tab.setTotalWidth(510f);
		PdfPTable t = new PdfPTable(1);
		t.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		t.addCell(getInfo("   Nombre ____________________________________________________________________________________________"));
		t.addCell(getInfo("   Domicilio _________________________________________________________________________________________"));
		t.addCell(getInfo("   Ciudad ____________________________________________ Telefono ______________________________________"));
		t.addCell("");
		tab.addCell(t);
		
		return tab;
	}
	
	class BorderEvent implements PdfPTableEvent {
		 
        protected boolean bottom = true;
        protected boolean top = true;
 
        public void splitTable(PdfPTable table) {
    	    bottom = false;
        }
 
        public void afterSplitTable(PdfPTable table, PdfPRow startRow, int startIdx) {
        	top = false;
        }
 
        public void tableLayout(PdfPTable table, float[][] width, float[] height,
                int headerRows, int rowStart, PdfContentByte[] canvas) {
            float widths[] = width[0];
            float y1 = height[0];
            float y2 = height[height.length - 1];
            float x1 = widths[0];
            float x2 = widths[widths.length - 1];
            PdfContentByte cb = canvas[PdfPTable.LINECANVAS];
            cb.moveTo(x1, y1);
            cb.lineTo(x1, y2);
            cb.moveTo(x2, y1);
            cb.lineTo(x2, y2);
            if (top) {
                cb.moveTo(x1, y1);
                cb.lineTo(x2, y1);
            }
            if (bottom) {
                cb.moveTo(x1, y2);
                cb.lineTo(x2, y2);
            }
            cb.stroke();
            cb.resetRGBColorStroke();
            bottom = true;
            top = true;
        }
    }
	
	class MyFooter extends PdfPageEventHelper {
        Font ffont = new Font(Font.FontFamily.UNDEFINED, 12, Font.ITALIC);
 
        public void onEndPage(PdfWriter writer, Document document) {
            PdfContentByte cb = writer.getDirectContent();
            Phrase footer = new Phrase("Lista de productos", ffont);
            ColumnText.showTextAligned(cb, Element.ALIGN_CENTER,
                    footer,
                    (document.right() - document.left()) / 2 + document.leftMargin(),
                    document.bottom() - 10, 0);
        }
    }
	
	private Paragraph getHeader(String texto) {
		Paragraph p = new Paragraph();
		Chunk c = new Chunk();
		p.setAlignment(Element.ALIGN_CENTER);
		c.append(texto);
		c.setFont(fuenteBold);
		p.add(c);
		return p;
	}
	
	private Paragraph getInfo(String texto) {
		Paragraph p = new Paragraph();
		Chunk c = new Chunk();
		p.setAlignment(Element.ALIGN_JUSTIFIED_ALL);
		c.append(texto);
		c.setFont(fuenteNormal);
		p.add(c);
		return p;
	}
	
	private Paragraph getFooter(String texto) {
		Paragraph p = new Paragraph();
		Chunk c = new Chunk();
		p.setAlignment(Element.ALIGN_LEFT);
		c.append(texto);
		c.setFont(fuenteItalic);
		p.add(c);
		return p;
	}
}
