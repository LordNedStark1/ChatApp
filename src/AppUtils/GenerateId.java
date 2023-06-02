package AppUtils;

import java.security.SecureRandom;

public class GenerateId {
    private static final SecureRandom random = new SecureRandom();
    private static long idCounter;
    public static final String generateId(){
        StringBuilder a_z = new StringBuilder("abcdefghijklmnopqrstuvwxyz");
        StringBuilder combined = new StringBuilder();

        idCounter ++;

        String number = Integer.toString( 999 + random.nextInt(9999));
        combined.append(number);
        String number2 = Integer.toString( 1 + random.nextInt(1000));
        combined.append(number2);

        combined.append(idCounter);

        combined.append(a_z.charAt(1 + random.nextInt(25)));
        combined.append(a_z.charAt(1 + random.nextInt(25)));

        return combined.toString();
    }
}
