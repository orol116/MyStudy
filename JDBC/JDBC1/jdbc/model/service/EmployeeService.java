package edu.kh.jdbc.model.service;

import edu.kh.jdbc.model.dao.EmployeeDAO;
import edu.kh.jdbc.model.vo.Employee;

import java.util.List;


// Service : 요청에 맞는 기능을 수행하여 결과를 제공하는 클래스
// - 전달 받은 데이터 또는 DAO 수행 결과 데이터를 필요한 형태로 가공처리
public class EmployeeService {

    private EmployeeDAO dao = new EmployeeDAO();

    /**
     * 전체 사원 정보 조회 서비스
     * @return
     */
    public List<Employee> selectAll() {

        // 별도 가공할 내용이 없으면 바로 DAO 호출
        List<Employee> empList = dao.selectAll();

        return empList; // DAO 호출 결과를 바로 View로 반환
    }

    /**
     * 사번으로 사원 정보 조회 Service
     * @param input
     * @return emp
     */
    public Employee selectOne(int input) {

        Employee emp = dao.selectOne(input);

        return emp; // DAO 호출 결과를 바로 View로 반환
    }


    /**
     * 입력 급여 이상으로 받는 사원 정보 조회 Service
     * @param input
     * @return empList
     */
    public List<Employee> selectSalary(int input) {

       List<Employee> empList = dao.selectSalary(input);

        return empList;
    }

    /** 새로운 사원 정보 추가 Service
     * @param emp
     * @return
     */
    public int insertEmployee(Employee emp) {

        int result = dao.insertEmployee(emp);

        return result; // INSERT 수행 결과 반환
    }

    /** 사번으로 사원 정보 삭제 Service
     * @param input
     * @return result
     */
    public int deleteEmployee(int input) {

        int result = dao.deleteEmployee(input);

        return result;
    }

    /**
     * 사번으로 사원 정보 수정 Service
     * @param emp
     * @return result
     */
    public int updateEmployee2(Employee emp) {
        int result = dao.updateEmployee(emp);

        return result;
    }

    /**
     * 부서코드, 보너스율을 입력 받아 해당 부서의 보너스를 모두 수정 Service
     * @param emp
     * @return result
     */
    public int updateBonus(Employee emp) {

        int result = dao.updateBonus(emp);

        return result;
    }
}
