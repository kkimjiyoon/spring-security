package com.nhnacademy.security.certificate.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
@Getter
@Setter
@Entity
@Table(name = "household_composition_resident")
public class HouseholdCompositionResident {
    @EmbeddedId
    private Pk pk;

    @MapsId("residentSerialNumber")
    @OneToOne
    @JoinColumn(name = "resident_serial_number") // 주민일련번호(외래키)
    private Resident resident;

    @MapsId("householdSerialNumber")
    @ManyToOne
    @JoinColumn(name = "household_serial_number") // 세대일련번호(외래키)
    private Household household;

    @Column(name = "report_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMdd", timezone = "Asia/Seoul")
    private LocalDate reportDate; // 신고일자

    @Column(name = "household_relationship_code")
    private String householdRelationshipCode; // 세대주관계코드

    @Column(name = "household_composition_change_reason_code")
    private String householdCompositionChangeReasonCode; // 세대구성변동사유코드


    @NoArgsConstructor
    @AllArgsConstructor
    @EqualsAndHashCode
    @Getter
    @Setter
    @Embeddable
    public static class Pk implements Serializable {
        @Column(name = "household_serial_number")
        private int householdSerialNumber; // 세대일련번호

        @Column(name = "resident_serial_number")
        private int residentSerialNumber; // 주민일련번호
    }
}
