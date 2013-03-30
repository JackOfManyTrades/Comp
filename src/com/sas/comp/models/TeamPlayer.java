package com.sas.comp.models;

// Generated Mar 30, 2013 1:36:42 PM by Hibernate Tools 3.4.0.CR1

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * TeamPlayer generated by hbm2java
 */
@SuppressWarnings("serial")
@Entity(name = "team_player")
public class TeamPlayer implements Serializable {

  @EmbeddedId
  private TeamPlayerId id;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "team_id", insertable = false, updatable = false)
  private Team team;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "player_id", insertable = false, updatable = false)
  private Player player;
  private Boolean isGoalie;
  private Boolean isCaptain;
  private Boolean isCoCaptain;

  public TeamPlayer() {
  }

  public TeamPlayerId getId() {
    return this.id;
  }

  public void setId(final TeamPlayerId id) {
    this.id = id;
  }

  public Team getTeam() {
    return this.team;
  }

  public void setTeam(final Team team) {
    this.team = team;
  }

  public Player getPlayer() {
    return this.player;
  }

  public void setPlayer(final Player player) {
    this.player = player;
  }

  public Boolean getIsGoalie() {
    return this.isGoalie;
  }

  public void setIsGoalie(final Boolean isGoalie) {
    this.isGoalie = isGoalie;
  }

  public Boolean getIsCaptain() {
    return this.isCaptain;
  }

  public void setIsCaptain(final Boolean isCaptain) {
    this.isCaptain = isCaptain;
  }

  public Boolean getIsCoCaptain() {
    return this.isCoCaptain;
  }

  public void setIsCoCaptain(final Boolean isCoCaptain) {
    this.isCoCaptain = isCoCaptain;
  }

}
