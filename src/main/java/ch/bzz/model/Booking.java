package ch.bzz.model;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "booking")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Column(name = "text", length = 255)
    private String text;

    @ManyToOne
    @JoinColumn(name = "debitAccount_fk", nullable = false)
    private Account debitAccount;

    @ManyToOne
    @JoinColumn(name = "creditAccount_fk", nullable = false)
    private Account creditAccount;

    @Column(name = "amount", nullable = false)
    private double amount;

    @ManyToOne
    @JoinColumn(name = "project_fk", nullable = false)
    private Project project;
}
