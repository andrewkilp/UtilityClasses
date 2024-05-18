import java.util.InputMismatchException;
import java.util.Scanner;
public class ColorUtil {
    /**
     * this is the way to just use it in the terminal
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        boolean valid = true;
        do {
            System.out.println("INT to decode an int to a hex Code, RGB to decode to INT value and HEX code, HEX to decode into a RGB value and a INT value, or END to stop");
            try {
                switch (in.next()) {
                    case "INT" -> {
                        System.out.println("input an int value");
                        int value = in.nextInt();
                        byte byte1 = (byte) ((value >> 16) & 0xFF);
                        byte byte2 = (byte) ((value >> 8) & 0xFF);
                        byte byte3 = (byte) (value & 0xFF);
                        System.out.printf("RGB Value: %02X red, %02X green, %02X blue%n", byte1, byte2, byte3);
                        System.out.printf("Hexcode: %02X%02X%02X%n", byte1, byte2, byte3);
                    } 
                    case "RGB" -> {
                        System.out.println("Input red value");
                        int red = in.nextInt();
                        if(red >= 255)
                            throw new InputMismatchException();
                        System.out.println("Input green value");
                        int green = in.nextInt();
                        if(green >= 255)
                            throw new InputMismatchException();
                        System.out.println("Input blue value");
                        int blue = in.nextInt();
                        if(blue >= 255)
                            throw new InputMismatchException();
                        String hexcode = String.format("%02X%02X%02X%n", red,green,blue);
                        System.out.printf("Hexcode: %s", hexcode);
                        System.out.printf("INT value:%d%n", Integer.decode("0x"+hexcode));     
                    } 
                    case "HEX" -> {
                        System.out.println("input an hex value");
                        String value = in.next();
                        if(value.length()==6) {
                            int val = Integer.decode("0x"+value);
                            System.out.printf("INT value: %d%n", val);
                            System.out.printf("RGB value: red %d, green %d, blue %d %n", ((val >> 16) & 0xFF), ((val >> 8) & 0xFF), (val & 0xFF));
                        } else {
                            throw new InputMismatchException();
                        }
                    } 
                    case "END" -> {
                        valid = false;
                    }
                }
            }
            catch(InputMismatchException ex) {
                System.out.println("bad Input");
            }
        } while (valid);
        
        in.close();
    }
    /**
     * takes an int value in the range of 0 - 255^3 and returns the hexcode without a # or 0x
     * @param value
     * @return
     */
    public static String decodeIntToHex(int value) {
        byte byte1 = (byte) ((value >> 16) & 0xFF);
        byte byte2 = (byte) ((value >> 8) & 0xFF);
        byte byte3 = (byte) (value & 0xFF);
        return String.format("%02X%02X%02X", byte1,byte2,byte3);
    }
    /**
     * takes an int array of red green blue values and returns the hexcode without a # or 0x
     * @param rgb
     * @return
     */
    public static String decodeRGBToHex(int[] rgb) throws InputMismatchException {
        if(rgb.length > 3)
            throw new InputMismatchException();
        for(int i = 0 ; i<rgb.length;i++) { // checks if valid array
            if(rgb[i] >255)
                throw new InputMismatchException();
        }
        return String.format("%02X%02X%02X", rgb[0],rgb[1],rgb[2]);
    }
    /**
     * takes a hexcode without 0x or # as a prefix and returns the int value
     * @param hexcode
     * @return
     */
    public static int decodeHexToInt(String hexcode) {
        return Integer.decode("0x"+hexcode);
    }
    /**
     * takes a int[] where values are less than 256 and returns an int value
     * @param rgb
     * @return
     */
    public static int decodeRGBToInt(int[] rgb) throws InputMismatchException{
        if(rgb.length > 3)
            throw new InputMismatchException("array is to long");
        for(int i = 0 ; i<rgb.length;i++) {
            if(rgb[i] >255)
                throw new InputMismatchException("index" + i + "is greater than 255");
        }
        return  Integer.decode(String.format("%02X%02X%02X%n", rgb[0],rgb[1],rgb[2]));
    }
    /**
     * takes an int value and returns a int array of rgb values
     * @param value
     * @return
     */
    public static int[] decodeIntToRGB(int value) {
        return new int[] {(byte) ((value >> 16) & 0xFF), (byte) ((value >> 8) & 0xFF), (byte) (value & 0xFF)};
    }
    /**
     * takes a hexcode without 0x or # as a prefix and returns the rgb values in an int[]
     * @param hexcode
     * @return
     */
    public static int[] decodeHexToRGB(String hexcode) {
        int value = Integer.decode(hexcode);
        return new int[] {(byte) ((value >> 16) & 0xFF), (byte) ((value >> 8) & 0xFF), (byte) (value & 0xFF)};
    }
}
