package AES;

public class application {

    public static void main(String[] args) throws Exception {

        try {
            String plainText = "AES256 테스트를 위한 PlainText";
            String encText = AES256.encrypt(plainText);
            String decText = AES256.decrypt(encText);

            System.out.println("plainText = " + plainText);
            System.out.println("encText = " + encText);
            System.out.println("decText = " + decText);

        } catch (Exception e) {
            System.out.println("Exception 발생 " + e);
            throw e;
        }
    }
}
