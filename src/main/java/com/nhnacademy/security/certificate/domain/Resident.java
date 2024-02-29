package com.nhnacademy.security.certificate.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;
@Getter
@Setter
@Entity
@Table(name = "resident") // 주민
public class Resident {

    @Id
    @Column(name = "resident_serial_number")
    private int residentSerialNumber; // 주민일련번호

    @Column(name = "name")
    private String name; // 성명

    @Column(name = "resident_registration_number")
    private String residentRegistrationNumber; // 주민등록번호

    @Column(name = "gender_code")
    private String genderCode; // 성별코드

    @Column(name = "birth_date")
    private LocalDateTime birthDate; // 출생일시

    @Column(name = "birth_place_code")
    private String birthPlaceCode; // 출생장소코드

    @Column(name = "registration_base_address")
    private String registrationBaseAddress; // 등록기준지주소

    @Column(name = "death_date")
    private LocalDateTime deathDate; // 사망일시

    @Column(name = "death_place_code")
    private String deathPlaceCode; // 사망장소코드

    @Column(name = "death_place_address")
    private String deathPlaceAddress; // 사망장소주소

    @Column(name = "resident_id", unique = true)
    private String residentId;

    @Column(name = "resident_password")
    private String residentPassword;

    @Column(name = "resident_email", unique = true)
    private String residentEmail;
}
