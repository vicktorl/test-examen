package ufro.test1.service;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import ufro.test1.model.comunas;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Service
public class CsvReaderService {

    public List<comunas> readCsv() throws IOException {
        List<comunas> comunas = new ArrayList<>();

        ClassPathResource resource = new ClassPathResource("comunas.csv");
        BufferedReader reader = new BufferedReader(new InputStreamReader(resource.getInputStream()));

        // Saltar la primera línea que contiene los encabezados
        String line = reader.readLine();

        while ((line = reader.readLine()) != null) {
            if (StringUtils.hasText(line)) {
                String[] data = line.split(";");
                if (data.length == 6) {
                    comunas comuna = new comunas();
                    comuna.setRegionCode(data[0]);
                    comuna.setRegionName(data[1]);
                    comuna.setProvinceCode(data[2]);
                    comuna.setProvinceName(data[3]);
                    comuna.setCommuneCode(data[4]);
                    comuna.setCommuneName(data[5]);

                    comunas.add(comuna);
                }
            }
        }

        reader.close();

        return comunas;
    }
}
