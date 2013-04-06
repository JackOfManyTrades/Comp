package com.sas.comp.listener;

import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.sas.comp.models.Cache;
import com.sas.comp.models.Season;
import com.sas.comp.service.ScheduleService;
import com.sas.comp.service.SeasonService;
import com.sas.comp.service.StandingService;
import com.sas.comp.service.StatisticService;

@WebListener
public class CacheListener implements ServletContextListener {

	@Override
	public void contextDestroyed(final ServletContextEvent arg0) {
	}

	private final SeasonService seasonService = new SeasonService();
	private final ScheduleService scheduleService = new ScheduleService();
	private final StandingService standingService = new StandingService();
	private final StatisticService statisticService = new StatisticService();

	@Override
	public void contextInitialized(final ServletContextEvent arg0) {
		final List<Season> seasons = this.seasonService.getSeasons();
		for (final Season season : seasons) {
			season.setStandings(this.standingService.getStandings(season.getId()));
			season.setLeagueSchedule(this.scheduleService.getLeagueSchedule(season.getId()));
			season.setPlayoffSchedule(this.scheduleService.getPlayoffSchedule(season.getId()));
			season.setPlayerStatistics(this.statisticService.getPlayerStatistics(season.getId()));
			season.setGoalieStatistics(this.statisticService.getGoalieStatistics(season.getId()));
		}
		Cache.getCache().setSeasons(seasons);
	}

}
