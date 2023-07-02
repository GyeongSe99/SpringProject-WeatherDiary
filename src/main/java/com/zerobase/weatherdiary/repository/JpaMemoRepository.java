package com.zerobase.weatherdiary.repository;

import com.zerobase.weatherdiary.domain.Memo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaMemoRepository extends JpaRepository<Memo, Integer> {

}
