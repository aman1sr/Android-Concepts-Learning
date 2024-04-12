package com.petofy.androidarchdemoprojects.lifecycle;

import static android.app.PendingIntent.getActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.petofy.androidarchdemoprojects.databinding.ActivityTestStaticVm2Binding;

public class TestStaticVM2Activity extends AppCompatActivity {
    private ActivityTestStaticVm2Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTestStaticVm2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Log.d("TestStaticVM_d", "onCreate: Activity2");
        Intent intent = getIntent();
        String receivedIntent = intent.getStringExtra("data");

//        TestStaticViewModel vm =  new ViewModelProvider(this).get(TestStaticViewModel.class);

        TestStaticViewModel vm =  TestStaticVM1Activity.getSharedViewModel();
        Log.d("TestStaticVM_d", "observing VM activity2: " + vm);
        if (receivedIntent != null) {
            vm.setNewData(receivedIntent);
            vm.showList();
        }



        /*why isn't possible: (https://g.co/gemini/share/1497f5fa033e)*/
//        TestStaticVM1Activity obj = new TestStaticVM1Activity();
//        TestStaticViewModel vm =  obj.getSharedViewModel();

        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (receivedIntent != null) {
                    Intent resultIntent = new Intent();
                    resultIntent.putExtra("key", "surya");
                    setResult(RESULT_OK, resultIntent);
                    finish();
                } else {
                    Intent intent = new Intent(v.getContext(), TestStaticVM1Activity.class);
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("TestStaticVM_d", "onStart: Activity2");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("TestStaticVM_d", "onPause: Activity2");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("TestStaticVM_d", "onStop: Activity2");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("TestStaticVM_d", "onDestroy: Activity2");

    }
}