package io.deepakeuler.ipldashboard.data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.batch.item.ItemProcessor;

import io.deepakeuler.ipldashboard.model.Match;

public class MatchDataProcessor implements ItemProcessor<MatchInput, Match> {

  private static final Logger log = LoggerFactory.getLogger(MatchDataProcessor.class);

  private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

  @Override
  public Match process(final MatchInput matchInput) throws Exception {

    Match match = new Match();

    // match.setId(Long.parseLong("matchInput.getId()"));

    try {
      match.setId(Long.parseLong(matchInput.getId()));
    } catch (NumberFormatException e) {
      // Handle the parsing error gracefully
      match.setId(0L); // or any default value that makes sense for your application
    }

    match.setCity(matchInput.getCity());
    
    try {
      match.setDate(LocalDate.parse(matchInput.getDate(), dateFormatter));
  } catch (Exception e) {
      // Handle the parsing error gracefully or throw an exception
      // For example, set a default date or log an error
      match.setDate(LocalDate.now()); // Set default date to current date
  }

    match.setPlayerOfMatch(matchInput.getPlayer_of_match());
    match.setVenue(matchInput.getVenue());

    String firstInningsTeam, secondInningsTeam;

    // set team one and team 2 according to innings order
    if ("bat".equals(matchInput.getToss_decision())) {
      firstInningsTeam = matchInput.getToss_winner();
      secondInningsTeam = matchInput.getToss_winner().equals(matchInput.getTeam1()) ? matchInput.getTeam2()
          : matchInput.getTeam1();
    } else {
      secondInningsTeam = matchInput.getToss_winner();
      firstInningsTeam = matchInput.getToss_winner().equals(matchInput.getTeam1()) ? matchInput.getTeam2()
          : matchInput.getTeam1();
    }

    match.setTeam1(firstInningsTeam);
    match.setTeam2(secondInningsTeam);

    match.setTossWinner(matchInput.getToss_winner());
    match.setTossDecision(matchInput.getToss_decision());
    match.setMatchWinner(matchInput.getWinner());
    match.setResult(matchInput.getResult());
    match.setResultMargin(matchInput.getResult_margin());
    match.setUmpire1(matchInput.getUmpire1());
    match.setUmpire2(matchInput.getUmpire2());

    log.info("Converting (" + matchInput + ") into (" + match + ")");
    return match;
  }

}
