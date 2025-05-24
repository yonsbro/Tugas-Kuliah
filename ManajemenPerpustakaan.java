/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Wiryono
 */

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

class Buku {
    private String judul;
    private String penulis;
    private String kategori;
    private boolean tersedia;
    private Date tanggalPinjam;

    public Buku(String judul, String penulis, String kategori) {
        this.judul = judul;
        this.penulis = penulis;
        this.kategori = kategori;
        this.tersedia = true;
        this.tanggalPinjam = null;
    }

    // Getter dan Setter
    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getPenulis() {
        return penulis;
    }

    public void setPenulis(String penulis) {
        this.penulis = penulis;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public boolean isTersedia() {
        return tersedia;
    }

    public void setTersedia(boolean tersedia) {
        this.tersedia = tersedia;
    }

    public Date getTanggalPinjam() {
        return tanggalPinjam;
    }

    public void setTanggalPinjam(Date tanggalPinjam) {
        this.tanggalPinjam = tanggalPinjam;
    }

    @Override
    public String toString() {
        String status = tersedia ? "Tersedia" : "Dipinjam";
        return "Judul: " + judul + 
               "\nPenulis: " + penulis + 
               "\nKategori: " + kategori + 
               "\nStatus: " + status;
    }
}

public class ManajemenPerpustakaan {
    private static ArrayList<Buku> daftarBuku = new ArrayList<>();
    private static ArrayList<Buku> bukuDipinjam = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public static void main(String[] args) {
        inisialisasiDataBuku();
        
        while (true) {
            tampilkanMenuUtama();
            int pilihan = scanner.nextInt();
            scanner.nextLine(); // Membersihkan newline
            
            switch (pilihan) {
                case 1:
                    tampilkanDaftarBuku();
                    break;
                case 2:
                    prosesPeminjaman();
                    break;
                case 3:
                    prosesPengembalian();
                    break;
                case 4:
                    manajemenBuku();
                    break;
                case 5:
                    System.out.println("Terima kasih telah menggunakan sistem perpustakaan.");
                    System.exit(0);
                default:
                    System.out.println("Pilihan tidak valid. Silakan coba lagi.");
            }
        }
    }

    private static void inisialisasiDataBuku() {
        // Data buku fiksi
        daftarBuku.add(new Buku("Laskar Pelangi", "Andrea Hirata", "Fiksi"));
        daftarBuku.add(new Buku("Bumi Manusia", "Pramoedya Ananta Toer", "Fiksi"));
        daftarBuku.add(new Buku("Harry Potter dan Batu Bertuah", "J.K. Rowling", "Fiksi"));
        daftarBuku.add(new Buku("The Hobbit", "J.R.R. Tolkien", "Fiksi"));
        
        // Data buku non-fiksi
        daftarBuku.add(new Buku("Atomic Habits", "James Clear", "Non-Fiksi"));
        daftarBuku.add(new Buku("The Psychology of Money", "Morgan Housel", "Non-Fiksi"));
        daftarBuku.add(new Buku("Sapiens", "Yuval Noah Harari", "Non-Fiksi"));
        daftarBuku.add(new Buku("Educated", "Tara Westover", "Non-Fiksi"));
        
        // Data buku teknologi
        daftarBuku.add(new Buku("Clean Code", "Robert C. Martin", "Teknologi"));
        daftarBuku.add(new Buku("Design Patterns", "Erich Gamma", "Teknologi"));
        daftarBuku.add(new Buku("The Pragmatic Programmer", "Andrew Hunt", "Teknologi"));
        daftarBuku.add(new Buku("Algoritma dan Pemrograman", "Rinaldi Munir", "Teknologi"));
        
        // Data buku sejarah
        daftarBuku.add(new Buku("Sejarah Indonesia Modern", "M.C. Ricklefs", "Sejarah"));
        daftarBuku.add(new Buku("Revolusi 1945", "Asvi Warman Adam", "Sejarah"));
        daftarBuku.add(new Buku("The History of Java", "Thomas Stamford Raffles", "Sejarah"));
        daftarBuku.add(new Buku("Sejarah Dunia yang Disembunyikan", "Jonathan Black", "Sejarah"));
    }

    private static void tampilkanMenuUtama() {
        System.out.println("\n=== Sistem Manajemen Perpustakaan ===");
        System.out.println("1. Tampilkan Daftar Buku");
        System.out.println("2. Peminjaman Buku");
        System.out.println("3. Pengembalian Buku");
        System.out.println("4. Manajemen Buku (Admin)");
        System.out.println("5. Keluar");
        System.out.print("Pilih menu: ");
    }

    private static void tampilkanDaftarBuku() {
        System.out.println("\n=== Daftar Buku Perpustakaan ===");
        for (Buku buku : daftarBuku) {
            System.out.println(buku);
            System.out.println("-----------------------------");
        }
    }

