package mocks;

import Staff.Gender;
import Staff.Worker;

public class WorkerDummy extends Worker {

    public WorkerDummy(String fullName, String phone, String email, String dateOfBirth,
                       float salary, String password, ACCESSLEVEL accessLevel, Gender gender) {
        super(fullName, phone, email, dateOfBirth, salary, password, accessLevel, gender);
    }

    @Override
    public void interact() {
    }
}
