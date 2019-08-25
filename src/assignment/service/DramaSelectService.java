package assignment.service;

public class DramaSelectService {

  private static final String DRM_CODE = "drm_code";
  private static final String DRM_NAME = "drm_name";
  private static final String DRM_PRD = "drm_prd";
  private static final String DRM_BRD = "drm_brd";
  private static final String DRM_OPDATE = "drm_opdate";
  private static final String ALL = "*";

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
        while (resultSet.next()) {
          System.out.println("[" + DRM_CODE + "] " + resultSet.getString(DRM_CODE));
          System.out.println("[" + DRM_NAME + "] " + resultSet.getString(DRM_NAME));
          System.out.println("[" + DRM_PRD + "] " + resultSet.getString(DRM_PRD));
          System.out.println("[" + DRM_BRD + "] " + resultSet.getString(DRM_BRD));
          System.out.println("[" + DRM_OPDATE + "] " + resultSet.getString(DRM_OPDATE));
          System.out.println("-----------------------------------------------");
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }, where, ALL);
  }

  public static void main(String[] args) {
    DramaSelectService dramaSelectService = new DramaSelectService();
    dramaSelectService.printByBroadcastStation("KBC", "SBC");
  }

}
