package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;
import java.util.ArrayList;
import de.hdodenhof.circleimageview.CircleImageView;

public class MyAdapter extends RecyclerView.Adapter {

    ArrayList<item> datas;
    Context context;

    public MyAdapter(ArrayList<item> datas, Context context) {
        this.datas = datas;
        this.context= context;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View itemView= inflater.inflate(R.layout.recycler_item,parent,false);
        VH vh=new VH(itemView);

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position)
    {
        VH vh=(VH)holder;

        item ite= datas.get(position);
        vh.tvName.setText(ite.name);
        vh.tvMsg.setText(ite.msg);

        Glide.with(context).load(ite.icon).into(vh.ivIcon);
        Glide.with(context).load(ite.img).into(vh.ivImg);
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }


    class VH extends RecyclerView.ViewHolder{

        CircleImageView ivIcon;
        TextView tvName;
        TextView tvMsg;
        ImageView ivImg;

        public VH(@NonNull View itemView){
            super(itemView);

            ivIcon=itemView.findViewById(R.id.iv_icon);
            tvName=itemView.findViewById(R.id.tv_name);
            tvMsg=itemView.findViewById(R.id.tv_msg);
            ivImg=itemView.findViewById(R.id.iv_img);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position= getLayoutPosition();

                    String name=datas.get(position).name;
                    String first = "영상 확인";
                    String second = "사용자 정보 입력";
                    String third = "신고";

                    if (name == first) {
                        Intent intent= new Intent(context, DetailActivity.class);
                        intent.putExtra("Name",name);
                        context.startActivity(intent);
                    }
                    else if (name == second ) {
                        Intent intent= new Intent(context, DetailActivity.class);
                        intent.putExtra("Name",name);
                        context.startActivity(intent);
                    }
                }
            });



        }
    }
}