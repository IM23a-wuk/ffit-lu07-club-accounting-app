package ch.bzz.model;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Booking {
    private Long id;
    private LocalDate date;
    private String text;
    private Account debitAccount;
    private Account creditAccount;
    private double amount;
    private Project project;
}
