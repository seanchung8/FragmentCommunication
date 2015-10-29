package edu.cascadia.brianb.fragmentcommunication;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity implements ItemFragment.OnFragmentInteractionListener, SimpleFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        insertItemFragment();
    }

    private void insertItemFragment() {
        ItemFragment itemFragment = new ItemFragment();

        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, itemFragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onFragmentInteraction(String id) {
        // instantiate simple fragment using factory method
        SimpleFragment simpleFragment = SimpleFragment.newInstance(id);

        FragmentTransaction ft = getFragmentManager().beginTransaction();
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            // do slide left animation when in landscape mode.
            ft.setCustomAnimations(
                    R.anim.slide_in_left, R.anim.slide_in_right, R.anim.slide_in_left, R.anim.slide_in_right);
        } else {
            // do fade in/out animation when in landscape mode.
            ft.setCustomAnimations(
                    R.anim.fade_in, R.anim.fade_out, R.anim.fade_in, R.anim.fade_out);
        }

        ft.replace(R.id.fragment_container, simpleFragment);
        ft.addToBackStack(null);
        ft.commit();

    }

    @Override
    public void onFragmentInteraction() {

        getFragmentManager().popBackStack();
    }

    public void onBackButtonClicked(View view) {
        getFragmentManager().popBackStack();
    }
}
