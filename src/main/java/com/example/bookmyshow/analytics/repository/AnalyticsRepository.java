package com.example.bookmyshow.analytics.repository;

import com.example.bookmyshow.analytics.schema.Analytics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnalyticsRepository extends JpaRepository<Analytics,Long> {

}
