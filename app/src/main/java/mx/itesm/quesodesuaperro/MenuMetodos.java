package mx.itesm.quesodesuaperro;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MenuMetodos extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener
{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        findViewById(R.id.ImagenCentral).setVisibility(View.VISIBLE);
        getSupportFragmentManager().beginTransaction().replace(R.id.Contenedor, new Fragment()).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Fragment fragment = new Fragment();
        boolean esMenu = false;
        if (id == R.id.menus){
            esMenu = true;
        } else if (id == R.id.biseccion) {
            fragment  = new Biseccion();
        } else if (id == R.id.internewton) {
            fragment  = new InterpolacionNewton();
        } else if (id == R.id.cramer) {
            fragment  = new Cramer();
        } else if (id == R.id.bairstow){
            fragment = new Bairstow();
        } else if (id == R.id.gauss){
            fragment = new Gauss();
        } else if (id == R.id.lagrange){
            fragment = new GaussSeidel();
        } else if (id == R.id.ayuda){
            fragment = new Ayuda();
        } else if (id == R.id.creditos){
            fragment = new Creditos();
        }
        if(!esMenu){
            findViewById(R.id.ImagenCentral).setVisibility(View.INVISIBLE);
            getSupportFragmentManager().beginTransaction().replace(R.id.Contenedor, fragment).commit();
        } else{
            findViewById(R.id.ImagenCentral).setVisibility(View.VISIBLE);
            getSupportFragmentManager().beginTransaction().replace(R.id.Contenedor, new Fragment()).commit();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
