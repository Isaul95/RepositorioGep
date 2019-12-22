
package ticket;


import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import static java.awt.print.Printable.NO_SUCH_PAGE;
import static java.awt.print.Printable.PAGE_EXISTS;
import java.awt.print.PrinterException;
import java.util.ArrayList;
import java.util.List;
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.HashPrintServiceAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.PrintServiceAttributeSet;
import javax.print.attribute.standard.PrinterName;

public class ServicioImp implements Printable {
	
        // Este metodo retorna un alista de las impresoras reconicidas
	public List<String> getImpresoras()
        {
		DocFlavor flavor = DocFlavor.BYTE_ARRAY.AUTOSENSE;
		PrintRequestAttributeSet bras = new HashPrintRequestAttributeSet();
                
		PrintService impresoras[] = PrintServiceLookup.lookupPrintServices(flavor, bras);
		
		List<String> imp_l = new ArrayList<String>();
		for(PrintService printer: impresoras){
			imp_l.add(printer.getName());
		}
		
		return imp_l;
	}

	@Override
	public int print(Graphics g, PageFormat pf, int page)throws PrinterException
        {
		if (page > 0) { 
			return NO_SUCH_PAGE;
		}
 
		Graphics2D g2d = (Graphics2D) g;
		g2d.translate(pf.getImageableX(), pf.getImageableY());
 		g.setFont(new Font("Roman", 0, 8));
		g.drawString("Hello world !", 0, 10);
		return PAGE_EXISTS;
	}
        
        // Este metodo impime la cadena en la impresoraX
	public void printCadena(String nom_impresora, String text) 
        {
	// Encontrar el servico de impresion con el nombre pasado
		DocFlavor flavor = DocFlavor.BYTE_ARRAY.AUTOSENSE;
		PrintRequestAttributeSet bras = new HashPrintRequestAttributeSet();
                // lista de servicios 
		PrintService serviciosImpresion[] = PrintServiceLookup.lookupPrintServices(flavor, bras);
		PrintService service = encontrarServicio(nom_impresora, serviciosImpresion);
                // Se crea objeto con el servicio selelccionado
		DocPrintJob job = service.createPrintJob();
		try 
                {       // se obtinen bytes i se imprimen
			byte[] bytes;
			bytes = text.getBytes("CP437");
			Doc doc = new SimpleDoc(bytes, flavor, null);	
			job.print(doc, null);
		} catch (Exception e) {
			//mostrar excep 
                        e.printStackTrace();
		}

	}
        public void cashdrawerOpen(String nombreimpresora) {

byte[] open = {27, 112, 48, 55, 121};
// byte[] cutter = {29, 86,49};
String printer = nombreimpresora;
PrintServiceAttributeSet printserviceattributeset = new HashPrintServiceAttributeSet();
printserviceattributeset.add(new PrinterName(printer,null));
PrintService[] printservice = PrintServiceLookup.lookupPrintServices(null, printserviceattributeset);
if(printservice.length!=1){
System.out.println("Printer not found");
}
PrintService pservice = printservice[0];
DocPrintJob job = pservice.createPrintJob();
DocFlavor flavor = DocFlavor.BYTE_ARRAY.AUTOSENSE;
Doc doc = new SimpleDoc(open,flavor,null);
PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
try {
job.print(doc, aset);
} catch (PrintException ex) {
System.out.println(ex.getMessage());
}
}

	public void printBytes(String nom_impresora, byte[] bytes) 
        {
		DocFlavor flavor = DocFlavor.BYTE_ARRAY.AUTOSENSE;
		PrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();
                // busacan los servicios y se encuentra el respectivo 
		PrintService servicios[] = PrintServiceLookup.lookupPrintServices(flavor, pras);
                
		PrintService service = encontrarServicio(nom_impresora,servicios);
		DocPrintJob job = service.createPrintJob();
		try {   // se imprime
			Doc doc = new SimpleDoc(bytes, flavor, null);
			job.print(doc, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	// Este metodo retorna el servicio con el nom pasado por param
	private PrintService encontrarServicio(String nombre_imp,PrintService[] servicios) 
        {
		for (PrintService serv : servicios) {
			if (serv.getName().equalsIgnoreCase(nombre_imp)) {
				return serv;
			}
		}

		return null;
	}
        
}