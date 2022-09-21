package visa.prog.visas.db;

import lombok.Data;

import javax.persistence.*;
@Data
@Entity
@Table(schema="public", name="admin")
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String first_name;
    private String last_name;
    @Column(name = "email")
    private String email;
    private String status;
}


