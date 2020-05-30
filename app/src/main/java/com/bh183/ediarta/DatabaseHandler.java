package com.bh183.ediarta;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.ArrayList;

public class DatabaseHandler extends SQLiteOpenHelper {

    private final static int DATABASE_VERSION = 2;
    private final static String DATABASE_NAME = "db_buku";
    private final static String TABLE_BUKU ="t_buku";
    private final static String KEY_ID_BUKU = "ID_Buku";
    private final static String KEY_JUDUL = "Judul";
    private final static String KEY_PENULIS = "Penulis";
    private final static String KEY_PENERBIT = "Penerbit";
    private final static String KEY_GENRE = "Genre";
    private final static String KEY_TAHUN = "Tahun";
    private final static String KEY_GAMBAR = "Gambar";
    private final static String KEY_SINOPSIS = "Sinopsis";
    private Context context;


    public DatabaseHandler(Context ctx){
        super(ctx, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = ctx;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE_BUKU = "CREATE TABLE " + TABLE_BUKU
                + "(" + KEY_ID_BUKU + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + KEY_JUDUL + " TEXT, " + KEY_PENULIS + " TEXT, "
                + KEY_PENERBIT + " TEXT, " + KEY_GENRE + " TEXT, "
                + KEY_TAHUN + " TEXT, " + KEY_GAMBAR + " TEXT, " + KEY_SINOPSIS + " TEXT);";

        db.execSQL(CREATE_TABLE_BUKU);
        inisialisasiBukuAwal(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_BUKU;
        db.execSQL(DROP_TABLE);
        onCreate(db);
    }

    public void tambahBuku(Buku dataBuku){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(KEY_JUDUL, dataBuku.getJudul());
        cv.put(KEY_PENULIS, dataBuku.getPenulis());
        cv.put(KEY_PENERBIT, dataBuku.getPenerbit());
        cv.put(KEY_GENRE, dataBuku.getGenre());
        cv.put(KEY_TAHUN, dataBuku.getTahun());
        cv.put(KEY_GAMBAR, dataBuku.getGambar());
        cv.put(KEY_SINOPSIS, dataBuku.getSinopsis());


        db.insert(TABLE_BUKU, null, cv);
        db.close();
    }

    public void tambahBuku(Buku dataBuku, SQLiteDatabase db){
        ContentValues cv = new ContentValues();

        cv.put(KEY_JUDUL, dataBuku.getJudul());
        cv.put(KEY_PENULIS, dataBuku.getPenulis());
        cv.put(KEY_PENERBIT, dataBuku.getPenerbit());
        cv.put(KEY_GENRE, dataBuku.getGenre());
        cv.put(KEY_TAHUN, dataBuku.getTahun());
        cv.put(KEY_GAMBAR, dataBuku.getGambar());
        cv.put(KEY_SINOPSIS, dataBuku.getSinopsis());
        db.insert(TABLE_BUKU, null, cv);
    }

    public void editBuku(Buku dataBuku){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(KEY_JUDUL, dataBuku.getJudul());
        cv.put(KEY_PENULIS, dataBuku.getPenulis());
        cv.put(KEY_PENERBIT, dataBuku.getPenerbit());
        cv.put(KEY_GENRE, dataBuku.getGenre());
        cv.put(KEY_TAHUN, dataBuku.getTahun());
        cv.put(KEY_GAMBAR, dataBuku.getGambar());
        cv.put(KEY_SINOPSIS, dataBuku.getSinopsis());

        db.update(TABLE_BUKU, cv, KEY_ID_BUKU + "=?", new String[]{String.valueOf(dataBuku.getIdBuku())});
        db.close();
    }

    public void hapusBuku (int idBuku){
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_BUKU, KEY_ID_BUKU + "=?", new String[]{String.valueOf(idBuku)});
        db.close();
    }

    public ArrayList<Buku> getAllBuku(){
        ArrayList<Buku> dataBuku = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_BUKU;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor csr = db.rawQuery(query, null);
        if(csr.moveToFirst()){
            do {
                Buku tempBuku = new Buku(
                        csr.getInt(0),
                        csr.getString(1),
                        csr.getString(2),
                        csr.getString(3),
                        csr.getString(4),
                        csr.getString(5),
                        csr.getString(6),
                        csr.getString(7)

                );

                dataBuku.add(tempBuku);
            } while (csr.moveToNext());
        }

        return dataBuku;
    }

    private String storeImageFile(int id) {
        String location;
        Bitmap image = BitmapFactory.decodeResource(context.getResources(), id);
        location = InputActivity.saveImageToInternalStorage(image, context);
        return location;
    }

    private void inisialisasiBukuAwal(SQLiteDatabase db){
        int idBuku = 0;

        // Menambahkan data buku ke 1
        Buku buku1 = new Buku(
                idBuku,
                "TENGGELAMNYA KAPAL VAN DER WICK",
                "BUYA HAMKA",
                "PT. BULAN BINTANG",
                "ROMAN",
                "1984",
                storeImageFile(R.drawable.buku1),
                "Novel ini mengisahkan tetang cinta, adat, keturunan, dan kekayaan. Semua itu masuk dalam kisah yang dibungkus oleh BUYA HAMKA dalm novel Tenggelmanya Kapal Van Der Wijck ini. Kisah cinta abadi dari Zainuddin dan Hayati yang tak lekang oleh waktu, tak terpisah oleh dunia dan pincangnya adat di negeri Minang. Minangkabau sebagai salah satu suku yang memegang tegus adat dan tradisi. Keturunan dan kekayaan menjadi segala-galanya. Cinta suci Zainuddin untuk Hayati terhalang oleh keturunan dan kemiskinan. Zainuddin yang merupakan keturuna campuran Minang dan Bugis tidak mendapat pengakuan sebagai suku Minang asli, karena ibunya bersuku Bugis. Cinta mereka pun terhalang dan Hayati menikah dengan Aziz, seorang Minang asli dan kaya. Zainuddin setia dan tetap hidup dengan dirinya dan karya-karyanya. Zainuddin pindah ke Pulau Jawa bersama bang Muluk sahabatnya dan menemukan titik kesuksesan disana (Surabaya). Hayati dan Aziz akhirnya berpisah, Aziz mati bunuh diri dan Hayati menjanda. Zainuddin yang demawan tidak ingin melihat Hayati menderita, meskipun Hayati telah menjadi janda, Zainuddin tidak menikahi Hayati.Hayati diminta untuk pulang ke Padang menaiki kapal Belanda termewah yaitu Kapal Van Der Wijck yang berlabuh ke laut Andalas. Hingga saatnya tiba Hayati pulang dan tak kembali lagi seiring dengan kecelakaan yang menenggelamkan Kapal Van Der Wijck tersebut. Nyawa Hayati tidak dapat diselamatkan. Zainuddin merasa menyesal atas keputusannya menyuruh Hayati kembali ke Padang. Setelah hayati meningeal dalam peristiwa itu, Zainuddin setiap hati mendatangi kubur Hayati, ia hidup dalam baying cintanya yang tetap ada dihatinya, Zainuddin semakin rapuh dan sakit-sakitan, Zainuddin yang terkenal dengan karya-karya hikayatnya kini telah tenggelam bersama bayang dan angan bersama Hayati. Hingga setahun kemudian Zainuddin menyusul hayati kea lam abadi. Zainuddin meninggalkan harta benda melimpah dan karya-karya sastranya yang indah. Saat maut menjemputnya Zainuddin menyelesaikan kisah hikayat cintanya bersama Hayati dalam tulisan terkahirnya yang berjudul Tenggelamnya Kapal Van Der Wijck. Zainuddinpun di kubur bersama angan dan cintanya yang abadi di samping kubur Hayati sang kekasih abadinya."
        );

        tambahBuku(buku1, db);
        idBuku++;

        // Menambahkan data buku ke 2
        Buku buku2 = new Buku(
                idBuku,
                "DILAN : DIA ADALAH DILANKU 1990",
                "PIDI BAIQ",
               "PT MIZAN PUSTAKA",
                "ROMAN",
                "2014",
                storeImageFile(R.drawable.buku2),
                "Novel ini menceritakan tentang kisah cinta Milea. Milea adalah seorang murid baru pindahan dari Jakarta. Dan di saat ia berjalan menuju sekolah, ia bertemu dengan seorang teman satu sekolahnya, seorang peramal. Peramal itu mengatakan bahwa nanti mereka akan bertemu di kantin. Awalnya Milea tidak menghiraukan laki-laki peramal itu, tapi setiap hari laki-laki peramal tersebut selalu mengganggunya. Mau tidak mau, Milea mulai mencari tahu, laki-laki peramal itu bernama Dilan.\n" +
                        "Suatu hari, saat Dilan mengikuti Milea pulang dengan angkot ia berkata, “Milea, kamu cantik, tapi aku belum mencintaimu. Enggak tahu kalau sore. Tunggu aja”. Perkataan Dilan itu membuat hati Milea berdebar-debar, mungkin ia kaget atas ucapan Dilan. Milea diam mendengar ucapan itu, ia juga memikirkan Beni, pacarnya yang ada di Jakarta.\n" +
                        "Dilan mendekati Milea dengan cara yang tidak biasa, mungkin itu yang membuat Milea selalu memikirkannya. Dilan memberikan coklat kepada Milea melalui tukang pos, Dilan membawa Bi Asih untuk memijiti Milea saat sedang sakit, Dilan memberikan hadiah Teka Teki Silang pada Milea sebagai hadiah ulang tahun dengan sebuah tulisan “SELAMAT ULANG TAHUN, MILEA.INI HADIAH UNTUKMU, CUMA TTS. TAPI SUDAH KUISI SEMUA. AKU SAYANG KAMU. AKU TIDAK MAU KAMU PUSING KARENA HARUS MENGISINYA. DILAN”\n" +
                        "Lambat laun, seiring berjalannya waktu Milea dan Dilan menjadi akrab. Milea mengetahui beberapa hal tentang dilan dari Wati, sepupu Dilan yang sekelas dengannya. Sekolah Milea di Bandung terpilih menjadi peserta Cerdas Cermat TVRI, beberapa siswa yang bukan peserta dianjurkan untuk ikut memberikan semangat buat teman-temannya yang sedang berlomba. Milea salah satunya, dan di Jakarta ia sudah berencana untuk bertemu dengn Beni, pacarnya. Milea sudah lama menunggu Beni yang berjanji untuk datang ke TVRI, namun Beni tak kunjung datang. Akhirnya, Milea pergi makan bersama Nandan dan Wati. Saat itulah Beni datang dan marah-marah melihat Milea makan bersama laki-laki lain. Hubungan mereka pun berakhir.\n"
        );

        tambahBuku(buku2, db);
        idBuku++;

        // Menambahkan data buku ke 3
        Buku buku3 = new Buku(
                idBuku,
                "LASKAR PELANGI",
                "ANDREA HIRATA",
                "BENTANG PUSTAKA",
                "ROMAN",
                "2007",
                storeImageFile(R.drawable.buku3),
                "Novel ini mengisahkan tentang sepuluh anak Belitung yang tergabung dalam Laskar Pelangi mereka adalah Mahar, Ikal, Lintang, Harun, Syahdan,A Kiong,Trapani, Borek, Kucai dan satu-satunya wanita yaitu Sahara . Cerita ini mengisahkan tentang kehidupan di pedalaman Belitung yang kontras dan yang kaya akan timah, namun masyrakatnya tidak mampu memenuhi kehidupannya sehari-hari. Novel ini juga menceritakan tentang semangat juang dari anak-anak kampung Belitung untuk mengubah nasib mereka melalui sekolah. Sebagian besar orang tua mereka lebih senang melihat anak-anaknya membantunya dari pada belajar di sekolah.\n" +
                        "Kesulitan terus menerus membayangi sekolah kampung itu, sekolah yang dibangun atas jiwa ikhlas dan kepeloporan dua orang guru yaitu seorang Kepala Sekolah yang sudah tua yang bernama bapak Harfan Efendy Noor dan ibu guru muda yang bernama ibu Muslimah Hafsari yang juga sangat miskin berusaha mempertahankan semangat besar pendidikan. Sekolah yang nyaris dibubarkan oleh pengawas sekolah Depdikbud Sumsel karena kekurangan murid itu terselamatkan berkat seorang anak yang sepanjang masa bersekolah yang tak pernak mendapatkan rapot.\n" +
                        "Sekolah yang dihidupi lewat uluran tangan karena donator dikomunitas marjinal itu begitu miskin. Seperti gedung sekolahnya yang sudah roboh, ruang kelas beralas tanah, beratap bolong-bolong, berbangku seadanya dan pada malam hari dipakai untuk menyimpan ternak, bahkan kapur tulis sekalipun terasa mahal bagi sekolah yang hanya mampu menggaji guru dan kepala sekolahnya dengan sekian kilo beras, sehingga para guru itu terpaksa menafkahi keluarganya dengan cara lain. Sang kepala sekolah mencangkul sebidang kebun dan sang ibu guru menerima jahitan. Kendati demikian, keajaiban seakan terjadi setiap hari disekolah yang dari jauh tampak seperti bangunan yang akan roboh itu. Semuanya terjadi karena sejak hari pertama kelas satu sang kepala sekolah dan sang ibu guru muda yang hanya berijazah SKP ( Sekolah Kepandaian Putri ). Mereka berdua saling bahu membahu membesarkan hati anak-anak tadi agar percaya diri, berani berkompetisi, agar menghargai dan menempatkan pendidikan sebagai hal yang sangat penting dalam hidup ini. Kedua guru itu juga merupakan guru yang ulung sehingga menghasilkan seorang murid yang sangat pintar dan mereka mampu mengasah bakat beberapa murid lainnya. Pak Harfan dan buk Mus juga mengajarkan cinta sesama dan mereka amat menyayangi kesepuluh muridnya. Kedua guru miskin itu member julukan kesepuluh murid itu sebagai laskar pelangi.\n" +
                        "Keajaiban juga terjadi ketika sekolah muhammadiyah, dipimpin oleh salah satu laskar pelangi mampu menjuarai karnaval mengalahkan sekolah PN dan keajaiban mencapai puncaknya ketika tiga orang anak anggota laskar pelangi yaitu ( Ikal, Lintang, dan Sahara ) berhasil menjuarai lomba cerdas pangkas mengalahkan sekolah-sekolah PN dan sekolah-sekolah negeri. Taayal, kejadian yang paling menyedihkan melanda sekolah muhammadiyah ketika Lintang sisiwa paling jenius anggota laskar pelangi itu harus berhenti sekolah padahal Cuma tinggal satu triwulan menyelesaikan SMP. Ia harus berhenti karena ia anak laki-laki tertua yang harus menghidupi keluarganya, sebab ayahnya sudah meninggal dunia. Meskipun awal tahun 90-an sekolah muhammadiyah itu akhirnya ditutup karena samaskali sudah tidak bisa membiayai diri sendiri, tapi semangat, integritas, keluruhan budi dan ketekunan yang diajarkan pak Harfan dan buk Mus tetap hidup dalam hati laskar pelangi. Akhirnya kedua guru itu bisa berbangga karena diantara sepuluh orang anggota laskar pelangi sekarang ada yang menjadi wakil rakyat, ada yang menjadi research and development manager disalah satu perusahaan multi nasional paling penting di negeri ini, dan juga ada yang mendapatkan beasiswa internasional kemudian melakukan research di University the paris surbonne dan lulus S2 dengan predikat with distinction dari sebuah universitar termuka di Inggris semua itu berkat dari pendidikan dan akhlak kecintaan intelektual yang diajarkan oleh pak Harfan dan buk Mus. Kedua orang hebat yang mungkin bahkan belum pernah keluar dari pulau diujung paling selatan Sumatra itu.\n"
        );

        tambahBuku(buku3, db);
        idBuku++;

        // Menambahkan data buku ke 4
        Buku buku4 = new Buku(
                idBuku,
                "5 CM",
                "DHONNY DHIRGANTORO",
                "PT GARASINDO",
                "ROMAN",
                "2005",
                storeImageFile(R.drawable.buku4),
                "Buku 5 cm ini menceritakan tentang persahabatan lima orang anak muda yang menjalin persahabatan selama tujuh tahun, mereka diantaranya  bernama Arial, Riani, Zafran, Ian, dan Genta.\n" +
                        "Mereka adalah sahabat yang kompak, memiliki obsesi dan impian masing-masing, mereka selalu pergi bersama dan ketemu setiap saat. Karena bosan bertemu setiap hari, akhirnya mereka menemukan titik jenuh dengan aktivitas yang selalu mereka lakukan bersama dan mereka memutuskan untuk tidak saling berkomunikasi selama tiga bulan.\n" +
                        "Dalam masa “berpisah tersebut”, mereka tidak diperkenankan melakukan komunikasi dalam bentuk apapun. Dalam kurun 3 bulan tersebutlah, mereka ditempa dengan hal baru. Dengan rasa rindu yang saling menyilang. Tentang tokoh Riani yang mencintai salah satu sahabatnya zafran. Tentang Zafran yang merindui dinda adik Arial, sahabatnya sendiri. Tentang Genta yang memilih mengagumi Riani dengan diam.\n" +
                        "Selama tiga bulan berpisah itulah terjadi banyak hal yang membuat hati mereka lebih kaya dari sebelumnya. Pertemuan setelah tiga bulan yang penuh dengan rasa kangen akhirnya terjadi dan dirayakan dengan sebuah perjalanan ‘reuni’ mereka dengan mendaki gunung tertinggi di Pulau Jawa, Mahameru.\n" +
                        "“Biarkan keyakinan kamu, 5 centimeter menggantung mengambang di depan kamu, cuma kaki yang akan berjalan lebih jauh dari biasanya, tangan yang akan berbuat lebih banyak dari biasanya, mata yang akan menatap lebih lama dari biasanya, leher yang akan lebih sering melihat ke atas. Lapisan tekad yang seribu kali lebih keras dari baja, hati yang akan bekerja lebih keras dari biasanya serta mulut yang akan selalu berdoa. percaya pada 5 centimeter di depan kening kamu”\n"
        );

        tambahBuku(buku4, db);
        idBuku++;


    }
}
