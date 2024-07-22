import java.io.*;

public class DeleteCommentsInText {

    private static final String fileToChange = "InputFile.txt";
    private boolean inComment = false;
    public static void main(String[] args) {
        DeleteCommentsInText del = new DeleteCommentsInText();
        del.changeFile(fileToChange);
    }

    private void changeFile(String fileToChange) {
        try(FileReader fr = new FileReader(fileToChange);
            BufferedReader br = new BufferedReader(fr);
            FileWriter fw = new FileWriter("OutputFile.txt");
            BufferedWriter bw = new BufferedWriter(fw)
        ){
            String line;
            while ((line = br.readLine())!= null){
                String processedLine = processLine(line);
                bw.write(processedLine);
                bw.newLine();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String processLine(String line) {
        String startCom1 = "//";
        String startCom2 = "/*";
        String stopCom2 = "*/";
        if(inComment){
            if(!line.contains(stopCom2)){
                line = "";
                return line;
            }else {
                line = line.replaceFirst(".*?\\*/", "");
                inComment = false;
            }
        }
        if(line.contains(startCom1)){
            line = line.replaceAll("//.*$","");
        }
        if(line.contains(startCom2) && line.contains(stopCom2)){
            line = line.replaceAll("/\\*.*?\\*/", "");
        }
        if(line.contains(startCom2)){
            line = line.replaceAll("/\\*.*$","");
            inComment = true;
        }
        return line;
    }
}