package generateFile;

import com.pic.aes3g.core.exception.CvsException;
import com.pic.framework.util.FileUtil;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.PosixFilePermission;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.output.FileWriterWithEncoding;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.log4j.Logger;

public class FileHelper {
    private static final Logger log4j = Logger.getLogger(FileHelper.class); // Log4j紀錄

    /**
     * 根據文件路徑名稱 刪除文件
     * 
     * @param fileName
     */
    public static void deleteFile(String fileName) {
        // 經文件路徑轉為絕對路徑
        fileName = getAbsolutePath(fileName);

        //		int lastIndexSeparator = fileName.lastIndexOf("/");
        //		String tmpFileName = fileName.substring(lastIndexSeparator + 1);
        //		if (tmpFileName.indexOf('.') != -1) {
        // 如果文件有擴展名，則只刪除 符合條件的文件
        File file = new File(fileName);
        if (file.exists()) {
            log4j.info("清除目的檔案(" + fileName + ")");
            file.delete();
        }
        //		} else {
        //			// 刪除具有相同文件名稱，但擴展名不同的文件
        //			String parent = fileName.substring(0, lastIndexSeparator + 1); // 上層路徑名稱
        //			File[] files = new File(parent).listFiles();
        //
        //			if (ArrayUtils.isNotEmpty(files)) {
        //				for (File file : files) {
        //					fileName = parent + file.getName();
        //					if (file.isFile() && file.getName().startsWith(tmpFileName)) {
        //						log4j.info("清除目的檔案(" + parent + file.getName() + ")");
        //						file.delete();
        //					}
        //				}
        //			}
        //		}
    }

    /**
     * 根據文件路徑名稱List 刪除多個文件
     * 
     * @param fileName
     */
    public static void deleteFiles(List<String> fileNamesList) {
        for (String tmpFileName : fileNamesList) {
            deleteFile(tmpFileName);
        }
    }

    /**
     * 將文件內容dataList 輸出到 fileName
     * 
     * @param dataList
     * @param fileName
     */
    public static void writeListToFile(List<String> dataList, String fileName, String newLine) {
        writeListToFile(dataList, fileName, true, false, newLine);
    }

    /**
     * 將文件內容dataList 輸出到 fileName
     * 
     * @param dataList 資料
     * @param fileName 寫出的檔
     * @param deleteFlag 是否刪除該檔(如果該檔已存在)
     * @param appendFlag 續寫or寫新的
     */
    public static void writeListToFile(List<String> dataList, String fileName, boolean deleteFlag, boolean appendFlag, String newLine) {
        // ILogger logger = ThreadSafeUtil.getLogger();
        // logger.functionStart("寫目的檔案(" + fileName + ") 開始");

        //BufferedWriter bw = null;
        BufferedWriter bw = null;
        try {
            File dstFile = new File(fileName);
            if (!dstFile.getParentFile().exists()) {
                //log4j.info("目錄" + dstFile.getParent() + "不存在, 嘗試創建...");
                dstFile.getParentFile().mkdirs();
            }

            if (deleteFlag) {
                // 如果文件已存在，則先將該文件刪除
                deleteFile(fileName);
            }

            bw = new BufferedWriter(new FileWriter(dstFile, appendFlag));
            for (String data : dataList) {
                if (appendFlag) {
                    bw.append(data + newLine);
                } else {
                    bw.write(data + newLine);
                }
            }

            // logger.functionEnd("寫目的檔案失敗(" + fileName + ") 結束");
        } catch (Exception ex) {
            // logger.error("寫目的檔案失敗(" + fileName + ") 失敗");
            // throw ex;
        } finally {
            if (bw != null) {
                try {
                    bw.close();
                } catch (Exception e) {
                    // logger.error("執行失敗, 錯誤原因: " + ExceptionUtil.getCause(e));
                }
            }
        }
    }
    
    
    /**
     * 將文件內容dataList 輸出到 fileName
     * 
     * @param dataList 資料
     * @param fileName 寫出的檔
     * @param deleteFlag 是否刪除該檔(如果該檔已存在)
     * @param appendFlag 續寫or寫新的
     */
    public static void writeListToFile(List<String> dataList, String fileName, boolean deleteFlag, boolean appendFlag, String newLine, String encoding) {
        // ILogger logger = ThreadSafeUtil.getLogger();
        // logger.functionStart("寫目的檔案(" + fileName + ") 開始");

        //BufferedWriter bw = null;
        BufferedWriter bw = null;
        FileWriterWithEncoding fw = null;
        try {
            File dstFile = new File(fileName);
            if (!dstFile.getParentFile().exists()) {
                //log4j.info("目錄" + dstFile.getParent() + "不存在, 嘗試創建...");
                dstFile.getParentFile().mkdirs();
            }

            if (deleteFlag) {
                // 如果文件已存在，則先將該文件刪除
                deleteFile(fileName);
            }

            fw = new FileWriterWithEncoding(dstFile, encoding, appendFlag);
            bw = new BufferedWriter(fw);
            for (String data : dataList) {
                if (appendFlag) {
                    bw.append(data + newLine);
                } else {
                    bw.write(data + newLine);
                }
            }

            // logger.functionEnd("寫目的檔案失敗(" + fileName + ") 結束");
        } catch (Exception ex) {
            // logger.error("寫目的檔案失敗(" + fileName + ") 失敗");
            // throw ex;
        	final CvsException exception = new CvsException("S301999", ex, new String[] {fileName});
			throw exception;
        } finally {
            if (bw != null) {
                try {
                    bw.close();
                } catch (Exception e) {
                    // logger.error("執行失敗, 錯誤原因: " + ExceptionUtil.getCause(e));
                }
            }
            if (fw != null) {
            	try {
            		fw.close();
            	} catch (Exception e) {
            		
            	}
            }
        }
    }

