package vn.edu.poly.testmob201.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import vn.edu.poly.testmob201.R;
import vn.edu.poly.testmob201.holder.HolderScore;
import vn.edu.poly.testmob201.model.Score;
import vn.edu.poly.testmob201.sqlitedao.ScoreDAO;


public class AdapterScore extends RecyclerView.Adapter<HolderScore>{
    private Context context;
    private List<Score> scores;
    private ScoreDAO scoreDAO;

    public AdapterScore(Context context, List<Score> scores, ScoreDAO scoreDAO) {
        this.context = context;
        this.scores = scores;
        this.scoreDAO = scoreDAO;
    }


    @NonNull
    @Override
    public HolderScore onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_diem,
                parent, false);
        return new HolderScore(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderScore holder, final int position) {

        final Score score = scores.get(position);
        holder.tvID.setText(score.id);
        holder.tvLop.setText(score.classId);
        holder.tvToan.setText(score.math);
        holder.tvVan.setText(score.vanth);
        holder.imgTrash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                long result = scoreDAO.deleteScore(score.id);
                if (result < 0) {
                    Toast.makeText(context,"Co loi xay ra!!!",Toast.LENGTH_SHORT).show();
                } else {
                    // xoa du lieu tuong ung sau khi xoa trong DB
                    scores.remove(position);
                    // f5 lai listview
                    notifyDataSetChanged();
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return scores.size();
    }
}
