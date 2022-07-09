package com.example.house;

import static android.content.Context.MODE_PRIVATE;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;


import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class User_set_gameAdapter extends RecyclerView.Adapter<User_set_gameAdapter.ViewHolder> {

    private ArrayList<User_set_gameItem> gameList;
    private Context context;
    ActivityResultLauncher<Intent> launcher;
    SharedPreferences sharedPreferences;
    String TAG = "어댑터";

    public void context(Context context) {
        Log.d("테스트", "context = " + context);
        this.context = context;
    }
    public void abclauncher(ActivityResultLauncher<Intent> launcher) {
        this.launcher = launcher;
    }

    public User_set_gameAdapter(){

    }

    @NonNull
    @Override
    /**
     * 리스트 아이템을 가져와서 레이아웃을 실체화 해줌
     * 실체화를 해주는 아이가 Inflater
     * **/
    public User_set_gameAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder() 호출됨");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_set_item, parent, false);
        return new ViewHolder(view);
    }

    /**
     * 액티비티에서 받아온 데이터를 바인딩해줌.
     * **/
    @Override
    public void onBindViewHolder(@NonNull User_set_gameAdapter.ViewHolder holder,int position) {
        Log.d(TAG, "onBindViewHolder() 호출됨");
        holder.onBind(gameList.get(holder.getAdapterPosition()));
    }


    /**
     * 리사이클러뷰 리스트 사이즈를 불러옴
     * **/
    @Override
    public int getItemCount() {
        Log.d(TAG, "getItemCount() 호출됨");
        Log.d(TAG,"ListSize : " + gameList.size());
        return gameList.size();
    }

//  뷰와 데이터를 연결해줌.
    public void setGameList(ArrayList<User_set_gameItem> list) {
        Log.d(TAG, "setGameList() 호출됨");
        this.gameList = list;
        Log.d(TAG, "어댑터 리스트 : " + gameList);
        notifyDataSetChanged();
    }

    /**
     * 뷰홀더 생성
     * **/
    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView game_image;
        TextView game_name;
        TextView comment;
        ImageButton removebutton;
        ImageButton editbutton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Log.d(TAG, "ViewHolder() 호출됨");
            game_image = (ImageView)itemView.findViewById(R.id.game_image);
            game_name = (TextView)itemView.findViewById(R.id.game_name);
            comment = (TextView)itemView.findViewById(R.id.comment);

            editbutton = itemView.findViewById(R.id.edit_Button);

            //삭제 버튼
            removebutton = itemView.findViewById(R.id.remove_button);
            removebutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d(TAG, "삭제 버튼 클릭");
                    gameList.remove(getAdapterPosition());
                    SharedPreferences sharedPreferences = context.getSharedPreferences("게임목록",MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.remove(String.valueOf(getAdapterPosition()));
//                    editor.clear();
                    editor.commit();
                    notifyDataSetChanged();

// 팝업 버튼
//                    PopupMenu popup = new PopupMenu(itemView.getContext(),view);
//                    popup.getMenuInflater().inflate(R.menu.popup,popup.getMenu());
//                    popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
//                        @Override
//                        public boolean onMenuItemClick(MenuItem menuItem) {
//                            return false;
//                        }
//                    });
                }
            });

//            수정 버튼 클릭
            editbutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d(TAG, "수정 버튼 클릭");
                    Intent i = new Intent(context,AddRecyclerviewActivity.class);
                    i.putExtra("포지션",getAdapterPosition());
                    i.putExtra("visibility","야마최고");
                    Log.d(TAG, "어댑터에서 포지션 넘기는 중 : " + getAdapterPosition());
                    launcher.launch(i);
//                    gameList.set(getAdapterPosition(),);
                }
            });

        }

        void onBind(User_set_gameItem item) {
            Log.d(TAG, "onBind() 호출됨");
            game_image.setImageResource(item.getIdNum());
            game_name.setText(item.getName());
            comment.setText(item.getComment());

        }

    }
}