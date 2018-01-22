package hr.foi.air.optimix.optimix;

import android.app.Activity;
import android.widget.Button;

import com.example.fileprinter.FilePrinter;
import com.example.pdfgenerator.Generator;

/**
 * Created by erdel on 22.1.2018..
 */

/**
 * Chooses which button for document is pressed.
 */
public class DocumentButtonManager {
    FilePrinter filePrinter;
    Generator pdfGenerator;

    public DocumentButtonManager() {
        filePrinter=new FilePrinter();
        pdfGenerator = new Generator();
    }

    public void StartModule(Button selectedButton, String name, String content, Activity activity) throws Exception {
        if (selectedButton.getText().equals(filePrinter.getElementName())){
            filePrinter.CreateFile(name,content,activity);
        }else if(selectedButton.getText().equals(pdfGenerator.getElementName())){
            pdfGenerator.CreateFile(name,content,activity);
        }
    }
}
