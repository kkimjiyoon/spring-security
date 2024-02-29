package com.nhnacademy.security.certificate.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
@Getter
@Setter
@Entity
@Table(name = "family_relationship") // 가족관계
public class FamilyRelationship {
    @EmbeddedId
    private Pk pk;

    @MapsId("baseResidentSerialNumber")
    @ManyToOne
    @JoinColumn(name = "base_resident_serial_number") // 기준주민일련번호(외래키)
    private Resident baseResident;

    @MapsId("familyResidentSerialNumber")
    @ManyToOne
    @JoinColumn(name = "family_resident_serial_number") // 대상가족주민일련번호(외래키)
    private Resident familyResident;


    @Column(name = "family_relationship_code")
    private String familyRelationshipCode; // 가족관계코드


    @NoArgsConstructor
    @AllArgsConstructor
    @EqualsAndHashCode
    @Getter
    @Setter
    @Embeddable
    public static class Pk implements Serializable {
        private int baseResidentSerialNumber;

        private int familyResidentSerialNumber;
    }
}
