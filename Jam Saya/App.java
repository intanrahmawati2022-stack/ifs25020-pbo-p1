import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Membaca jam awal
        if (!scanner.hasNextLine()) return;
        String initialTimeStr = scanner.nextLine().trim();

        // Validasi format pemisahan jam dan menit (harus tepat 2 bagian)
        String[] timeParts = initialTimeStr.split(":");
        if (timeParts.length != 2) {
            System.out.println("Jam tidak valid");
            return;
        }

        int h, m;
        try {
            h = Integer.parseInt(timeParts[0].trim());
            m = Integer.parseInt(timeParts[1].trim());
        } catch (NumberFormatException e) {
            System.out.println("Jam tidak valid");
            return;
        }

        // Validasi rentang jam (0-23) dan menit (0-59)
        if (h < 0 || h > 23 || m < 0 || m > 59) {
            System.out.println("Jam tidak valid");
            return;
        }

        int currentMinutes = h * 60 + m;
        int totalGeser = 0;
        int pergantianHari = 0;

        // Membaca perintah geser baris per baris hingga "---"
        while (scanner.hasNextLine()) {
            String cmd = scanner.nextLine().trim();
            if (cmd.equals("---")) break;
            if (cmd.isEmpty()) continue;

            // Validasi format perintah harus berawalan '+' atau '-' diikuti angka
            if (!cmd.matches("^[+-]\\d+$")) {
                System.out.println("Perintah tidak valid");
                continue;
            }

            int n = Integer.parseInt(cmd);
            totalGeser += n;
            currentMinutes += n;

            // Normalisasi dan perhitungan pergantian hari (maju atau mundur melewati tengah malam)
            while (currentMinutes >= 1440) {
                currentMinutes -= 1440;
                pergantianHari++;
            }
            while (currentMinutes < 0) {
                currentMinutes += 1440;
                pergantianHari++;
            }
        }

        // Konversi kembali dari total menit ke jam dan menit format 2 digit (%02d)
        int finalH = currentMinutes / 60;
        int finalM = currentMinutes % 60;

        // Menampilkan hasil akhir
        System.out.println("Jam Awal: " + String.format("%02d:%02d", h, m));
        System.out.println("Jam Akhir: " + String.format("%02d:%02d", finalH, finalM));
        
        // Aturan tanda pada total menit: '+' jika positif, kosong/0 jika nol, '-' jika negatif
        String sign = (totalGeser > 0) ? "+" : "";
        System.out.println("Total Menit: " + sign + totalGeser);
        System.out.println("Pergantian Hari: " + pergantianHari);
    }
}