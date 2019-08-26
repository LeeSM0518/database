package tutorial;

import java.sql.Date;

public class Main {

  public static void main(String[] args) {
    StudentSelectService selectStudent = new StudentSelectService();

    System.out.println("전체 학생을 검색합니다.");
    selectStudent.getStudentAll();
    System.out.println();

    System.out.println("학번 20110101 학생을 검색합니다.");
    int studentNo = 20110101;

    selectStudent.getStudentByNo(studentNo);
    System.out.println();

    System.out.println("이름 일지매 학생을 검색합니다.");
    String studentName = "일지매";

    selectStudent.getStudentByName(studentName);
    System.out.println();

    System.out.println("생일 1990-03-01 학생을 검색합니다.");
    String studentBirthday = "1990-03-01";

    selectStudent.getStudentByBirthday(studentBirthday);
    System.out.println();

    Student student = new Student();
    Student[] students = new Student[8];

    for (int i = 0; i < students.length; i++) {
      students[i] = new Student();
    }

    StudentInsertService insertService = new StudentInsertService();
    System.out.println("학번이 20110401이고, 이름이 이순신인 학생을 추가한다.");
    student.setId(20110401);
    student.setName("이순신");
    insertService.insertStudent(student);
    System.out.println();

    System.out.println("학번이 20110501이고, 이름이 이율곡인 학생을 추가한다.");
    System.out.println("학번이 20110601이고, 이름이 이수일인 학생을 추가한다.");
    System.out.println("학번이 20110701이고, 이름이 김순애인 학생을 추가한다.");
    System.out.println("학번이 20110801이고, 이름이 임꺽정인 학생을 추가한다,");
    students[0].setId(20110501);
    students[0].setName("이율곡");
    students[1].setId(20110601);
    students[1].setName("이수일");
    students[2].setId(20110701);
    students[2].setName("김순애");
    students[3].setId(20110801);
    students[3].setName("임꺽정");
    insertService.insertStudentMulti(students);
    System.out.println();

    System.out.println("학번이 20110901이고, 이름이 이상훈인 학생을 추가한다.");
    System.out.println("학번이 20111001이고, 이름이 강동희인 학생을 추가한다.");
    System.out.println("학번이 20111101이고, 이름이 김호성인 학생을 추가한다.");
    System.out.println("학번이 20111201이고, 이름이 김정준인 학생을 추가한다,");
    students[0].setId(20110901);
    students[1].setId(20111001);
    students[2].setId(20111101);
    students[3].setId(20111201);
    students[0].setName("이상훈");
    students[1].setName("강동희");
    students[2].setName("김호성");
    students[3].setName("김정준");
    insertService.insertStudentMultiBatch(students);

    StudentUpdateService updateService = new StudentUpdateService();

    System.out.println("학번이 20110401인 학생의 생일을 1990-03-21으로 변경합니다.");
    updateService.updateStudentBirthday(20110401, "1990-03-21");
    System.out.println();

    System.out.println("학번이 20110501인 학생의 생일을 1990-03-01으로 변경합니다.");
    System.out.println("학번이 20110601인 학생의 생일을 1990-04-01으로 변경합니다.");
    System.out.println("학번이 20110701인 학생의 생일을 1990-05-01으로 변경합니다.");
    System.out.println("학번이 20110801인 학생의 생일을 1990-06-01으로 변경합니다.");
    System.out.println("학번이 20110901인 학생의 생일을 1990-07-01으로 변경합니다.");
    System.out.println("학번이 20111001인 학생의 생일을 1990-08-01으로 변경합니다.");
    System.out.println("학번이 20111101인 학생의 생일을 1990-09-01으로 변경합니다.");
    System.out.println("학번이 20111201인 학생의 생일을 1990-10-01으로 변경합니다.");

    students[0].setId(20110501);
    students[1].setId(20110601);
    students[2].setId(20110701);
    students[3].setId(20110801);
    students[4].setId(20110901);
    students[5].setId(20111001);
    students[6].setId(20111101);
    students[7].setId(20111201);
    students[0].setBirthday(Date.valueOf("1990-03-01"));
    students[0].setBirthday(Date.valueOf("1990-04-01"));
    students[0].setBirthday(Date.valueOf("1990-05-01"));
    students[0].setBirthday(Date.valueOf("1990-06-01"));
    students[0].setBirthday(Date.valueOf("1990-07-01"));
    students[0].setBirthday(Date.valueOf("1990-08-01"));
    students[0].setBirthday(Date.valueOf("1990-09-01"));
    students[0].setBirthday(Date.valueOf("1990-10-01"));

    updateService.updateStudentBirthdayMultiBatch(students);
    System.out.println();

    selectStudent.getStudentAll();

    StudentDeleteService deleteService = new StudentDeleteService();

    System.out.println("학번이 20110401인 학생을 목록에서 제거합니다.");
    // TODO deleteStudentId 구현
    deleteService.deleteStudentId(20110401);
    System.out.println();

    selectStudent.getStudentAll();

    System.out.println("학번이 20110501인 학생을 목록에서 제거합니다.");
    System.out.println("학번이 20110601인 학생을 목록에서 제거합니다.");
    System.out.println("학번이 20110701인 학생을 목록에서 제거합니다.");
    System.out.println("학번이 20110801인 학생을 목록에서 제거합니다.");
    System.out.println("학번이 20110901인 학생을 목록에서 제거합니다.");
    System.out.println("학번이 20111001인 학생을 목록에서 제거합니다.");
    System.out.println("학번이 20111101인 학생을 목록에서 제거합니다.");
    System.out.println("학번이 20111201인 학생을 목록에서 제거합니다.");

    students[0].setId(20110501);
    students[1].setId(20110601);
    students[2].setId(20110701);
    students[3].setId(20110801);
    students[4].setId(20110901);
    students[5].setId(20111001);
    students[6].setId(20111101);
    students[7].setId(20111201);

    // TODO deleteStudentIdMultiBatch 구현
    deleteService.deleteStudentIdMultiBatch(students);
    System.out.println();

    selectStudent.getStudentAll();
  }

}
