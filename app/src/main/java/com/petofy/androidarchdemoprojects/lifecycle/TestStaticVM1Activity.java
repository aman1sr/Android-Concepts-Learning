package com.petofy.androidarchdemoprojects.lifecycle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.petofy.androidarchdemoprojects.R;
import com.petofy.androidarchdemoprojects.databinding.ActivityTestStaticVm1Binding;

/*
 * viewModel androd doc overview (https://developer.android.com/topic/libraries/architecture/viewmodel)
 * lifecycle callback (https://medium.com/proggy-blast/lifecycle-methods-of-android-activity-scenario-based-question-8d3e14169297)
 *
 * todo: how to pass the viewModel to another activity without creating static
 *  how to share VM from activity1 -> activity2
 * */
public class TestStaticVM1Activity extends AppCompatActivity {
    private ActivityTestStaticVm1Binding binding;

    private static TestStaticViewModel vm;   // NOTE: creating static ViewModel stays in memory even if the Activity is destroyed
//    private  TestStaticViewModel vm;

    public final ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == RESULT_OK) {
                        String newData = result.getData().getStringExtra("key");
                        Log.d("TestStaticVM_d", "onActivityResult: data" + newData);
//                        vm.setNewData(newData);
                    }
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTestStaticVm1Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Log.d("TestStaticVM_d", "onCreate: Activity1");
        Log.d("TestStaticVM_d", "observing VM before intializing: " + vm);
        vm = new ViewModelProvider(this).get(TestStaticViewModel.class);
        Log.d("TestStaticVM_d", "observing VM after intilized: " + vm);
        vm.setData();
        vm.getUpperData().observe(this, livedata -> {
            Log.d("TestStaticVM_d", "observing: " + livedata);
            String list = "";
            for (String str : livedata) {
                list += str + ",";
            }
            binding.textView2.setText(list);
        });



        binding.button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), TestStaticVM2Activity.class);
               startActivity(intent);
               finish();
            }
        });

        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), TestStaticVM2Activity.class);
                intent.putExtra("data", "ABC");
                activityResultLauncher.launch(intent);
            }
        });


    }
//    public  TestStaticViewModel getSharedViewModel() {
//        return vm;
//    }
    public static TestStaticViewModel getSharedViewModel() {
        return vm;
    }
    @Override
    protected void onStart() {
        super.onStart();
        Log.d("TestStaticVM_d", "onStart: Activity1");
    }
    @Override
    protected void onPause() {
        super.onPause();
        Log.d("TestStaticVM_d", "onPause:::::::: Activity1");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("TestStaticVM_d", "onStop:::::::: Activity1");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("TestStaticVM_d", "onDestroy:::::::: Activity1");

    }
}