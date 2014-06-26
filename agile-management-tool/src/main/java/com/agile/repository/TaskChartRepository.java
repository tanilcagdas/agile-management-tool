package com.agile.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.agile.beans.TaskChartData;


public interface TaskChartRepository extends JpaRepository<TaskChartData, String> {
}

