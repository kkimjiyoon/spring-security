package com.nhnacademy.security.certificate.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "birth_death_report_resident") // 출생사망신고주민
public class BirthDeathReportResident {
    @EmbeddedId
    private Pk pk;

    @MapsId("residentSerialNumber")
    @ManyToOne
    @JoinColumn(name = "resident_serial_number") // 주민일련번호(외래키)
    private Resident targetResident;

    @ManyToOne
    @JoinColumn(name = "report_resident_serial_number")
    private Resident reportResident; // 신고주민일련번호

    @Column(name = "birth_death_report_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMdd", timezone = "Asia/Seoul")
    private LocalDate birthDeathReportDate; // 출생사망신고일자

    @Column(name = "birth_report_qualifications_code")
    private String birthReportQualificationsCode;

    @Column(name = "death_report_qualifications_code")
    private String deathReportQualificationsCode;

    @Column(name = "email_address")
    private String emailAddress; // 이메일 주소

    @Column(name = "phone_number")
    private String phoneNumber; // 전화번호

    @NoArgsConstructor
    @AllArgsConstructor
    @EqualsAndHashCode
    @Getter
    @Setter
    @Embeddable
    public static class Pk implements Serializable {
        @Column(name = "resident_serial_number")
        private int residentSerialNumber;

        @Column(name = "birth_death_type_code")
        private String birthDeathTypeCode; // 출생사망유형코드
    }

}
