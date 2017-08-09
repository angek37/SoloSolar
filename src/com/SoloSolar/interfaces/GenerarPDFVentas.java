package com.SoloSolar.interfaces;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;

import com.SoloSolar.Capsulas.Cliente;
import com.SoloSolar.Capsulas.Usuario;
import com.SoloSolar.DB.ClienteBD;
import com.SoloSolar.DB.UsuarioBD;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPCellEvent;
import com.itextpdf.text.pdf.PdfPRow;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPTableEvent;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;

public class GenerarPDFVentas {
	private Font fuenteBold = new Font(Font.FontFamily.COURIER, 7, Font.BOLD);
	private Font fuenteNormal = new Font(Font.FontFamily.COURIER, 7, Font.NORMAL);
	private Font fuenteItalic = new Font(Font.FontFamily.COURIER, 7, Font.BOLDITALIC);
	
	public GenerarPDFVentas(String ruta, int renglones, String dataPDF[][], int cantidades, double total,
			String infAd[], boolean ivaSel) {
		try {
			FileOutputStream archivo = new FileOutputStream(ruta + ".pdf");
			Document doc = new Document();
			PdfWriter writer = PdfWriter.getInstance(doc, archivo);
			PdfPCell cTF = new PdfPCell(getHeader("Realizó"));
			cTF.setBorderWidthBottom(1f);
			cTF.setBorderWidthTop(1f);
			cTF.setBorder(Rectangle.TOP);
			PdfPTable tableFooter = new PdfPTable(5);
			tableFooter.setTotalWidth(510f);
			tableFooter.setLockedWidth(true);
			tableFooter.setHorizontalAlignment(0);
			tableFooter.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			tableFooter.addCell(cTF);
			tableFooter.addCell(new Paragraph(" "));
			cTF = new PdfPCell(getHeader("Revisó"));
			cTF.setBorderWidthBottom(1f);
			cTF.setBorderWidthTop(1f);
			cTF.setBorder(Rectangle.TOP);
			tableFooter.addCell(cTF);
			tableFooter.addCell(new Paragraph(" "));
			cTF = new PdfPCell(getHeader("Autorizó"));
			cTF.setBorderWidthBottom(1f);
			cTF.setBorderWidthTop(1f);
			cTF.setBorder(Rectangle.TOP);
			tableFooter.addCell(cTF);
			FooterTable ft = new FooterTable(tableFooter);
			writer.setPageEvent(ft);
			doc.setPageSize(new Rectangle(612, 791));
			doc.open();
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			Date date = new Date();
			doc.add(getFecha(dateFormat.format(date)));
			doc.add(new Paragraph(" "));
			PdfPTable t = new PdfPTable(2);
			t.setWidths(new float[] {2, 4});
			doc.add(addHeaderInformation(t, infAd));
			doc.add(getFooter("BLVD. JUAN ALONSO DE TORRES OTE. #202 B COL. VIBAR TEL.: (477)"
					+ "114 56 37 CEL.: 044 477 136 5097, LEÓN, GTO."));
			doc.add(new Paragraph("\n"));
			PdfPTable observaciones = new PdfPTable(1);
			observaciones.setTotalWidth(510f);
			observaciones.setLockedWidth(true);
			observaciones.setHorizontalAlignment(0);
			observaciones.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			observaciones.getDefaultCell().setCellEvent(new RoundedBorder());
			PdfPTable camp = new PdfPTable(1);
			camp.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			camp.addCell(getHeader("Observaciones: "));
			camp.addCell(getInfo(infAd[0]));
			observaciones.addCell(camp);
			doc.add(observaciones);
			doc.add(new Paragraph("\n"));
			PdfPTable table = new PdfPTable(1);
			//doc.add(addTableInformation(table));
			PdfPTable tab = new PdfPTable(7);
			tab.setWidths(new float[] {3, 5, 2, 1, 1, 1, 1});
			PdfPCell cellClave = new PdfPCell(getHeader("Clave")),
					 cellNombre = new PdfPCell(getHeader("Nombre")),
					 cellCant = new PdfPCell(getHeader("Cantidad")),
					 cellPack = new PdfPCell(getHeader("Pack")),
					 cellL = new PdfPCell(getHeader("L")),
					 cellPrec = new PdfPCell(getHeader("Precio")),
					 cellSub = new PdfPCell(getHeader("Total"));
			
			cellClave.setBorderWidthBottom(1f);
			cellClave.setBorderWidthTop(1f);
			cellClave.setBorder(Rectangle.BOTTOM);
			cellNombre.setBorderWidthBottom(1f);
			cellNombre.setBorderWidthTop(1f);
			cellNombre.setBorder(Rectangle.BOTTOM);
			cellCant.setBorderWidthBottom(1f);
			cellCant.setBorderWidthTop(1f);
			cellCant.setBorder(Rectangle.BOTTOM);
			cellPack.setBorderWidthBottom(1f);
			cellPack.setBorderWidthTop(1f);
			cellPack.setBorder(Rectangle.BOTTOM);
			cellL.setBorderWidthBottom(1f);
			cellL.setBorderWidthTop(1f);
			cellL.setBorder(Rectangle.BOTTOM);
			cellPrec.setBorderWidthBottom(1f);
			cellPrec.setBorderWidthTop(1f);
			cellPrec.setBorder(Rectangle.BOTTOM);
			cellSub.setBorderWidthBottom(1f);
			cellSub.setBorderWidthTop(1f);
			cellSub.setBorder(Rectangle.BOTTOM);
			tab.addCell(cellClave);
			tab.addCell(cellNombre);
			tab.addCell(cellCant);
			tab.addCell(cellPack);
			tab.addCell(cellL);
			tab.addCell(cellPrec);
			tab.addCell(cellSub);
			tab.setTotalWidth(510f);
			tab.setLockedWidth(true);
			tab.setHorizontalAlignment(0);
			/*tab.getDefaultCell().setBorder(Rectangle.BOTTOM);
			tab.getDefaultCell().setCellEvent(new RoundedBorder());*/
			tab.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			tab.setTableEvent(new BorderEvent());
			for(int i = 0; i < renglones; i++) {
				for(int j = 0; j < 7; j++) {
					tab.addCell(getInfo(dataPDF[i][j]));
				}
			}
			doc.add(tab);
			PdfPTable tabResults = new PdfPTable(7);
			tabResults.setWidths(new float[] {3, 5, 2, 1, 1, 1, 1});
			tabResults.setTotalWidth(510f);
			tabResults.setLockedWidth(true);
			tabResults.setHorizontalAlignment(0);
			tabResults.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			tabResults.addCell(getHeader("Productos encontrados: "));
			tabResults.addCell(getHeader(renglones + ""));
			tabResults.addCell(getHeader(cantidades + ""));
			tabResults.addCell(getHeader(""));
			tabResults.addCell(getHeader(""));
			if(ivaSel) {
				tabResults.addCell(getHeader(""));
				tabResults.addCell(getHeader(total + ""));
			} else {
				PdfPTable pet = new PdfPTable(1);
				pet.getDefaultCell().setBorder(Rectangle.NO_BORDER);
				pet.addCell(getHeader(" "));
				pet.addCell(getHeader("IVA: "));
				pet.addCell(getHeader("Total: "));
				tabResults.addCell(pet);
				PdfPTable piva = new PdfPTable(1);
				piva.getDefaultCell().setBorder(Rectangle.NO_BORDER);
				piva.addCell(getHeader(round(total, 1) + ""));
				piva.addCell(getInfo(round(total * 0.16, 1) + ""));
				piva.addCell(getInfo(round(total + (total * 0.16), 1) + ""));
				tabResults.addCell(piva);
			}
			doc.add(tabResults);
			doc.close();
			JOptionPane.showMessageDialog(null, "Se ha guardado correctamente", "¡Exito!", JOptionPane.INFORMATION_MESSAGE);
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "No se puede crear el archivo. \nYa existe uno con el mismo nombre y esta en uso", 
					"El archivo ya existe", JOptionPane.INFORMATION_MESSAGE);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	
	public double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    long factor = (long) Math.pow(10, places);
	    value = value * factor;
	    long tmp = Math.round(value);
	    return (double) tmp / factor;
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
	
	public PdfPTable addHeaderInformation(PdfPTable t, String infAd[]) {
		Usuario u = UsuarioBD.Datos();
		Cliente c = ClienteBD.getCliente(Integer.parseInt(infAd[4]));
		Image imagen;
		try {
			t.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			t.setHorizontalAlignment(0);
			t.setLockedWidth(true);
			t.setTotalWidth(510f);
			imagen = Image.getInstance("assets/logoPDF.png");
			imagen.scaleAbsolute(150, 30);
			Phrase img = new Phrase();
			img.add(new Chunk(imagen, 0, 0));
			t.addCell(img);
			PdfPTable tc = new PdfPTable(4);
			tc.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			tc.addCell(getHeader("Nombre:"));
			tc.addCell(getInfo(c.getNombre()));
			tc.addCell(getHeader("Apellidos: "));
			tc.addCell(getInfo(c.getApellidos()));
			tc.addCell(getHeader("RFC: "));
			tc.addCell(getInfo(c.getRFC()));
			tc.addCell(getHeader("Email:"));
			tc.addCell(getInfo(c.getEmail()));
			tc.addCell(getHeader("Calle: "));
			tc.addCell(getInfo(c.getCalle()));
			tc.addCell(getHeader("Colonia: "));
			tc.addCell(getInfo(c.getColonia()));
			PdfPCell cB = new PdfPCell();
			cB.setBorder(Rectangle.LEFT | Rectangle.TOP | Rectangle.RIGHT);
			cB.addElement(tc);
			t.addCell(cB);
			PdfPTable t2 = new PdfPTable(1);
			t2.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			t2.addCell(getHeader(u.getNombre()));
			t2.addCell(getHeader("R.F.C. " + u.getRFC()));
			t2.addCell(getHeader("alberto-426@hotmail.com"));
			t.addCell(t2);
			PdfPTable t3 = new PdfPTable(4);
			t3.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			t3.addCell(getHeader("No. direccion:"));
			t3.addCell(getInfo(c.getNoDir()));
			t3.addCell(getHeader("Estado: "));
			t3.addCell(getInfo(c.getEstado()));
			t3.addCell(getHeader("Ciudad: "));
			t3.addCell(getInfo(c.getCiudad()));
			t3.addCell(getHeader("Codigo postal:"));
			t3.addCell(getInfo(c.getCP()));
			t3.addCell(getHeader("Telefono empresa: "));
			t3.addCell(getInfo(c.getTelEmp()));
			t3.addCell(getHeader("Telefono celular: "));
			t3.addCell(getInfo(c.getTelefono()));
			cB = new PdfPCell();
			cB.setBorder(Rectangle.LEFT | Rectangle.BOTTOM | Rectangle.RIGHT);
			cB.addElement(t3);
			t.addCell(cB);
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
            cb.setLineWidth(1f);
            cb.moveTo(x1, y1);
            //cb.lineTo(x1, y2);
            cb.moveTo(x2, y1);
            //cb.lineTo(x2, y2);
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
	
	public class FooterTable extends PdfPageEventHelper {
        protected PdfPTable footer;
        public FooterTable(PdfPTable footer) {
            this.footer = footer;
        }
        public void onEndPage(PdfWriter writer, Document document) {
            footer.writeSelectedRows(0, -1, 36, 64, writer.getDirectContent());
        }
    }
	
	private Paragraph getFecha(String texto) {
		Paragraph p = new Paragraph();
		Chunk c = new Chunk();
		p.setAlignment(Element.ALIGN_RIGHT);
		c.append(texto);
		c.setFont(fuenteBold);
		p.add(c);
		return p;
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
