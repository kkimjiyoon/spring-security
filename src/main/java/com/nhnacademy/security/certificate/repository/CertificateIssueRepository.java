package com.nhnacademy.security.certificate.repository;

import com.nhnacademy.security.certificate.domain.CertificateIssue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CertificateIssueRepository extends JpaRepository<CertificateIssue, Long> {
}