    private static void prosesPeminjaman() {
        ArrayList<Buku> pinjamanSementara = new ArrayList<>();
        String input;
        
        System.out.println("\n=== Peminjaman Buku ===");
        System.out.println("Masukkan judul buku yang ingin dipinjam (ketik 'selesai' untuk mengakhiri):");
        
        while (true) {
            tampilkanBukuTersedia();
            System.out.print("Judul buku: ");
            input = scanner.nextLine();
            
            if (input.equalsIgnoreCase("selesai")) {
                break;
            }
            
            Buku bukuDipilih = cariBuku(input);
            
            if (bukuDipilih == null) {
                System.out.println("Buku tidak ditemukan. Silakan coba lagi.");
                continue;
            }
            
            if (!bukuDipilih.isTersedia()) {
                System.out.println("Buku sedang dipinjam. Silakan pilih buku lain.");
                continue;
            }
            
            pinjamanSementara.add(bukuDipilih);
            System.out.println("Buku '" + bukuDipilih.getJudul() + "' ditambahkan ke daftar pinjaman.");
        }
        
        if (!pinjamanSementara.isEmpty()) {
            System.out.print("Masukkan tanggal peminjaman (dd/MM/yyyy): ");
            String tanggalStr = scanner.nextLine();
            
            try {
                Date tanggalPinjam = dateFormat.parse(tanggalStr);
                
                for (Buku buku : pinjamanSementara) {
                    buku.setTersedia(false);
                    buku.setTanggalPinjam(tanggalPinjam);
                    bukuDipinjam.add(buku);
                }
                
                cetakStrukPeminjaman(pinjamanSementara, tanggalPinjam);
            } catch (ParseException e) {
                System.out.println("Format tanggal tidak valid. Peminjaman dibatalkan.");
            }
        } else {
            System.out.println("Tidak ada buku yang dipinjam.");
        }
    }

    private static void tampilkanBukuTersedia() {
        System.out.println("\nBuku yang tersedia:");
        for (Buku buku : daftarBuku) {
            if (buku.isTersedia()) {
                System.out.println("- " + buku.getJudul());
            }
        }
    }

    private static Buku cariBuku(String judul) {
        for (Buku buku : daftarBuku) {
            if (buku.getJudul().equalsIgnoreCase(judul)) {
                return buku;
            }
        }
        return null;
    }

    private static void cetakStrukPeminjaman(ArrayList<Buku> bukuDipinjam, Date tanggalPinjam) {
        System.out.println("\n=== Struk Peminjaman ===");
        System.out.println("Tanggal Pinjam: " + dateFormat.format(tanggalPinjam));
        System.out.println("Daftar Buku:");
        
        for (Buku buku : bukuDipinjam) {
            System.out.println("- " + buku.getJudul());
        }
        
        System.out.println("\nStatus: Berhasil dipinjam");
        System.out.println("Harap kembalikan buku maksimal 7 hari setelah peminjaman.");
    }

    private static void prosesPengembalian() {
        if (bukuDipinjam.isEmpty()) {
            System.out.println("\nTidak ada buku yang sedang dipinjam.");
            return;
        }
        
        ArrayList<Buku> pengembalianSementara = new ArrayList<>();
        String input;
        
        System.out.println("\n=== Pengembalian Buku ===");
        System.out.println("Masukkan judul buku yang akan dikembalikan (ketik 'selesai' untuk mengakhiri):");
        
        while (true) {
            System.out.println("\nBuku yang sedang dipinjam:");
            for (Buku buku : bukuDipinjam) {
                System.out.println("- " + buku.getJudul());
            }
            
            System.out.print("Judul buku: ");
            input = scanner.nextLine();
            
            if (input.equalsIgnoreCase("selesai")) {
                break;
            }
            
            Buku bukuDikembalikan = null;
            
            for (Buku buku : bukuDipinjam) {
                if (buku.getJudul().equalsIgnoreCase(input)) {
                    bukuDikembalikan = buku;
                    break;
                }
            }
            
            if (bukuDikembalikan == null) {
                System.out.println("Buku tidak ditemukan dalam daftar pinjaman. Silakan coba lagi.");
                continue;
            }
            
            pengembalianSementara.add(bukuDikembalikan);
            System.out.println("Buku '" + bukuDikembalikan.getJudul() + "' ditambahkan ke daftar pengembalian.");
        }
        
        if (!pengembalianSementara.isEmpty()) {
            System.out.print("Masukkan tanggal pengembalian (dd/MM/yyyy): ");
            String tanggalStr = scanner.nextLine();
            
            try {
                Date tanggalKembali = dateFormat.parse(tanggalStr);
                int totalDenda = 0;
                
                for (Buku buku : pengembalianSementara) {
                    buku.setTersedia(true);
                    bukuDipinjam.remove(buku);
                    
                    // Hitung denda jika ada
                    long selisihHari = (tanggalKembali.getTime() - buku.getTanggalPinjam().getTime()) / (1000 * 60 * 60 * 24);
                    if (selisihHari > 7) {
                        int denda = (int)(selisihHari - 7) * 5000;
                        totalDenda += denda;
                        System.out.println("Denda untuk buku '" + buku.getJudul() + "': Rp " + denda);
                    }
                }
                
                cetakStrukPengembalian(pengembalianSementara, tanggalKembali, totalDenda);
            } catch (ParseException e) {
                System.out.println("Format tanggal tidak valid. Pengembalian dibatalkan.");
            }
        } else {
            System.out.println("Tidak ada buku yang dikembalikan.");
        }
    }

