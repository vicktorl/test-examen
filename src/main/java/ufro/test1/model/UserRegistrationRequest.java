package ufro.test1.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegistrationRequest {
    private String rut;
    private String nombre;
    private String apellido;
    private String ciudad;

}
