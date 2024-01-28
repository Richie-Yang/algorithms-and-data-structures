import java.util.ArrayList;

public class TestTimeToInformEmployee {
  static public void main(String[] args) {
    TimeToInformEmployee tte = new TimeToInformEmployee();
    tte.testCase();
  }
}

class TimeToInformEmployee {
  Integer result;
  TimeToInformEmployee() {

  }

  public void exec(Integer n, Integer headId, Integer[] managers, Integer[] informTime) {
    /**
     * 1. find the time for headId in informTime array
     * 2. find all subManger for headId in managers array
     * 3. get all inform time from subManager in informTime array
     */
    this.result = 0;
    ArrayList<Integer> records = new ArrayList<>();
    traverse(headId, managers, informTime, 0, records);
    System.out.printf("%s\n", this.result);
  }

  private void traverse(Integer employeeId, Integer[] managers, Integer[] informTime, Integer minute, ArrayList<Integer> records) {
    if (informTime[employeeId] == 0) {
      if (this.result < minute) this.result = minute;
      return;
    }
    minute = minute + informTime[employeeId];
    for (Integer i = 0; i < managers.length; i++) {
      Integer managerId = managers[i];
      Integer subEmployeeId = i;
      if (employeeId == managerId && !records.contains(subEmployeeId)) {
        records.add(subEmployeeId);
        traverse(subEmployeeId, managers, informTime, minute, records);
      }
    }
  }

  public void testCase() {
    Integer n = 8;
    Integer headId = 4;
    Integer[] managers = new Integer[]{2,2,4,6,-1,4,4,5};
    Integer[] informTime = new Integer[]{0,0,4,0,7,3,6,0};
    /**
     * 4(7m)  -> 2(4m) -> 0 (0m)
     *                 -> 1 (0m)
     *        -> 5(3m) -> 7 (0m)
     *        -> 6(6m) -> 3 (0m)
     * total: 13m
     */
    this.exec(n, headId, managers, informTime);
  }
}