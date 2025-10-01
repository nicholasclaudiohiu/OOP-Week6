package Week6_nicholasclaudiohiu.id.ac.umn;

class Handphone extends Barang {
    private String warna;

    public Handphone(int id, String nama, double harga, int stok, String warna) {
        super(id, nama, harga, stok);
        this.warna = warna;
    }

    public String getWarna() { return warna; }

    @Override
    public double getHargaJual() {
        return harga;
    }
}

