package com.bh183.ediarta;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;


public class Adapter extends RecyclerView.Adapter<Adapter.BukuViewHolder> {

    private Context context;
    private ArrayList<Buku> dataBuku;


    public Adapter(Context context, ArrayList<Buku> dataBuku) {
        this.context = context;
        this.dataBuku = dataBuku;
    }

    @NonNull
    @Override
    public BukuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.list_buku, parent, false);
        return new BukuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BukuViewHolder holder, int position) {
        Buku tempBuku = dataBuku.get(position);
        holder.idBuku = tempBuku.getIdBuku();
        holder.judul.setText(tempBuku.getJudul());
        holder.sinopsis.setText(tempBuku.getSinopsis());
        holder.penulis.setText(tempBuku.getPenulis());
        holder.gambar = tempBuku.getGambar();
        holder.genre.setText(tempBuku.getGenre());
        holder.penerbit.setText(tempBuku.getPenerbit());
        holder.tahun.setText(tempBuku.getTahun());

        try {
            File file = new File(holder.gambar);
            Bitmap bitmap = BitmapFactory.decodeStream(new FileInputStream(file));
            holder.imgGambar.setImageBitmap(bitmap);
            holder.imgGambar.setContentDescription(holder.gambar);
        } catch (FileNotFoundException er){
            er.printStackTrace();
            Toast.makeText(context, "Gagal mengambil gambar dalam media penyimpanan", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public int getItemCount() {

        return dataBuku.size();
    }

    public class BukuViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{

        public int idBuku;
        private ImageView imgGambar;
        private TextView judul;
        private TextView sinopsis;
        private TextView genre;
        private TextView penulis;
        private TextView penerbit;
        private TextView tahun;
        private String gambar;

        public BukuViewHolder(@NonNull View itemView) {
            super(itemView);

            imgGambar = itemView.findViewById(R.id.iv_buku);
            judul = itemView.findViewById(R.id.tv_judul_buku);
            penulis = itemView.findViewById(R.id.tv_penulis);
            penerbit = itemView.findViewById(R.id.tv_penerbit);
            genre = itemView.findViewById(R.id.tv_genre);
            tahun = itemView.findViewById(R.id.tv_tahun);
            sinopsis = itemView.findViewById(R.id.tv_sinopsis);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View v) {

            Intent bukaBuku = new Intent(context, TampilActivity.class);
            bukaBuku.putExtra("ID", idBuku);
            bukaBuku.putExtra("JUDUL", judul.getText().toString());
            bukaBuku.putExtra("PENULIS", penulis.getText().toString());
            bukaBuku.putExtra("PENERBIT", penerbit.getText().toString());
            bukaBuku.putExtra("GENRE", genre.getText().toString());
            bukaBuku.putExtra("TAHUN", tahun.getText().toString());
            bukaBuku.putExtra("GAMBAR", gambar);
            bukaBuku.putExtra("SINOPSIS", sinopsis.getText().toString());
            context.startActivity(bukaBuku);
        }

        @Override
        public boolean onLongClick(View v) {

            Intent bukaInput = new Intent(context, InputActivity.class);
            bukaInput.putExtra("OPERASI", "update");
            bukaInput.putExtra("ID", idBuku);
            bukaInput.putExtra("JUDUL", judul.getText().toString());
            bukaInput.putExtra("PENULIS", penulis.getText().toString());
            bukaInput.putExtra("PENERBIT", penerbit.getText().toString());
            bukaInput.putExtra("GENRE", genre.getText().toString());
            bukaInput.putExtra("TAHUN", tahun.getText().toString());
            bukaInput.putExtra("GAMBAR", gambar);
            bukaInput.putExtra("SINOPSIS", sinopsis.getText().toString());
            context.startActivity(bukaInput);
            return true;
        }
    }
}
