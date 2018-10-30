package vn.edu.poly.testmob201.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import vn.edu.poly.testmob201.R;


public class HolderScore extends RecyclerView.ViewHolder{

    public TextView tvID;
    public TextView tvLop;
    public TextView tvToan;
    public TextView tvVan;
    public ImageView imgTrash;


    public HolderScore(View itemView) {
        super(itemView);

        tvID = itemView.findViewById(R.id.tvId);
        tvLop = itemView.findViewById(R.id.tvLop);
        tvToan = itemView.findViewById(R.id.tvToan);
        tvVan = itemView.findViewById(R.id.tvVan);
        imgTrash = itemView.findViewById(R.id.imgTrash);


    }
}
