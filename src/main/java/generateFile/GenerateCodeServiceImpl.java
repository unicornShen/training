package generateFile;

import com.pic.aes3g.batchreceiver.service.st.IGenerateCodeService;
import com.pic.aes3g.batchreceiver.service.st.GenerateCodeRefType.FileType;
import com.pic.aes3g.core.helper.FileHelper;
import com.pic.aes3g.dao.ma.IMaStoreEffDao;
import com.pic.framework.util.FileUtil;
import com.pic.framework.util.ThreadSafeUtil;
import com.pic.pos3g.batch.comm.log.ILogger;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * XXX
 */
public class GenerateCodeServiceImpl implements IGenerateCodeService {

    private static final String BASE_STORE_NO = "00976150";

    @Autowired
    IMaStoreEffDao effDao;

    public void process() {
        final ILogger logger = ThreadSafeUtil.getLogger();
        logger.info("Generate code start...");

        final List<String> storeNoList = effDao.getAllEffStore();
        logger.info("storeNoList.size = " + CollectionUtils.size(storeNoList));

        // ACNC
        genACNC(storeNoList);
        logger.info("Generate AC NC end.");

        // ACAC
        genACAC();
        logger.info("Generate AC AC end.");

        // ACXC
        genACXC();
        logger.info("Generate AC XC end.");

        // ACYC
        genACYC();
        logger.info("Generate AC YC end.");

        // ACKC
        genACKC();
        logger.info("Generate AC KC end.");

        // ACLC
        genACLC(storeNoList);
        logger.info("Generate AC LC end.");

        // ACSC
        genACSC(storeNoList);
        logger.info("Generate AC SC end.");

        // ACVC
        genACVC();
        logger.info("Generate AC VC end.");

        logger.info("Generate code end!!!");
    }

    //---------------------------------
    //----
    //---------------------------------

    /**
     * 報紙月結檔 
     * ACNC
     * ACNCNEWS
     */
    private void genACNC(final List<String> storeNoList) {
        final String filePath = "/aes3g/indata/IGX/ACNC.B01";

        // 新增內容.
        addData(filePath + "/00874252", storeNoList);

        // +500
        turnData(filePath);
    }

    /** ACAC. */
    private void genACAC() {
        final String filePath = "/aes3g/indata/IGX/ACAC.B01";
        turnDataByACAC(filePath);
    }

    /** ACXC. */
    private void genACXC() {
        final String filePath = "/aes3g/indata/IGX/ACXC.B01";
        turnDataByACXC(filePath);
    }

    /** ACYC. */
    private void genACYC() {
        final String filePath = "/aes3g/indata/IGX/ACYC.B01";
        turnDataByACYC(filePath);
    }

    /** ACKC. */
    private void genACKC() {
        final String filePath = "/aes3g/indata/IGX/ACKC.B01";
        turnDataByACKC(filePath);
    }

    /** ACLC. */
    private void genACLC(final List<String> storeNoList) {
        final String filePath = "/aes3g/indata/IGX/ACLC.B01";

        // 新增內容.
        addDataByDir2Dir(filePath + "/00975375", storeNoList);

        // +500
        turnDataByACLC(filePath);
    }

    /**
     * ACSC
     */
    private void genACSC(final List<String> storeNoList) {
        final String filePath = "/aes3g/indata/IGX/ACSC.B01";

        // 新增內容.
        addDataByDir2Dir(filePath + "/00972468", storeNoList);

        // +500
        turnDataByACSC(filePath);
    }

    /**
     * ACVC
     */
    private void genACVC() {
        final String filePath = "/aes3g/indata/IGX/ACVC.B01";
        turnDataByACVC(filePath);
    }

