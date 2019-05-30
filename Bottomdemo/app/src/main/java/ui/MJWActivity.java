package ui;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.app.Activity;
import android.content.Intent;
import android.os.Environment;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;



import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;



import com.example.yun.bottomdemo.R;

public class MJWActivity extends Activity {

    OkHttpClient okHttpClient=new OkHttpClient();

    private TextView mTvResult_1;
    private TextView mTvResult_2;
    private TextView mTvResult_3;
    private TextView mTvResult_4;
    private TextView mfilestate_dmsbtexcoords;
    private Button mButton_dmsbtexcoords_delete;
    private String fileName="";
    private String fileState="";

    //    private String mBaseUrl="http://47.98.170.72:8084/downfile/"+fileName;
    private String mBaseUrl="";

    private ProgressBar mProgressBar_1;
    private ProgressBar mProgressBar_2;
    private ProgressBar mProgressBar_3;
    private ProgressBar mProgressBar_4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mjw);

        mTvResult_1=(TextView) findViewById(R.id.tv_result_1);
        mTvResult_2=(TextView) findViewById(R.id.tv_result_2);
        mTvResult_3=(TextView) findViewById(R.id.tv_result_3);
        mTvResult_4=(TextView) findViewById(R.id.tv_result_4);

        mProgressBar_1=(ProgressBar) findViewById(R.id.pgb);
        mProgressBar_2=(ProgressBar) findViewById(R.id.pgb_2);
        mProgressBar_3=(ProgressBar) findViewById(R.id.pgb_3);
        mProgressBar_4=(ProgressBar) findViewById(R.id.pgb_4);


        initfile("mjw");
    }






    public void initfile(String modelName){
        if(modelName.equals("mjw")){
            File file_1 = new File(Environment.getExternalStorageDirectory(),"majunwuverts.txt");
            File file_2 = new File(Environment.getExternalStorageDirectory(),"majunwutexcoords.txt");
            File file_3 = new File(Environment.getExternalStorageDirectory(),"majunwunormals.txt");
            File file_4 = new File(Environment.getExternalStorageDirectory(),"majunwu.jpg");
            if(file_1.exists()&&file_2.exists()&&file_3.exists()&&file_4.exists())
            {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
//                mfilestate_dmsbtexcoords.setText("此模型已经下载");
                        mProgressBar_1.setProgress(100);
                        mProgressBar_2.setProgress(100);
                        mProgressBar_3.setProgress(100);
                        mProgressBar_4.setProgress(100);
                        mTvResult_1.setText("已下载");
                        mTvResult_2.setText("已下载");
                        mTvResult_3.setText("已下载");
                        mTvResult_4.setText("已下载");
                    }
                });
            }else{
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
//                mfilestate_dmsbtexcoords.setText("此模型未下载");
                        mProgressBar_1.setProgress(0);
                        mProgressBar_2.setProgress(0);
                        mProgressBar_3.setProgress(0);
                        mProgressBar_4.setProgress(0);
                        mTvResult_1.setText("未下载");
                        mTvResult_2.setText("未下载");
                        mTvResult_3.setText("未下载");
                        mTvResult_4.setText("未下载");
                    }
                });
            }
        }
    }



    public void down_dmsbtexcoords_onClick(View view) throws IOException {
        String down_file_name_1="majunwutexcoords.txt";
        String down_file_name_2="majunwunormals.txt";
        String down_file_name_3="majunwuverts.txt";
        String down_file_name_4="majunwu.jpg";
        String dmsbtexcoords_down_url_1="http://47.101.207.179:8084/downfile/majunwutexcoords.txt";
        String dmsbtexcoords_down_url_2="http://47.101.207.179:8084/downfile/majunwunormals.txt";
        String dmsbtexcoords_down_url_3="http://47.101.207.179:8084/downfile/majunwuverts.txt";
        String dmsbtexcoords_down_url_4="http://47.101.207.179:8084/downfile/majunwu.jpg";
        doPostFile(view,dmsbtexcoords_down_url_1,dmsbtexcoords_down_url_2,dmsbtexcoords_down_url_3,dmsbtexcoords_down_url_4,down_file_name_1,down_file_name_2,down_file_name_3,down_file_name_4);
    }

    /**
     * 删除文件dmsbtexcoords.txt
     * @param view
     * @throws IOException
     */
    public void delete_dmsbtexcoords_onClick(View view) throws IOException {
        String file_name_delete_1="majunwutexcoords.txt";
        String file_name_delete_2="majunwunormals.txt";
        String file_name_delete_3="majunwuverts.txt";
        String file_name_delete_4="majunwu.jpg";
        deleteFile(view,file_name_delete_1,file_name_delete_2,file_name_delete_3,file_name_delete_4);
    }




    public void deleteFile(View view,String file_name_delete_1,String file_name_delete_2,String file_name_delete_3,String file_name_delete_4){
        /**
         * 删除已存储的文件
         */
        try
        {
            // 找到文件所在的路径并删除该文件
            File file_1 = new File(Environment.getExternalStorageDirectory(), file_name_delete_1);
            File file_2 = new File(Environment.getExternalStorageDirectory(), file_name_delete_2);
            File file_3 = new File(Environment.getExternalStorageDirectory(), file_name_delete_3);
            File file_4 = new File(Environment.getExternalStorageDirectory(), file_name_delete_4);

            if(file_1.exists()&&file_2.exists()&&file_3.exists()&&file_4.exists())
            {
                file_1.delete();
                file_2.delete();
                file_3.delete();
                file_4.delete();
                mProgressBar_1.setProgress(0);
                mProgressBar_2.setProgress(0);
                mProgressBar_3.setProgress(0);
                mProgressBar_4.setProgress(0);
                Toast.makeText(getApplicationContext(), "删除成功！", Toast.LENGTH_SHORT).show();
//                mfilestate_dmsbtexcoords.setText("未下载");
            } else {
                Toast.makeText(getApplicationContext(), "文件不存在！", Toast.LENGTH_SHORT).show();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
//                Looper.prepare();  // Can't create handler inside thread that has not called Looper.prepare()。
            Toast.makeText(getApplicationContext(), "删除失败！", Toast.LENGTH_SHORT).show();
//                Looper.loop();
        }
    }

    public void goLunchermjw(View view){

        Intent intent = new Intent(MJWActivity.this,app.ImageTargetsMJW.class);
        startActivity(intent);

    }

    public void goMain(View view){

        Intent intent = new Intent(MJWActivity.this,ListActivity.class);
        startActivity(intent);

    }


    //下载文件方法
    public void doPostFile(View view, String downUrl_1,String downUrl_2,String downUrl_3,String downUrl_4,final String downFileName_1,final String downFileName_2,final String downFileName_3,final String downFileName_4) throws IOException {
//        FormEncodingBuilder requestBodyBuilder=new FormEncodingBuilder();
////        RequestBody requestBody=requestBodyBuilder.add()
////        RequestBody requestBody=requestBodyBuilder.add("username","hyman").add("password","123");
//        FormEncodingBuilder requestBody=requestBodyBuilder.add("username","hyman").add("password","123");
//        Request.Builder builder=new Request.Builder();
//        builder.url(mBaseUrl).post();


//        RequestBody requestBody = new FormBody.Builder()
//                .add("username","admin").add("password","123").build();
//        Request request = new Request.Builder()
//                .url(mBaseUrl+"login").post(requestBody).build();
//        executeRequest(request);
//        File file=new File(Environment.getExternalStorageDirectory(),"banner.jpg");
//        if(!file.exists()){
//            L.e(file.getAbsolutePath()+"not exit!");
//            return;
//        }
// mime type
//        RequestBody requestBody= RequestBody.create(MediaType.parse("application/octet-stream"),file);
        Request.Builder builder_1=new Request.Builder();
        Request request_1=builder_1.get().url(downUrl_1).build();
        executeRequest(request_1);

        Request.Builder builder_2=new Request.Builder();
        Request request_2=builder_2.get().url(downUrl_2).build();
        executeRequest(request_2);

        Request.Builder builder_3=new Request.Builder();
        Request request_3=builder_3.get().url(downUrl_3).build();
        executeRequest(request_3);

        Request.Builder builder_4=new Request.Builder();
        Request request_4=builder_4.get().url(downUrl_4).build();
        executeRequest(request_4);

        Call call_1=okHttpClient.newCall(request_1);
        Call call_2=okHttpClient.newCall(request_2);
        Call call_3=okHttpClient.newCall(request_3);
        Call call_4=okHttpClient.newCall(request_4);

        call_1.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
//                L.e("onfailure:"+e.getMessage());
                e.printStackTrace();
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {

//                L.e("onResponse:");
//                final String res=response.body().string();
//                L.e(res);
//
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        mTvResult.setText(res);
//                    }
//                });
//                L.e("onResponse:");
                InputStream is=response.body().byteStream();
                final long total =response.body().contentLength();
                Log.e("文件大小",total+"");
                Log.e("文件链接",mBaseUrl);
//                String fileName = URLEncoder.encode(response.body().getFileName(), "UTF-8");
                long sum=0L;
                int len=0;
                File file =new File(Environment.getExternalStorageDirectory(),downFileName_1);
                Log.e("文件名字",downFileName_1);
                byte[] buf=new byte[2048];
                FileOutputStream fos=new FileOutputStream(file);
                while((len=is.read(buf))!=-1){
                    fos.write(buf,0,len);
                    sum+=len;
//                    L.e(sum +"/"+total);
                    final long finalSum = sum;
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
//                            mTvResult.setText(finalSum/(Math.pow(2,20))+"M" +"/"+total/(Math.pow(2,20))+"M");
                            //保留两位有效数字；
                            DecimalFormat df = new DecimalFormat("#.00");
                            mTvResult_1.setText(df.format(finalSum/(Math.pow(2,20)))+"M" +"/"+df.format(total/(Math.pow(2,20)))+"M");
//                            mTvResult1.setText((int)((finalSum*1.0/total)*100)+"/"+finalSum+"/"+total);
                            //不知道为什么，float/float  竟然还需要乘以1.0，不然不能用
                            mProgressBar_1.setProgress((int)((finalSum*1.0/total)*100));
                        }
                    });
                    if(finalSum==total)
                    {
                        Looper.prepare();  // Can't create handler inside thread that has not called Looper.prepare()。
                        Toast.makeText(getApplicationContext(), "下载成功！", Toast.LENGTH_SHORT).show();
                        Looper.loop();
                    }

                }
                fos.flush();
                fos.close();
                is.close();
