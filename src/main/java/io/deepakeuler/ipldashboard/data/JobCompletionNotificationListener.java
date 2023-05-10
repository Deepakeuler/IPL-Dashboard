package io.deepakeuler.ipldashboard.data;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import io.deepakeuler.ipldashboard.model.Team;
import jakarta.persistence.EntityManager;

@Component
public class JobCompletionNotificationListener implements JobExecutionListener {

  private final EntityManager em;

  private static final Logger log = LoggerFactory.getLogger(JobCompletionNotificationListener.class);

  @Autowired
  public JobCompletionNotificationListener(EntityManager em) {
    this.em = em;
  }

  @Override
  @Transactional
  public void afterJob(JobExecution jobExecution) {
    if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
      log.info("!!! JOB FINISHED! Time to verify the results");

      HashMap<String, Team> teamData = new HashMap<>();

      // Get team name and the total number of matches the team has played
      em.createQuery("select m.team1, count(*) from Match m group by m.team1", Object[].class)
          .getResultList()
          .stream()
          .map(e -> new Team((String) e[0], (long) e[1]))
          .forEach(team -> teamData.put(team.getTeamName(), team));

      em.createQuery("select m.team2, count(*) from Match m group by m.team2", Object[].class)
          .getResultList()
          .stream()
          .forEach(e -> {
            String teamName = (String) e[0];
            long matchesPlayed = (long) e[1];
            Team team = teamData.getOrDefault(teamName, new Team(teamName, 0L));
            team.setTotalMatches(team.getTotalMatches() + matchesPlayed);
            teamData.put(teamName, team);
          });

      em.createQuery("select m.matchWinner, count(*) from Match m group by m.matchWinner", Object[].class)
          .getResultList()
          .stream()
          .forEach(e -> {
            String teamName = (String) e[0];
            long totalWins = (long) e[1];
            Team team = teamData.get(teamName);
            if (team != null) {
              team.setTotalWins(totalWins);
            } else {
              log.error("Team not found: {}", teamName);
            }
          });

      teamData.values().forEach(team -> em.persist(team));
      teamData.values().forEach(team -> System.out.println(team));
    }
  }
}