    //---------------------------------
    //----
    //---------------------------------

/**
     * OK
     * 換目錄名稱 + 內容
     */
    private static List<String> turnData(final String filePath) {
        final File file = new File(filePath);
        final String[] files = file.list();

        final List<String> storeNoList = new ArrayList<>();
        for (String detail01 : files) {
            storeNoList.add(detail01);
            System.out.println("turnData.detail01 = " + detail01); // 目錄名稱

            if (BASE_STORE_NO.compareTo(detail01) > 0) {
                continue;
            } else if (!StringUtils.substring(detail01, StringUtils.length(detail01) - 1).matches("[0-9]")) {
                continue;
            }

            final File dir01 = new File(file, detail01);

            final File file01 = new File(dir01.getPath());
            final String[] files01 = file01.list();

            for (String detail02 : files01) {
                System.out.println("turnData.detail02 = " + detail02); // file name

                String srcFile = file.getPath() + "/" + detail01 + "/" + detail02;
                String destDir = file.getPath() + "/" + getFakeNo(detail01);
                // System.out.println(srcFile);
                // System.out.println(destDir);

                final File destFile = new File(destDir);
                if (destFile.exists()) {
                    continue;
                }

                try {
                    FileUtils.copyFileToDirectory(new File(srcFile), new File(destDir));
                } catch (IOException e) {
                    e.printStackTrace();
                }

                readWrith(srcFile, destDir + "/" + detail02, detail02);
            }

        }

        return storeNoList;
    }

