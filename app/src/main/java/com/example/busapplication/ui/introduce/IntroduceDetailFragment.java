package com.example.busapplication.ui.introduce;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.busapplication.R;

import java.util.ArrayList;

public class IntroduceDetailFragment extends Fragment {
    private ArrayList<String> array;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        super.onCreateView(inflater, container, savedInstanceState);
        Bundle bundle = getArguments();
        array = bundle.getStringArrayList("data");
        return inflater.inflate(R.layout.fragment_introduce_detail, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        ImageView imageView = (ImageView) view.findViewById(R.id.bus_image);
        TextView textView1 = (TextView) view.findViewById(R.id.textView_busName);
        TextView textView2 = (TextView) view.findViewById(R.id.textView_busDetails);
        Button button = (Button) view.findViewById(R.id.button_busWebsite);
        imageView.setImageResource(Integer.parseInt(array.get(2)));
        textView1.setText(array.get(1));
        textView2.setText(array.get(4));
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(array.get(3)));
                startActivity(intent);
            }
        });
    }
}
