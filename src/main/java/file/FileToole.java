package file;


import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import org.apache.commons.lang3.StringUtils;

/**
 *
 */
//public class FileToole {
//    /**
//     * 單檔壓縮.
//     * <p>
//     * 如果來源與目標檔案路徑相同: 會先使用 tempZipFile 作為檔名壓縮，壓縮完後檔案名稱會重新命名回原檔名，且會刪除來源檔案.
//     * 
//     * @param srcFileName 來源檔案含路徑(只限檔案)
//     * @param zipFileName 目標檔案含路徑
//     */
//    public static void zipFile(final String srcFileName, final String zipFileName) {
//        zipFile(srcFileName, zipFileName, false);
//    }
//    
//    /**
//     * 單檔壓縮.
//     * <p>
//     * 如果來源與目標檔案路徑相同: 會先使用 tempZipFile 作為檔名壓縮，壓縮完後檔案名稱會重新命名回原檔名，且會刪除來源檔案(會忽略 isDeleteSrcFile 參數).
//     * 
//     * @param srcFileName 來源檔案含路徑(只限檔案)
//     * @param zipFileName 目標檔案含路徑
//     * @param isDeleteSrcFile 是否刪除來源檔案
//     */
//    public static void zipFile(final String srcFileName, final String zipFileName, final boolean isDeleteSrcFile) {
//        File srcFile = new File(srcFileName);
//        File zipFile = new File(zipFileName);
//
//        if (!srcFile.exists()) {
//            throw new Exception("要被壓縮的檔案不存在");
//        }
//
//        // 如果來源與目標檔案路徑相同: 會先使用 tempZipFile 作為檔名壓縮.
//        if (StringUtils.equals(srcFileName, zipFileName)) {
//            zipFile = new File(StringUtils.replace(zipFile.getAbsolutePath(), zipFile.getName(), "tempZipFile"));
//        }
//
//        try {
//            // 檔案壓縮.
//            FileUtil.zipQueryList(zipFile.getAbsolutePath(), Arrays.asList(srcFile)); // 只限檔案.
//
//            // 壓縮完後...
//            if (StringUtils.equals(zipFile.getName(), "tempZipFile")) {
//                // 原檔刪除.
//                deleteSubFile(srcFile.getAbsolutePath());
//
//                // 檔案名稱重新命名回原檔名.
//                zipFile.renameTo(new File(srcFileName));
//
//            } else {
//                if (isDeleteSrcFile) {
//                    // 原檔刪除.
//                    deleteSubFile(srcFile.getAbsolutePath());
//                }
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw e;
//        }
//    }
//    
//    /**
//     * 刪除檔案或檔案目錄(含底下所有檔案).
//     * 
//     * @param filePath 檔案或檔案目錄完整路徑. 
//     */
//    public static void deleteSubFile(final String filePath) {
//        String pathName = filePath;
//        if (!StringUtils.endsWith(filePath, "/")) {
//            pathName = filePath + "/";
//        }
//
//        final File file = new File(pathName);
//        final String[] files = file.list();
//
//        // 最後一層目錄
//        if (files == null) {
////            log4j.debug("刪除目的檔案(" + pathName + ")");
//            file.delete();
//
//        } else { // 上層目錄
////            log4j.info("刪除目的檔案目錄(" + pathName + ")");
//
//            for (String detail : files) {
//                final File subFile = new File(file, detail);
//                if (subFile.isDirectory()) {
//                    deleteSubFile(pathName + subFile.getName());
//                }
//                subFile.delete();
//            }
//
//            file.delete();
//        }
//    }
//    
//    /**
//     * 移動檔案.
//     * 
//     * @param srcFilePath 來源檔案路徑
//     * @param destFilePath 目的檔案路徑
//     */
//    public static void moveFile(final String srcFilePath, final String destFilePath) {
//        String srcPathName = srcFilePath; // 來源檔案路徑.
//        if (!StringUtils.endsWith(srcFilePath, "/")) {
//            srcPathName = srcFilePath + "/";
//        }
//
//        String destPathName = destFilePath; // 目的檔案路徑.
//        if (!StringUtils.endsWith(srcFilePath, "/")) {
//            destPathName = destFilePath + "/";
//        }
//
////        log4j.info("移動檔案:");
////        log4j.info("來源檔案路徑:" + srcPathName);
////        log4j.info("目的檔案路徑:" + destPathName);
//
//        final File file = new File(srcPathName);
//        final String[] files = file.list();
//
//        if (files == null) {
////            log4j.info(srcPathName + " 沒有可移動的檔案");
//
//        } else {
//            for (String detail : files) {
//                final File subFile = new File(file, detail);
//
//                try {
//                    org.apache.commons.io.FileUtils.moveToDirectory(new File(srcPathName + subFile.getName()), new File(destPathName), true);
//
//                } catch (IOException e) {
////                    log4j.error("移動檔案(目錄)失敗:" + subFile.getPath(), e);
//                }
//            }
//        }
//    }
//    
//}
