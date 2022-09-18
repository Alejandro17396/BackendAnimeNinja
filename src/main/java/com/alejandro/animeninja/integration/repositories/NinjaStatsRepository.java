package com.alejandro.animeninja.integration.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alejandro.animeninja.bussines.model.NinjaStats;
import com.alejandro.animeninja.bussines.model.NinjaStatsKey;

public interface NinjaStatsRepository extends JpaRepository<NinjaStats, NinjaStatsKey>{

}
