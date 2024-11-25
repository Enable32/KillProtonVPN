package com.killprotonvpn;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        executeCommands();

        Intent intent = new Intent();
        intent.setComponent(new ComponentName("ch.protonvpn.android", "ch.protonvpn.android.RoutingActivity"));
        startActivity(intent);

        System.exit(0);
    }

    private void executeCommands() {
        try {
            // Запуск команды с правами root
            Process process = Runtime.getRuntime().exec("su");

            // Пишем команды в поток процесса
            process.getOutputStream().write("am force-stop ch.protonvpn.android\n".getBytes());
            process.getOutputStream().write("pm clear ch.protonvpn.android\n".getBytes());
            // process.getOutputStream().write("am start -n ch.protonvpn.android/ch.protonvpn.android.RoutingActivity".getBytes());
            process.getOutputStream().flush();
            process.getOutputStream().close();

            process.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }


}