    private static void cetakStrukPengembalian(ArrayList<Buku> bukuDikembalikan, Date tanggalKembali, int totalDenda) {
        System.out.println("\n=== Struk Pengembalian ===");
        System.out.println("Tanggal Kembali: " + dateFormat.format(tanggalKembali));
        System.out.println("Daftar Buku:");
        
        for (Buku buku : bukuDikembalikan) {
            System.out.println("- " + buku.getJudul());
        }
        
        System.out.println("\nStatus: Berhasil dikembalikan");
        
        if (totalDenda > 0) {
            System.out.println("Total Denda: Rp " + totalDenda);
        } else {
            System.out.println("Tidak ada denda.");
        }
    }

    private static void manajemenBuku() {
        while (true) {
            System.out.println("\n=== Menu Manajemen Buku (Admin) ===");
            System.out.println("1. Tambah Buku Baru");
            System.out.println("2. Ubah Data Buku");
            System.out.println("3. Hapus Buku");
            System.out.println("4. Kembali ke Menu Utama");
            System.out.print("Pilih menu: ");
            
            int pilihan = scanner.nextInt();
            scanner.nextLine(); // Membersihkan newline
            
            switch (pilihan) {
                case 1:
                    tambahBuku();
                    break;
                case 2:
                    ubahBuku();
                    break;
                case 3:
                    hapusBuku();
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Pilihan tidak valid. Silakan coba lagi.");
            }
        }
    }

    private static void tambahBuku() {
        System.out.println("\n=== Tambah Buku Baru ===");
        System.out.print("Judul: ");
        String judul = scanner.nextLine();
        System.out.print("Penulis: ");
        String penulis = scanner.nextLine();
        System.out.print("Kategori: ");
        String kategori = scanner.nextLine();
        
        daftarBuku.add(new Buku(judul, penulis, kategori));
        System.out.println("Buku berhasil ditambahkan.");
    }

    private static void ubahBuku() {
        System.out.println("\n=== Ubah Data Buku ===");
        System.out.print("Masukkan judul buku yang akan diubah: ");
        String judul = scanner.nextLine();
        
        Buku buku = cariBuku(judul);
        
        if (buku == null) {
            System.out.println("Buku tidak ditemukan.");
            return;
        }
        
        System.out.println("\nData buku saat ini:");
        System.out.println(buku);
        
        System.out.println("\nMasukkan data baru (kosongkan jika tidak ingin mengubah):");
        System.out.print("Judul [" + buku.getJudul() + "]: ");
        String newJudul = scanner.nextLine();
        System.out.print("Penulis [" + buku.getPenulis() + "]: ");
        String newPenulis = scanner.nextLine();
        System.out.print("Kategori [" + buku.getKategori() + "]: ");
        String newKategori = scanner.nextLine();
        
        if (!newJudul.isEmpty()) {
            buku.setJudul(newJudul);
        }
        if (!newPenulis.isEmpty()) {
            buku.setPenulis(newPenulis);
        }
        if (!newKategori.isEmpty()) {
            buku.setKategori(newKategori);
        }
        
        System.out.println("Data buku berhasil diubah.");
    }

    private static void hapusBuku() {
        System.out.println("\n=== Hapus Buku ===");
        System.out.print("Masukkan judul buku yang akan dihapus: ");
        String judul = scanner.nextLine();
        
        Buku buku = cariBuku(judul);
        
        if (buku == null) {
            System.out.println("Buku tidak ditemukan.");
            return;
        }
        
        System.out.println("\nData buku yang akan dihapus:");
        System.out.println(buku);
        
        System.out.print("Apakah Anda yakin ingin menghapus buku ini? (ya/tidak): ");
        String konfirmasi = scanner.nextLine();
        
        if (konfirmasi.equalsIgnoreCase("ya")) {
            daftarBuku.remove(buku);
            System.out.println("Buku berhasil dihapus.");
        } else {
            System.out.println("Penghapusan buku dibatalkan.");
        }
    }
}