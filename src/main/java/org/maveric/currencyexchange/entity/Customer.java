package org.maveric.currencyexchange.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.maveric.currencyexchange.enums.GenderType;

import java.time.LocalDate;
import java.time.Period;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Customer{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_seq_generator")
    @SequenceGenerator(name = "customer_seq_generator", sequenceName = "customer_seq", allocationSize = 1)
    private Long id;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false)
    private LocalDate dob;
    @Column(nullable = false, unique = true)
    private String email;
    private Integer age;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private GenderType gender;
    @Column(nullable = false, unique = true)
    private String phone;
    @CreationTimestamp
    private Date createdAt;
    @UpdateTimestamp
    private Date updatedAt;
    @OneToMany(
            mappedBy = "customer",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    private List<Account> accounts;

    @OneToMany(
            mappedBy = "customer",
            fetch = FetchType.EAGER
    )
    private List<Transaction> transactions;

    @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL)
    private Credentials credentials;

    @PrePersist
    private void setAge() {
        LocalDate currentDate = LocalDate.now();
        age = Period.between(dob, currentDate).getYears();
    }
}
