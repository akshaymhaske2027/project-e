package com.akshay.service.stats;


import com.akshay.dto.GraphDto;
import com.akshay.dto.StatsDto;

public interface StatsService {



    GraphDto getChartData();

    StatsDto getStats();
}