    /** . */
    public static void readWrith(final String srcFilePath, final String destFilePath, final String fileName) {

        final FileType type = FileType.lookup(fileName);
        final int startNumber = type.getStartNumber();
        final int endNumber = type.getEndNumber();

        try (BufferedReader in = new BufferedReader(new FileReader(srcFilePath)); //
                BufferedWriter out = new BufferedWriter(new FileWriter(destFilePath));) {

            String str;
            while ((str = in.readLine()) != null) {
                final String newPerStr = StringUtils.substring(str, 0, startNumber);
                //                System.out.println("newPerStr = " + newPerStr);

                final String fakeStoreNo = getFakeNo(StringUtils.substring(str, startNumber, endNumber)); // TODO
                //                System.out.println("newPreStr = " + fakeStoreNo);

                final String newLastStr = StringUtils.substring(str, endNumber);
                //                System.out.println("newLastStr = " + newLastStr);

                out.write(newPerStr + fakeStoreNo + newLastStr);
                out.newLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //---------------------------------
    //----
    //---------------------------------

    /**
     * 新增內容
     * 目前傳入路徑要會指定複製哪一家店
     */
    private static void addData(final String filePath, final List<String> storeNoList) {
        final File srcFile = new File(filePath);
        final String[] srcFiles = srcFile.list();

        for (String detail01 : srcFiles) {
            System.out.println("detail01 = " + detail01); // 目錄名稱

            for (String storeNo : storeNoList) {
                String srcFilePath = srcFile.getPath() + "/" + detail01;
                String destDirPath = srcFile.getParent() + "/00" + storeNo;

                // System.out.println("srcFilePath = " + srcFilePath);
                // System.out.println("destDirPath = " + destDirPath);

                final File destFile = new File(destDirPath);
                if (destFile.exists()) {
                    continue;
                }

                try {
                    FileUtils.copyFileToDirectory(new File(srcFilePath), destFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                readWrithByAdd(srcFilePath, destDirPath + "/" + detail01, detail01, storeNo);
            }
        }
    }

    /** . */
    public static void readWrithByAdd(final String srcFilePath, final String destFilePath, final String fileName, final String storeNo) {

        final FileType type = FileType.lookup(fileName);
        final int startNumber = type.getStartNumber();
        final int endNumber = type.getEndNumber();

        try (BufferedReader in = new BufferedReader(new FileReader(srcFilePath)); //
                BufferedWriter out = new BufferedWriter(new FileWriter(destFilePath));) {

            String str;
            while ((str = in.readLine()) != null) {
                final String newPerStr = StringUtils.substring(str, 0, startNumber);
                //                System.out.println("newPerStr = " + newPerStr);

                final String fakeStoreNo = storeNo; // TODO
                //                System.out.println("newPreStr = " + fakeStoreNo);

                final String newLastStr = StringUtils.substring(str, endNumber);
                //                System.out.println("newLastStr = " + newLastStr);

                out.write(newPerStr + fakeStoreNo + newLastStr);
                out.newLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 新增內容
     * 目前傳入路徑要會指定複製哪一家店
     */
    private static void addDataByDir2Dir(final String filePath, final List<String> storeNoList) {
        final File srcFile = new File(filePath);
        final String[] srcFiles = srcFile.list();

        for (String detail01 : srcFiles) {
            System.out.println("detail01 = " + detail01); // 目錄名稱

            for (String storeNo : storeNoList) {
                String srcFilePath = srcFile.getPath() + "/" + detail01;
                String destDirPath = srcFile.getParent() + "/00" + storeNo;

                // System.out.println("srcFilePath = " + srcFilePath);
                // System.out.println("destDirPath = " + destDirPath);

                final File destFile = new File(destDirPath);
                if (destFile.exists()) {
                    // TODO 有多個檔案會被忽略
                    continue;
                }

                try {
                    FileUtils.copyFileToDirectory(new File(srcFilePath), destFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //---------------------------------
    //---- ACAC
    //---------------------------------

    /**
     * 換目錄名稱 + 內容
     */
    private static List<String> turnDataByACAC(final String filePath) {
        final File file = new File(filePath);
        final String[] files = file.list();

        final List<String> storeNoList = new ArrayList<>();
        for (String detail01 : files) {
            storeNoList.add(detail01);
            System.out.println("turnData.detail01 = " + detail01); // 目錄名稱

            if (BASE_STORE_NO.compareTo(detail01) > 0) {
                continue;
            } else if (!StringUtils.substring(detail01, StringUtils.length(detail01) - 1).matches("[0-9]")) {
                continue;
            }

            final File dir01 = new File(file, detail01);

            final File file01 = new File(dir01.getPath());
            final String[] files01 = file01.list();

            for (String detail02 : files01) {
                System.out.println("turnData.detail02 = " + detail02); // file name

                final String srcFile = file.getPath() + "/" + detail01 + "/" + detail02;
                final String destDir = file.getPath() + "/" + getFakeNo(detail01);
                // System.out.println(srcFile);
                // System.out.println(destDir);

                final File sFile = new File(srcFile);
                if (sFile.isDirectory()) {
                    // System.out.println("sFile.isDirectory() = " + detail02);
                    continue;
                }

                try {
                    FileUtils.copyFileToDirectory(new File(srcFile), new File(destDir));
                } catch (IOException e) {
                    e.printStackTrace();
                }

                readWrith(srcFile, destDir + "/" + detail02, detail02);
            }
        }

        return storeNoList;
    }

    //---------------------------------
    //---- ACXC
    //---------------------------------

    /**
     * OK
     * 換目錄名稱 + 內容
     * ZIP
     */
    private static List<String> turnDataByACXC(final String filePath) {
        final File file = new File(filePath);
        final String[] files = file.list();

        final List<String> storeNoList = new ArrayList<>();
        for (String detail01 : files) {
            storeNoList.add(detail01);
            System.out.println("turnData.detail01 = " + detail01); // 目錄名稱

            if (BASE_STORE_NO.compareTo(detail01) > 0) {
                continue;
            } else if (!StringUtils.substring(detail01, StringUtils.length(detail01) - 1).matches("[0-9]")) {
                continue;
            }

            final File dir01 = new File(file, detail01);

            final File file01 = new File(dir01.getPath());
            final String[] files01 = file01.list();

            for (String detail02 : files01) {
                handleZip(file, detail01, detail02, "AIDLVREG");
            }
        }

        return storeNoList;
    }

    /** Handle ZIP file. */
    private static void handleZip(final File file, String detail01, String detail02, final String conditionFileName) {
        System.out.println("turnData.detail02 = " + detail02); // file name

        final String srcFile = file.getPath() + "/" + detail01 + "/" + detail02;
        final String destDir = file.getPath() + "/" + getFakeNo(detail01);
        System.out.println(srcFile);
        System.out.println(destDir);

        // Copy.
        try {
            FileUtils.copyFileToDirectory(new File(srcFile), new File(destDir));
        } catch (IOException e) {
            e.printStackTrace();
        }

        final String destFileTitle = destDir + "/" + detail02;
        // 解壓縮.
        try {
            FileHelper.unzipFile(destFileTitle, destDir);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 刪除來源檔案.
        FileUtils.deleteQuietly(new File(destFileTitle));

        // change store_no.
        final String tempName = conditionFileName + "_ok";
        readWrithByReplace(destDir + "/" + conditionFileName, destDir + "/" + tempName, StringUtils.substring(detail01, 2));

        // 刪除來源檔案.
        FileUtils.deleteQuietly(new File(destDir + "/" + conditionFileName));

        final File nowName = new File(destDir + "/" + tempName);
        nowName.renameTo(new File(destDir + "/" + conditionFileName));

        // Do zip.
        FileHelper.zipFile(destDir + "/" + conditionFileName, destFileTitle, true);
    }

    /** . */
    public static void readWrithByReplace(final String srcFilePath, final String destFilePath, final String storeNo) {

        try (BufferedReader in = new BufferedReader(new FileReader(srcFilePath)); //
                BufferedWriter out = new BufferedWriter(new FileWriter(destFilePath));) {

            String str;
            while ((str = in.readLine()) != null) {
                // final String newStr = StringUtils.replace(str, storeNo, getFakeNo(storeNo), 1);
                final String newStr = StringUtils.replace(str, storeNo, getFakeNo(storeNo));

                out.write(newStr);
                out.newLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //---------------------------------
    //---- ACYC
    //---------------------------------

    /**
     * 換目錄名稱 + 內容
     */
    private static List<String> turnDataByACYC(final String filePath) {
        final File file = new File(filePath);
        final String[] files = file.list();

        final List<String> storeNoList = new ArrayList<>();
        for (String detail01 : files) {
            storeNoList.add(detail01);
            System.out.println("turnData.detail01 = " + detail01); // 目錄名稱

            if (BASE_STORE_NO.compareTo(detail01) > 0) {
                continue;
            } else if (!StringUtils.substring(detail01, StringUtils.length(detail01) - 1).matches("[0-9]")) {
                continue;
            }

            final File dir01 = new File(file, detail01);

            final File file01 = new File(dir01.getPath());
            final String[] files01 = file01.list();

            for (String detail02 : files01) {
                System.out.println("turnData.detail02 = " + detail02); // file name

                final String srcFile = file.getPath() + "/" + detail01 + "/" + detail02;
                final String destDir = file.getPath() + "/" + getFakeNo(detail01);
                //                System.out.println(srcFile);
                //                System.out.println(destDir);

                final File destDirFile = new File(destDir);
                try {
                    FileUtils.copyDirectoryToDirectory(new File(srcFile), destDirFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                final File file02 = new File(destDirFile, detail02);
                final String[] files02 = file02.list();

                for (String detail03 : files02) {
                    //                    System.out.println("turnData.detail03 = " + detail03); // file name
                    //                    System.out.println("file02.getPath() = " + file02.getPath());

                    readWrithByReplace(srcFile + "/" + detail03, file02.getPath() + "/" + detail03, detail01);
                }

            }
        }

        return storeNoList;
    }

    //---------------------------------
    //---- ACKC
    //---------------------------------

    /**
     * OK
     * 換目錄名稱 + 內容
     * ZIP
     */
    private static List<String> turnDataByACKC(final String filePath) {
        final File file = new File(filePath);
        final String[] files = file.list();

        final List<String> storeNoList = new ArrayList<>();
        for (String detail01 : files) {
            storeNoList.add(detail01);
            System.out.println("turnData.detail01 = " + detail01); // 目錄名稱

            if (BASE_STORE_NO.compareTo(detail01) > 0) {
                continue;
            } else if (!StringUtils.substring(detail01, StringUtils.length(detail01) - 1).matches("[0-9]")) {
                continue;
            }

            final File dir01 = new File(file, detail01);

            final File file01 = new File(dir01.getPath());
            final String[] files01 = file01.list();

            for (String detail02 : files01) {
                handleZip(file, detail01, detail02, "AMSTKCHG");
            }
        }

        return storeNoList;
    }

    //---------------------------------
    //---- ACLC
    //---------------------------------

    /**
     * OK
     * 換目錄名稱 + 內容
     * ZIP
     */
    private static List<String> turnDataByACLC(final String filePath) {
        final File file = new File(filePath);
        final String[] files = file.list();

        final List<String> storeNoList = new ArrayList<>();
        for (String detail01 : files) {
            storeNoList.add(detail01);
            System.out.println("turnData.detail01 = " + detail01); // 目錄名稱

            if (BASE_STORE_NO.compareTo(detail01) > 0) {
                continue;
            } else if (!StringUtils.substring(detail01, StringUtils.length(detail01) - 1).matches("[0-9]")) {
                continue;
            }

            final File dir01 = new File(file, detail01);

            final File file01 = new File(dir01.getPath());
            final String[] files01 = file01.list();

            for (String detail02 : files01) {
                handleZip(file, detail01, detail02, "AMALKREG");
            }
        }

        return storeNoList;
    }

    //---------------------------------
    //---- ACSC
    //---------------------------------

    /**
     * OK
     * 換目錄名稱 + 內容
     * ZIP
     */
    private static List<String> turnDataByACSC(final String filePath) {
        final File file = new File(filePath);
        final String[] files = file.list();

        final List<String> storeNoList = new ArrayList<>();
        for (String detail01 : files) {
            storeNoList.add(detail01);
            System.out.println("turnData.detail01 = " + detail01); // 目錄名稱

            if (BASE_STORE_NO.compareTo(detail01) > 0) {
                continue;
            } else if (!StringUtils.substring(detail01, StringUtils.length(detail01) - 1).matches("[0-9]")) {
                continue;
            }

            final File dir01 = new File(file, detail01);

            final File file01 = new File(dir01.getPath());
            final String[] files01 = file01.list();

            for (String detail02 : files01) {
                final List<String> zipFileNames = new ArrayList<>();
                zipFileNames.add("AMSTCINV");
                zipFileNames.add("AMSTEINV");
                zipFileNames.add("AMSTKINV");

                multZipFile(file, detail01, detail02, zipFileNames);
            }
        }

        return storeNoList;
    }

    //---------------------------------
    //---- ACVC
    //---------------------------------

    /**
     * 換目錄名稱 + 內容
     * ZIP
     */
    private static List<String> turnDataByACVC(final String filePath) {
        final File file = new File(filePath);
        final String[] files = file.list();

        final List<String> storeNoList = new ArrayList<>();
        for (String detail01 : files) {
            storeNoList.add(detail01);
            System.out.println("turnData.detail01 = " + detail01); // 目錄名稱

            if (BASE_STORE_NO.compareTo(detail01) > 0) {
                continue;
            } else if (!StringUtils.substring(detail01, StringUtils.length(detail01) - 1).matches("[0-9]")) {
                continue;
            }

            final File dir01 = new File(file, detail01);

            final File file01 = new File(dir01.getPath());
            final String[] files01 = file01.list();

            for (String detail02 : files01) {
                final List<String> zipFileNames = new ArrayList<>();
                zipFileNames.add("AACUPSVC");
                zipFileNames.add("AAEXPSVC");
                zipFileNames.add("AAVDCSVC");

                multZipFile(file, detail01, detail02, zipFileNames);
            }
        }

        return storeNoList;
    }

    /**
     * @param file
     * @param detail01
     * @param detail02
     * @param zipFileNames
     */
    private static void multZipFile(final File file, String detail01, String detail02, final List<String> zipFileNames) {
        System.out.println("turnData.detail02 = " + detail02); // file name

        final String srcFile = file.getPath() + "/" + detail01 + "/" + detail02;
        final String destDir = file.getPath() + "/" + getFakeNo(detail01);
        // System.out.println("handleZip.srcFile = " + srcFile);
        // System.out.println("handleZip.destDir = " + destDir);

        // Copy.
        try {
            FileUtils.copyFileToDirectory(new File(srcFile), new File(destDir));
        } catch (IOException e) {
            e.printStackTrace();
        }

        final String destFileTitle = destDir + "/" + detail02;
        // 解壓縮.
        try {
            FileHelper.unzipFile(destFileTitle, destDir);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 刪除來源檔案.
        FileUtils.deleteQuietly(new File(destFileTitle));

        final List<File> zipFiles = new ArrayList<>();
        for (String conditionFileName : zipFileNames) {
            // change store_no.
            final String tempName = conditionFileName + "_ok";
            readWrithByReplace(destDir + "/" + conditionFileName, destDir + "/" + tempName, StringUtils.substring(detail01, 2));

            // 刪除來源檔案.
            FileUtils.deleteQuietly(new File(destDir + "/" + conditionFileName));

            final File nowName = new File(destDir + "/" + tempName);
            nowName.renameTo(new File(destDir + "/" + conditionFileName));

            // 記錄檔案名稱，壓縮用.
            zipFiles.add(new File(file.getPath() + "/" + getFakeNo(detail01) + "/" + conditionFileName));
        }

        // Do zip.
        final String zipFilePath = file.getPath() + "/" + getFakeNo(detail01) + "/" + detail02;
        try {
            FileUtil.zipQueryList(zipFilePath, zipFiles);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 刪除來源檔案.
        for (File f : zipFiles) {
            FileUtils.deleteQuietly(f);
        }
    }

    //---------------------------------
    //---- EOS
    //---------------------------------

    /**
     * EOS
     */
    private static void turnDataByEOS(final String path) {
        final File file = new File(path);
        System.out.println("file.exists() = " + file.exists());

        try (BufferedReader in = new BufferedReader(new FileReader(file)); //
                BufferedWriter out = new BufferedWriter(new FileWriter(file + "_tmp"));) {

            int startNumber = 0;
            int endNumber = 6;
            String str;
            
            final List<String> newLineList = new ArrayList<>();
            final Set<String> storeNoSet = new HashSet<>();
            while ((str = in.readLine()) != null) {
                final String storeNo = StringUtils.substring(str, startNumber, endNumber);

                if (("00" + storeNo).compareTo(BASE_STORE_NO) > 0) {
                    // System.out.println("storeNo = " + storeNo);
                    storeNoSet.add(storeNo);

                    final String newPerStr = StringUtils.substring(str, 0, startNumber);
                    final String fakeStoreNo = getFakeNo(storeNo);
                    final String newLastStr = StringUtils.substring(str, endNumber);

                    // System.out.println(newPerStr + fakeStoreNo + newLastStr);
                    newLineList.add(newPerStr + fakeStoreNo + newLastStr);
                }

                out.write(str);
                out.newLine();
            }
            
            for(String newLine : newLineList){
                out.append(newLine);
                out.newLine();
            }
            
            System.out.println("storeNoSet.size() = " + CollectionUtils.size(storeNoSet));
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //---------------------------------
    //----
    //---------------------------------

    /** 假店號. */
    public static String getFakeNo(final String str) {
        final String strPre = StringUtils.substring(str, 0, StringUtils.length(str) - 1);
        final String strLast = StringUtils.substring(str, StringUtils.length(str) - 1);
        return strPre + getEnCode(strLast);
    }

    /** OK. */
    public static String getEnCode(final String str) {
        final Map<String, String> map = new HashMap<>();
        map.put("1", "A");
        map.put("2", "B");
        map.put("3", "C");
        map.put("4", "D");
        map.put("5", "E");
        map.put("6", "F");
        map.put("7", "G");
        map.put("8", "H");
        map.put("9", "I");
        map.put("0", "Z");

        return map.get(str);
    }
}