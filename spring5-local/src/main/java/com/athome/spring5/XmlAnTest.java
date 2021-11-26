package com.athome.spring5;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;

/**
 * @Description:
 * @Author Zengfc
 * @Date 2021/10/29 16:41
 * @Version 1.0
 */
public class XmlAnTest {
    public static void main(String[] args) throws DocumentException {

        String str = "            &lt;data PALTYPE=\"USER_XDR\" DIM_LAST_ORIGINAL=\"USER_AGENT为\" MODE=\"ccnum\" CCNUM=\"1\" CCRATIO_THRESHOLD_LOW=\"0.00\" USERLIST_ORIGINAL=\"2@SPLIT_AND@735328E57748330BDACAF9378DA197B0@SPLIT_AND@986821DD88FA3053A32F0787E0DB2062\" CCRATIO_P25=\"0.00\" IMPORT_FLAG=\"0\" DIM_LAST_VALUE=\"fafda\" CCRATIO_THRESHOLD_HIGH=\"0.00\" ABFLAG=\"0\" USERLIST=\"1590665****\" CCRATIO_RAISE_THRESHOLD=\"0.00\" CCRATIO_NORMAL=\"0.00\" TASKID=\"202110291448\" CCRATIO=\"0.93\" CCRATIO_P75=\"0.00\" CCRATIO_P10=\"0.00\" DIM_LAST=\"USER_AGENT为\" CLASS2=\"User_Agent\" CCRATIO_P50=\"0.00\" DIM_NAME=\"USER_AGENT\" CLASS1=\"接入层\" CCNUM_RAISE=\"0.00\" CCNUM_RAISE_THRESHOLD=\"0.00\" CCRATIO_P90=\"0.00\" CCRATIO_RAISE=\"0.00\" CELLLIST=\"46000602ED0C\" /&gt;";
        String replace = str.replace("&lt;", "<")
                .replace("&gt;", ">")
                .replace("&quot;", "\"");
        System.out.println(replace);
        Document document = DocumentHelper.parseText(replace);
        System.out.println(document);
    }
}
