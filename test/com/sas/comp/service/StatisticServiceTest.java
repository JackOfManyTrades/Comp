package com.sas.comp.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.sas.comp.models.Statistic;

public class StatisticServiceTest {

	@Test
	public void testPlayerStatistics() {
		final StatisticService statisticService = new StatisticService();
		final List<Statistic> statistics = statisticService.getPlayerStatistics(5);
		Assert.assertNotNull(statistics);
		Assert.assertTrue(statistics.size() > 0);
	}

	@Test
	public void testGoalieStatistics() {
		final StatisticService statisticService = new StatisticService();
		final List<Statistic> statistics = statisticService.getGoalieStatistics(5);
		Assert.assertNotNull(statistics);
		Assert.assertTrue(statistics.size() > 0);
	}
}
