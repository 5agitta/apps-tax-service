package com.sagitta.taxservice.tax;

import com.sagitta.taxservice.tax.domain.Tax;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TaxRepository  extends JpaRepository<Tax, Long> {
    Optional<Tax> findByEtinAndYear(String etin, int year);

}
