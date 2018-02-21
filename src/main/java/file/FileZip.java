package file;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 *
 */
public class FileZip {
//    /**
//     * 將檔案清單壓縮成zip檔
//     *
//     * @param zipFilePath
//     * @param srcFiles
//     */
//    public static void zipQueryList(String zipFilePath, List<File> srcFiles) throws Exception {
//        byte[] readBuffer = new byte[BUFFER_SIZE];
//        int bytesIn = 0;
//        FileInputStream fis = null ;
//        FileOutputStream fos = null ;
//        ZipOutputStream zos = null ;
//        try {
//            fos = new FileOutputStream(zipFilePath);
//            zos = new ZipOutputStream(fos);
//
//            for(File file : srcFiles) {
//                fis = new FileInputStream(file.getPath());
//                ZipEntry anEntry = new ZipEntry(file.getName());
//                zos.putNextEntry(anEntry);
//                zos.setEncoding("MS950"); // 解決亂碼問題
//                while ((bytesIn = fis.read(readBuffer)) != -1) {
//                    zos.write(readBuffer, 0, bytesIn);
//                }
//                zos.closeEntry();
//                //colin add 2011/08/30
//                if(fis != null) {
//                    fis.close() ;
//                }
//            }
//            zos.flush();
//        } catch(Exception e){
//            e.printStackTrace();
//        }finally {
//            if(fis != null) {
//                try{
//                    fis.close() ;
//                }catch(Exception e){
//
//                }
//            }
//            if(zos != null) {
//                try{
//                    zos.close() ;
//                }catch(Exception e){
//
//                }
//            }
//            if(fos != null) {
//                try{
//                    fos.close() ;
//                }catch(Exception e){
//
//                }
//            }
//        }
//    }
    
    public static void zip(String src, String dec) throws Exception {
        //開啟欲壓縮的檔案 
        File f = new File(src);
        FileInputStream fis = new FileInputStream(f);

        //開起壓縮後輸出的檔案 
        ZipOutputStream zOut = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(dec)));

        /*
         * 在壓縮檔內建立一個項目(表示一個壓縮的檔案或目錄，可以目錄結構的方式表示，    
         *  解壓縮後可以設定的目錄結構放置檔案)
         * */
        zOut.putNextEntry(new ZipEntry("TEST/" + f.getName()));

        //設定壓縮的程度0~9         
        zOut.setLevel(0);

        //以byte的方式讀取檔案並寫入壓縮檔 
        int byteNo;
        byte[] b = new byte[64];
        while ((byteNo = fis.read(b)) > 0) {
            zOut.write(b, 0, byteNo);

            //將檔案寫入壓縮檔 } 
            zOut.close();
            fis.close();
        }
    }
}
