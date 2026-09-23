import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        String nim = input.nextLine().toUpperCase();
        input.close();

        if (nim.length() != 8) {
            System.out.println("NIM harus 8 karakter");
            return;
        }

        String prefixProdi = nim.substring(0, 3);
        String namaProdi = ambilNamaProgramStudi(prefixProdi);

        if (namaProdi.isEmpty()) {
            System.out.println("Kode tidak tersedia");
            return;
        }

        String strAngkatan = "20" + nim.substring(3, 5);
        int angkatan = Integer.parseInt(strAngkatan);

        String strUrutan = nim.substring(5, 8);
        int urutan = Integer.parseInt(strUrutan);

        System.out.printf("Informasi NIM %s: %n", nim);
        System.out.printf(">> Program Studi: %s%n", namaProdi);
        System.out.printf(">> Angkatan: %d%n", angkatan);
        System.out.printf(">> Urutan: %d%n", urutan);
    }

    public static String ambilNamaProgramStudi(String prefix) {
        switch (prefix) {
            case "11S":
                return "Sarjana Informatika";
            case "12S":
                return "Sarjana Sistem Informasi";
            case "13S":
                return "Sarjana Teknik Elektro";
            case "21S":
                return "Sarjana Manajemen Rekayasa";
            case "22S":
                return "Sarjana Teknik Metalurgi";
            case "31S":
                return "Sarjana Teknik Bioproses";
            case "32S":
                return "Sarjana Bioteknologi";
            case "114":
                return "Diploma 4 Teknologi Rekayasa Perangkat Lunak";
            case "113":
                return "Diploma 3 Teknologi Informasi";
            case "133":
                return "Diploma 3 Teknologi Komputer";
            default:
                return "";
        }
    }
}
