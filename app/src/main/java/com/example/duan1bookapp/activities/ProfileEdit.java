package com.example.duan1bookapp.activities;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.duan1bookapp.Constants;
import com.example.duan1bookapp.HttpStatus;
import com.example.duan1bookapp.R;
import com.example.duan1bookapp.databinding.ActivityProfileEditBinding;
import com.example.duan1bookapp.models.Address;
import com.example.duan1bookapp.models.Customer;
import com.example.duan1bookapp.models.CustomerDataManager;
import com.example.duan1bookapp.models.ImageData;
import com.example.duan1bookapp.retrofit.CustomerApi;
import com.example.duan1bookapp.retrofit.IComicAPI;
import com.example.duan1bookapp.retrofit.RetrofitService;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileEdit extends AppCompatActivity {

    private CustomerDataManager customerDataManager;

    private FirebaseAuth firebaseAuth;
    //progress dialog
    private ProgressDialog progressDialog;
    private static final int REQUEST_PICK_IMAGE = 1;
    private EditText editText1;
    private EditText editText2;
    private EditText editText3;
    private EditText editText4;
    private EditText editText5;
    private EditText editText6;
    private Button uploadButton;
    private ImageButton imageButton;
    private ShapeableImageView shapeableImageView;
    private static final String TAG ="PROFILE_EDIT_TAG";
    private Uri imageUrl = null;
    private final Handler handler = new Handler();

    private String uploadedImageUrl = "";
    private RetrofitService retrofitService = new RetrofitService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_edit);
        customerDataManager = new CustomerDataManager(this);
        Customer customer = customerDataManager.getUser();
        //setup firebase auth
        //setup progress dialog
        progressDialog=new ProgressDialog(this);
        progressDialog.setTitle("Please wait");
        progressDialog.setCanceledOnTouchOutside(false);//don't dismiss while clicking outside of progress dialog
        //setup firebase auth
        firebaseAuth=FirebaseAuth.getInstance();

        imageButton = findViewById(R.id.Edit_cs_backBtn);
        editText1 = findViewById(R.id.cs_update_edit_name);
        editText2 = findViewById(R.id.cs_update_edit_password);
        editText3 = findViewById(R.id.cs_update_edit_password_confirm);
        editText4 = findViewById(R.id.cs_update_edit_birthday);
        editText5 = findViewById(R.id.cs_update_edit_Street);
        editText6 = findViewById(R.id.cs_update_edit_City);
        uploadButton = findViewById(R.id.CS_updateBtn);

        editText1.setText(customer.getCustomerName());
        editText2.setText(customer.getCustomerPassword());
        editText3.setText(customer.getCustomerPassword());
        editText4.setText(customer.getCustomerbirthDate());
        editText5.setText(customer.getAddress().getStreet());
        editText6.setText(customer.getAddress().getCity());
        shapeableImageView = (ShapeableImageView) findViewById(R.id.profile_edit_imageView);

        if(!customer.getAvatar_url().isEmpty()){
            Glide.with(ProfileEdit.this).load(Constants.URL_IMAGE_AVATar+ customer.getAvatar_url()).timeout(500).into(shapeableImageView);
        }
        imageButton.setOnClickListener(v -> onBackPressed());
        shapeableImageView.setOnClickListener(v -> showImageAttachMenu());
        uploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                update(customer);
            }
        });

    }

    private void update(Customer customer){
        progressDialog.setMessage("Updating Profile");
        progressDialog.show();

        String newName = editText1.getText().toString();
        String newPassword = editText2.getText().toString();
        String newPasswordConfirm = editText3.getText().toString();
        String newBirthDate = editText4.getText().toString();
        String newStreet = editText5.getText().toString();
        String newCity = editText6.getText().toString();
        int idcustomer = customer.getId();
        int idddress = customer.getAddress().getId();
        if(!newPassword.equals(newPasswordConfirm)){
            Toast.makeText(this, "Save failed! Passwords do not match", Toast.LENGTH_SHORT).show();
            return;
        }

        String last36Chars = "";
        if (uploadedImageUrl != null && uploadedImageUrl.length() >= 36) {
            int length = uploadedImageUrl.length();
            last36Chars = uploadedImageUrl.substring(length - 36);
        }
        CustomerApi customerApi = retrofitService.getRetrofit().create(CustomerApi.class);
        customerApi.update(idcustomer, newName, newPassword, newBirthDate, last36Chars, idddress, newStreet, newCity)
                .enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if (response.isSuccessful()) {
                            progressDialog.dismiss();
                            // HTTP request was successful
                            int statusCode = response.code(); // Retrieve the HTTP status code
                            // Check the status code to differentiate between success and failure
                            if (statusCode == HttpStatus.OK) {
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(getApplication(), "Cap Nhap Thanh Cong!", Toast.LENGTH_SHORT).show();
                                    }
                                }, 1000);
                            } else {
                                // Handle other success cases based on different status codes if necessary
                            }
                        } else {
                            // Handle unsuccessful response (e.g., server error, client error)
                            // Show an error message or take appropriate action based on the response
                        }
                    }
                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        progressDialog.dismiss();
                        Log.d("123456", Objects.requireNonNull(t.getMessage()));
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getApplication(), "Cap Nhap That Bai Vui long Kiem tra lai", Toast.LENGTH_SHORT).show();
                            }
                        }, 1000);
                    }
                });


    }


    private void showImageAttachMenu() {
        //init/setup popup menu
        PopupMenu popupMenu=new PopupMenu(this,shapeableImageView);
        popupMenu.getMenu().add(Menu.NONE,0,0,"Camera");
        popupMenu.getMenu().add(Menu.NONE,1,1,"Gallery");

        popupMenu.show();
        //handle menu item click
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                //get id of item clicked
                int which=item.getItemId();
                if(which == 0){
                    //camera clicked
                    pickImageCamera();
                }else if(which == 1){
                    //gallery clicked
                    pickImageGallery();
                }
                return false;
            }
        });
    }

    private void pickImageCamera() {
        //intent to pick images from camera
        ContentValues values=new ContentValues();
        values.put(MediaStore.Images.Media.TITLE,"New Pick");
        values.put(MediaStore.Images.Media.DESCRIPTION,"Sample Image Description");
        imageUrl =getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,values);

        Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT,imageUrl);
        cameraActivityResultLauncher.launch(intent);
    }

    private void pickImageGallery() {
        //intent to pick images from Gallery
        Intent intent=new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        galleryActivityResultLauncher.launch(intent);
    }
    private ActivityResultLauncher<Intent> cameraActivityResultLauncher=registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    //user to handle result of camera intent
                    //get uri of image
                    if(result.getResultCode() == Activity.RESULT_OK){
                        Log.d(TAG, "onActivityResult: Picked From camera"+imageUrl);
                        Intent data=result.getData();//no need here as in camera case we already have images in imageUri variable
                        shapeableImageView.setImageURI(imageUrl);

                    }else {
                        Toast.makeText(ProfileEdit.this, "Cacnelled", Toast.LENGTH_SHORT).show();
                    }
                }
            }
    );
    private ActivityResultLauncher<Intent> galleryActivityResultLauncher=registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    //user to handle result of camera intent
                    //get uri of image
                    if(result.getResultCode() == Activity.RESULT_OK){
                        Log.d(TAG, "onActivityResult: "+imageUrl);
                        Intent data=result.getData();
                        imageUrl =data.getData();
                        Log.d(TAG, "onActivityResult: "+imageUrl);
                        shapeableImageView.setImageURI(imageUrl);
                        uploadImage();

                    }else {
                        Toast.makeText(ProfileEdit.this, "Cacnelled", Toast.LENGTH_SHORT).show();
                    }
                }
            }
    );
    private void uploadImage() {
        Log.d(TAG, "uploadImage: Uploading profile image...");
        progressDialog.setMessage("Updating profile image");
        progressDialog.show();

        //image path and name,use uid to replace previous
        String filePathAndName ="ProfileImages/"+firebaseAuth.getUid();

        //storage reference
        StorageReference reference= FirebaseStorage.getInstance().getReference(filePathAndName);
        reference.putFile(imageUrl)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Log.d(TAG, "onSuccess: Profile image uploaded");
                        Log.d(TAG, "onSuccess: Getting url of uploaded image");
                        Task<Uri> uriTask=taskSnapshot.getStorage().getDownloadUrl();
                        while (!uriTask.isSuccessful());
                         uploadedImageUrl=""+uriTask.getResult();
                        Log.d(TAG, "onSuccess: Uploaded Image Url"+uploadedImageUrl);
                        progressDialog.dismiss();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d(TAG, "onFailure: Failed to upload image due to"+e.getMessage());
                        progressDialog.dismiss();
//                        Toast.makeText(ProfileEditActivity.this, "Failed to upload image due to", Toast.LENGTH_SHORT).show();
                    }
                });
    }


}