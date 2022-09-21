package visa.prog.visas.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import visa.prog.visas.db.Status;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import visa.prog.visas.converter.Converter;
import visa.prog.visas.db.Admin;
import visa.prog.visas.db.AdminRepository;
import visa.prog.visas.dto.AdminDto;
import visa.prog.visas.exceptions.CenterServiceException;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class AdminServiceImplements implements AdminService {

    private final AdminRepository adminRepository;
    private final ObjectMapper objectMapper;
    private static final String EXC_MESSAGE = "Admin with id %d is not found";

    @Override
    public AdminDto create(AdminDto adminDto) {
        if (adminDto == null) {
            throw new CenterServiceException("Admin is null");
        }

        Long id = adminDto.getId();
        if (id != null && adminRepository.findById(id).isPresent()) {
            throw new CenterServiceException(String.format("Admin with id %d already exists", id));
        }

        Admin admin = objectMapper.convertValue(adminDto, Admin.class);
        admin.setStatus(String.valueOf(Status.CREATED));
        Admin save = adminRepository.save(admin);
        return objectMapper.convertValue(save, AdminDto.class);
    }

    @Override
    public AdminDto read(Long id) {
        Admin admin = adminRepository.findById(id).orElseThrow(() ->
                new CenterServiceException(String.format(EXC_MESSAGE, id)));
        return objectMapper.convertValue(admin, AdminDto.class);
    }

    @Override
    public AdminDto update(AdminDto adminDto) {
        Long id = adminDto.getId();
        if (id == null) {
            throw new CenterServiceException("id is null");
        }

        read(id);

        Admin admin = objectMapper.convertValue(adminDto, Admin.class);
        admin.setStatus(String.valueOf(Status.UPDATED));
        Admin save = adminRepository.save(admin);
        return objectMapper.convertValue(save, AdminDto.class);
    }

    @Override
    public AdminDto delete(Long id) {
        Admin admin = objectMapper.convertValue(read(id), Admin.class);
        admin.setStatus(String.valueOf(Status.DELETED));
        Admin save = adminRepository.save(admin);
        return objectMapper.convertValue(save, AdminDto.class);
    }

    @Override
    public List<AdminDto> readAll() {
        return adminRepository.findAll().stream()
                .map(admin -> objectMapper.convertValue(admin, AdminDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public ResponseEntity<String> createAdmin(AdminDto adminDto) {
        if (adminDto == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Admin is not created! Body is null");
        }

        AdminDto admin = create(adminDto);
        String dto = Converter.getString(admin, objectMapper);

        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @Override
    public ResponseEntity<String> readAdmin(Long id) {
        if (id == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Admin is not found! Id is null");
        }

        AdminDto admin = read(id);
        String dto = Converter.getString(admin, objectMapper);

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> updateAdmin(AdminDto adminDto) {
        if (adminDto == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Admin is not updated! Body is null");
        }

        AdminDto admin = update(adminDto);
        String dto = Converter.getString(admin, objectMapper);

        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @Override
    public ResponseEntity<String> deleteAdmin(Long id) {
        if (id == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Admin assignment is not deleted! Id is null");
        }

        AdminDto admin = delete(id);
        String dto = Converter.getString(admin, objectMapper);

        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @Override
    public ResponseEntity<List<AdminDto>> getAllAdmins() {
        return ResponseEntity.ok(readAll());
    }
}

