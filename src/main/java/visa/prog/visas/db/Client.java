package visa.prog.visas.db;

import javax.persistence.*;
import java.time.LocalDate;
@Entity

public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate dateBirst;
    private String address;
    private String phone;
    private String email;
    private String documents;

    public Client() {
    }

    public Client(String firstName, String lastName, LocalDate dateBirst, String address, String phone, String email, String documents) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateBirst = dateBirst;
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDateBirst() {
        return dateBirst;
    }

    public void setDateBirst(LocalDate dateBirst) {
        this.dateBirst = dateBirst;
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
