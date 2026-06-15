package com.example.bookmyshow.refund.repository;

import com.example.bookmyshow.refund.schema.Refund;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public  interface RefundRepository extends JpaRepository<Refund,Long> {
    Long CountAllByCompleted();
}
