import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

public class App {

    private static final String[] SIMBOL = {"PA", "T", "K", "P", "UTS", "UAS"};
    private static final String[] NAMA = {"Partisipatif", "Tugas", "Kuis", "Proyek", "UTS", "UAS"};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] bobotAwal = new int[6];
        int totalBobot = 0;
        for (int i = 0; i < 6; i++) {
            bobotAwal[i] = Integer.parseInt(scanner.nextLine().trim());
            totalBobot += bobotAwal[i];
        }

        if (totalBobot != 100) {
            System.out.println("Total bobot harus 100");
            return;
        }

        Map<String, Integer> totalBobotKomponen = new HashMap<>();
        Map<String, Integer> totalPerolehanKomponen = new HashMap<>();
        for (String s : SIMBOL) {
            totalBobotKomponen.put(s, 0);
            totalPerolehanKomponen.put(s, 0);
        }

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.trim().equals("---")) {
                break;
            }

            String[] parts = line.split("\\|", -1);
            if (parts.length != 3) {
                System.out.println("Data tidak valid. Silahkan menggunakan format: Simbol|Bobot|Perolehan-Nilai");
                continue;
            }

            String simbol = parts[0].trim();
            String bobotStr = parts[1].trim();
            String perolehanStr = parts[2].trim();

            int bobot;
            int perolehan;
            try {
                bobot = Integer.parseInt(bobotStr);
                perolehan = Integer.parseInt(perolehanStr);
            } catch (NumberFormatException e) {
                System.out.println("Data tidak valid. Silahkan menggunakan format: Simbol|Bobot|Perolehan-Nilai");
                continue;
            }

            if (!totalBobotKomponen.containsKey(simbol)) {
                System.out.println("Simbol tidak dikenal");
                continue;
            }

            if (perolehan > bobot) {
                perolehan = bobot;
            }
            if (perolehan < 0) {
                perolehan = 0;
            }

            totalBobotKomponen.put(simbol, totalBobotKomponen.get(simbol) + bobot);
            totalPerolehanKomponen.put(simbol, totalPerolehanKomponen.get(simbol) + perolehan);
        }

        System.out.println("Perolehan Nilai:");
        double nilaiAkhir = 0;
        for (int i = 0; i < 6; i++) {
            String simbol = SIMBOL[i];
            int totalX = totalBobotKomponen.get(simbol);
            int perolehanX = totalPerolehanKomponen.get(simbol);
            int bobotOverall = bobotAwal[i];

            int perolehanX100 = totalX == 0 ? 0 : (perolehanX * 100) / totalX;
            double kontribusi = (perolehanX100 / 100.0) * bobotOverall;
            nilaiAkhir += kontribusi;

            System.out.printf(Locale.US, ">> %s: %d/100 (%.2f/%d)%n", NAMA[i], perolehanX100, kontribusi, bobotOverall);
        }

        System.out.println();
        nilaiAkhir = Math.round(nilaiAkhir * 100.0) / 100.0;
        System.out.printf(Locale.US, ">> Nilai Akhir: %.2f%n", nilaiAkhir);
        System.out.println(">> Grade: " + getGrade(nilaiAkhir));
    }

    private static String getGrade(double nilai) {
        if (nilai >= 79.5) return "A";
        if (nilai >= 72) return "AB";
        if (nilai >= 64.5) return "B";
        if (nilai >= 57) return "BC";
        if (nilai >= 49.5) return "C";
        if (nilai >= 34) return "D";
        return "E";
    }
}