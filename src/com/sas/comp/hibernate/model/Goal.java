package com.sas.comp.hibernate.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@SuppressWarnings("serial")
@Entity(name = "goals")
public class Goal implements Serializable {

	@Id
	private Integer id;
	@Column(name = "game_id")
	private Integer gameId;
	@Column(name = "player_id")
	private Integer playerId;

	public Integer getId() {
		return id;
	}

	public void setId(final Integer id) {
		this.id = id;
	}

	public Integer getGameId() {
		return gameId;
	}

	public void setGameId(final Integer gameId) {
		this.gameId = gameId;
	}

	public Integer getPlayerId() {
		return playerId;
	}

	public void setPlayerId(final Integer playerId) {
		this.playerId = playerId;
	}
}
