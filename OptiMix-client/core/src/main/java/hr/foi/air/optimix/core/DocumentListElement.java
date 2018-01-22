package hr.foi.air.optimix.core;

import android.app.Activity;
import android.app.Fragment;

import java.io.FileNotFoundException;

/**
 * Created by erdel on 17.1.2018..
 */

public interface DocumentListElement {
    public String getElementName();
    public int GetPositionInList();
    public void SetPositionInList(int position);
    public String CreateFile(String fileName, String fileContent, Activity activity)throws Exception;
}
