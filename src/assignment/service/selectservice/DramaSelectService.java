package assignment.service.selectservice;

import assignment.Drama;

import java.util.ArrayList;
import java.util.List;

public class DramaSelectService {

  public static final String DRM_CODE = "drm_code";
  public static final String DRM_NAME = "drm_name";
  public static final String DRM_PRD = "drm_prd";
  public static final String DRM_BRD = "drm_brd";
  public static final String DRM_OPDATE = "drm_opdate";
  public static final String ALL = "*";

  private SelectService selectService = new SelectService("drama");

  public void printByProducer(String name) {
    String where = DRM_PRD + " like " + "'%" + name + "%'";
    selectService.includeWhere(resultSet -> {
      try {
        while (resultSet.next()) {
          System.out.println("[" + DRM_CODE + "] " + resultSet.getString(DRM_CODE));
          System.out.println("[" + DRM_NAME + "] " + resultSet.getString(DRM_NAME));
          System.out.println("-----------------------------------------------");
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }, where, DRM_CODE, DRM_NAME);
  }

  public void printByBroadcastStation(String... stations) {
    String station = String.join("' , '", stations);
    String where = DRM_BRD + " in " + "('" + station + "')";
    selectService.includeWhere(resultSet -> {
      try {
        List<Drama> dramas = new ArrayList<>();
        while (resultSet.next()) {
          dramas.add(new Drama(resultSet.getString(DRM_CODE),
              resultSet.getString(DRM_NAME),
              resultSet.getString(DRM_PRD),
              resultSet.getString(DRM_BRD), resultSet.getDate(DRM_OPDATE)));
        }
        dramas.forEach(System.out::println);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }, where, ALL);
  }

  public void printGroupBy(String groupBy) {
    selectService.groupBy(resultSet -> {
      try {
        while (resultSet.next()) {
          System.out.println("[" + groupBy + "] " + resultSet.getString(groupBy));
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }, groupBy);
  }

  public void printIsNull(String target, String... selectList) {
    String where = target + " is null";
    selectService.includeWhere(resultSet -> {
      try {
        while (resultSet.next()) {
          for (String str : selectList) {
            System.out.println("[" + str + "] " + resultSet.getString(str));
          }
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }, where, selectList);
  }


}
