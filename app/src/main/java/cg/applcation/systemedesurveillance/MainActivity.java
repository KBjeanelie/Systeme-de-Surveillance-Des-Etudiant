package cg.applcation.systemedesurveillance;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;


import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import cg.applcation.systemedesurveillance.administrateur.AddClassroom;
import cg.applcation.systemedesurveillance.administrateur.AddClassroomFragment;
import cg.applcation.systemedesurveillance.administrateur.AddStudent;
import cg.applcation.systemedesurveillance.administrateur.AddStudentFragment;
import cg.applcation.systemedesurveillance.administrateur.AddSubject;
import cg.applcation.systemedesurveillance.administrateur.AddSubjectFragment;
import cg.applcation.systemedesurveillance.administrateur.AddTeacher;
import cg.applcation.systemedesurveillance.administrateur.AddTeacherFragment;
import cg.applcation.systemedesurveillance.administrateur.AddUserAccount;
import cg.applcation.systemedesurveillance.administrateur.DisplayTeacherFragment;
import cg.applcation.systemedesurveillance.administrateur.DisplayTeachers;
import cg.applcation.systemedesurveillance.administrateur.ProfileFragment;
import cg.applcation.systemedesurveillance.authentification.LoginActivity;
import cg.applcation.systemedesurveillance.models.UserAccount;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private FrameLayout frameLayout;
    private NavigationView navigationView;
    private SwitchCompat darkModeSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeViews();
        toggleDrawer();
        initializeDefaultFragment(savedInstanceState,0);
    }


    /**
     * Initialize all widgets
     */
    private void initializeViews() {
        toolbar = findViewById(R.id.toolbar_id);
        toolbar.setTitle(R.string.toolbar_title);
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.drawer_layout_id);
        frameLayout = findViewById(R.id.framelayout_id);
        navigationView = findViewById(R.id.navigationview_id);
        navigationView.setNavigationItemSelectedListener(MainActivity.this);
    }

    /**
     * Checks if the savedInstanceState is null - onCreate() is ran
     * If so, display fragment of navigation drawer menu at position itemIndex and
     * set checked status as true
     * @param savedInstanceState
     * @param itemIndex
     */
    private void initializeDefaultFragment(Bundle savedInstanceState, int itemIndex){
        if (savedInstanceState == null){
            MenuItem menuItem = navigationView.getMenu().getItem(itemIndex).setChecked(true);
            onNavigationItemSelected(menuItem);
        }
    }

    /**
     * Creates an instance of the ActionBarDrawerToggle class:
     * 1) Handles opening and closing the navigation drawer
     * 2) Creates a hamburger icon in the toolbar
     * 3) Attaches listener to open/close drawer on icon clicked and rotates the icon
     */
    private void toggleDrawer() {
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
    }

    @Override
    public void onBackPressed() {
        //Checks if the navigation drawer is open -- If so, close it
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        // If drawer is already close -- Do not override original functionality
        else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.nav_profile_id:
                getSupportFragmentManager().beginTransaction().replace(R.id.framelayout_id, new ProfileFragment())
                        .commit();
                closeDrawer();
                break;
            case R.id.nav_add_teacher_id:
                getSupportFragmentManager().beginTransaction().replace(R.id.framelayout_id, new AddTeacherFragment())
                        .commit();
                deSelectCheckedState();
                closeDrawer();
                break;
            case R.id.nav_display_teacher_id:
                getSupportFragmentManager().beginTransaction().replace(R.id.framelayout_id, new DisplayTeacherFragment())
                        .commit();
                deSelectCheckedState();
                closeDrawer();
                break;
            case R.id.nav_add_student_id:
                getSupportFragmentManager().beginTransaction().replace(R.id.framelayout_id, new AddStudentFragment())
                        .commit();
                deSelectCheckedState();
                closeDrawer();
                break;
            case R.id.nav_add_classroom_id:
                getSupportFragmentManager().beginTransaction().replace(R.id.framelayout_id, new AddClassroomFragment())
                        .commit();
                deSelectCheckedState();
                closeDrawer();
                break;
            case R.id.nav_add_subject_id:_id:
                getSupportFragmentManager().beginTransaction().replace(R.id.framelayout_id, new AddSubjectFragment())
                        .commit();
                deSelectCheckedState();
                closeDrawer();
                break;
        }
        return true;
    }

    /**
     * Checks if the navigation drawer is open - if so, close it
     */
    private void closeDrawer(){
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }

    /**
     * Iterates through all the items in the navigation menu and deselects them:
     * removes the selection color
     */
    private void deSelectCheckedState(){
        int noOfItems = navigationView.getMenu().size();
        for (int i=0; i<noOfItems;i++){
            navigationView.getMenu().getItem(i).setChecked(false);
        }
    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_menu, menu); //your file name
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.about:
                //your code
                // EX : call intent if you want to swich to other activity
                return true;
            case R.id.logout:
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}