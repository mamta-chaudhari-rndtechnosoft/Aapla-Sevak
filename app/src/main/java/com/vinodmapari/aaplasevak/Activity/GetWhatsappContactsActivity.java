package com.vinodmapari.aaplasevak.Activity;

import static android.view.View.GONE;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.telephony.PhoneNumberUtils;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.oginotihiro.cropview.CropUtil;
import com.oginotihiro.cropview.CropView;
import com.vinodmapari.aaplasevak.BuildConfig;
import com.vinodmapari.aaplasevak.Model.Constants;
import com.vinodmapari.aaplasevak.Model.GetContact;
import com.vinodmapari.aaplasevak.Model.Wp_Img;
import com.vinodmapari.aaplasevak.PostImageChooseDialog;
import com.vinodmapari.aaplasevak.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Objects;

import okio.Utf8;

public class GetWhatsappContactsActivity extends AppCompatActivity implements PostImageChooseDialog.AlertPositiveListener{

    RecyclerView rv;
    TextView textView_empty;
    private SearchView searchView;
    LottieAnimationView loader, empty_icon;
    WhatsappAdapter whatsappAdapter;
    String mobile1, message;
    ImageView iv;
    CheckBox checkBox;
    private final String KEY_RECYCLER_STATE="recycler_state";
    private static Bundle mBundleRecyclerView;
    Button btn;
    private Uri selectedImage;
    private Uri outputFileUri;
    private static String APP_TEMP_FOLDER = "";
    ArrayList<GetContact> getContacts;
    String houseNo, row, series, watersupply, colony, image;
    ArrayList<Wp_Img> imgs;
    EditText etMessage;
    private String filePath;
    private String selectedPostImg = "";
    private static final int REQUEST_PERMISSIONS = 100;
    private static final int PICK_IMAGE_REQUEST = 101;
    public static final int CREATE_POST_IMG = 5;
//    boolean flag=false;
    Bitmap bitmap;
    int color;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_contacts);



        etMessage = findViewById(R.id.etMessage);



        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorWhite));
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_back));

        toolbar.setTitle(Html.fromHtml("<b>" + "Contacts" + "</b>"));
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        getContacts = new ArrayList<>();
        imgs = new ArrayList<>();



        ///////////////////////////////////////////////////////


        Intent in = getIntent();
        // houseNo = in.getStringExtra("house_no");
        colony = in.getStringExtra("colony_id");
        series = in.getStringExtra("series_id");
        row = in.getStringExtra("row_id");
        watersupply = in.getStringExtra("water_Supply_id");

        //Toast.makeText(this, "W: " + watersupply  + "" +"C: " + colony + " " + "S: " + series + " " + "R: " + row, Toast.LENGTH_SHORT).show();


        //////////////////////////////////////////////////////////////////

        textView_empty = findViewById(R.id.tv_empty_search);
        textView_empty.setVisibility(View.VISIBLE);
        textView_empty.setText(R.string.type_search);
        loader = findViewById(R.id.loader);
        empty_icon = findViewById(R.id.empty_icon);
        loader.setVisibility(GONE);
        empty_icon.setVisibility(GONE);
        rv = findViewById(R.id.rv_contacts);


        rv.setLayoutManager(new LinearLayoutManager(GetWhatsappContactsActivity.this));

        getContactList();


    }


    private void getContactList() {

        final RequestQueue requestQueue = Volley.newRequestQueue(GetWhatsappContactsActivity.this);

        loader.setVisibility(View.VISIBLE);
        empty_icon.setVisibility(GONE);


        StringRequest stringRequest = new StringRequest(Request.Method.GET, Constants.whatsapp + "&series_id=" + series + "&colony_id=" + colony + "&row_id=" + row + "&watersupply_id=" + watersupply, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                getContacts.clear();
                getContacts = new ArrayList<>();
                try {
                    JSONObject json = new JSONObject(response);
                   // Toast.makeText(GetWhatsappContactsActivity.this, "onResponse"+response, Toast.LENGTH_SHORT).show();

                      Log.d("TAG", "onResponse: "+response);

                    //Toast.makeText(GetWhatsappContactsActivity.this, ""+response, Toast.LENGTH_SHORT).show();

                    JSONArray jsonArray = json.getJSONArray("WHATSAPP_NO");

                    for (int i = 0; i < jsonArray.length(); i++) {

                        JSONObject object = jsonArray.getJSONObject(i);
                        String id = object.getString("id");
                        String name = object.getString("name");
                        mobile1 = object.getString("mobile1");
                        String middle_name = object.getString("middle_name");
                        String surname = object.getString("surname");


                        getContacts.add(new GetContact(id, name, mobile1, middle_name, surname));
                    }
                    if (getContacts.size() > 0) {
                        loader.setVisibility(GONE);
                        textView_empty.setVisibility(GONE);
                        empty_icon.setVisibility(GONE);
                        rv.setVisibility(View.VISIBLE);
                        rv.setAdapter(new WhatsappAdapter(GetWhatsappContactsActivity.this, getContacts));
                    } else {
                        loader.setVisibility(GONE);
                        rv.setVisibility(GONE);
                        textView_empty.setText("No matches found");
                        textView_empty.setVisibility(View.VISIBLE);
                        empty_icon.setVisibility(View.VISIBLE);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    loader.setVisibility(GONE);
                    rv.setVisibility(GONE);
                    textView_empty.setVisibility(View.VISIBLE);
                    textView_empty.setText("No matches found");
                    empty_icon.setVisibility(View.VISIBLE);
//                    Toast.makeText(GetWhatsappContactsActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                   // Log.d("TAG1", "onResponse: "+e.getMessage());
                    //Toast.makeText(SearchActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                requestQueue.stop();
                loader.setVisibility(GONE);
                rv.setVisibility(GONE);
                textView_empty.setVisibility(View.VISIBLE);
//                Log.d("TAG2", "onResponse: "+error.getMessage());
               // Toast.makeText(GetWhatsappContactsActivity.this, ""+error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
        requestQueue.add(stringRequest);
    }


    @Override
    protected void onResume()
    {
        ///getContactList();
//        Toast.makeText(GetWhatsappContactsActivity.this, "heloo", Toast.LENGTH_SHORT).show();

        // Constants.flag=true;


        super.onResume();
        if(mBundleRecyclerView!=null)
        {
            Parcelable listst=mBundleRecyclerView.getParcelable(KEY_RECYCLER_STATE);
            rv.getLayoutManager().onRestoreInstanceState(listst);
        }
    }



    public class WhatsappAdapter extends RecyclerView.Adapter<WhatsappAdapter.ViewHolder> {


        private Activity activity;
        ArrayList<GetContact> getContacts;
        Wp_Img wp;

        public WhatsappAdapter(Activity activity, ArrayList<GetContact> getContacts)
        {
            this.activity = activity;
            this.getContacts = getContacts;
        }

        @NonNull
        @Override
        public WhatsappAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(activity).inflate(R.layout.rv_wp_item, parent, false);

            return new WhatsappAdapter.ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull WhatsappAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {


            GetContact getContact = getContacts.get(position);


            holder.tvName.setText(getContact.getSurname() + " " + getContact.getName() + " " + getContact.getMiddle_name());
            holder.tvMob.setText("+91"+getContact.getMobile1());

            holder.ivImage.setOnClickListener(new View.OnClickListener()
            {
                @SuppressLint("ResourceAsColor")
                @Override
                public void onClick(View v) {

                   Constants.flag=true;
                    message = etMessage.getText().toString();


                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse("http://api.whatsapp.com/send?phone=" + holder.tvMob.getText().toString() + "&text=" + message));
                        startActivity(intent);

                        //holder.cd.setBackgroundColor(R.color.colorGrey);



                        holder.cd.setCardBackgroundColor(Color.parseColor("#cccccc"));

                        holder.ivImage.setEnabled(false);
                        holder.ivImage.setVisibility(GONE);
                        holder.send.setVisibility(View.VISIBLE);



                }
            });







        }

        @Override
        public int getItemCount() {
            return getContacts.size();
        }






        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView tvName, tvMob, send;
            ImageView ivImage;
            CardView cd;


            public ViewHolder(@NonNull View itemView) {
                super(itemView);

                tvName = itemView.findViewById(R.id.tv_name);
                tvMob = itemView.findViewById(R.id.mobile1);
                ivImage = itemView.findViewById(R.id.img);
                cd = itemView.findViewById(R.id.cd_search);
                send = itemView.findViewById(R.id.send);

            }
        }
    }

    private void getImage() {
        imgs.clear();
        imgs = new ArrayList<>();

        final RequestQueue requestQueue = Volley.newRequestQueue(GetWhatsappContactsActivity.this);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, Constants.whatsapp_img, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


                try {
                    JSONObject jsonObject = new JSONObject(response);
                    //    Log.d("TAG", "onResponse: " + response);

                    JSONArray jsonArray = jsonObject.getJSONArray("WTSIMG");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                        String id = jsonObject1.getString("id");
                        image = jsonObject1.getString("image");

                        imgs.add(new Wp_Img(id, image));
                        Glide.with(GetWhatsappContactsActivity.this).load(image).thumbnail(Glide.with(GetWhatsappContactsActivity.this).load(R.drawable.loading)).into(iv);



                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                requestQueue.stop();
            }
        });
        requestQueue.add(stringRequest);

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }


    public static String getImageUrlWithAuthority(Context context, Uri uri, String fileName) {

        InputStream is = null;

        if (uri.getAuthority() != null) {

            try {

                is = context.getContentResolver().openInputStream(uri);
                Bitmap bmp = BitmapFactory.decodeStream(is);

                return writeToTempImageAndGetPathUri(context, bmp, fileName).toString();

            } catch (FileNotFoundException e) {

                e.printStackTrace();

            } finally {

                try {

                    if (is != null) {

                        is.close();
                    }

                } catch (IOException e) {

                    e.printStackTrace();
                }
            }
        }

        return null;
    }

    public static String writeToTempImageAndGetPathUri(Context inContext, Bitmap inImage, String fileName) {

        String file_path = Environment.getExternalStorageDirectory() + File.separator + APP_TEMP_FOLDER;
        File dir = new File(file_path);
        if (!dir.exists()) dir.mkdirs();

        File file = new File(dir, fileName);

        try {

            FileOutputStream fos = new FileOutputStream(file);

            inImage.compress(Bitmap.CompressFormat.JPEG, 100, fos);

            fos.flush();
            fos.close();

        } catch (FileNotFoundException e) {

            Toast.makeText(inContext, "Error occured. Please try again later.", Toast.LENGTH_SHORT).show();

        } catch (IOException e) {

            e.printStackTrace();
        }

        return Environment.getExternalStorageDirectory() + File.separator + APP_TEMP_FOLDER + File.separator + "photo.jpg";
    }


    @Override
    public void onImageFromGallery() {
        imageFromGallery();
    }

    @Override
    public void onImageFromCamera() {
       // imageFromCamera();
    }

    public void imageFromGallery() {
        checkPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE,REQUEST_PERMISSIONS);

        if ((ContextCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) && (ContextCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)) {
            if ((ActivityCompat.shouldShowRequestPermissionRationale(GetWhatsappContactsActivity.this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) && (ActivityCompat.shouldShowRequestPermissionRationale(GetWhatsappContactsActivity.this,
                    Manifest.permission.READ_EXTERNAL_STORAGE))) {

            } else {
                ActivityCompat.requestPermissions(GetWhatsappContactsActivity.this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_PERMISSIONS);
            }
        } else {
            //  profile_scrollView.setVisibility(View.GONE);

            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
            iv.setVisibility(View.VISIBLE);
        }
    }

    public void checkPermission(String permission, int requestCode)
    {
        if (ContextCompat.checkSelfPermission(GetWhatsappContactsActivity.this, permission) == PackageManager.PERMISSION_DENIED) {

            // Requesting the permission
            ActivityCompat.requestPermissions(GetWhatsappContactsActivity.this, new String[] { permission }, requestCode);
        }
        else {
            //      Toast.makeText(ProfileActivity.this, "Permission already granted", Toast.LENGTH_SHORT).show();
        }
    }

    public void imageFromCamera() {


        try {

            File root = new File(Environment.getExternalStorageDirectory(), getResources().getString(R.string.app_name));

            if (!root.exists()) {

                root.mkdirs();
            }

            File sdImageMainDirectory = new File(root, "photo.jpg");

            outputFileUri = FileProvider.getUriForFile(this, BuildConfig.APPLICATION_ID + ".provider", sdImageMainDirectory);

            Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
            cameraIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            startActivityForResult(cameraIntent, CREATE_POST_IMG);


        } catch (Exception e) {

            Toast.makeText(this, "Error occured. Please try again later.", Toast.LENGTH_SHORT).show();
        }
    }


    public String getPath(Uri uri) {
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        cursor.moveToFirst();
        String document_id = cursor.getString(0);
        document_id = document_id.substring(document_id.lastIndexOf(":") + 1);
        cursor.close();

        cursor = getContentResolver().query(
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                null, MediaStore.Images.Media._ID + " = ? ", new String[]{document_id}, null);
        cursor.moveToFirst();
        String path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
        cursor.close();

        return path;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {



            Uri picUri = data.getData();
            filePath = getPath(picUri);

            if (filePath != null) {
                try {

                    Log.d("filePath", String.valueOf(filePath));
                    bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), picUri);
//                    uploadBitmap(bitmap);
                    iv.setImageBitmap(bitmap);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else if(filePath == null)
            {
                Toast.makeText(
                        GetWhatsappContactsActivity.this,"no image selected",
                        Toast.LENGTH_LONG).show();

                Intent intent = new Intent(getApplicationContext(),GetWhatsappContactsActivity.class);
                startActivity(intent);
            }

        }


    }



    @Override
    protected void onPause() {
        super.onPause();
        mBundleRecyclerView=new Bundle();
        Parcelable liststate=rv.getLayoutManager().onSaveInstanceState();
        mBundleRecyclerView.putParcelable(KEY_RECYCLER_STATE,liststate);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }
}