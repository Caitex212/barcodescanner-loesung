//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    private final String STX = "\u0002";
    private final String LF = "\n";
    private final String ACK = "\u0006";
    private final String NAK = "\u0015";

    private Serial serial;

    public Main() {
        serial = new Serial("COM1", 9600, 8, 1, 0);
    }

    public String scanneBarcode() {
        if (!serial.open()) {
            return null;
        };
        String barcode = null;
        boolean pruefe = false;
        while(!pruefe) {
            serial.setRTS(true);
            serial.write(STX + "READ" + LF);

            String data = serial.readLine();
            barcode = data.trim();
            if(!pruefeBarcode(barcode)) {
                serial.write(NAK);
            } else {
                serial.write(ACK);
                pruefe = true;
            }
        }
        serial.close();
        return barcode;
    }

    private boolean pruefeBarcode(String barcode) {
        return true;
    }
}