package com.people.trombinoscope.util;

import android.graphics.Bitmap;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.people.trombinoscope.BuildConfig;

import java.io.ByteArrayOutputStream;
import java.util.Calendar;

/**
 * Created by patrickvongpraseuth on 22/09/2017.
 */

public class FirebaseUtils {

    private static final String TAG = "FirebaseUtils";

    private static FirebaseUtils instance;

    public FirebaseUtils() {
        if (instance == null) {
            instance = new FirebaseUtils();
        }
    }

    public static FirebaseUtils getInstance() {
        return instance;
    }

    public static void uploadFile(String id, Bitmap bitmap) {
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReferenceFromUrl(BuildConfig.STORAGE_URL);
        StorageReference mountainImagesRef = storageRef.child("images/" + id + "_" + Calendar.getInstance().getTime().getTime() + ".jpg");
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 20, baos);
        byte[] data = baos.toByteArray();
        UploadTask uploadTask = mountainImagesRef.putBytes(data);
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle unsuccessful uploads
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                // taskSnapshot.getMetadata() contains file metadata such as size, content-type, and download URL.
                Uri downloadUrl = taskSnapshot.getDownloadUrl();
//                sendMsg("" + downloadUrl, 2);
                Log.d("downloadUrl-->", "" + downloadUrl);
            }
        });

    }

}
