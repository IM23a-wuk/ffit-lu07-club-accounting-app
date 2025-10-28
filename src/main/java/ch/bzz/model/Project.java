package ch.bzz.model;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "project")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Project {

    @Id
    @Column(name = "projectName", nullable = false, unique = true, length = 100)
    private String projectName;

    @Column(name = "passwordHash", nullable = false)
    private String passwordHash;
}
