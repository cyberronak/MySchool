package com.example.school.fragment;

import com.example.school.R;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class StudentCornerFragment extends Fragment implements OnClickListener{

	private TextView lblName, lblRollNo, lblContactNo;
	private TextView txtName, txtRollNo, txtContactNo;
	private Button btnViewTest, btnExam, btnTopic, btnRemark;
	private Typeface _customFontR, _customFontB;

    public StudentCornerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
		// load custom fonts
		_customFontR = Typeface.createFromAsset(getActivity().getAssets(),
				"fonts/American_Typewriter_Regular.ttf");
		_customFontB = Typeface.createFromAsset(getActivity().getAssets(),
				"fonts/American_Typewriter_Bold.ttf");

		View rootView = inflater.inflate(R.layout.fragment_student_corner, container, false);
        
        lblName =(TextView)rootView.findViewById(R.id.lbl_std_name);
        lblName.setTypeface(_customFontB);
        lblRollNo =(TextView)rootView.findViewById(R.id.lbl_std_rollno);
        lblRollNo.setTypeface(_customFontB);
        lblContactNo =(TextView)rootView.findViewById(R.id.lbl_std_contact_no);
        lblContactNo.setTypeface(_customFontB);

        txtName =(TextView)rootView.findViewById(R.id.txt_std_name);
        txtName.setTypeface(_customFontR);
        txtRollNo =(TextView)rootView.findViewById(R.id.txt_std_rollno);
        txtRollNo.setTypeface(_customFontR);
        txtContactNo =(TextView)rootView.findViewById(R.id.txt_std_contact_no);
        txtContactNo.setTypeface(_customFontR);
        
        btnViewTest =(Button)rootView.findViewById(R.id.btn_view_test);
        btnViewTest.setTypeface(_customFontB);
        btnExam =(Button)rootView.findViewById(R.id.btn_exam);
        btnExam.setTypeface(_customFontB);
        btnTopic =(Button)rootView.findViewById(R.id.btn_topic);
        btnTopic.setTypeface(_customFontB);
        btnRemark =(Button)rootView.findViewById(R.id.btn_remark);
        btnRemark.setTypeface(_customFontB);
        
        btnViewTest.setOnClickListener(this);
        btnExam.setOnClickListener(this);
        btnTopic.setOnClickListener(this);
        btnRemark.setOnClickListener(this);
        return rootView;
    }

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_view_test:
			
			break;
		case R.id.btn_exam:
			
			break;
		case R.id.btn_topic:
			
			break;
		case R.id.btn_remark:
			
			break;

		default:
			break;
		}
	}
}
