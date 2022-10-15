package leetcode;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.util.concurrent.TimeUnit;

public class Main {

    File file;
    Main self;
    static int mMonth = 1;

    public Main(){
        file = new File("test");
        self = this;
    }

    public void record(String content){
        boolean append = true;
        try {
            try {
                Path fp = file.toPath();
                BasicFileAttributes ra = Files.readAttributes(fp, BasicFileAttributes.class);
                FileTime fileTime = FileTime.fromMillis(System.currentTimeMillis());
                if ((fileTime.to(TimeUnit.DAYS) - ra.creationTime().to(TimeUnit.DAYS)) > 1) {
                    Files.setAttribute(fp, "basic:creationTime", fileTime);
                    append = false;
                }
            } catch (Exception e) {
                append = false;
                e.printStackTrace();
            }
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file,append));
            bufferedWriter.write(content);
            //bufferedWriter.close();
        }catch (Exception e){
            e.printStackTrace();
        }


    }

    public void update(File file){
        Path fp = file.toPath();
        try{
            BasicFileAttributes ra = Files.readAttributes(fp, BasicFileAttributes.class);
            long currentTimeMillis = System.currentTimeMillis();
            FileTime fileTime = FileTime.fromMillis(currentTimeMillis);
            Files.setAttribute(fp, "basic:creationTime", fileTime);
            FileTime creationTime = (FileTime)Files.getAttribute(fp, "basic:creationTime");
            System.out.println("NEW CREATION TIME:" + creationTime.toString());
            System.out.println("OLD CREATION TIME:" + ra.creationTime());
        }catch(IOException e){
            e.printStackTrace();
        }
    }

//    public void recordToFile(String content){
//        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
//            bufferedWriter.write(content);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }

    public static void main(String[] args) throws IOException {
        //System.out.println("dddd");
        // 115206
        System.out.println(System.getProperty("user.dir"));
        File directory = new File("");
        System.out.println(directory.getCanonicalPath());
        System.out.println(directory.getAbsolutePath());
        System.out.println(directory.getPath());

      
    }

}
