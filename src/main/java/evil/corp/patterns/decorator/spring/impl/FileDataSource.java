package evil.corp.patterns.decorator.spring.impl;

import evil.corp.patterns.decorator.spring.DataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;

@Service
public class FileDataSource implements DataSource {

    private final String dataSourcePath;

    public FileDataSource(@Value("${data-source.path}") String dataSourcePath) {
        this.dataSourcePath = dataSourcePath;
    }

    @Override
    public void writeData(String data) {
        File file = new File(dataSourcePath);
        try (OutputStream fos = new FileOutputStream(file)) {
            fos.write(data.getBytes(), 0, data.length());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public String readData() {
        char[] buffer = null;
        File file = new File(dataSourcePath);
        try (FileReader reader = new FileReader(file)) {
            buffer = new char[(int) file.length()];
            reader.read(buffer);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return new String(buffer);
    }
}
