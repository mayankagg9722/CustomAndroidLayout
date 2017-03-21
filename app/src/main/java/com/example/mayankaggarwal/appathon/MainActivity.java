package com.example.mayankaggarwal.appathon;

import android.Manifest;
import android.animation.Animator;
import android.annotation.TargetApi;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewAnimationUtils;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    ImageView optionone,optiontwo,optionthree,optiononeset,optiontwoset,optionthreeset;
    CardView card,extraone,extratwo,cardtwo,cardthree,extrathree;
    LinearLayout calltext;
    TextView bugs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


//        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
//                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
//        drawer.setDrawerListener(toggle);
//        toggle.syncState();
//
//        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
//        navigationView.setNavigationItemSelectedListener(this);

//        toggle.setDrawerIndicatorEnabled(false);
        toolbar.setNavigationIcon(R.drawable.navicon);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                drawer.openDrawer(GravityCompat.START);
            }
        });

        Drawable drawable = ContextCompat.getDrawable(getApplicationContext(),R.drawable.help);
        toolbar.setOverflowIcon(drawable);


        optionone=(ImageView)findViewById(R.id.optionone);
        optiontwo=(ImageView)findViewById(R.id.optiontwo);
        optionthree=(ImageView)findViewById(R.id.optionthree);
        optiononeset=(ImageView)findViewById(R.id.optiononeset);
        optiontwoset=(ImageView)findViewById(R.id.optiontwoset);
        optionthreeset=(ImageView)findViewById(R.id.optionthreeset);
        card=(CardView)findViewById(R.id.card);
        cardtwo=(CardView)findViewById(R.id.cardtwo);
        cardthree=(CardView)findViewById(R.id.cardthree);
        extraone=(CardView)findViewById(R.id.extraone);
        extratwo=(CardView)findViewById(R.id.extratwo);
        extrathree=(CardView)findViewById(R.id.extrathree);

        calltext=(LinearLayout) findViewById(R.id.call);

        calltext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = "+919585418609";
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null));
                startActivity(intent);
            }
        });

        bugs=(TextView)findViewById(R.id.bugs);

        bugs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto","client@gmail.com", null));
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Report Bugs");
                emailIntent.putExtra(Intent.EXTRA_TEXT, "No Bugs");
                startActivity(Intent.createChooser(emailIntent, "Send email..."));
            }
        });


        optionone.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                CardView view = (CardView) findViewById(R.id.card);
                int centerX = view.getRight();
                int centerY = view.getBottom();

                int startRadius = 0;
                int endRadius = (int) Math.hypot(view.getWidth(), view.getHeight());

                Animator anim =
                        ViewAnimationUtils.createCircularReveal(view, centerX, centerY, startRadius, endRadius);

                anim.start();
                anim.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        extraone.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                });
            }
        });

        optiononeset.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(final View v) {
                final CardView view = (CardView) findViewById(R.id.extraone);
                int centerX = view.getRight();
                int centerY = view.getBottom();

                int startRadius = 0;
                int endRadius = (int) Math.hypot(view.getWidth(), view.getHeight());

                Animator anim =
                        ViewAnimationUtils.createCircularReveal(view, centerX, centerY, startRadius, endRadius);

                anim.start();
                anim.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        extraone.setVisibility(View.GONE);
                        card.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                });
            }
        });

        optiontwo.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                CardView view = (CardView) findViewById(R.id.cardtwo);
                int centerX = view.getRight();
                int centerY = view.getBottom();

                int startRadius = 0;
                int endRadius = (int) Math.hypot(view.getWidth(), view.getHeight());

                Animator anim =
                        ViewAnimationUtils.createCircularReveal(view, centerX, centerY, startRadius, endRadius);

                anim.start();
                anim.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        extratwo.setVisibility(View.VISIBLE);
                        cardtwo.setVisibility(View.GONE);
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                });
            }
        });

        optiontwoset.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(final View v) {
                final CardView view = (CardView) findViewById(R.id.extratwo);
                int centerX = view.getRight();
                int centerY = view.getBottom();

                int startRadius = 0;
                int endRadius = (int) Math.hypot(view.getWidth(), view.getHeight());

                Animator anim =
                        ViewAnimationUtils.createCircularReveal(view, centerX, centerY, startRadius, endRadius);

                anim.start();
                anim.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        extratwo.setVisibility(View.GONE);
                        cardtwo.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                });
            }
        });


        optionthree.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                CardView view = (CardView) findViewById(R.id.cardthree);
                int centerX = view.getRight();
                int centerY = view.getBottom();

                int startRadius = 0;
                int endRadius = (int) Math.hypot(view.getWidth(), view.getHeight());

                Animator anim =
                        ViewAnimationUtils.createCircularReveal(view, centerX, centerY, startRadius, endRadius);

                anim.start();
                anim.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        extrathree.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                });
            }
        });

        optionthreeset.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(final View v) {
                final CardView view = (CardView) findViewById(R.id.extrathree);
                int centerX = view.getRight();
                int centerY = view.getBottom();

                int startRadius = 0;
                int endRadius = (int) Math.hypot(view.getWidth(), view.getHeight());

                Animator anim =
                        ViewAnimationUtils.createCircularReveal(view, centerX, centerY, startRadius, endRadius);

                anim.start();
                anim.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        extrathree.setVisibility(View.GONE);
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                });
            }
        });



        card.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                CardView view = (CardView) findViewById(R.id.card);
                int centerX = view.getTop();
                int centerY = view.getLeft();

                int startRadius = 0;
                int endRadius = (int) Math.hypot(view.getWidth(), view.getHeight());

                Animator anim =
                        ViewAnimationUtils.createCircularReveal(view, centerX, centerY, startRadius, endRadius);

                anim.start();
                startActivity(new Intent(MainActivity.this,AddContact.class));
            }
        });

        cardthree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,AddProduct.class));
            }
        });

        cardtwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,SeeOrder.class));
            }
        });

    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
