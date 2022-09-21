package test.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Michael
 * @date 2022--2022:57
 */
@Data
@AllArgsConstructor
public class Stock {
    int uid;
    String code;
    String name;
    int market;
}
