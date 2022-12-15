public class Enigma {
    private Rotor innerRotor;
    private Rotor middleRotor;
    private Rotor outerRotor;

    private static final String[] initRotors = {"#GNUAHOVBIPWCJQXDKRYELSZFMT", "#EJOTYCHMRWAFKPUZDINSXBGLQV", "#BDFHJLNPRTVXZACEGIKMOQSUWY", "#NWDKHGXZVRIFJBLMAOPSCYUTQE", "#TGOWHLIFMCSZYRVXQABUPEJKND"};

    // Constructor: generate rotors based on given IDs and initial positions
    public Enigma(int innerRotor, int middleRotor, int outerRotor, String init) {
        char innerStart = init.charAt(0);
        this.innerRotor = new Rotor(initRotors[innerRotor - 1], innerStart);

        char middleStart = init.charAt(1);
        this.middleRotor = new Rotor(initRotors[middleRotor - 1], middleStart);

        char outerStart = init.charAt(2);
        this.outerRotor = new Rotor(initRotors[outerRotor - 1], outerStart);
    }

    public String encrypt(String message) {
        String encrypted = "";
        for (int i = 0; i < message.length(); i++) {
            char c = message.charAt(i);
            int innerIndex = this.innerRotor.getIndex(c); // get index of character in inner rotor
            char outerChar = this.outerRotor.getChar(innerIndex); // get character at that index in outer rotor
            int middleIndex = this.middleRotor.getIndex(outerChar); // get index of that character in middle rotor
            encrypted += this.outerRotor.getChar(middleIndex); // add character to encrypted message

            this.innerRotor.rotateClockwise(); // rotate inner rotor
            // Rotate middle rotor if inner rotor is back to its original position
            if (this.innerRotor.firstChar == this.innerRotor.getChar(0)) {
                this.middleRotor.rotateClockwise();
            }
        }
        return encrypted;
    }

    public String decrypt(String message) {
        String decrypted = "";
        for (int i = 0; i < message.length(); i++) {
            char c = message.charAt(i);

            int outerIndex = this.outerRotor.getIndex(c); // get index of character in outer rotor
            char middleChar = this.middleRotor.getChar(outerIndex); // get character at that index in middle rotor
            int outerIndex2 = this.outerRotor.getIndex(middleChar); // get index of that character in outer rotor
            decrypted += this.innerRotor.getChar(outerIndex2); // add character to decrypted message

            this.innerRotor.rotateClockwise(); // rotate inner rotor
            // Rotate middle rotor if inner rotor is back to its original position
            if (this.innerRotor.firstChar == this.innerRotor.getChar(0)) {
                this.middleRotor.rotateClockwise();
            }
        }

        return decrypted;
    }
}