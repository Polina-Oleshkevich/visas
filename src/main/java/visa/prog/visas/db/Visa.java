package visa.prog.visas.db;

import javax.persistence.*;

@Entity
public class Visa {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String visaType;
    private Integer visaTerm;
    private String purpose;
    private String country;
    private Integer price;

    public Visa(String visaType, Integer visaTerm, String purpose, String country, Integer price) {
        this.visaType = visaType;
        this.visaTerm = visaTerm;
        this.purpose = purpose;
        this.country = country;
        this.price = price;
    }

    public Visa() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVisaType() {
        return visaType;
    }

    public void setVisaType(String visaType) {
        this.visaType = visaType;
    }

    public Integer getVisaTerm() {
        return visaTerm;
    }

    public void setVisaTerm(Integer visaTerm) {
        this.visaTerm = visaTerm;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
