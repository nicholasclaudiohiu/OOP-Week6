package Week6_nicholasclaudiohiu.id.ac.umn;

class Voucher extends Barang {
    private double pajak;

    public Voucher(int id, String nama, double harga, int stok, double pajak) {
        super(id, nama, harga, stok);
        this.pajak = pajak;
    }

    public double getPajak() { return pajak; }

    @Override
    public double getHargaJual() {
        return harga + (harga * pajak);
    }
}

