package mx.itesm.quesodesuaperro;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;


public class Ayuda extends Fragment {

    private TextView ayuda;

    public Ayuda() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ayuda, container, false);
        ayuda = (TextView) view.findViewById(R.id.Ayudatext);
        ayuda.setMovementMethod( new ScrollingMovementMethod());
        return view;
    }

}
