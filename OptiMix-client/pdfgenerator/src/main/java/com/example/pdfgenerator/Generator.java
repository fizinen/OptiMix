package com.example.pdfgenerator;

import android.app.Activity;
import android.os.Environment;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

import hr.foi.air.optimix.core.DocumentListElement;

/**
 * Created by erdel on 15.1.2018..
 */

public class Generator implements DocumentListElement {
    private File pdfFile;
    private int position;
    private String name = "Kreiraj pdf";

    private String createPdf(String headerName, String fileContent) throws FileNotFoundException, DocumentException {

        File docsFolder = new File(Environment.getExternalStorageDirectory() + "/Documents");
        if (!docsFolder.exists()) {
            docsFolder.mkdir();
        }

        String outputFileName = "Optimix-Recept-" + headerName + ".pdf";
        pdfFile = new File(docsFolder.getAbsolutePath(), outputFileName);
        OutputStream output = new FileOutputStream(pdfFile);
        Document document = new Document();
        PdfWriter.getInstance(document, output);
        document.open();
        document.addHeader(headerName, headerName);
        document.add(new Paragraph(headerName+"\n\n\n"));
        //This might not be necessary
        document.add(new LineSeparator());
        document.add(new Paragraph(fileContent));
        document.close();

        return outputFileName;
    }


    @Override
    public String getElementName() {
        return name;
    }

    @Override
    public int GetPositionInList() {
        return position;
    }

    @Override
    public void SetPositionInList(int position) {
        this.position = position;
    }

    @Override
    public String CreateFile(String fileName, String fileContent, Activity activity) throws FileNotFoundException, DocumentException  {
        String createdFileName;
        try {
            createdFileName = createPdf(fileName,fileContent);
        }
        catch(FileNotFoundException |DocumentException e1)
        {
            return "failed";
        }
        return createdFileName;
    }
}