    /**
     * 將多個文件內容合併到文件 saveFileName 中
     *
     * @param sourceFileNameList
     * @param saveFileName
     * @return
     */
    public static File mergeToOneFile(String regexPath, String saveFileName) {
        try {
            List<File> fileList = obtainFileListFromRegexPath(regexPath);
            if (CollectionUtils.isNotEmpty(fileList)) {
                log4j.info("一共找到" + fileList.size() + "個原始檔案");
            }

            File outFile = new File(saveFileName);
            if (!outFile.exists()) {
                outFile.createNewFile();
            }
            FileChannel outFileChannel = new FileOutputStream(outFile).getChannel();
            FileChannel inFileChannel;
            for (File file : fileList) {
                inFileChannel = new FileInputStream(file).getChannel();
                inFileChannel.transferTo(0, inFileChannel.size(), outFileChannel);
                inFileChannel.close();
            }
            outFileChannel.close();

            return outFile;
        } catch (Exception ex) {
            throw new RuntimeException(ex.getCause());
        }
    }

    /**
     * 將多個文件內容合併到文件 saveFileName 中
     *
     * @param sourceFileNameList
     * @param saveFileName
     * @return
     */
    public static File mergeToOneFile(List<File> fileList, String saveFileName) {
        try {
            File outFile = new File(saveFileName);
            if (!outFile.exists()) {
                outFile.createNewFile();
            }
            FileChannel outFileChannel = new FileOutputStream(outFile).getChannel();
            FileChannel inFileChannel;
            for (File file : fileList) {
                inFileChannel = new FileInputStream(file).getChannel();
                inFileChannel.transferTo(0, inFileChannel.size(), outFileChannel);
                inFileChannel.close();
            }
            outFileChannel.close();

            return outFile;
        } catch (Exception ex) {
            throw new RuntimeException(ex.getCause());
        }
    }

    /**
     * 將路徑轉為絕對路徑
     * 
     * @param regexPath
     * @return
     */
    private static String getAbsolutePath(String regexPath) {
        String osName = System.getProperty("os.name"); // 獲取操作系統類型
        // 如果是Windows系統需對路徑做處理，Linux則不需要做
        if (osName.toLowerCase().startsWith("windows")) {
            // 如果是Windows操作系統
            // 如果不是絕對路徑，則需要對路徑處理，改為絕對路徑方式
            if (regexPath.indexOf(':') != 1) {
                File directory = new File("");
                if (regexPath.startsWith("\\") || regexPath.startsWith("/")) {
                    regexPath = directory.getAbsolutePath() + regexPath;
                } else {
                    regexPath = directory.getAbsolutePath() + "/" + regexPath;
                }
            }
        }

        // 將路徑中的【\】替換為【/】
        regexPath = regexPath.replace("\\", "/");

        return regexPath;
    }

    /**
     * 從指定含有正則表達式的路徑下獲取文件列表
     *
     * @param regexPath
     * @return
     */
    public static List<File> obtainFileListFromRegexPath(String regexPath) {
        List<File> resultList = new ArrayList<File>();
        log4j.info("從指定含有正則表達式的路徑(" + regexPath + ")下獲取文件列表 開始");
        String[] regexPaths = null;
        if (StringUtils.isNotBlank(regexPath)) {
            File rootFile = (new File("")).getAbsoluteFile();
            while (null != rootFile.getParentFile()) {
                rootFile = rootFile.getParentFile();
            }
            File[] rootFiles = rootFile.listFiles();
            regexPaths = regexPath.replaceAll("//", "/").split("/");
            if (StringUtils.isBlank(regexPaths[0])) {
                regexPaths = ArrayUtils.remove(regexPaths, 0);
            }
            getRegexFileList(resultList, rootFiles, regexPaths, 0);
        }
        log4j.info("從指定含有正則表達式的路徑(" + regexPath + ")下獲取文件列表 結束");
        return resultList;
    }

