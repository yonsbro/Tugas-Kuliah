import java.util.Scanner;

public class HotelReservation {
    // Data kamar hotel disimpan dalam array
    private static Kamar[] daftarKamar = {
        new Kamar(101, "Standar", 300000),
        new Kamar(102, "Standar", 300000),
        new Kamar(201, "Superior", 500000),
        new Kamar(202, "Superior", 500000),
        new Kamar(301, "Deluxe", 800000),
        new Kamar(302, "Deluxe", 800000),
        new Kamar(401, "Suite", 1200000),
        new Kamar(402, "Suite", 1200000)
    };

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        // Tampilkan daftar kamar
        tampilkanDaftarKamar();
        
        // Proses reservasi
        System.out.println("\n=== RESERVASI KAMAR ===");
        System.out.print("Berapa kamar yang ingin dipesan (maks 3)? ");
        int jumlahKamar = input.nextInt();
        
        if (jumlahKamar < 1 || jumlahKamar > 3) {
            System.out.println("Jumlah kamar tidak valid!");
            return;
        }
        
        // Array untuk menyimpan kamar yang dipesan
        Kamar[] kamarDipilih = new Kamar[jumlahKamar];
        int[] lamaMenginap = new int[jumlahKamar];
        
        // Input data reservasi
        for (int i = 0; i < jumlahKamar; i++) {
            System.out.print("\nMasukkan nomor kamar ke-" + (i+1) + ": ");
            int nomorKamar = input.nextInt();
            
            System.out.print("Lama menginap (malam): ");
            lamaMenginap[i] = input.nextInt();
            
            // Cari kamar berdasarkan nomor
            kamarDipilih[i] = cariKamar(nomorKamar);
            
            if (kamarDipilih[i] == null || !kamarDipilih[i].isTersedia()) {
                System.out.println("Kamar tidak tersedia!");
                return;
            }
        }
        
        // Hitung total biaya
        double totalBiaya = hitungTotalBiaya(kamarDipilih, lamaMenginap);
        
        // Cetak struk
        cetakStruk(kamarDipilih, lamaMenginap, totalBiaya);
        
        input.close();
    }
    
    // Method untuk menampilkan daftar kamar
    public static void tampilkanDaftarKamar() {
        System.out.println("=== DAFTAR KAMAR HOTEL ===");
        System.out.println("No Kamar\tTipe\tHarga/Malam\tStatus");
        
        for (Kamar kamar : daftarKamar) {
            String status = kamar.isTersedia() ? "Tersedia" : "Terisi";
            System.out.printf("%d\t\t%s\tRp%,.0f\t%s%n",
                kamar.getNomorKamar(),
                kamar.getTipeKamar(),
                kamar.getHargaPerMalam(),
                status);
        }
    }
    
    // Method untuk mencari kamar berdasarkan nomor
    public static Kamar cariKamar(int nomorKamar) {
        for (Kamar kamar : daftarKamar) {
            if (kamar.getNomorKamar() == nomorKamar) {
                return kamar;
            }
        }
        return null;
    }
    
    // Method untuk menghitung total biaya
    public static double hitungTotalBiaya(Kamar[] kamarDipilih, int[] lamaMenginap) {
        double subTotal = 0;
        
        for (int i = 0; i < kamarDipilih.length; i++) {
            subTotal += kamarDipilih[i].getHargaPerMalam() * lamaMenginap[i];
        }
        
        // Biaya layanan Rp50.000 per kamar
        double biayaLayanan = 50000 * kamarDipilih.length;
        
        // Pajak 10%
        double pajak = subTotal * 0.1;
        
        double total = subTotal + pajak + biayaLayanan;
        
        // Diskon 15% jika total > Rp500.000
        if (total > 500000) {
            total -= total * 0.15;
        }
        
        return total;
    }
    
    // Method untuk mencetak struk
    public static void cetakStruk(Kamar[] kamarDipilih, int[] lamaMenginap, double totalBiaya) {
        System.out.println("\n=== STRUK RESERVASI ===");
        System.out.println("Kamar yang dipesan:");
        System.out.println("No Kamar\tTipe\tHarga/Malam\tLama\tSubtotal");
        
        double subTotal = 0;
        for (int i = 0; i < kamarDipilih.length; i++) {
            double subtotalKamar = kamarDipilih[i].getHargaPerMalam() * lamaMenginap[i];
            subTotal += subtotalKamar;
            
            System.out.printf("%d\t\t%s\tRp%,.0f\t%d\tRp%,.0f%n",
                kamarDipilih[i].getNomorKamar(),
                kamarDipilih[i].getTipeKamar(),
                kamarDipilih[i].getHargaPerMalam(),
                lamaMenginap[i],
                subtotalKamar);
            
            // Update status kamar
            kamarDipilih[i].setTersedia(false);
        }
        
        System.out.println("\nRincian Biaya:");
        System.out.printf("Subtotal Kamar: Rp%,.0f%n", subTotal);
        System.out.printf("Pajak (10%%): Rp%,.0f%n", subTotal * 0.1);
        System.out.printf("Biaya Layanan: Rp%,.0f%n", 50000.0 * kamarDipilih.length);
        
        // Cek diskon dan penawaran
        if (totalBiaya > 500000) {
            System.out.println("Diskon 15%: Ya");
        }
        if (totalBiaya > 300000) {
            System.out.println("Bonus: Gratis sarapan untuk semua tamu");
        }
        
        System.out.printf("\nTOTAL BIAYA: Rp%,.0f%n", totalBiaya);
        System.out.println("\nTerima kasih atas reservasi Anda!");
    }
}