import java.io.File;
import java.io.IOException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.encryption.AccessPermission;
import org.apache.pdfbox.pdmodel.encryption.StandardProtectionPolicy;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;

public class ProtectedPDF {

    public static void main(String args[]){

        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String strDate = dateFormat.format(date);
        System.out.println("Converted String: " + strDate);

        final String ENCRYPTED_PDF = "your path.pdf";
        final String OWNER_PASSWORD = "owner";

        try {
            //load an existing PDF
            PDDocument document = PDDocument.load(new File(ENCRYPTED_PDF));
            AccessPermission ap = new AccessPermission();
            ap.setCanPrint(false);
            ap.setCanExtractContent(false);
            StandardProtectionPolicy standardPP = new StandardProtectionPolicy(OWNER_PASSWORD, strDate, ap);
            standardPP.setEncryptionKeyLength(128);
            document.protect(standardPP);
            document.save(ENCRYPTED_PDF);
            document.close();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
