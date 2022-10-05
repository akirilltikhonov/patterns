package evil.corp.patterns.decorator.pure;

public interface DataSource {
    void writeData(String data);

    String readData();
}