    /**
     * 由searchFiles檔案清單[遞迴]往下找符合regexPaths規範(正則表達式)的resultList檔案清單
     * @param resultList
     * @param searchFiles
     * @param regexPaths
     * @param regexIndex
     */
    public static void getRegexFileList(List<File> resultList, File[] searchFiles, String[] regexPaths, int regexIndex) {
        if (null == searchFiles || searchFiles.length == 0)
            return;
        String compareName = regexPaths[regexIndex];
        for (File nowPathFile : searchFiles) {
            //			System.out.println(nowPathFile.getName()+" vs "+compareName);
            if (!nowPathFile.getName().matches(compareName))
                continue;//有對應上才做
            if (regexPaths.length == (regexIndex + 1)) {//如果已經check到最後
                resultList.add(nowPathFile);
            } else {
                getRegexFileList(resultList, nowPathFile.listFiles(), regexPaths, (regexIndex + 1));
            }
        }
    }

    /**
     * 從指定含有正則表達式的路徑下獲取文件列表
     *
     * @param regexPath
     * @return
     */
    /*
    public static List<File> obtainFileListFromRegexPath(String regexPath) {
    	log4j.info("從指定含有正則表達式的路徑(" + regexPath + ")下獲取文件列表 開始");
    	// //將路徑中的【\】替換為【/】
    	// regexPath = regexPath.replace("\\", "/");
    	// 將路徑轉為絕對路徑
    //		regexPath = getAbsolutePath(regexPath);

    	List<File> list = new ArrayList<File>();
    	if (StringUtils.isNotBlank(regexPath)) {
    		String[] regexPaths = regexPath.substring(0,
    				regexPath.lastIndexOf("/")).split("/");
    		String inputFileName = regexPath.substring(regexPath
    				.lastIndexOf("/") + 1);

    		// 從根驅動目錄開始找
    		File[] roots = File.listRoots();
    		String osName = System.getProperty("os.name"); // 獲取操作系統類型
    		// 如果是Windows系統需對路徑做處理，Linux則不需要做
    		if (osName.toLowerCase().startsWith("windows")) {
    			// 如果是Windows操作系統
    			// 如果不是絕對路徑，則需要對路徑處理，改為絕對路徑方式
    			for (File root : roots) {
    				String path = root.getPath();
    				if (path.endsWith("\\")) {
    					path = CommonStringUtil.getSubstring(path, 0,
    							path.length() - 1);
    				}

    				if (path.toUpperCase().matches(regexPaths[0].toUpperCase())) {
    					list.addAll(obtainFileListFromRegexPath(path,
    							regexPaths, 0, inputFileName));
    				}
    			}
    		} else {
    			for (File root : roots) {
    				String path = root.getPath();
    				if (path.endsWith("\\")) {
    					path = CommonStringUtil.getSubstring(path, 0,
    							path.length() - 1);
    				}

    				if (path.replace("/", "").toUpperCase()
    						.matches(regexPaths[0].toUpperCase())) {
    					list.addAll(obtainFileListFromRegexPath(path,
    							regexPaths, 0, inputFileName));
    				}
    			}
    		}
    	}

    	log4j.info("從指定含有正則表達式的路徑(" + regexPath + ")下獲取文件列表 結束");

    	return list;
    }
    */
    /**
     * 從某根目錄下開始遞歸獲取指定正則表達式的文件列表
     * 
     * @param parentPath
     * @param regexPaths
     * @param startRegexPathsIndex
     * @return
     */
    /*
    private static List<File> obtainFileListFromRegexPath(String parentPath,
    		String[] regexPaths, int startRegexPathsIndex, String inputFileName) {
    	List<File> list = new ArrayList<File>();
    	File parent = new File(parentPath);

    	if (startRegexPathsIndex < regexPaths.length) {
    		if (parent.isDirectory()) {
    			if (!parentPath.endsWith("/") && !parentPath.endsWith("\\")) {
    				parent = new File(parentPath + "/");
    			}

    			File[] files = parent.listFiles();
    			String fileName = "";
    			if (ArrayUtils.isNotEmpty(files)) {
    				for (File file : files) {
    					if (startRegexPathsIndex == regexPaths.length - 1) {
    						if (file.isFile() && list.indexOf(file) == -1) {
    							fileName = file.getName();
    							// if (inputFileName.indexOf(".") == -1) {
    							// if (file.getName().lastIndexOf(".") != -1) {
    							// fileName =
    							// file.getName().substring(0,file.getName().lastIndexOf("."));
    							// }
    							// }

    							if (fileName.matches(inputFileName)) {
    								list.add(file);
    							}
    						}
    					} else {
    						String currentRegexPath = regexPaths[startRegexPathsIndex + 1];
    						if (file.getName().matches(currentRegexPath)) {
    							if (file.isDirectory()) {
    								list.addAll(obtainFileListFromRegexPath(
    										file.getPath(), regexPaths,
    										startRegexPathsIndex + 1,
    										inputFileName));
    							}
    						}
    					}

    				}
    			}
    		}
    	}
    	return list;
    }
    */

