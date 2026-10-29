package kz.iitu.student;

import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class StudentController {

    private final StudentRepository repo;

    public StudentController(StudentRepository repo) {
        this.repo = repo;
    }

    @GetMapping("/")
    public String home() {
        return "Student API by Ulpan Saparbek, group IT2-2310, ID 37615";
    }

    @GetMapping("/api/students")
    public List<Student> all() {
        return repo.findAll();
    }

    @GetMapping("/api/students/{id}")
    public Student one(@PathVariable Long id) {
        return repo.findById(id).orElseThrow(() ->
            new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found: " + id));
    }

    @PostMapping("/api/students")
    @ResponseStatus(HttpStatus.CREATED)
    public Student create(@Valid @RequestBody Student s) {
        s.setId(null);
        return repo.save(s);
    }

    @PutMapping("/api/students/{id}")
    public Student update(@PathVariable Long id, @Valid @RequestBody Student s) {
        Student old = one(id);
        old.setFirstName(s.getFirstName());
        old.setLastName(s.getLastName());
        old.setGroupName(s.getGroupName());
        old.setEmail(s.getEmail());
        return repo.save(old);
    }

    @DeleteMapping("/api/students/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        one(id);
        repo.deleteById(id);
    }
}
