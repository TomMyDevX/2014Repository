package com.tmd.systemtools;

import com.android.clicnect.libdev.R;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnLongClickListener;
import android.widget.Button;
public class KeyBoardFragment extends Fragment {

private Button one_btn;
private Button two_btn;
private Button three_btn;
private Button four_btn;
private Button five_btn;
private Button six_btn;
private Button seven_btn;
private Button eight_btn;
private Button nine_btn;
private Button zero_btn;
private Button back_btn;
private Button done_btn;

private StringBuilder sb;

public onKeyBoardEvent keyboardEventListener;


private int maxLength=10;
private int currentLength;

public static KeyBoardFragment newInstance(String EditTextValue)
{
    KeyBoardFragment fragment=new KeyBoardFragment();
    Bundle bundle=new Bundle();
    bundle.putString("et_value", EditTextValue);
    fragment.setArguments(bundle);
    return fragment;
}

@Override
public void onAttach(Activity activity) {
    try{

        keyboardEventListener=(onKeyBoardEvent)activity;
    }
    catch(ClassCastException e)
    {
        Log.e("ClassCastException in KeyBoardFragment row 50",activity.toString()+" must implement onKeyboardEvent");
        e.printStackTrace();
    }

    super.onAttach(activity);
}

@Override
public View onCreateView(LayoutInflater inflater, ViewGroup container,
        Bundle savedInstanceState) {
    // TODO Auto-generated method stub
    sb=new StringBuilder(getArguments().getString("et_value"));
    currentLength=sb.length();
    View rootView=inflater.inflate(R.layout.numpad_keyboard, container, false);
    one_btn=(Button)rootView.findViewById(R.id.one_btn);
    one_btn.setOnClickListener(new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            add("1");
        }
    });
    two_btn=(Button)rootView.findViewById(R.id.two_btn);
    two_btn.setOnClickListener(new View.OnClickListener() {

        @Override
        public void onClick(View v) {

            add("2");
        }
    });
    three_btn=(Button)rootView.findViewById(R.id.three_btn);
    three_btn.setOnClickListener(new View.OnClickListener() {

        @Override
        public void onClick(View v) {

            add("3");

        }
    });
    four_btn=(Button)rootView.findViewById(R.id.four_btn);
    four_btn.setOnClickListener(new View.OnClickListener() {

        @Override
        public void onClick(View v) {

            add("4");
        }
    });
    five_btn=(Button)rootView.findViewById(R.id.five_btn);
    five_btn.setOnClickListener(new View.OnClickListener() {

        @Override
        public void onClick(View v) {

            add("5");

        }
    });
    six_btn=(Button)rootView.findViewById(R.id.six_btn);
    six_btn.setOnClickListener(new View.OnClickListener() {

        @Override
        public void onClick(View v) {


            add("6");
        }
    });
    seven_btn=(Button)rootView.findViewById(R.id.seven_btn);
    seven_btn.setOnClickListener(new View.OnClickListener() {

        @Override
        public void onClick(View v) {

            add("7");
        }
    });
    eight_btn=(Button)rootView.findViewById(R.id.eight_btn);
    eight_btn.setOnClickListener(new View.OnClickListener() {

        @Override
        public void onClick(View v) {

            add("8");

        }
    });
    nine_btn=(Button)rootView.findViewById(R.id.nine_btn);
    nine_btn.setOnClickListener(new View.OnClickListener() {

        @Override
        public void onClick(View v) {

            add("9");
        }
    });
    zero_btn=(Button)rootView.findViewById(R.id.zero_btn);
    zero_btn.setOnClickListener(new View.OnClickListener() {

        @Override
        public void onClick(View v) {

            if(sb.length()>0)
                add("0");
        }
    });
    back_btn=(Button)rootView.findViewById(R.id.back_btn);
    back_btn.setOnClickListener(new View.OnClickListener() {

        @Override
        public void onClick(View v) {

            if(sb.length()>0)
            {
                currentLength--;
                sb.deleteCharAt((sb.length())-1);
                keyboardEventListener.backButtonPressed(sb.toString());
            }
        }
    });
    back_btn.setOnLongClickListener(new View.OnLongClickListener() {

        @Override
        public boolean onLongClick(View v) {

            currentLength=0;
            sb=new StringBuilder();
            keyboardEventListener.backLongPressed();
            return false;
        }
    });
    done_btn=(Button)rootView.findViewById(R.id.done_btn);
    done_btn.setOnClickListener(new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            keyboardEventListener.doneButtonPressed(sb.toString());
        }
    });
    return rootView;
}
public interface onKeyBoardEvent
{
    public void numberIsPressed(String total);
    public void doneButtonPressed(String total);
    public void backLongPressed();
    public void backButtonPressed(String total);
}

public int getMaxLength() {
    return maxLength;
}

public void setMaxLength(int maxLength) {
    this.maxLength = maxLength;
}
public void add(String num)
{
    currentLength++;
    if(currentLength<=maxLength)
    {

        sb.append(num);
        keyboardEventListener.numberIsPressed(sb.toString());
    }
    else
        currentLength--;
}
}