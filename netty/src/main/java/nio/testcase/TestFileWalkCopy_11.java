package nio.testcase;

import java.io.IOException;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created on 2022/3/7.
 *
 * @author Weikaichen
 */
public class TestFileWalkCopy_11 {
    public static void main(String[] args) throws IOException {
        //ctrl+alt+T
        String source="D:\\learning";
        String target="D:\\learningTest";
        Files.walk(Paths.get(source)).forEach(path -> {
            try {
                String targetDir=path.toString().replace(source, target);
                if (Files.isDirectory(path)) {
                    Files.createDirectory(Paths.get(targetDir));
                }
                //文件进行拷贝
                if (Files.isRegularFile(path)){
                    Files.copy(path, Paths.get(target));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }
}
