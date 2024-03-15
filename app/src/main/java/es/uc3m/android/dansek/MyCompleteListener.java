package es.uc3m.android.dansek;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class MyCompleteListener implements OnCompleteListener<AuthResult> {

    Context context;
    FirebaseAuth mAuth;

    public MyCompleteListener(Context context, FirebaseAuth mAuth) {
        this.context = context;
        this.mAuth = mAuth;
    }

    @Override
    public void onComplete(@NonNull Task<AuthResult> task) {
        if (task.isSuccessful()) {
            FirebaseUser user = mAuth.getCurrentUser();
            Log.d(this.getClass().getName(),
                    "createUserWithEmail:success " + user);
            context.startActivity(new Intent(context, PrincipalScreen.class));
        } else {
            Log.w(this.getClass().getName(), "createUserWithEmail:failure",
                    task.getException());

            String message = context.getResources().getString(R.string.auth_failed) + " " +
                    task.getException().getMessage();
            Snackbar snackbar = Snackbar.make(((Activity) context).getCurrentFocus(),
                    message, Snackbar.LENGTH_LONG);
            snackbar.show();
        }
    }
}