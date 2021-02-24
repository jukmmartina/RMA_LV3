package martinamagdalenajukic.ferit.lv3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements ButtonClickListener{

    private MessageFragment mMessageFragment;
    private InputFragment mInputFragment;
    private FragmentManager mFragmentManager;
    private boolean mSwitched=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFragmentManager=getSupportFragmentManager();
        setUpFragments();
        setUpListeners();
    }

    private void setUpListeners() {
        findViewById(R.id.btSwitch).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                switchFragments();
                FragmentTransaction fragmentTransaction=mFragmentManager.beginTransaction();
                if(mSwitched){
                    fragmentTransaction.replace(R.id.firstFragment, mInputFragment);
                    fragmentTransaction.replace(R.id.secondFragment, mMessageFragment);
                }
                else{
                    fragmentTransaction.replace(R.id.firstFragment, mMessageFragment);
                    fragmentTransaction.replace(R.id.secondFragment, mInputFragment);
                }
                fragmentTransaction.commit();
            }
        });
    }

    private void switchFragments() {
        mSwitched=!mSwitched;
        removeFragments();
    }

    private void removeFragments() {
        FragmentTransaction fragmentTransaction=mFragmentManager.beginTransaction();
        fragmentTransaction.remove(mInputFragment);
        fragmentTransaction.remove(mMessageFragment);
        fragmentTransaction.commit();
        mFragmentManager.executePendingTransactions();  //obavlja sve transakcije prije ove
    }


    private void setUpFragments() {
        mInputFragment= new InputFragment();
        mMessageFragment= new MessageFragment();
        FragmentTransaction fragmentTransaction=mFragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.firstFragment, mMessageFragment);
        fragmentTransaction.add(R.id.secondFragment, mInputFragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onButtonClicked(String message) {
        mMessageFragment.displayMessage(message);
    }
}