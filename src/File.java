import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class File {
    List<Dados> dadosList = new ArrayList<>();
    public List<Dados> verificarRegras(String filename) {
       try {
           java.io.File file = new java.io.File(filename);
           Scanner scanner = new Scanner(file);
           while (scanner.hasNextLine()) {
               String line = scanner.nextLine();
               String module;
               String value = "";
               if (line.contains("availableSaleTypes")) {
                   Pattern pattern = Pattern.compile("\\[(.*?)]");
                   Matcher matcher = pattern.matcher(line);
                   if (matcher.find()) {
                       value = matcher.group(1);
                   }
                   line = line.replaceFirst(Pattern.quote(value), "");
                   String[] values = line.split(",");
                   module = values[2].replaceAll("\"", "'");
               } else {
                   String[] values = line.split(",");
                   value = values[1].replaceAll("\"", "'");
                   module = values[2].replaceAll("\"", "'");
               }
               String[] values = line.split(",");
               String key = values[0].replaceAll("\"", "'");
               String arrayString = Arrays.toString(values);
               System.out.println(arrayString);
               Dados dados = new Dados(key, value, module);
               dadosList.add(dados);
           }
           scanner.close();
       } catch (Exception e) {
           e.printStackTrace();
       }
       return dadosList;
   }
}
