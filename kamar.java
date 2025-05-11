public class Kamar {
    private int nomorKamar;
    private String tipeKamar;
    private double hargaPerMalam;
    private boolean tersedia;

    // Constructor
    public Kamar(int nomorKamar, String tipeKamar, double hargaPerMalam) {
        this.nomorKamar = nomorKamar;
        this.tipeKamar = tipeKamar;
        this.hargaPerMalam = hargaPerMalam;
        this.tersedia = true;
    }

    // Getter methods
    public int getNomorKamar() { return nomorKamar; }
    public String getTipeKamar() { return tipeKamar; }
    public double getHargaPerMalam() { return hargaPerMalam; }
    public boolean isTersedia() { return tersedia; }

    // Setter methods
    public void setTersedia(boolean tersedia) { this.tersedia = tersedia; }
}