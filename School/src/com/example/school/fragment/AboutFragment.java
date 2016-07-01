package com.example.school.fragment;

import com.example.school.R;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class AboutFragment extends Fragment {

	TextView lblAddress, lblEmail, lblContact, lblDesc;
	TextView txtAddress, txtEmail, txtContact, txtDesc;
	private Typeface _customFontR, _customFontB;

	public AboutFragment() {
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

		View rootView = inflater.inflate(R.layout.fragment_about, container,
				false);
		
		lblAddress =(TextView)rootView.findViewById(R.id.lbl_address);
		lblAddress.setTypeface(_customFontB);
		lblEmail =(TextView)rootView.findViewById(R.id.lbl_email);
		lblEmail.setTypeface(_customFontB);
		lblContact =(TextView)rootView.findViewById(R.id.lbl_contact);
		lblContact.setTypeface(_customFontB);
		lblDesc =(TextView)rootView.findViewById(R.id.lbl_desc);
		lblDesc.setTypeface(_customFontB);

		txtAddress =(TextView)rootView.findViewById(R.id.txt_address);
		txtAddress.setTypeface(_customFontR);
		txtEmail =(TextView)rootView.findViewById(R.id.txt_email);
		txtEmail.setTypeface(_customFontR);
		txtContact =(TextView)rootView.findViewById(R.id.txt_contact);
		txtContact.setTypeface(_customFontR);
		txtDesc =(TextView)rootView.findViewById(R.id.txt_desc);
		txtDesc.setTypeface(_customFontR);

		// Inflate the layout for this fragment
		return rootView;
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
	}

	@Override
	public void onDetach() {
		super.onDetach();
	}
}