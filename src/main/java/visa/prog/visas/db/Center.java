package visa.prog.visas.db;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Center {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long id;
    private String address;
    private String phone;
    private String email;
    private String documents;

    public Center() {
    }

    public Center(String address, String phone, String email, String documents) {
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.documents = documents;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDocuments() {
        return documents;
    }

    public void setDocuments(String documents) {
        this.documents = documents;
    }
}
