package org.example.projectbcms.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;


import java.time.LocalDate;

@Entity
@Table(name = "booking")
@Data
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "start_time", nullable = false)
    private LocalDate startTime;

    @Column(name = "end_time", nullable = false)
    private LocalDate endTime;

    @Column(name = "total_price", nullable = false)
    private Double totalPrice;

    @Column(name = "booking_status", length = 50)
    private String bookingStatus;

    @JoinColumn
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @JoinColumn
    @ManyToOne(fetch = FetchType.LAZY)
    private Court court;

}
