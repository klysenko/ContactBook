package services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class ReadService {
    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    String readField(String fieldName) throws IOException {
        String field;
        do {
            System.out.print(fieldName);
            field = br.readLine();
        } while (field.trim().isEmpty());
        return field.trim();
    }
}
