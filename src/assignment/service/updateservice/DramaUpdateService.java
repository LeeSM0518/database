package assignment.service.updateservice;

import assignment.service.DatabaseService;

import java.sql.Date;

public class DramaUpdateService {

  private String targetTable = "drama";

  public void updateDramaOpdate(String date) {
    String query = "update " + targetTable + " set drm_opdate = '" + date
        + "' where drm_opdate is null";
    DatabaseService.executeUpdateQuery(query);
  }

}
