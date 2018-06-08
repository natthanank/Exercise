package com.example.admin.exercise.exercise3;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.admin.exercise.R;
import com.example.admin.exercise.exercise4.FourActivity;
import com.example.admin.exercise.exercise5.FiveActivity;
import com.example.admin.exercise.exercise6.SixActivity;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link LoginFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link LoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private EditText userEdit, passwordEdit;
    private Button loginBtn;
    private String userID, password;
    private int requestCode;

    public LoginFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static LoginFragment newInstance() {
        LoginFragment fragment = new LoginFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getActivity().getIntent().getExtras();
        requestCode = bundle.getInt("request_code");

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_login, container, false);
        userEdit = rootView.findViewById(R.id.username_edit);
        passwordEdit = rootView.findViewById(R.id.password_edit);
        loginBtn = rootView.findViewById(R.id.loginBtn);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userID = userEdit.getText().toString();
                password = passwordEdit.getText().toString();

                if (requestCode == FourActivity.LOGIN_RESULT) {
                    Intent i = new Intent(getActivity(), FourActivity.class);
                    i.putExtra("userID", userID);
                    if (userID.equals("Natthanan") && password.equals("stupid")) {
                        i.putExtra("result", "Successful");
                    } else {
                        i.putExtra("result", "Failed");
                    }
                    getActivity().setResult(FourActivity.LOGIN_RESULT, i);
                    getActivity().finish();
                } else if (requestCode == FiveActivity.LOGIN_RESULT) {
                    if (userID.equals("Natthanan") && password.equals("stupid")) {
                        Intent i = new Intent(getActivity(), FiveActivity.class);
                        i.putExtra("userID", userID);
                        getActivity().setResult(FiveActivity.LOGIN_RESULT, i);
                        getActivity().finish();
                    } else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                        builder.setTitle("Try Again")
                                .setMessage("Please Check your login details")
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        dialogInterface.dismiss();
                                    }
                                });
                        builder.create();
                        builder.show();
                        userEdit.setText("");
                        passwordEdit.setText("");
                    }
                } else if (requestCode == SixActivity.LOGIN_RESULT) {
                    if (userID.equals("Natthanan") && password.equals("stupid")) {
                        Intent i = new Intent(getActivity(), SixActivity.class);
                        i.putExtra("userID", userID);
                        getActivity().setResult(SixActivity.LOGIN_RESULT, i);
                        getActivity().finish();
                    } else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                        builder.setTitle("Try Again")
                                .setMessage("Please Check your login details")
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        dialogInterface.dismiss();
                                    }
                                });
                        builder.create();
                        builder.show();
                        userEdit.setText("");
                        passwordEdit.setText("");
                    }
                }
                else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    if (userID.equals("Natthanan") && password.equals("stupid")) {
                        builder.setTitle("Success")
                                .setMessage("Login Successful")
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        dialogInterface.dismiss();
                                    }
                                });
                    } else {
                        builder.setTitle("Try Again")
                                .setMessage("Please Check your login details")
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        dialogInterface.dismiss();
                                    }
                                });
                    }
                    builder.create();
                    builder.show();
                    userEdit.setText("");
                    passwordEdit.setText("");
                }



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
