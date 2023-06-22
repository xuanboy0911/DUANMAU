package com.example.asm.Adapter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.asm.R;
import com.example.asm.SQLite.Dao.LoaiSachDao;
import com.example.asm.SQLite.Dao.SachDao;
import com.example.asm.SQLite.model.LoaiSach;
import com.example.asm.SQLite.model.Sach;
import com.example.asm.ui.home.sach.sachFragment;

import java.util.List;

public class Reciview_Adapter_Sach extends RecyclerView.Adapter<Reciview_Adapter_Sach.viewHolder> {
    Context context;
    List<Sach> list;
    LoaiSachDao loaiSachDao;
    sachFragment sachFragment;
    SachDao sachDao;

    Dialog dialog , dialogSach;
    EditText eTMaS,etTenS,etGiaThue, etTrangSach;
    Button btnSave,btnCancel, btnOK;
    Spinner spinner;
    List<LoaiSach> listLS;
    Spinner_Adapter_LSach spinnerAdapterSach;
    int maLS,Vitri;
    Sach sach;

    public Reciview_Adapter_Sach(Context context, List<Sach> list, sachFragment sachFragment) {
        this.context = context;
        this.list = list;
        this.sachFragment = sachFragment;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_rcv_sach,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        loaiSachDao = new LoaiSachDao(context);
        sachDao = new SachDao(context);
        sach = new Sach();
        if(list != null){
         holder.tvMaSach.setText("Mã sách : "+list.get(position).getMaSach());
         holder.tvTenSach.setText("Tên sách : "+list.get(position).getTenSach());
       //  holder.tvTrangSach.setText("Trang sách : " + list.get(position).getTrangSach());
         holder.tvGiaThue.setText("Giá thuê : "+list.get(position).getGiaThue());
         holder.tvLoaiSach.setText("Tên loại sách : "+loaiSachDao.getId(String.valueOf(list.get(position).getMaLoaiSach())).getTenLoaiSach());

//         holder.btnSetTS.setOnClickListener(v -> {
//             dialogSach = new Dialog(context);
//             dialogSach.setContentView(R.layout.item_dialog_trang_sach);
//
//             etTrangSach = dialogSach.findViewById(R.id.etTrangSach);
//             btnOK = dialogSach.findViewById(R.id.btnOK);
//
//             btnOK.setOnClickListener(v1 -> {
//                 int maSach = list.get(holder.getAdapterPosition()).getMaSach();
//                 String trangSachU = etTrangSach.getText().toString();
//                 if (trangSachU.isEmpty()){
//                     Toast.makeText(context, "Bạn cần nhập trang sách", Toast.LENGTH_SHORT).show();
//
//                 }else {
//                     if (sachDao.updataTrangSach(maSach, Integer.parseInt(trangSachU)) > 0){
//                         Toast.makeText(context, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
//                         sachFragment.setData();
//                     }else {
//                         Toast.makeText(context, "Cập nhật thất bại", Toast.LENGTH_SHORT).show();
//                     }
//                     dialogSach.dismiss();
//                 }
//             });
//             dialogSach.show();
//         });
         holder.ivDelete.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 AlertDialog.Builder builder = new AlertDialog.Builder(context);
                 builder.setTitle("Delete")
                         .setMessage("Bạn muốn xóa")
                         .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                             @Override
                             public void onClick(DialogInterface dialogInterface, int i) {

                                 if(sachDao.delete(String.valueOf(list.get(holder.getAdapterPosition()).getMaSach())) > 0){
                                     Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                                     sachFragment.setData();
                                 }else {
                                     Toast.makeText(context, "Xóa thất bại", Toast.LENGTH_SHORT).show();
                                 }

                                 dialogInterface.cancel();
                             }
                         })
                         .setNegativeButton("No", new DialogInterface.OnClickListener() {
                             @Override
                             public void onClick(DialogInterface dialogInterface, int i) {
                                 dialogInterface.cancel();
                             }
                         });

                 AlertDialog alertDialog = builder.create();
                 alertDialog.show();
             }
         });
         holder.ivUpdate.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 dialog = new Dialog(context);
                 dialog.setContentView(R.layout.item_dialog_sach);
                 eTMaS = dialog.findViewById(R.id.etMaSachDialog);
                 etTenS = dialog.findViewById(R.id.etTenSachDialog);
                 etGiaThue = dialog.findViewById(R.id.etGiaThueDialog);
                 spinner = dialog.findViewById(R.id.spinnerLS);
                 btnSave = dialog.findViewById(R.id.btnSaveS);
                 btnCancel = dialog.findViewById(R.id.btnCancelS);
                 eTMaS.setEnabled(false);
                 // spinner
                 listLS = loaiSachDao.getAll();
                 spinnerAdapterSach = new Spinner_Adapter_LSach(context,listLS);
                 spinner.setAdapter(spinnerAdapterSach);
                 // do du lieu len
                 eTMaS.setText(String.valueOf(list.get(holder.getAdapterPosition()).getMaSach()));
                 etTenS.setText(list.get(holder.getAdapterPosition()).getTenSach());
                 etGiaThue.setText(String.valueOf(list.get(holder.getAdapterPosition()).getGiaThue()));
                 spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                     @Override
                     public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                         maLS = listLS.get(i).getMaLoaiSach();
                     }

                     @Override
                     public void onNothingSelected(AdapterView<?> adapterView) {

                     }
                 });
                 for (int i = 0 ; i < listLS.size() ; i++){
                     if(list.get(holder.getAdapterPosition()).getMaLoaiSach() == listLS.get(i).getMaLoaiSach()){
                         Vitri = i;
                     }
                 }
                 spinner.setSelection(Vitri);

                 // xu li
                 btnCancel.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View view) {
                         dialog.dismiss();
                     }
                 });
                 btnSave.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View view) {
                         if(validate() > 0){
                             sach.setMaSach(Integer.parseInt(eTMaS.getText().toString()));
                             sach.setTenSach(etTenS.getText().toString());
                             sach.setGiaThue(Integer.parseInt(etGiaThue.getText().toString()));
                             sach.setMaLoaiSach(maLS);
                             if(sachDao.updata(sach) > 0){
                                 Toast.makeText(context, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                                 sachFragment.setData();
                             }else {
                                 Toast.makeText(context, "Cập nhật thất bại", Toast.LENGTH_SHORT).show();
                             }
                             dialog.dismiss();
                         }
                     }
                 });

                 dialog.show();

             }
         });
        }
    }

    public int validate(){
        if(etTenS.getText().toString().isEmpty() || etGiaThue.getText().toString().isEmpty()){
            Toast.makeText(context, "Bạn phải nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            return -1;
        }
        return 1;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{
         TextView tvMaSach,tvTenSach,tvGiaThue,tvLoaiSach, tvTrangSach;
         ImageView ivUpdate,ivDelete;

         LinearLayout btnSetTS ;
        public viewHolder(@NonNull View itemView) {
            super(itemView);

            tvMaSach = itemView.findViewById(R.id.tvMaSach);
            tvTenSach = itemView.findViewById(R.id.tvTenSach);
            tvGiaThue = itemView.findViewById(R.id.tvGiaThue);
            tvLoaiSach = itemView.findViewById(R.id.tvLoaiSach);
//            tvTrangSach = itemView.findViewById(R.id.tvTrangSach);
//            btnSetTS = itemView.findViewById(R.id.btnSetTrangSach);

            ivUpdate = itemView.findViewById(R.id.iVUpdateSach);
            ivDelete = itemView.findViewById(R.id.iVXoaSach);

        }
    }
}
