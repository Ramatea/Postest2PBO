import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import java.io.*;

public class Main {
    static int batasan;
    static ArrayList<ListPesanan> Datapesanan = new ArrayList<>();
    private static InputStreamReader p = new InputStreamReader(System.in);
    private static BufferedReader input = new BufferedReader(p);
    static Random rand = new Random();
    static LocalDateTime now = LocalDateTime.now();

    private static void clear(){
        System.out.print("\033[H\033[2J");  
        System.out.flush();
    }

    public static void main(String[] args) throws ParseException, IOException {
        while(true){
            clear();
            System.out.println("===========================");
            System.out.println("    DataBase Pesanan       ");
            System.out.println("===========================");
            System.out.println("= 1. TAMBAH  DATA PESANAN =");
            System.out.println("= 2. LIHAT   DATA PESANAN =");
            System.out.println("= 3. UBAH    DATA PESANAN =");
            System.out.println("= 4. HAPUS   DATA PESANAN =");
            System.out.println("= 5. KELUAR  PROGRAM      =");
            System.out.print("\nMasukan Pilihan Anda >> ");
            int pilih = Integer.parseInt(input.readLine());
            switch(pilih){
                case 1:
                    clear();
                    add();
                    break;
                case 2:
                    clear();
                    view();
                    break;
                case 3:
                    clear();
                    update();
                    break;
                case 4:
                    clear();
                    delete();
                    break;
                case 5:
                    clear();
                    System.exit(0);
                    break;
                default:
                    System.err.println("======================");
                    System.err.println("PILIHAN ANDA TIDAK ADA");
                    System.err.println("======================");
                    System.out.println("Tekan enter untuk melanjutkan"); input.readLine();
                    break;
            }
        }
    }

    private static void add() throws ParseException, IOException{
        System.out.print("Ingin masukkan berapa data >>> ");
        batasan = Integer.parseInt(input.readLine());
        for(int i = 0; i < batasan; i++){
            clear();
            System.out.println("Data ke - " + (i + 1));
            System.out.print("masukkan nama pemesan >>");
            String pemesan = input.readLine();
            System.out.print("masukkan nomor telepon >> ");
            String nomor_telepon = input.readLine();
            int nomor_antrian = rand.nextInt(100);
            String waktuString = now.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS"));
            int angkaAcak = (int) (Math.random() * 900) + 100;
            String No_pesanan = waktuString + angkaAcak;
            ListPesanan pesanan_baru = new ListPesanan(pemesan, No_pesanan, nomor_telepon, nomor_antrian);
            System.out.println("");
            Datapesanan.add(pesanan_baru);
        }
        
    }

    private static void view() throws IOException{
        clear();
        for(int i = 0; i < Datapesanan.size(); i++){
            System.out.println("===========================================");
            System.out.println("data ke - " +  (i+1));
            System.out.println("KODE    PESANAN >>> "  + Datapesanan.get(i).getNoPesanan());
            System.out.println("NOMOR   ANTRIAN >>> "  + Datapesanan.get(i).getNo_Antrian());
            System.out.println("NAMA    PEMESAN >>> "  + Datapesanan.get(i).getNama());
            System.out.println("NOMOR   TELP    >>> "  + Datapesanan.get(i).getNo_telp());
            System.out.println("===========================================");
            System.out.println("");
            System.out.println("TEKAN ENTER UNTUK MELANJUTKAN. . .");input.readLine();
        }
    }

    private static void update() throws IOException{
        System.out.print("Masukkan nama pemesan >> ");
        String nama = input.readLine();
        for(ListPesanan pemesan : Datapesanan){
            if(pemesan.getNama().equals(nama)){
                System.out.print("masukkan nama pemesan >>");
                pemesan.setNama(input.readLine());
                System.out.print("masukkan nomor telepon >> ");
                pemesan.setNoTelp(input.readLine());
                clear();
                System.out.println("====================");
                System.out.println("= DATA TELAH DIRUBAH");
                System.out.println("====================");
                System.out.println("");
                System.out.println("TEKAN ENTER UNTUK MELANJUTKAN. . .");input.readLine();
            }
        }
    }
    private static void  delete() throws IOException{
        System.out.print("Masukkan nama pemesan >> ");
        String nama_hapus = input.readLine();
        for(int i = 0; i < Datapesanan.size(); i++){
            if(Datapesanan.get(i).getNama().equals(nama_hapus)){
                Datapesanan.remove(i);
                System.out.println("====================");
                System.out.println("= DATA TELAH DIHAPUS");
                System.out.println("====================");
                System.out.println("");
                System.out.println("TEKAN ENTER UNTUK MELANJUTKAN. . .");input.readLine();
            }
        }
    }
}