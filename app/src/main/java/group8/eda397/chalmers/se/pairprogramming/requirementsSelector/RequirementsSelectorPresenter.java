package group8.eda397.chalmers.se.pairprogramming.requirementsSelector;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by mysko1 on 2016-04-21.
 */
public class RequirementsSelectorPresenter {


    private static ArrayList<String> myData = new ArrayList<String>();
    private static File f = new File("/home/mysko1/workspace_android/pdfreader/android-PdfRendererBasic/Application/src/main/assets");
    private static String[] files = f.list();
    private static String fileName;
    static {


        for (int i = 0; i < files.length; i++) {
            if (fileName == null ||
                    files[i].matches(fileName))
                myData.add(files[i]);
        }
    }

}