//                System.out.print("地址"+Environment.getExternalStorageDirectory());
//                L.e("success");
//                L.e("地址"+Environment.getExternalStorageDirectory().toString());
            }
        });


        call_2.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
//                L.e("onfailure:"+e.getMessage());
                e.printStackTrace();
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {

//                L.e("onResponse:");
//                final String res=response.body().string();
//                L.e(res);
//
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        mTvResult.setText(res);
//                    }
//                });
//                L.e("onResponse:");
                InputStream is=response.body().byteStream();
                final long total =response.body().contentLength();
                Log.e("文件大小",total+"");
                Log.e("文件链接",mBaseUrl);
//                String fileName = URLEncoder.encode(response.body().getFileName(), "UTF-8");
                long sum=0L;
                int len=0;
                File file =new File(Environment.getExternalStorageDirectory(),downFileName_2);
                Log.e("文件名字",downFileName_2);
                byte[] buf=new byte[2048];
                FileOutputStream fos=new FileOutputStream(file);
                while((len=is.read(buf))!=-1){
                    fos.write(buf,0,len);
                    sum+=len;
//                    L.e(sum +"/"+total);
                    final long finalSum = sum;
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
//                            mTvResult.setText(finalSum/(Math.pow(2,20))+"M" +"/"+total/(Math.pow(2,20))+"M");
                            //保留两位有效数字；
                            DecimalFormat df = new DecimalFormat("#.00");
                            mTvResult_2.setText(df.format(finalSum/(Math.pow(2,20)))+"M" +"/"+df.format(total/(Math.pow(2,20)))+"M");
//                            mTvResult1.setText((int)((finalSum*1.0/total)*100)+"/"+finalSum+"/"+total);
                            //不知道为什么，float/float  竟然还需要乘以1.0，不然不能用
                            mProgressBar_2.setProgress((int)((finalSum*1.0/total)*100));
                        }
                    });
                    if(finalSum==total)
                    {
                        Looper.prepare();  // Can't create handler inside thread that has not called Looper.prepare()。
                        Toast.makeText(getApplicationContext(), "下载成功！", Toast.LENGTH_SHORT).show();
                        Looper.loop();
                    }

                }
                fos.flush();
                fos.close();
                is.close();
