package visa.prog.visas.dto;

import lombok.*;
@Data
public class AdminDto {
    private Long id;
    private String first_name;
    private String last_name;
    private String email;
    private String login;
    private String password;
    private String status;
}
