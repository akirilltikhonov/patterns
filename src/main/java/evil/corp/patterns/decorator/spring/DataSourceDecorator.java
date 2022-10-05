package evil.corp.patterns.decorator.spring;

import org.springframework.stereotype.Service;

@Service
public class DataSourceDecorator implements DataSource {

    private final DataSource compressionDataSource;

    public DataSourceDecorator(DataSource compressionDataSource) {
        this.compressionDataSource = compressionDataSource;
    }

    @Override
    public void writeData(String data) {
        compressionDataSource.writeData(data);
    }

    @Override
    public String readData() {
        return compressionDataSource.readData();
    }
}
