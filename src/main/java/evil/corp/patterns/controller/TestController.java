package evil.corp.patterns.controller;

import evil.corp.patterns.decorator.spring.DataSource;
import evil.corp.patterns.decorator.spring.impl.FileDataSource;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TestController {

    private final DataSource dataSourceDecorator;
    @Value("${data-source.path}")
    private String path;

    @GetMapping("/decorator")
    void testDecorator() {
        String salaryRecords = "Name,Salary\nJohn Smith,100000\nSteven Jobs,912000";
//        DataSource dataSourceDecorator = new DataSourceDecorator(
//                new CompressionDataSource(
//                        new EncryptionDataSource(
//                                new FileDataSource(path)
//                        )
//                )
//        );
        dataSourceDecorator.writeData(salaryRecords);
        DataSource plain = new FileDataSource(path);

        System.out.println("- Input ----------------");
        System.out.println(salaryRecords);
        System.out.println("- Encoded --------------");
        System.out.println(plain.readData());
        System.out.println("- Decoded --------------");
        System.out.println(dataSourceDecorator.readData());
    }
}
