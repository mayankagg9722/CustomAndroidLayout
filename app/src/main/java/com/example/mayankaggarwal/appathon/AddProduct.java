package com.example.mayankaggarwal.appathon;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class AddProduct extends AppCompatActivity implements AdapterView.OnItemSelectedListener {


    private static final int REQUEST_PERMISSION = 1;
    private static final int img=1;
    private static final int cap=2;

    EditText name,price,desc;
    Button addproduct;
    ImageView addimage;
    static  int flag=0;
    static Uri capturedImageUri = null;
    int number=0;
    File file;

    Intent mainData;

    ProgressDialog pd ;


    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#a856b1")));
        getSupportActionBar().setTitle("Add Product");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        name=(EditText)findViewById(R.id.name);
        price=(EditText)findViewById(R.id.price);
        desc=(EditText)findViewById(R.id.desc);
        addimage=(ImageView) findViewById(R.id.addimage);

        pd= new ProgressDialog(AddProduct.this);
        pd.setMessage("Uploading");


        Spinner spinner = (Spinner) findViewById(R.id.spinner);

        spinner.setOnItemSelectedListener(this);

        List<String> categories = new ArrayList<String>();


        String serialized = Prefs.getPrefs("storenamelist",this);
        serialized=serialized.replace("[","").replace("]","");
        List<String> list = Arrays.asList(TextUtils.split(serialized, ","));


        for(String n:list){
            categories.add(n);
        }

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(dataAdapter);



        addimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                        && ContextCompat.checkSelfPermission(AddProduct.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(AddProduct.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                            REQUEST_PERMISSION);
                    return;
                }
                AlertDialog.Builder builder=new AlertDialog.Builder(AddProduct.this);
                CharSequence[] array = {"Camera","Gallery"};
                builder.setTitle("Choose");
                builder.setSingleChoiceItems(array, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                      number=which;
                    }
                }).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(number==0){
                            flag=0;
                            Calendar cal = Calendar.getInstance();
                            File dir=new File(Environment.getExternalStorageDirectory()+"/Pik69");
                            if(!dir.exists()){
                                dir.mkdir();
                            }
                            file = new File(dir,(cal.getTimeInMillis() + ".jpg"));
                            if (!file.exists()) {
                                try {
                                    file.createNewFile();
                                } catch (IOException e) {
                                    // TODO Auto-generated catch block
                                    e.printStackTrace();
                                }
                            } else {
                                file.delete();
                                try {
                                    file.createNewFile();
                                } catch (IOException e) {
                                    // TODO Auto-generated catch block
                                    e.printStackTrace();
                                }
                            }
                            capturedImageUri = Uri.fromFile(file);
                            Log.v("uri",capturedImageUri.toString());
                            Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                            intent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, capturedImageUri);
                            startActivityForResult(intent,cap);

                        }else{
                            Intent intent=new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                            intent.setType("image/*");
                            startActivityForResult(Intent.createChooser(intent,"Select Picture"),img);
                        }
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                builder.create();
                builder.show();
            }
        });

        addproduct=(Button)findViewById(R.id.addproduct);

        addproduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadFile(mainData,name.getText().toString(),price.getText().toString(),desc.getText().toString());
            }
        });
    }



    @Override
    public void onRequestPermissionsResult(final int requestCode, @NonNull final String[] permissions, @NonNull final int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted.
//                Prefs.setPrefs("readPermission","1",this);
            } else {
                // User refused to grant permission.
//                Prefs.setPrefs("readPermission","0",this);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        flag = 0;
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == img && resultCode == RESULT_OK && data != null && data.getData() != null) {
            data.getData();
            //Log.e("uridata",data.getData().toString());

            if (data.getData() != null) {
                //Log.v("uri",data.getData().toString());
                Bitmap photo = null;
                try {
                    photo = MediaStore.Images.Media.getBitmap(getContentResolver()
                            , data.getData());
                    flag = 2;
                    photo = getResizedBitmap(photo, 900, 900);
                    addimage.setImageBitmap(photo);
                    mainData=data;
                } catch (IOException e) {
                    e.printStackTrace();
                }

                //Bitmap bitmap = ((BitmapDrawable)imageView.getDrawable()).getBitmap();
                //mFaceOverlayView.setBitmap(bitmap);
            } else if (requestCode == cap && resultCode == RESULT_OK) {
                flag = 0;
                //Bundle extra=data.getExtras();
                //Bitmap photo=(Bitmap)extra.get("data");
                Bitmap photo = null;
                try {
                    photo = MediaStore.Images.Media.getBitmap(getContentResolver()
                            , capturedImageUri);

                    flag = 1;
                        photo = getResizedBitmap(photo, 900, 1200);
                    addimage.setImageBitmap(photo);
                    mainData=data;
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }

    }

    public void uploadFile(Intent data,String name,String price,String desc){
        pd.show();
        Uri uripath= data.getData();
        String uri = getRealPathFromURI( uripath);

        FileInputStream is=null;
        try {
            is = new FileInputStream(uri);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Log.d("tagg","working");

        Data.updateAttendance(this, is,name,price,desc, new Data.UpdateCallback() {
            @Override
            public void onUpdate() {
                pd.dismiss();
            }

            @Override
            public void onFailure() {
                pd.dismiss();
            }
        });
    }

    public String getRealPathFromURI(Uri contentUri) {
        Cursor cursor = null;
        try {
            String[] proj = { MediaStore.Images.Media.DATA };
            cursor =getContentResolver().query(contentUri,  proj, null, null, null);
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    public Bitmap getResizedBitmap(Bitmap bm, int newWidth, int newHeight) {
        int width = bm.getWidth();
        int height = bm.getHeight();
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // CREATE A MATRIX FOR THE MANIPULATION
        Matrix matrix = new Matrix();
        // RESIZE THE BIT MAP
        matrix.postScale(scaleWidth, scaleHeight);

        // "RECREATE" THE NEW BITMAP
        Bitmap resizedBitmap = Bitmap.createBitmap(
                bm, 0, 0, width, height, matrix, false);
        bm.recycle();
        return resizedBitmap;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//        String item = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item
//        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
