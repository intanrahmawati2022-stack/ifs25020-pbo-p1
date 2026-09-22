import java.util.*;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Integer> list = new ArrayList<>();

        while (scanner.hasNext()) {
            String token = scanner.next();
            if (token.equals("---")) break;
            list.add(Integer.parseInt(token));
        }

        // Jika input langsung --- (tanpa nilai), program tidak menampilkan apa pun.
        if (list.isEmpty()) return;

        int tertinggi = Collections.max(list);
        int terendah = Collections.min(list);

        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int val : list) {
            freqMap.put(val, freqMap.getOrDefault(val, 0) + 1);
        }

        int terbanyakVal = list.get(0);
        int maxFreq = -1;
        int tersedikitVal = list.get(0);
        int minFreq = Integer.MAX_VALUE;

        long maxJumlah = Long.MIN_VALUE;
        int jumlahTertinggiVal = list.get(0);
        long minJumlah = Long.MAX_VALUE;
        int jumlahTerendahVal = list.get(0);

        for (Map.Entry<Integer, Integer> entry : freqMap.entrySet()) {
            int val = entry.getKey();
            int freq = entry.getValue();
            long jumlah = (long) val * freq;

            // Terbanyak: frekuensi tertinggi; jika seri, pilih nilai lebih besar
            if (freq > maxFreq) {
                maxFreq = freq;
                terbanyakVal = val;
            } else if (freq == maxFreq) {
                if (val > terbanyakVal) terbanyakVal = val;
            }

            // Tersedikit: frekuensi terendah; jika seri, pilih nilai lebih kecil
            if (freq < minFreq) {
                minFreq = freq;
                tersedikitVal = val;
            } else if (freq == minFreq) {
                if (val < tersedikitVal) tersedikitVal = val;
            }

            // Jumlah Tertinggi: hasil (nilai * frekuensi) terbesar; jika seri, pilih nilai lebih besar
            if (jumlah > maxJumlah) {
                maxJumlah = jumlah;
                jumlahTertinggiVal = val;
            } else if (jumlah == maxJumlah) {
                if (val > jumlahTertinggiVal) jumlahTertinggiVal = val;
            }

            // Jumlah Terendah: hasil (nilai * frekuensi) terkecil; jika seri, pilih nilai lebih kecil
            if (jumlah < minJumlah) {
                minJumlah = jumlah;
                jumlahTerendahVal = val;
            } else if (jumlah == minJumlah) {
                if (val < jumlahTerendahVal) jumlahTerendahVal = val;
            }
        }

        // Menampilkan Output Sesuai Format
        System.out.println("Tertinggi: " + tertinggi);
        System.out.println("Terendah: " + terendah);
        System.out.println("Terbanyak: " + terbanyakVal + " (" + maxFreq + "x)");
        System.out.println("Tersedikit: " + tersedikitVal + " (" + minFreq + "x)");
        System.out.println("Jumlah Tertinggi: " + jumlahTertinggiVal + " * " + freqMap.get(jumlahTertinggiVal) + " = " + maxJumlah);
        System.out.println("Jumlah Terendah: " + jumlahTerendahVal + " * " + freqMap.get(jumlahTerendahVal) + " = " + minJumlah);
    }
}