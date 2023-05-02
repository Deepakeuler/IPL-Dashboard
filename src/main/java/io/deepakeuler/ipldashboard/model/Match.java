package io.deepakeuler.ipldashboard.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Match {

	@Id//identifier id as primary key
    private String id;
    private String city;
    private String date;
    private String playerOfMatch;
    private String venue;
    private String team1;
    private String team2;
    private String tossWinner;
    private String tossDecision;
    private String matchWinner;
    private String result;
    private String resultMargin;
    private String umpire1;
    private String umpire2;//add
    

public Match(){

}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getPlayerOfMatch() {
		return playerOfMatch;
	}
	public void setPlayerOfMatch(String playerOfMatch) {
		this.playerOfMatch = playerOfMatch;
	}
	public String getVenue() {
		return venue;
	}
	public void setVenue(String venue) {
		this.venue = venue;
	}
	public String getTeam1() {
		return team1;
	}
	public void setTeam1(String team1) {
		this.team1 = team1;
	}
	public String getTeam2() {
		return team2;
	}
	public void setTeam2(String team2) {
		this.team2 = team2;
	}
	public String getTossWinner() {
		return tossWinner;
	}
	public void setTossWinner(String tossWinner) {
		this.tossWinner = tossWinner;
	}
	public String getTossDecision() {
		return tossDecision;
	}
	public void setTossDecision(String tossDecision) {
		this.tossDecision = tossDecision;
	}
	public String getMatchWinner() {
		return matchWinner;
	}
	public void setMatchWinner(String matchWinner) {
		this.matchWinner = matchWinner;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public Match(String id, String city, String date, String playerOfMatch, String venue, String team1, String team2,
			String tossWinner, String tossDecision, String matchWinner, String result, String resultMargin,
			String umpire1, String umpire2) {
		this.id = id;
		this.city = city;
		this.date = date;
		this.playerOfMatch = playerOfMatch;
		this.venue = venue;
		this.team1 = team1;
		this.team2 = team2;
		this.tossWinner = tossWinner;
		this.tossDecision = tossDecision;
		this.matchWinner = matchWinner;
		this.result = result;
		this.resultMargin = resultMargin;
		this.umpire1 = umpire1;
		this.umpire2 = umpire2;
	}
	public String getResultMargin() {
		return resultMargin;
	}
	public void setResultMargin(String resultMargin) {
		this.resultMargin = resultMargin;
	}
	public String getUmpire1() {
		return umpire1;
	}
	public void setUmpire1(String umpire1) {
		this.umpire1 = umpire1;
	}
	public String getUmpire2() {
		return umpire2;
	}
	public void setUmpire2(String umpire2) {
		this.umpire2 = umpire2;
	}
    
}