//                System.out.print("地址"+Environment.getExternalStorageDirectory());
//                L.e("success");
//                L.e("地址"+Environment.getExternalStorageDirectory().toString());
            }
        });

        call_3.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
//                L.e("onfailure:"+e.getMessage());
                e.printStackTrace();
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {

//                L.e("onResponse:");
//                final String res=response.body().string();
//                L.e(res);
//
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        mTvResult.setText(res);
//                    }
//                });
//                L.e("onResponse:");
                InputStream is=response.body().byteStream();
                final long total =response.body().contentLength();
                Log.e("文件大小",total+"");
                Log.e("文件链接",mBaseUrl);
//                String fileName = URLEncoder.encode(response.body().getFileName(), "UTF-8");
                long sum=0L;
                int len=0;
                File file =new File(Environment.getExternalStorageDirectory(),downFileName_3);
                Log.e("文件名字",downFileName_3);
                byte[] buf=new byte[2048];
                FileOutputStream fos=new FileOutputStream(file);
                while((len=is.read(buf))!=-1){
                    fos.write(buf,0,len);
                    sum+=len;
//                    L.e(sum +"/"+total);
                    final long finalSum = sum;
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
//                            mTvResult.setText(finalSum/(Math.pow(2,20))+"M" +"/"+total/(Math.pow(2,20))+"M");
                            //保留两位有效数字；
                            DecimalFormat df = new DecimalFormat("#.00");
                            mTvResult_3.setText(df.format(finalSum/(Math.pow(2,20)))+"M" +"/"+df.format(total/(Math.pow(2,20)))+"M");
//                            mTvResult1.setText((int)((finalSum*1.0/total)*100)+"/"+finalSum+"/"+total);
                            //不知道为什么，float/float  竟然还需要乘以1.0，不然不能用
                            mProgressBar_3.setProgress((int)((finalSum*1.0/total)*100));
                        }
                    });
                    if(finalSum==total)
                    {
                        Looper.prepare();  // Can't create handler inside thread that has not called Looper.prepare()。
                        Toast.makeText(getApplicationContext(), "下载成功！", Toast.LENGTH_SHORT).show();
                        Looper.loop();
                    }

                }
                fos.flush();
                fos.close();
                is.close();
