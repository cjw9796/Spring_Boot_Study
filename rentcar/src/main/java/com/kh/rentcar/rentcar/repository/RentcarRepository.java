package com.kh.rentcar.rentcar.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kh.rentcar.rentcar.entity.Rentcar;

public interface RentcarRepository extends JpaRepository<Rentcar, Long> {

}
