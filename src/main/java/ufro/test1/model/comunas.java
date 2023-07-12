package ufro.test1.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class comunas {
    private String regionCode;
    private String regionName;
    private String provinceCode;
    private String provinceName;
    private String communeCode;
    private String communeName;

    public comunas(String regionCode, String regionName, String provinceCode, String provinceName, String communeCode, String communeName) {
        this.regionCode = regionCode;
        this.regionName = regionName;
        this.provinceCode = provinceCode;
        this.provinceName = provinceName;
        this.communeCode = communeCode;
        this.communeName = communeName;
    }

    public comunas() {

    }
}
