package com.nhnacademy.security.certificate.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
@Getter
@Setter
@Entity
@Table(name = "household") // 세대
public class Household {

    @Id
    @Column(name = "household_serial_number")
    private int householdSerialNumber; // 세대일련번호

    @OneToOne
    @JoinColumn(name = "household_resident_serial_number") // 세대주주민일련번호(외래키)
    private Resident householdResident;

    @Column(name = "household_composition_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMdd", timezone = "Asia/Seoul")
    private LocalDate householdCompositionDate; // 세대구성일자

    @Column(name = "household_composition_reason_code")
    private String householdCompositionReasonCode; // 세대구성사유코드

    @Column(name = "current_house_movement_address")
    private String currentHouseMovementAddress; // 현재전입주소

}
