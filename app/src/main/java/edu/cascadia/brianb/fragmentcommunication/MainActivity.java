package edu.cascadia.brianb.fragmentcommunication;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends Activity
        // ******* Implement the interface in the MainActivity ******
        // ******* You can implement multiple interface here ********
        implements ItemFragment.OnFragmentInteractionListener, SimpleFragment.OnFragmentInteractionListener{

        ItemFragment itemFragment; // display Item Fragment

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // return if Activity is being restored, no need to recreate GUI
        if (savedInstanceState != null)
            return;

        // always display ItemFragment when the app is first launched
        itemFragment = new ItemFragment();

        FragmentTransaction transaction =
                getFragmentManager().beginTransaction();
        transaction.add(R.id.fragment_container, itemFragment);
        transaction.addToBackStack(null);
        transaction.commit();

        // check whether layout contains fragmentContainer (phone layout);
        // ItemFragment is always displayed
//        if (findViewById(R.id.fragment_container) != null) {
//            // create ItemFragment
//            //onFragmentInteraction();
//            itemFragment = new ItemFragment();
//
//            FragmentTransaction transaction =
//                    getFragmentManager().beginTransaction();
//            transaction.add(R.id.fragment_container, itemFragment);
//            transaction.addToBackStack(null);
//            transaction.commit();
//        }
    }


    // ******* This method is called from ItemFragment ************
    public void onFragmentInteraction(String id)
    {
       //SimpleFragment.newInstance(id);

        FragmentTransaction transaction =
                getFragmentManager().beginTransaction();
        //transaction.replace(SimpleFragment.newInstance(id), null);
        transaction.replace(R.id.fragment_container, new SimpleFragment());
        //transaction.replace(R.id.fragment_container, SimpleFragment.newInstance(id));
        transaction.addToBackStack(null);
        transaction.commit();

    }

    // ***** this method is called from SimpleFragment *******************
    public void onFragmentInteraction(Uri uri)
    {
        ItemFragment itemFragment = new ItemFragment();

        FragmentTransaction transaction =
                getFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, itemFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

}
