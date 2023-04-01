import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Password {
    private final String REPEAT_CHAR = "*";
    private final int MIN_RANDOM_NUM = 1;
    private final int MAX_RANDOM_NUM = 20;


    private String oldPassword;
    private String newPassword;

    public Password(String password) {
        this.oldPassword = "";

        if (!setPassword(password)) {
            setPassword("3Wa1W1@gCV");
        }
    }

    public Password() {
        this.oldPassword = "";
    }

    @Override
    public String toString() {
        return this.newPassword.charAt(0) +
                REPEAT_CHAR.repeat(new Random().nextInt((MAX_RANDOM_NUM + 1 - MIN_RANDOM_NUM)) + MIN_RANDOM_NUM) +
                this.newPassword.charAt(this.newPassword.length() - 1);
    }

    public boolean equals(Password password) {
        return this.newPassword.equals(password.newPassword);
    }

    public String getPassword() {
        return newPassword;
    }

    public boolean setPassword(final String password) {
        if (this.havingNum(password) == 0) {
            System.out.println( "Your password havingNum = 0");
            return false;
        }

        if (this.newPasswordEqualsOld(this.oldPassword, password)) {
            System.out.println( "Your newPasswordEqualsOld");
            return false;
        }

        if (!this.isSufficienlyDifferent(this.oldPassword, password, this.oldPassword.length()/2)) {
            System.out.println( "Your password notIsSufficienlyDifferent");
            return false;
        }

        this.oldPassword = this.newPassword;
        this.newPassword = password;
        return true;
    }

    private int havingNum(final String password) {
        int count = 0;

        for (int i = 0; i < password.length(); i++) {
            if (((int) password.charAt(i) >= 48) && ((int) password.charAt(i) <= 57)) {
                count++;
            }
        }

        return count;
    }

    private boolean newPasswordEqualsOld(final String firstPassword, final String secondPassword) {
        if (firstPassword.length() != secondPassword.length()) {
            return false;
        }

        for (int i = 0; i < secondPassword.length(); i++) {
            if (firstPassword.charAt(i) != secondPassword.charAt(i)) {
                return false;
            }
        }

        return true;
    }

    private List<Integer> getCountNotEqualsPlaces(final String firstPassword, final String secondPassword) {
        List<Integer> array = new ArrayList<>();

        int min = Math.min(firstPassword.length(), secondPassword.length());
        int max = Math.max(firstPassword.length(), secondPassword.length());

        for (int i = 0; i < max; i++) {
            array.add(i);
        }

        for (int i = 0; i < min; i++) {
            if (firstPassword.charAt(i) == secondPassword.charAt(i)) {
                array.remove(i);
            }
        }

        return array;
    }

    private boolean isSufficienlyDifferent(String firstPassword, String secondPassword, int countDistinct) {
        return getCountNotEqualsPlaces(firstPassword, secondPassword).size() > countDistinct;
    }
}
