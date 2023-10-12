package com.example.foodrunner;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.google.firebase.auth.FirebaseAuth;

public class dialog_helper extends AppCompatDialogFragment {
    FirebaseAuth mAuth;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        mAuth = FirebaseAuth.getInstance();
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
                .setMessage("Do you want to signout?")
                .setPositiveButton("Yes, confirm", this::on_signout_clicked)
                .setNegativeButton("Cancel", (dialogInterface, i) -> dialogInterface.dismiss());
        return builder.create();
    }

    private void on_signout_clicked(DialogInterface dialogInterface, int i) {
        Toast.makeText(getContext(), "Signing Out", Toast.LENGTH_SHORT).show();
        mAuth.signOut();
        Intent intent = new Intent(getContext(), sign_in_screen.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("Exit", true);
        startActivity(intent);
    }
}