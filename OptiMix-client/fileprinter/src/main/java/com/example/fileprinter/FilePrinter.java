package com.example.fileprinter;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.PersistableBundle;
import android.print.PrintAttributes;
import android.print.PrintDocumentAdapter;
import android.print.PrintJob;
import android.print.PrintManager;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

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
 * Created by erdel on 19.1.2018..
 */

public class FilePrinter implements DocumentListElement {
    private File pdfFile;
    private int position;
    private String name = "Ispiši dokument";
    private PrintManager mgr=null;

    private WebView mWebView;


    private void doWebViewPrint(String headerName, String fileContent, final Activity activity) {
        // Create a WebView object specifically for printing
        WebView webView = new WebView(activity);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                createWebPrintJob(view, activity);
                mWebView = null;
            }
        });

        // Generate an HTML document on the fly:
        String htmlDocument = "<html><body><h1>"+headerName+"</h1>"+
                "<p>"+fileContent+"</p></body></html>";
        webView.loadDataWithBaseURL(null, htmlDocument, "text/HTML", "UTF-8", null);

        // Keep a reference to WebView object until you pass the PrintDocumentAdapter
        mWebView = webView;
    }

    private void createWebPrintJob(WebView webView, Activity activity) {
        // Get a PrintManager instance
        PrintManager printManager = (PrintManager) activity
                .getSystemService(Context.PRINT_SERVICE);

        // Get a print adapter instance
        PrintDocumentAdapter printAdapter = webView.createPrintDocumentAdapter();

        // Create a print job with name and adapter instance
        String jobName = R.string.app_name + "Printing Document";
        PrintJob printJob = printManager.print(jobName, printAdapter,
                new PrintAttributes.Builder().build());
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
    public String CreateFile(String fileName, String fileContent, Activity activity) throws Exception {
        String createdFileName;
        try {
            doWebViewPrint(fileName,fileContent, activity);
           return "succesful";
        }
        catch(Exception e1)
        {
            return "failed";
        }
    }
}
