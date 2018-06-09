package com.example.admin.exercise.exercise6;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.admin.exercise.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Second2Fragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Second2Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Second2Fragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    RadioGroup sexGroup, ageGroup;
    RadioButton male, female, child, adult;
    Button nextBtn;

    public Second2Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Second2Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Second2Fragment newInstance(String param1, String param2) {
        Second2Fragment fragment = new Second2Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_second2, container, false);
        BMI bmi = getArguments().getParcelable("bmi");
        nextBtn = rootView.findViewById(R.id.nextFragment);
        sexGroup = rootView.findViewById(R.id.sex_group);
        ageGroup = rootView.findViewById(R.id.age_group);
        male = rootView.findViewById(R.id.male);
        female = rootView.findViewById(R.id.female);
        child = rootView.findViewById(R.id.child);
        adult = rootView.findViewById(R.id.adult);
        sexGroup.check(R.id.male);
        ageGroup.check(R.id.child);

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bmi.setAdult((ageGroup.getCheckedRadioButtonId() == R.id.adult) ? true : false);
                bmi.setMale((sexGroup.getCheckedRadioButtonId() == R.id.male) ? true : false);
                Bundle bundle = new Bundle();
                bundle.putParcelable("bmi", bmi);
                Second3Fragment second3Fragment = Second3Fragment.newInstance(null,null);
                second3Fragment.setArguments(bundle);
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.fragment_tab_frame, second3Fragment);
                transaction.commit();
            }
        });
        return rootView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
