package assignment;

import assignment.service.*;
import assignment.service.selectservice.DepartmentSelectService;
import assignment.service.selectservice.DramaSelectService;
import assignment.service.selectservice.EmployeeSelectService;
import assignment.service.updateservice.DramaUpdateService;
import assignment.service.updateservice.EmployeeUpdateService;

import java.util.Collections;

public class Main {

  public static void main(String[] args) {
    DepartmentSelectService departmentSelectService = new DepartmentSelectService();

    DramaSelectService dramaSelectService = new DramaSelectService();
    DramaUpdateService dramaUpdateService = new DramaUpdateService();

    EmployeeSelectService employeeSelectService = new EmployeeSelectService();
    EmployeeUpdateService employeeUpdateService = new EmployeeUpdateService();
    EmployeeInsertService employeeInsertService = new EmployeeInsertService();
    EmployeeDeleteService employeeDeleteService = new EmployeeDeleteService();

    System.out.println("1. HNU Entertainment의 부서 코드, 이름 위치를 검색하시오.");
    departmentSelectService.printAll();
    System.out.println();

    System.out.println("2. HNU Entertainment의 연예관계자 코드, 이름, 관리자, 급여를 검색하시오");
    employeeSelectService.printNotAll(
        EmployeeSelectService.EMP_CODE,
        EmployeeSelectService.EMP_NAME,
        EmployeeSelectService.EMP_MGT,
        EmployeeSelectService.EMP_SAL);
    System.out.println();

    System.out.println("3. HNU Entertainment에서 제작한 드라마의 코드와 이름을 검색하시오.");
    dramaSelectService.printByProducer("HNU");
    System.out.println();

    System.out.println("4. 드라마 방영사가 KBC이거나 SBC인 드라마를 검색하시오.");
    dramaSelectService.printByBroadcastStation("KBC", "SBC");
    System.out.println();

    System.out.println("5. 드라마 제작사를 검색하시오. 단, 중복된 값이 있으면 제거하시오.");
    dramaSelectService.printGroupBy(DramaSelectService.DRM_PRD);
    System.out.println();

    System.out.println("6. 연예관계자들의 급여의 총합과 평균 급여액을 계산하시오.");
    employeeSelectService.printSumAndAvg(EmployeeSelectService.EMP_SAL);
    System.out.println();

    System.out.println("7. 방영일자가 아직 확정되지 않은 드라마의 이름을 검색하시오.");
    dramaSelectService.printIsNull(DramaSelectService.DRM_OPDATE, DramaSelectService.DRM_NAME);
    System.out.println();

    System.out.println("8. 연예관계자에 대해 연예관계자의 이름과 직속 상사의 이름을 검색하시오.");
    employeeSelectService.printNameAndManager();
    System.out.println();

    System.out.println("9. 연예관계자에 대해 이름과 급여를 출력하고, " +
        "급여의 내림차순으로 정렬하시오. 단, 동일 급여일 때는 이름의 오름차순으로 정렬하시오.");
    employeeSelectService.printOrderBySalaryAndName();
    System.out.println();

    System.out.println("10. 모든 연예관계자를 직급별로 그룹화하고, 평균 급여액이 5000 이상인 직급에 대해 " +
        "연예 관계자의 직급, 평균 급여액, 최소 급여액, 최대 급여액을 검색하시오.");
    employeeSelectService.printEmployeeBySalary();
    System.out.println();

    System.out.println("11. 모든 연예관계자의 평균 급여액보다 많은 급여를 받는 연예관계자의 이름과 급여를 검색하라.");
    employeeSelectService.printByOverAvgSalary();
    System.out.println();

    System.out.println("12. 방영일자가 확정되지 않은 드라마의 방영일자가 " +
        "2013-05-01로 편성되었습니다. 알맞게 변경하시오.");
    dramaUpdateService.updateDramaOpdate("2013-05-01");
    System.out.println();

    System.out.println("13. 연예관계자 김수현 씨가 대리에서 실장으로 " +
        "승진하고 급여가 20% 증가되었습니다. 알맞게 변경하시오.");
    employeeUpdateService.updateSalaryAndRole("김수현", "실장", 1.2);
    System.out.println();

    System.out.println("14. 우리 회사에 한 명의 임원이 등록되었습니다. 코드는 E903, 이름은 손진현, " +
        "관리자는 E901, 급여는 4000입니다. 알맞게 등록하시오.");
    employeeInsertService.insertEmployees(Collections.singletonList(
        new Employee("E903", "손진현", "E901", 4000, "R005")
    ));
    System.out.println();

    System.out.println("15. 연예관계자인 손진현님이 퇴직했습니다. 연예관계자 목록에서 제거하시오.");
    employeeDeleteService.deleteEmployee("손진현");
    System.out.println();
  }

}
