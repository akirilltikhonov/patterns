package evil.corp.patterns.decorator.spring;

public interface DataSource {

    void writeData(String data);

    String readData();
}
