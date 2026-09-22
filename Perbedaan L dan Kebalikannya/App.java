import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        if (!scanner.hasNextInt()) return;
        int n = scanner.nextInt();

        long[][] matrix = new long[n][n];
        long totalSum = 0;
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = scanner.nextLong();
                totalSum += matrix[i][j];
            }
        }

        // Kasus Khusus 1x1
        if (n == 1) {
            System.out.println("Nilai L: Tidak Ada");
            System.out.println("Nilai Kebalikan L: Tidak Ada");
            System.out.println("Nilai Tengah: " + matrix[0][0]);
            System.out.println("Perbedaan: Tidak Ada");
            System.out.println("Dominan: " + matrix[0][0]);
            return;
        }

        // Kasus Khusus 2x2
        if (n == 2) {
            System.out.println("Nilai L: Tidak Ada");
            System.out.println("Nilai Kebalikan L: Tidak Ada");
            System.out.println("Nilai Tengah: " + totalSum);
            System.out.println("Perbedaan: Tidak Ada");
            System.out.println("Dominan: " + totalSum);
            return;
        }

        // Perhitungan Nilai L (Matriks >= 3x3)
        // Seluruh kolom pertama (indeks 0) + seluruh baris terakhir kecuali pojok kanan bawah
        long nilaiL = 0;
        for (int i = 0; i < n; i++) {
            nilaiL += matrix[i][0];
        }
        for (int j = 1; j < n - 1; j++) {
            nilaiL += matrix[n - 1][j];
        }

        // Perhitungan Nilai Kebalikan L (Matriks >= 3x3)
        // Seluruh kolom terakhir (indeks n-1) + seluruh baris pertama kecuali pojok kiri atas
        long nilaiKebalikanL = 0;
        for (int i = 0; i < n; i++) {
            nilaiKebalikanL += matrix[i][n - 1];
        }
        for (int j = 1; j < n - 1; j++) {
            nilaiKebalikanL += matrix[0][j];
        }

        // Perhitungan Nilai Tengah
        long nilaiTengah;
        if (n % 2 != 0) {
            // Ganjil: tepat di tengah
            nilaiTengah = matrix[n / 2][n / 2];
        } else {
            // Genap: jumlah 4 elemen tengah (blok 2x2 di pusat)
            int mid = n / 2;
            nilaiTengah = matrix[mid - 1][mid - 1] + matrix[mid - 1][mid] +
                          matrix[mid][mid - 1] + matrix[mid][mid];
        }

        long perbedaan = Math.abs(nilaiL - nilaiKebalikanL);
        long dominan;
        if (perbedaan == 0) {
            dominan = nilaiTengah;
        } else {
            dominan = Math.max(nilaiL, nilaiKebalikanL);
        }

        // Menampilkan Output Sesuai Format
        System.out.println("Nilai L: " + nilaiL);
        System.out.println("Nilai Kebalikan L: " + nilaiKebalikanL);
        System.out.println("Nilai Tengah: " + nilaiTengah);
        System.out.println("Perbedaan: " + perbedaan);
        System.out.println("Dominan: " + dominan);
    }
}