package Week6_nicholasclaudiohiu.id.ac.umn;

import java.util.*;

public class Main {
    static Scanner in = new Scanner(System.in);
    static ArrayList<Barang> daftarBarang = new ArrayList<>();
    static ArrayList<Order> daftarOrder = new ArrayList<>();
    static int barangId = 1;

    public static void main(String[] args) {
        boolean running = true;

        while (running) {
            System.out.println("\n-----------Menu Toko Voucher & HP------------");
            System.out.println("1. Pesan Barang");
            System.out.println("2. Lihat Pesanan");
            System.out.println("3. Barang Baru");
            System.out.println("4. Keluar");
            System.out.print("Pilihan : ");
            int pilih = in.nextInt(); in.nextLine();

            switch (pilih) {
                case 1 -> pesanBarang();
                case 2 -> lihatPesanan();
                case 3 -> tambahBarangBaru();
                case 4 -> running = false;
                default -> System.out.println("Pilihan tidak valid!");
            }
        }
    }

    public static void tambahBarangBaru() {
        System.out.print("Voucher / Handphone (V/H): ");
        String tipe = in.nextLine();

        System.out.print("Nama : ");
        String nama = in.nextLine();
        System.out.print("Harga : ");
        double harga = in.nextDouble();
        System.out.print("Stok : ");
        int stok = in.nextInt();

        if (tipe.equalsIgnoreCase("v")) {
            System.out.print("PPN : ");
            double ppn = in.nextDouble();
            daftarBarang.add(new Voucher(barangId++, nama, harga, stok, ppn));
            System.out.println("Voucher telah berhasil diinput");
        } else if (tipe.equalsIgnoreCase("h")) {
            in.nextLine();
            System.out.print("Warna : ");
            String warna = in.nextLine();
            daftarBarang.add(new Handphone(barangId++, nama, harga, stok, warna));
            System.out.println("Handphone telah berhasil diinput");
        }
    }

    public static void pesanBarang() {
        if (daftarBarang.isEmpty()) {
            System.out.println("Barang tidak tersedia!");
            return;
        }

        System.out.println("Daftar Barang Toko Voucher & HP");
        for (Barang b : daftarBarang) {
            System.out.println("ID : " + b.getId() + 
                               " | Nama : " + b.getNama() + 
                               " | Stok : " + b.getStok() + 
                               " | Harga : " + b.getHargaJual());
        }

        System.out.print("Pesan Barang (ID / 0 untuk batal): ");
        int id = in.nextInt();

        if (id == 0) return;

        Barang barang = null;
        for (Barang b : daftarBarang) {
            if (b.getId() == id) {
                barang = b;
                break;
            }
        }

        if (barang == null) {
            System.out.println("ID tidak sesuai!");
            return;
        }

        System.out.print("Masukkan jumlah : ");
        int jumlah = in.nextInt();

        if (jumlah <= 0 || jumlah > barang.getStok()) {
            System.out.println("Stok tidak mencukupi!");
            return;
        }

        double totalHarga = barang.getHargaJual() * jumlah;
        System.out.println(jumlah + " @ " + barang.getNama() + " dengan total harga " + totalHarga);

        System.out.print("Masukkan jumlah uang : ");
        double uang = in.nextDouble();

        if (uang < totalHarga) {
            System.out.println("Jumlah uang tidak mencukupi");
            return;
        }

        // Pesanan berhasil
        barang.minusStok(jumlah);
        if (barang instanceof Handphone hp) {
            daftarOrder.add(new Order(hp, jumlah));
        } else if (barang instanceof Voucher v) {
            daftarOrder.add(new Order(v, jumlah));
        }
        System.out.println("Berhasil dipesan");
    }

    public static void lihatPesanan() {
        if (daftarOrder.isEmpty()) {
            System.out.println("Belum ada pesanan");
            return;
        }
        System.out.println("Daftar Pesanan Toko Multiguna");
        for (Order o : daftarOrder) {
            if (o.getHandphone() != null) {
                System.out.println("ID : " + o.getId() + " | Nama : " + o.getHandphone().getNama() +
                                   " | Jumlah : " + o.getJumlah() +
                                   " | Total : " + (o.getHandphone().getHargaJual() * o.getJumlah()));
            } else if (o.getVoucher() != null) {
                System.out.println("ID : " + o.getId() + " | Nama : " + o.getVoucher().getNama() +
                                   " | Jumlah : " + o.getJumlah() +
                                   " | Total : " + (o.getVoucher().getHargaJual() * o.getJumlah()));
            }
        }
    }
}

