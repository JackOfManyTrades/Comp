package com.sas.comp.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.sas.comp.models.Game;
import com.sas.comp.models.Player;
import com.sas.comp.mysql.Database;

public class GameService {

    public List<Game> getLeagueSchedule(final Integer seasonId) {
        return getSchedules(seasonId, false);
    }

    public List<Game> getPlayoffSchedule(final Integer seasonId) {
        return getSchedules(seasonId, true);
    }

    private List<Game> getSchedules(final Integer seasonId, final Boolean playoff) {
        final List<Game> schedules = new ArrayList<Game>();

        try {
            final Connection conn = Database.getConnection();
            final PreparedStatement pstmt = conn
                    .prepareStatement("SELECT * FROM schedule WHERE playoff = ? AND season_id = ?");
            pstmt.setBoolean(1, playoff);
            pstmt.setInt(2, seasonId);

            final ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                final Game schedule = new Game();
                schedule.setHome(rs.getString("home"));
                schedule.setHomeId(rs.getInt("home_id"));
                schedule.setAway(rs.getString("away"));
                schedule.setAwayId(rs.getInt("away_id"));
                schedule.setDate(rs.getTimestamp("date"));
                schedule.setGameId(rs.getInt("game_id"));

                schedule.setHomeScore(rs.getInt("home_score"));
                if (rs.wasNull()) {
                    schedule.setHomeScore(null);
                }

                schedule.setAwayScore(rs.getInt("away_score"));
                if (rs.wasNull()) {
                    schedule.setAwayScore(null);
                }

                schedules.add(schedule);
            }

            rs.close();
            pstmt.close();
            conn.close();
        } catch (final Exception e) {
            e.printStackTrace();
        }

