package com.example.admin.exercise.exercise9;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.admin.exercise.R;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DetailFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetailFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    EditText idEdit, nameEdit, ageEdit;
    Button backButton, updateButton, deleteButton;
    Database database;
    User user;

    public DetailFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DetailFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DetailFragment newInstance(String param1, String param2) {
        DetailFragment fragment = new DetailFragment();
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

        database = new Database(getContext());
        user = getArguments().getParcelable("user");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_detail2, container, false);

        idEdit = rootView.findViewById(R.id.idEdit);
        idEdit.setText(Integer.toString(user.getId()));
        idEdit.setEnabled(false);
        nameEdit = rootView.findViewById(R.id.nameEdit);
        nameEdit.setText(user.getName());
        ageEdit = rootView.findViewById(R.id.ageEdit);
        ageEdit.setText(Integer.toString(user.getAge()));
        updateButton = rootView.findViewById(R.id.updateBtn);
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user = new User();
                user.setId(Integer.parseInt(idEdit.getText().toString()));
                user.setName(nameEdit.getText().toString());
                user.setAge(Integer.parseInt(ageEdit.getText().toString()   ));
                int affectedRow = database.update(user);
                if (affectedRow > 0) {
                    Toast.makeText(view.getContext(), "Update Successful", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(view.getContext(), "Please Enter the correct information", Toast.LENGTH_SHORT).show();
                }
                ListFragment.ACTION = Database.UPDATE;
                ListFragment.setUpdateUser(user);
                getActivity().onBackPressed();

            }
        });
        deleteButton = rootView.findViewById(R.id.deleteBtn);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int affectedRow = database.delete(user);
                if (affectedRow > 0) {
                    Toast.makeText(view.getContext(), "Delete Successful", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(view.getContext(), "Can't Delete this user", Toast.LENGTH_SHORT).show();
                }
                ListFragment.ACTION = Database.DELETE;
                getActivity().onBackPressed();

            }
        });
        backButton = rootView.findViewById(R.id.backBtn);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
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
