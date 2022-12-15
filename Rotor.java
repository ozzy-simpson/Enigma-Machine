public class Rotor {
    private String value;
    public char firstChar;

    // Constructor
    public Rotor(String value, char firstChar) {
        this.value = value;
        this.firstChar = firstChar;

        // Rotate until the 0th character in the rotor is the given first char
        while (this.value.charAt(0) != firstChar) {
            this.rotateClockwise();
        }
    }

    // Given index, returns character at that index
    public char getChar(int index) {
        return this.value.charAt(index);
    }

    // Given character, returns index of that character
    public int getIndex(char c) {
        return this.value.indexOf(c);
    }

    // Rotates the rotor clockwise
    public void rotateClockwise() {
        this.value = this.value.substring(this.value.length() - 1) + this.value.substring(0, this.value.length() - 1); // put last character first, then add the rest of the string
    }
}