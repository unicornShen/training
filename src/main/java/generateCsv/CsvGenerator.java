package generateCsv;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.opencsv.CSVWriter;

/**
 *
 */
public class CsvGenerator {
    
    public static void main(String[] args) {
        List<List<String>> list = new ArrayList<>();
        list.add(Arrays.asList("客戶資料查核a", "s"));
        list.add(Arrays.asList("b", "客戶資料查核d"));
        list.add(Arrays.asList("c", "f", "客戶資料查核"));

        List<String[]> rList = new ArrayList<>();
        for (List<String> lst : list) {
            rList.add(lst.toArray(new String[] {}));
        }

        String exportPath = "D:/SFAP/";
        String outPutFileName = "csv_test.csv";
        final File file = new File(exportPath + outPutFileName);
        try (final FileOutputStream fo = new FileOutputStream(file);) {
            final OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fo, Charset.forName("ms950"));
            final CSVWriter csvWriter = new CSVWriter(outputStreamWriter);

            csvWriter.writeAll(rList);
            csvWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("OK");
    }
}
