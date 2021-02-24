package martinamagdalenajukic.ferit.lv3;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class InputFragment extends Fragment /*implements TextWatcher*/ {

    private EditText mEditText;
    private Button mSubmitButton;
    private ButtonClickListener mListner;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_input, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mEditText=view.findViewById(R.id.edMessage);
        mSubmitButton=view.findViewById(R.id.btSubmit);
        setUpListener();
    }

    private void setUpListener() {
        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mListner!=null){
                mListner.onButtonClicked(mEditText.getText().toString());
            }

                //mEditText.addTextChangedListener(this);
            }
        });
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof ButtonClickListener){
            mListner=(ButtonClickListener) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListner=null;
    }

   /* @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        mListner.onButtonClicked(mEditText.getText().toString());
    }

    @Override
    public void afterTextChanged(Editable s) {

    }*/
}