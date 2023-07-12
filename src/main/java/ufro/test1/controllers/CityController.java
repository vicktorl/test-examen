package ufro.test1.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import ufro.test1.model.comunas;
import ufro.test1.model.UserRegistrationRequest;
import ufro.test1.service.CsvReaderService;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CityController {

    private final CsvReaderService csvReaderService;

    @Autowired
    public CityController(CsvReaderService csvReaderService) {
        this.csvReaderService = csvReaderService;
    }

    @PostMapping("/register")
    public String registerUser(@RequestBody UserRegistrationRequest request) throws IOException {
        String rut = request.getRut();
        String nombre = request.getNombre();
        String apellido = request.getApellido();
        String ciudad = request.getCiudad();

        if (StringUtils.hasText(rut) && StringUtils.hasText(nombre) && StringUtils.hasText(apellido) && StringUtils.hasText(ciudad)) {
            List<comunas> persons = csvReaderService.readCsv();

            for (comunas comunas : persons) {
                if (comunas.getCommuneName().equalsIgnoreCase(ciudad)) {
                    return "Usuario registrado";
                }
            }

            return "La ciudad no se encuentra en el archivo CSV";
        }

        return "Los datos ingresados son inválidos";
    }

    @PostMapping("/person")
    public String createPerson(@RequestBody String requestBody) throws IOException {
        String[] params = requestBody.split(",");

        if (params.length == 4) {
            String rut = params[0].trim();
            String nombre = params[1].trim();
            String apellido = params[2].trim();
            String ciudad = params[3].trim();

            List<comunas> persons = csvReaderService.readCsv();
            String regionCode = "";

            for (comunas person : persons) {
                if (person.getCommuneName().equalsIgnoreCase(ciudad)) {
                    regionCode = person.getProvinceName();
                    break;
                }
            }

            if (!regionCode.isEmpty()) {
                return "Información de la persona:\nRut: " + rut + "\nNombre: " + nombre + "\nApellido: " + apellido + "\nCiudad: " + ciudad + "\nCódigo de región: " + regionCode;
            } else {
                return "La ciudad no se encuentra en el archivo CSV";
            }
        }

        return "Formato de parámetros incorrecto";
    }
}