//                System.out.print("地址"+Environment.getExternalStorageDirectory());
//                L.e("success");
//                L.e("地址"+Environment.getExternalStorageDirectory().toString());
            }
        });

        call_4.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
//                L.e("onfailure:"+e.getMessage());
                e.printStackTrace();
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {

//                L.e("onResponse:");
//                final String res=response.body().string();
//                L.e(res);
//
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        mTvResult.setText(res);
//                    }
//                });
//                L.e("onResponse:");
                InputStream is=response.body().byteStream();
                final long total =response.body().contentLength();
                Log.e("文件大小",total+"");
                Log.e("文件链接",mBaseUrl);
//                String fileName = URLEncoder.encode(response.body().getFileName(), "UTF-8");
                long sum=0L;
                int len=0;
                File file =new File(Environment.getExternalStorageDirectory(),downFileName_4);
                Log.e("文件名字",downFileName_4);
                byte[] buf=new byte[2048];
                FileOutputStream fos=new FileOutputStream(file);
                while((len=is.read(buf))!=-1){
                    fos.write(buf,0,len);
                    sum+=len;
//                    L.e(sum +"/"+total);
                    final long finalSum = sum;
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
//                            mTvResult.setText(finalSum/(Math.pow(2,20))+"M" +"/"+total/(Math.pow(2,20))+"M");
                            //保留两位有效数字；
                            DecimalFormat df = new DecimalFormat("#.00");
                            mTvResult_4.setText(df.format(finalSum/(Math.pow(2,20)))+"M" +"/"+df.format(total/(Math.pow(2,20)))+"M");
//                            mTvResult1.setText((int)((finalSum*1.0/total)*100)+"/"+finalSum+"/"+total);
                            //不知道为什么，float/float  竟然还需要乘以1.0，不然不能用
                            mProgressBar_4.setProgress((int)((finalSum*1.0/total)*100));
                        }
                    });
                    if(finalSum==total)
                    {
                        Looper.prepare();  // Can't create handler inside thread that has not called Looper.prepare()。
                        Toast.makeText(getApplicationContext(), "下载成功！", Toast.LENGTH_SHORT).show();
                        Looper.loop();
                    }

                }
                fos.flush();
                fos.close();
                is.close();
//                System.out.print("地址"+Environment.getExternalStorageDirectory());
//                L.e("success");
//                L.e("地址"+Environment.getExternalStorageDirectory().toString());
            }
        });
    }


    private void executeRequest(Request request) throws IOException {
        Call call=okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            //            @Override
//            public void onFailure(Request request, IOException e) {
//                L.e("onfailure:"+e.getMessage());
//                e.printStackTrace();
//            }
//
//            @Override
//            public void onResponse(Response response) throws IOException {
////                L.e("onResponse:");
//                final String res=response.body().string();
//                L.e(res);
//            }
            @Override
            public void onFailure(Call call, IOException e) {
                Toast.makeText(getApplicationContext(), "sd卡读取失败", Toast.LENGTH_SHORT).show();
//                L.e("onfailure:"+e.getMessage());
                e.printStackTrace();
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {

//                L.e("onResponse:");
                final String res=response.body().string();
//                L.e(res);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
//                        mTvResult.setText(res);
                    }
                });


            }
        });
    }
}