        return schedules;
    }

    public List<Game> getSchedules() {
        final List<Game> schedules = new ArrayList<Game>();

        try {
            final Connection conn = Database.getConnection();
            final PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM schedule ORDER BY date");

            final ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                final Game schedule = new Game();
                schedule.setHome(rs.getString("home"));
                schedule.setHomeId(rs.getInt("home_id"));
                schedule.setAway(rs.getString("away"));
                schedule.setAwayId(rs.getInt("away_id"));
                schedule.setDate(rs.getTimestamp("date"));
                schedule.setGameId(rs.getInt("game_id"));

                schedule.setHomeScore(rs.getInt("home_score"));
                if (rs.wasNull()) {
                    schedule.setHomeScore(null);
                }

                schedule.setAwayScore(rs.getInt("away_score"));
                if (rs.wasNull()) {
                    schedule.setAwayScore(null);
                }

                schedules.add(schedule);
            }

            rs.close();
            pstmt.close();
            conn.close();
        } catch (final Exception e) {
            e.printStackTrace();
        }

        return schedules;
    }

    public List<Game> getSchedules(final Date date) {
        final List<Game> schedules = new ArrayList<Game>();

        try {
            final Connection conn = Database.getConnection();
            final PreparedStatement pstmt = conn
                    .prepareStatement("SELECT * FROM schedule WHERE date between ? AND ? ORDER BY date");
            pstmt.setTimestamp(1, new java.sql.Timestamp(date.getTime()));

            final Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.add(Calendar.DATE, 1); // minus number would decrement the days

            pstmt.setTimestamp(2, new java.sql.Timestamp(cal.getTime().getTime()));

            final ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                final Game schedule = new Game();
                schedule.setHome(rs.getString("home"));
                schedule.setHomeId(rs.getInt("home_id"));
                schedule.setAway(rs.getString("away"));
                schedule.setAwayId(rs.getInt("away_id"));
                schedule.setDate(rs.getTimestamp("date"));
                schedule.setGameId(rs.getInt("game_id"));

                schedule.setHomeScore(rs.getInt("home_score"));
                if (rs.wasNull()) {
                    schedule.setHomeScore(null);
                }

                schedule.setAwayScore(rs.getInt("away_score"));
                if (rs.wasNull()) {
                    schedule.setAwayScore(null);
                }

                schedules.add(schedule);
            }

            rs.close();
            pstmt.close();
            conn.close();
        } catch (final Exception e) {
            e.printStackTrace();
        }

        return schedules;
    }

    public Game getGame(final Integer gameId) {
        Game game = null;

        try {
            final Connection conn = Database.getConnection();
            final PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM schedule WHERE game_id = ?");
            pstmt.setInt(1, gameId);

            final ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                game = new Game();
                game.setHome(rs.getString("home"));
                game.setHomeId(rs.getInt("home_id"));
                game.setAway(rs.getString("away"));
                game.setAwayId(rs.getInt("away_id"));
                game.setDate(rs.getTimestamp("date"));
                game.setGameId(rs.getInt("game_id"));

                game.setHomeScore(rs.getInt("home_score"));
                if (rs.wasNull()) {
                    game.setHomeScore(null);
                }

                game.setAwayScore(rs.getInt("away_score"));
                if (rs.wasNull()) {
                    game.setAwayScore(null);
                }
            }

            rs.close();
            pstmt.close();
            conn.close();
        } catch (final Exception e) {
            e.printStackTrace();
        }

        return game;
    }

    public void updateScore(final Game game) {
        try {
            final Connection conn = Database.getConnection();
            final PreparedStatement pstmt = conn
                    .prepareStatement("UPDATE games SET home_score = ?, away_score = ? WHERE id = ?");
            pstmt.setInt(1, game.getHomeScore());
            pstmt.setInt(2, game.getAwayScore());
            pstmt.setInt(3, game.getGameId());
            pstmt.execute();
            pstmt.close();
            conn.close();
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }

    public List<Player> getPlayers(final Integer gameId) {
        final List<Player> players = new ArrayList<Player>();

        try {
            final Connection conn = Database.getConnection();
            final PreparedStatement pstmt = conn
                    .prepareStatement("SELECT * FROM game_players WHERE game_id = ? ORDER BY player");
            pstmt.setInt(1, gameId);

            final ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                final Player player = new Player();
                player.setName(rs.getString("player"));
                player.setPlayerId(rs.getInt("player_id"));
                player.setTeamId(rs.getInt("team_id"));
                player.setGoals(rs.getInt("goals"));
                players.add(player);
            }

            rs.close();
            pstmt.close();
            conn.close();
        } catch (final Exception e) {
            e.printStackTrace();
        }

        return players;
    }

    public Boolean isScheduled(final Game game) {
        try {
            final Connection conn = Database.getConnection();
            final PreparedStatement pstmt = conn
                    .prepareStatement("SELECT * FROM games WHERE season_id=? AND home_team_id=? AND away_team_id=? AND date=?");
            pstmt.setInt(1, game.getSeasonId());
            pstmt.setInt(2, game.getHomeId());
            pstmt.setInt(3, game.getAwayId());
            pstmt.setTimestamp(4, new java.sql.Timestamp(game.getDate().getTime()));

            final ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                rs.close();
                pstmt.close();
                conn.close();

                return true;
            } else {
                rs.close();
                pstmt.close();
                conn.close();
            }


        } catch (final Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public void save(final Game game) {
        try {
            final Connection conn = Database.getConnection();
            final PreparedStatement pstmt = conn
                    .prepareStatement("INSERT INTO games VALUES(null, ?, ?, ?, ?, ?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, game.getSeasonId());
            pstmt.setInt(2, game.getHomeId());
            pstmt.setInt(3, game.getAwayId());
            pstmt.setTimestamp(4, new java.sql.Timestamp(game.getDate().getTime()));
            pstmt.setNull(5, Types.NULL);
            pstmt.setNull(6, Types.NULL);
            pstmt.setInt(7, 0);

            pstmt.execute();
            final ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                game.setGameId(rs.getInt(1));
            }

            rs.close();
            pstmt.close();
            conn.close();
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }
}
