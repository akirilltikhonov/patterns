package evil.corp.patterns.decorator.spring.impl;

import evil.corp.patterns.decorator.spring.DataSource;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
public class EncryptionDataSource implements DataSource {

    private final DataSource fileDataSource;

    public EncryptionDataSource(DataSource fileDataSource) {
        this.fileDataSource = fileDataSource;
    }

    @Override
    public void writeData(String data) {
        fileDataSource.writeData(encode(data));
    }

    @Override
    public String readData() {
        return decode(fileDataSource.readData());
    }

    private String encode(String data) {
        byte[] result = data.getBytes();
        for (int i = 0; i < result.length; i++) {
            result[i] += (byte) 1;
        }
        return Base64.getEncoder().encodeToString(result);
    }

    private String decode(String data) {
        byte[] result = Base64.getDecoder().decode(data);
        for (int i = 0; i < result.length; i++) {
            result[i] -= (byte) 1;
        }
        return new String(result);
    }
}
