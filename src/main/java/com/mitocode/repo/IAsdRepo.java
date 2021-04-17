package com.mitocode.repo;

import com.mitocode.model.SignosVitales;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAsdRepo extends JpaRepository<SignosVitales,Integer> {
}