    public static File newFile(String fullPathAndFileName) throws IOException {

        assert fullPathAndFileName != null;

        File file = new File(fullPathAndFileName);
        file.createNewFile();
        return file;
    }

    public static boolean exists(String fullPathAndFileName) {

        assert fullPathAndFileName != null;

        File file = new File(fullPathAndFileName);
        return file.exists();
    }

    /**
     * 檢查檔案格式
     * 
     * @param file
     * @param format
     */
    public static boolean checkFileFormat(String fileFileName, String format) {

        // 允許種類 (範例):String allowedFileTypes =
        // ".png .gif .jpg .gif .swf .PNG .GIF .JPG .GIF .SWF";

        // 抓取副檔名進行比對
        String extension = FilenameUtils.getExtension(fileFileName);

        // 檢查檔案副檔名 判斷上傳的檔案種類是否相符
        if (format.indexOf(extension.toLowerCase()) != -1) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 檢查檔案大小
     * 
     * @param file
     * @param size
     * @throws IOException
     */
    public static boolean checkFileSize(File file, float size) throws IOException {
        float fileSize = file.length();
        /*
         * float ret = getFileSizeInBytes(file.getName()); ret = ret / (float)
         * (1024 * 1024);
         */
        fileSize = fileSize / (float) (1024 * 1024);
        System.out.println(size);
        System.out.println(fileSize);
        if (fileSize <= size) {
            return true;
        } else {
            return false;
        }
    }

    /*
     * public static long getFileSizeInBytes(String fileName) { long ret = 0;
     * File f = new File(fileName); if (f.isFile()) { return f.length(); } else
     * if (f.isDirectory()) { File[] contents = f.listFiles(); for (int i = 0; i
     * < contents.length; i++) { if (contents[i].isFile()) { ret +=
     * contents[i].length(); } else if (contents[i].isDirectory()) ret +=
     * getFileSizeInBytes(contents[i].getPath()); } } return ret; }
     */

    /**
     * 檔案是否存在
     * 
     * @param file
     */
    public static boolean exist(File file) {
        if (file.exists()) {
            return true;
        } else {
            return false;

        }
    }

    /**
     * 取得內文
     * 
     * @param file
     * @throws FileNotFoundException 
     * @throws IOException
     */
    public static String getContent(File file) throws IOException {
    	String newContent = "";
        try (FileReader fr = new FileReader(file);
        		BufferedReader br = new BufferedReader(fr)) { // new一個FileReader物件

        	// new一個FileRead的BufferedReader物件
        	//BufferedReader br = new BufferedReader(fr);

        	String line;
        	
        	while ((line = br.readLine()) != null) { // 逐行讀入檔案內容

        		newContent = newContent + line + "\n";

        	}
        	// 關閉物件
        	//br.close();
        }

        return newContent;

    }

    /**
     * 上傳檔案
     * 
     * @param file
     * @param path
     * @throws IOException
     */
    public static void upload(File file, String path, String fileFileName) throws IOException {

        //FileInputStream in = null;
        //FileOutputStream out = null;
        // BufferedOutputStream bos =null;
        // InputStream in=null;
        File dir = new File(path);
        // 目錄位置如果不存在 ，則新增目錄
        if (!dir.exists()) {
            dir.mkdirs();
        }
        // 新檔名
        String targetPath = dir.getPath() + File.separator + FormatHelper.getDateStringToyyyyMMddHHmmss(FormatHelper.getNow()) + "." + FilenameUtils.getExtension(fileFileName);
        File destinationFile = new File(targetPath);
        try (FileInputStream in = new FileInputStream(file);
        		FileOutputStream out = new FileOutputStream(destinationFile)) {
            /*
             * bos = new BufferedOutputStream( new
             * FileOutputStream(destinationFile)); in = file.getInputStream();
             * BufferedInputStream bis = new BufferedInputStream(in);
             */

            //in = new FileInputStream(file);
            //out = new FileOutputStream(destinationFile);

            byte[] bytefer = new byte[1024];
            int length = 0;
            while ((length = in.read(bytefer)) != -1) {
                out.write(bytefer, 0, length);
            }
        }// finally {
        //    if (in != null) {
        //        in.close();
        //    }
        //    if (out != null) {
        //        out.flush();
        //        out.close();
        //    }
        //}
    }

    /**
     * 解壓縮tar檔案
     * 
     * @param tarFile    欲解壓縮的tar檔案
     * @param targetDir    解壓後的存放路徑
     */
    public static void unTar(final String tarFile, final String targetDir) {
        try {
            final File tarfile = new File(tarFile);
            FileUtil.unTar(tarfile, targetDir);
        } catch (Exception e) {
            final CvsException cvse = new CvsException("B431002", e);
            throw cvse;
        }
    }

    /**
     * 壓縮多個來源檔案至一個zip檔案
     * 
     * @param zipFileName    壓縮的zip檔案名稱
     * @param zipDir    壓縮後的存放路徑
     * @param srcFiles    欲壓縮的來源檔案
     * @param srcDir    欲壓縮的來源目錄
     */
    public static void zipMultipleFiles(String zipFileName, String zipDir, List<String> srcFiles, String srcDir) {
        File zipDirFile = new File(zipDir);
        File srcDirFile = new File(srcDir);
        //檢查目錄是否存在
        if (!zipDirFile.exists())
            FileUtil.createDirectory(zipDir, null);
        if (!srcDirFile.exists())
            FileUtil.createDirectory(srcDir, null);

        String resultFilePath = zipDirFile.getPath() + File.separator + zipFileName;
        List<File> srcFilePathList = new ArrayList<File>();
        for (String fileName : srcFiles) {
            File srcFile = new File(srcDirFile.getPath() + File.separator + fileName);
            if (!srcFile.exists()) {
                final CvsException cvsex = new CvsException("S301002", new String[] { srcFile.getPath(), "要被壓縮的檔案不存在" });
                log4j.error(cvsex.getMessageCodeAndCodeInfo());
                throw cvsex;
            }
            srcFilePathList.add(srcFile);
        }
        try {
            FileUtil.zipQueryList(resultFilePath, srcFilePathList);
        } catch (Exception e) {
            final CvsException cvsex = new CvsException("S301999", new String[] { resultFilePath, "壓縮檔案失敗" });
            log4j.error(cvsex.getMessageCodeAndCodeInfo());
            throw cvsex;
        }
    }

    /**
     * 壓縮一個來源檔案至一個zip檔案,
     * 如果zipFileName: 檔案存在,就直接將srcFileName加入zip檔,
     * 否則建立一個新的zipFileName檔,再將srcFileName加入zip檔.
     * 
     * @param zipFileName    壓縮的zip目錄及檔案名稱
     * @param srcFileName    欲壓縮的來源目錄及檔名
     */
    public static void zipOneFile(String zipFileName, String srcFileName, boolean addToExtendedZip) {
        File srcFile = new File(srcFileName);
        File zipFile = new File(zipFileName);
        if (!srcFile.exists()) {
            final CvsException cvsex = new CvsException("S301002", new String[] { srcFile.getPath(), "要被壓縮的檔案不存在" });
            log4j.error(cvsex.getMessageCodeAndCodeInfo());
            throw cvsex;
        }
        List<File> srcFilePathList = new ArrayList<File>();
        srcFilePathList.add(srcFile);
        try {
            if (addToExtendedZip && zipFile.exists()) {
                addFilesToZip(zipFile, srcFilePathList.toArray(new File[0]));
            } else {
                FileUtil.zipQueryList(zipFileName, srcFilePathList);
            }
        } catch (Exception e) {
            e.printStackTrace();
            final CvsException cvsex = new CvsException("S301999", new String[] { zipFileName, "壓縮檔案失敗" });
            log4j.error(cvsex.getMessageCodeAndCodeInfo());
            throw cvsex;
        }
    }

    public static void addFilesToZip(File zipFile, File[] files) throws IOException {
        File tempFile = null;
        ZipInputStream zin = null;
        ZipOutputStream out = null;
        byte[] buf = null;

        try {
            // get a temp file
            tempFile = File.createTempFile(zipFile.getName(), null, new File(zipFile.getParent()));
            // delete it, otherwise you cannot rename your existing zip to it.
            tempFile.delete();

            boolean renameOk = zipFile.renameTo(tempFile);
            if (!renameOk) {
                throw new RuntimeException("could not rename the file " + zipFile.getAbsolutePath() + " to " + tempFile.getAbsolutePath());
            }
            buf = new byte[1024];

            zin = new ZipInputStream(new FileInputStream(tempFile));
            out = new ZipOutputStream(new FileOutputStream(zipFile));

            ZipEntry entry = zin.getNextEntry();
            while (entry != null) {
                String name = entry.getName();
                boolean notInFiles = true;
                for (File f : files) {
                    if (f.getName().equals(name)) {
                        notInFiles = false;
                        break;
                    }
                }
                if (notInFiles) {
                    // Add ZIP entry to output stream.
                    out.putNextEntry(new ZipEntry(name));
                    // Transfer bytes from the ZIP file to the output file
                    int len;
                    while ((len = zin.read(buf)) > 0) {
                        out.write(buf, 0, len);
                    }
                }
                entry = zin.getNextEntry();
            }
        } finally {
            // Close the streams
            zin.close();
            // Compress the files
            for (int i = 0; i < files.length; i++) {
                InputStream in = new FileInputStream(files[i]);
                // Add ZIP entry to output stream.
                out.putNextEntry(new ZipEntry(files[i].getName()));
                // Transfer bytes from the file to the ZIP file
                int len;
                while ((len = in.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }
                // Complete the entry
                out.closeEntry();
                in.close();
            }
            out.close();
            tempFile.delete();
        }
    }

    /**
     * 單檔壓縮.
     * <p>
     * 如果來源與目標檔案路徑相同: 會先使用 tempZipFile 作為檔名壓縮，壓縮完後檔案名稱會重新命名回原檔名，且會刪除來源檔案.
     * 
     * @param srcFileName 來源檔案含路徑(只限檔案)
     * @param zipFileName 目標檔案含路徑
     * @return List 回傳 不存在的檔案 清單.
     */
    public static List<String> zipFile(final String srcFileName, final String zipFileName) {
        return zipFile(srcFileName, zipFileName, false);
    }

    /**
     * 單檔壓縮.
     * <p>
     * 如果來源與目標檔案路徑相同: 會先使用 tempZipFile 作為檔名壓縮，壓縮完後檔案名稱會重新命名回原檔名，且會刪除來源檔案(會忽略 isDeleteSrcFile 參數).
     * 
     * @param srcFileName 來源檔案含路徑(只限檔案).
     * @param zipFileName 目標檔案含路徑.
     * @param isDeleteSrcFile 是否刪除來源檔案.
     * @return List 回傳 不存在的檔案 清單.
     */
    public static List<String> zipFile(final String srcFileName, final String zipFileName, final boolean isDeleteSrcFile) {
        File srcFile = new File(srcFileName);
        File zipFile = new File(zipFileName);

        final List<String> notExistsFileList = new ArrayList<String>();
        if (!srcFile.exists()) {
            final CvsException cvsex = new CvsException("S301002", new String[] { srcFile.getPath(), "要被壓縮的檔案不存在" });
            log4j.error(cvsex.getMessageCodeAndCodeInfo());
            notExistsFileList.add(srcFileName);
            // throw cvsex; // TODO
        }

        // 如果來源與目標檔案路徑相同: 會先使用 tempZipFile 作為檔名壓縮.
        if (StringUtils.equals(srcFileName, zipFileName)) {
            final int lastIndexOf = StringUtils.lastIndexOf(zipFile.getAbsolutePath(), zipFile.getName());
            final String beforStr = StringUtils.substring(zipFile.getAbsolutePath(), 0, lastIndexOf);
            final String afterStr = StringUtils.substring(zipFile.getAbsolutePath(), lastIndexOf);

            final String fileName = beforStr + StringUtils.replace(afterStr, zipFile.getName(), "tempZipFile");
            zipFile = new File(fileName);
        }

        try {
            // 檔案壓縮.
            FileUtil.zipQueryList(zipFile.getAbsolutePath(), Arrays.asList(srcFile)); // 只限檔案.

            // 壓縮完後...
            if (StringUtils.equals(zipFile.getName(), "tempZipFile")) {
                // 原檔刪除.
                deleteSubFile(srcFile.getAbsolutePath());

                // 檔案名稱重新命名回原檔名.
                zipFile.renameTo(new File(srcFileName));

            } else {
                if (isDeleteSrcFile) {
                    // 原檔刪除.
                    deleteSubFile(srcFile.getAbsolutePath());
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            final CvsException cvsex = new CvsException("S301999", new String[] { zipFileName, "壓縮檔案失敗" });
            log4j.error(cvsex.getMessageCodeAndCodeInfo());
            throw cvsex;
        }
        
        return notExistsFileList;
    }

    /**
     * 解壓縮gzip檔案
     * 
     * @param tarFile    欲解壓縮的gzip檔案
     * @param targetDir    解壓後的存放路徑
     */
    public static void ungzipFile(final String gzipFile, final String targetDir) {
        try {
            FileUtil.ungzip(gzipFile, targetDir);
        } catch (Exception e) {
            final CvsException cvse = new CvsException("B431002", e);
            throw cvse;
        }
    }

    /**
     * 解壓縮tgz檔案
     * 
     * @param tarFile    欲解壓縮的tgz檔案
     * @param targetDir    解壓後的存放路徑
     */
    public static void untgzFile(final String tgzFilePath, final String tgzFileName, final String targetDir) {
        // 1. 先ungzip
        final String tgzFile = tgzFilePath + tgzFileName;
        // 2. untar
        final String tarFileName = StringUtils.replace(tgzFileName, ".tgz", ".tar");
        final String tarFile = targetDir + tarFileName;
        //final File tarfile = new File(tarFile);
        //if (tarfile.exists()) {
        //	tarfile.delete();
        //}
        ungzipFile(tgzFile, tarFile);
        unTar(tarFile, targetDir);
    }

    /**
     * 產生檔案欄位表頭
     * 
     * @param fullFileName 文件路徑名稱
     * @param strFistLine  表頭內容
     */
    public static void addFirstLine(String fullFileName, String strFistLine) {
        fileAppendContent(fullFileName, strFistLine, 0);
    }

    /**
     * 將字符寫入檔案
     * @param fileName 文件
     * @param content  寫入的內容
     * @param pos 寫入的位置
     */
    private static void fileAppendContent(String fileName, String content, int pos) {
        RandomAccessFile raf = null;
        try {
            File tmp = File.createTempFile("tmp", null);
            tmp.deleteOnExit();

            raf = new RandomAccessFile(fileName, "rw");
            FileOutputStream tmpOut = new FileOutputStream(tmp);
            FileInputStream tmpIn = new FileInputStream(tmp);
            // 文件长度，字节数
            long fileLength = raf.length();
            if (pos == 0) {
                // 將寫文件指針移到文件首
                raf.seek(pos);// 首先的话是0
            } else {
                // 将写文件指针移到文件尾。
                raf.seek(fileLength);
            }

            byte[] buf = new byte[64];
            int hasRead = 0;
            while ((hasRead = raf.read(buf)) > 0) {
                // 把原有内容读入临时文件
                tmpOut.write(buf, 0, hasRead);
            }

            if (pos == 0) {
                // 將寫文件指針移到文件首
                raf.seek(pos);// 首先的话是0
            } else {
                // 将写文件指针移到文件尾。
                raf.seek(fileLength);
            }
            //			String str=new String(content.getBytes("iso-8859-1"),"UTF-8");//加入乱码控制
            raf.write(content.getBytes("MS950"));
            // 追加临时文件的内容
            while ((hasRead = tmpIn.read(buf)) > 0) {
                raf.write(buf, 0, hasRead);
            }
        } catch (IOException e) {
            final CvsException aes3ge = new CvsException("S301008", new String[] { fileName });
            throw aes3ge;
        } finally {
            if (raf != null) {
                try {
                    raf.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 解壓縮gzip檔案
     * 
     * @param tarFile    欲解壓縮的gzip檔案
     * @param targetDir    解壓後的存放路徑
     * @throws Exception 
     */
    public static void unzipFile(final String zipFile, final String targetDir) throws Exception {

        FileUtil.unzip(zipFile, targetDir);
    }

    /**
     * zip目錄
     * @param zipFileName 壓縮檔名稱(包含目錄及檔名)
     * @param srcDir 來源目錄
     */
    public static void zipDir(final String zipFileName, final String srcDir) {
        try {
            FileUtil.zip(zipFileName, srcDir);
        } catch (Exception e) {
            final CvsException cvsex = new CvsException("S301999", new String[] { srcDir, "壓縮檔案失敗" });
            log4j.error(cvsex.getMessageCodeAndCodeInfo());
            throw cvsex;
        }
    }

    /**
     * 取得目錄下檔案
     */
    public static List<File> getFileList(final String srcPath) {
        return FileUtil.getFileList(srcPath);
    }

    /**
     * 取得srcPath下同srcFileName檔名開頭的所有沒有後綴名的壓縮文件清單
     */
    public static List<File> obtainFileListByFileNameBeginning(String srcPath, String srcFileName) {

        List<File> allFileList = FileUtil.getFileList(srcPath);
        List<File> zipFileList = new ArrayList<File>();

        if(CollectionUtils.isNotEmpty(allFileList)){
            for (File file : allFileList) {
                if (file.getName().startsWith(srcFileName)) {
                    zipFileList.add(file);
                }
            }
        }

        return zipFileList;
    }

    /**
     * 取得目錄下的目錄清單
     */
    public static List<String> getDirList(final String srcPath) {
        return FileUtil.getDirList(srcPath);
    }

    /**
     * 取得srcPath下同srcFileName檔名開頭的所有沒有後綴名的壓縮文件清單
     */
    public static List<String> getDirListByFileNameBeginning(String srcPath, String srcFileName) {

        List<String> allDirList = FileUtil.getDirList(srcPath);
        List<String> dirList = new ArrayList<String>();

        for (String dirName : allDirList) {
            if (dirName.startsWith(srcFileName)) {
                dirList.add(dirName);
            }
        }
        return dirList;
    }

    /**
     * 複製目錄下的檔案到目標目錄
     */
    public static void moveFile(List<File> fileList, String desPath) {
        for (File file : fileList) {
            try {
                Files.move(file.toPath(), Paths.get(desPath + File.separator + file.getName()), StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                log4j.error(e.getMessage());
            }
        }
    }

    /**
     * 刪除檔案或檔案目錄(含底下所有檔案).
     * 
     * @param filePath 檔案或檔案目錄完整路徑. 
     */
    public static void deleteSubFile(final String filePath) {
        String pathName = filePath;
        if (!StringUtils.endsWith(filePath, "/")) {
            pathName = filePath + "/";
        }

        final File file = new File(pathName);
        final String[] files = file.list();

        // 最後一層目錄
        if (files == null) {
            //log4j.debug("刪除目的檔案(" + pathName + ")");
            file.delete();

        } else { // 上層目錄
            //log4j.info("刪除目的檔案目錄(" + pathName + ")");

            for (String detail : files) {
                final File subFile = new File(file, detail);
                if (subFile.isDirectory()) {
                    deleteSubFile(pathName + subFile.getName());
                }
                subFile.delete();
            }

            file.delete();
        }
    }

    /**
     * 移動檔案.
     * 
     * @param srcFilePath 來源檔案路徑
     * @param destFilePath 目的檔案路徑
     */
    public static String moveFile(final String srcFilePath, final String destFilePath) {
        final StringBuilder exitMsg = new StringBuilder();

        String srcPathName = srcFilePath; // 來源檔案路徑.
        if (!StringUtils.endsWith(srcFilePath, "/")) {
            srcPathName = srcFilePath + "/";
        }

        String destPathName = destFilePath; // 目的檔案路徑.
        if (!StringUtils.endsWith(srcFilePath, "/")) {
            destPathName = destFilePath + "/";
        }

        log4j.info("[搬檔] 來源檔案路徑:" + srcPathName);
        log4j.info("[搬檔] 目的檔案路徑:" + destPathName);

        final File file = new File(srcPathName);
        final String[] files = file.list();

        if (files == null) {
            log4j.info(srcPathName + " 沒有可移動的檔案");

        } else {
            for (String detail : files) {
                final File subFile = new File(file, detail);

                try {
                    // 來源目錄；含子項.
                    final File srcDirectoryFile = new File(srcPathName + subFile.getName());

                    String[] detailFiles = srcDirectoryFile.list();

                    if (detailFiles == null) {
                        // 複製來源檔案到目標目錄.
                        FileUtils.copyFileToDirectory(srcDirectoryFile, new File(destPathName));

                        // 刪除來源檔案.
                        FileUtils.deleteQuietly(srcDirectoryFile);

                    } else {
                        // 複製來源目錄到目標目錄.
                        FileUtils.copyDirectoryToDirectory(srcDirectoryFile, new File(destPathName));

                        // 刪除來源目錄.
                        FileUtils.deleteDirectory(srcDirectoryFile);
                    }

                } catch (IOException e) {
                    log4j.error("移動檔案(目錄)失敗:" + subFile.getPath(), e);
                    exitMsg.append("移動檔案(目錄)失敗:" + subFile.getPath());
                    exitMsg.append(System.lineSeparator());
                }
            }
        }

        return exitMsg.toString();
    }
    
    /**
     * 取得檔案內容筆數
     * 
     * @param file
     * @throws IOException
     */
    public static long getRecordCnt(File file) {
    
//    	return Files.readAllLines(file.toPath(), StandardCharsets.UTF_8).size();
    	try(BufferedReader br = Files.newBufferedReader(file.toPath(), StandardCharsets.UTF_8) ){
   
    		String line;
			long recordCnt = 0;
	        while ((line = br.readLine()) != null) { // 逐行讀入檔案內容
//				if (StringUtils.isNotBlank(line)) {
					recordCnt++;
//				}
	        }

	        return recordCnt;
        }    
        catch(Exception ex){
        	throw new RuntimeException(ex);
        }
    }
    
	/**
	 * 建立目錄, 及設定目錄為權限為775
	 * @param dirName
	 */
	public static void createLinuxDirectoryAndPermission(final String dirName) {
		FileUtil.createDirectory(dirName, null);
		final Set<PosixFilePermission> perms = new HashSet<PosixFilePermission>();
		
		perms.add(PosixFilePermission.OWNER_READ);
		perms.add(PosixFilePermission.OWNER_WRITE);
		perms.add(PosixFilePermission.OWNER_EXECUTE);
		
		perms.add(PosixFilePermission.GROUP_READ);
		perms.add(PosixFilePermission.GROUP_WRITE);
		perms.add(PosixFilePermission.GROUP_EXECUTE);
		
		perms.add(PosixFilePermission.OTHERS_READ);
		perms.add(PosixFilePermission.OTHERS_EXECUTE);
		
		try {
			Files.setPosixFilePermissions(Paths.get(dirName), perms);
		} catch (IOException e) {
			final CvsException aes3ge = new CvsException("S301999", e, new Object[]{dirName});
			throw aes3ge;
		}
	}
}
