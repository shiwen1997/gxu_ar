package SampleApplication.utils;

/**
 * Created by yun on 2018/12/3.
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import android.content.res.AssetManager;
import android.os.Environment;
import android.util.Log;



public class Majunwu extends MeshObject
{

    private static final String LOGTAG = "Majunwu";

    private Buffer mVertBuff;
    private Buffer mTexCoordBuff;
    private Buffer mNormBuff;
    private Buffer mIndBuff;

    private int indicesNumber = 0;
    private int verticesNumber = 0;

    private AssetManager assetManager;

    public Majunwu(AssetManager inputassetManager)
    {
        this.assetManager = inputassetManager;
        setVerts();
        setTexCoords();
        setNorms();

    }


    double[] majunwu_VERTS;
    double[] majunwu_TEX_COORDS;
    double[] majunwu_NORMS;

//    InputStream inputFile = null;
    FileInputStream inputFile = null;

    private int loadVertsFromModel()
            throws IOException
    {
        try
        {
//            inputFile = assetManager.open(fileName);

            File f = new File(Environment.getExternalStorageDirectory(), "/majunwuverts.txt");


            inputFile = new FileInputStream(f);

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(inputFile));

            String line = reader.readLine();

            //ex.24168 3 floatsdata in 1 line
//            int floatsToRead = Integer.parseInt(line);
            int floatsToRead = 162885;

            majunwu_VERTS = new double[3*floatsToRead];


            for (int i = 0; i < floatsToRead; i++)
            {

                String curline = reader.readLine();
                if( curline.indexOf('/') >= 0 ){
                    i--;
                    continue;
                }

                //split 1 line to 3 data
                String floatStrs[] = curline.split(",");

                majunwu_VERTS[3*i] = Float.parseFloat(floatStrs[0]);
                majunwu_VERTS[3*i+1] = Float.parseFloat(floatStrs[1]);
                majunwu_VERTS[3*i+2] = Float.parseFloat(floatStrs[2]);
                Log.d(LOGTAG, ""+i);
            }

            return floatsToRead;


        } finally
        {
            if (inputFile != null)
                inputFile.close();
        }
    }
    private int loadTexCoordsFromModel()
            throws IOException
    {
        try
        {
            File f = new File(Environment.getExternalStorageDirectory(), "/majunwutexcoords.txt");


            inputFile = new FileInputStream(f);

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(inputFile));

            String line = reader.readLine();

            //ex.24168 3 floatsdata in 1 line
//            int floatsToRead = Integer.parseInt(line);
            int floatsToRead = 162885;

            majunwu_TEX_COORDS = new double[2*floatsToRead];


            for (int i = 0; i < floatsToRead; i++)
            {

                String curline = reader.readLine();
                if( curline.indexOf('/') >= 0 ){
                    i--;
                    continue;
                }

                //split 1 line to 2 data
                String floatStrs[] = curline.split(",");

                majunwu_TEX_COORDS[2*i] = Float.parseFloat(floatStrs[0]);
                majunwu_TEX_COORDS[2*i+1] = Float.parseFloat(floatStrs[1]);
                //banana_TEX_COORDS[3*i+2] = Float.parseFloat(floatStrs[2]);
            }

            return floatsToRead;


        } finally
        {
            if (inputFile != null)
                inputFile.close();
        }
    }

    private int loadNormsFromModel()
            throws IOException
    {
        try
        {
            File f = new File(Environment.getExternalStorageDirectory(), "/majunwunormals.txt");


            inputFile = new FileInputStream(f);

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(inputFile));

            String line = reader.readLine();

            //ex.24168 3 floatsdata in 1 line
//            int floatsToRead = Integer.parseInt(line);
            int floatsToRead = 162885;

            majunwu_NORMS = new double[3*floatsToRead];


            for (int i = 0; i < floatsToRead; i++)
            {

                String curline = reader.readLine();
                if( curline.indexOf('/') >= 0 ){
                    i--;
                    continue;
                }

                //split 1 line to 3 data
                String floatStrs[] = curline.split(",");

                majunwu_NORMS[3*i] = Float.parseFloat(floatStrs[0]);
                majunwu_NORMS[3*i+1] = Float.parseFloat(floatStrs[1]);
                majunwu_NORMS[3*i+2] = Float.parseFloat(floatStrs[2]);
            }

            return floatsToRead;


        } finally
        {
            if (inputFile != null)
                inputFile.close();
        }
    }

    private void setVerts()
    {
        int num = 0;
        try {
            num = loadVertsFromModel();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        mVertBuff = fillBuffer(majunwu_VERTS);
        verticesNumber = num ;
    }


    private void setTexCoords()
    {
        int num = 0;
        try {
            num = loadTexCoordsFromModel();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        mTexCoordBuff = fillBuffer(majunwu_TEX_COORDS);

    }


    private void setNorms()
    {
        int num = 0;
        try {
            num = loadNormsFromModel();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        mNormBuff = fillBuffer(majunwu_NORMS);
    }





    public int getNumObjectIndex()
    {
        return 0;
    }


    @Override
    public int getNumObjectVertex()
    {
        return verticesNumber;
    }


    @Override
    public Buffer getBuffer(BUFFER_TYPE bufferType)
    {
        Buffer result = null;
        switch (bufferType)
        {
            case BUFFER_TYPE_VERTEX:
                result = mVertBuff;
                break;
            case BUFFER_TYPE_TEXTURE_COORD:
                result = mTexCoordBuff;
                break;
            case BUFFER_TYPE_NORMALS:
                result = mNormBuff;
                break;
            default:
                break;

        }

        return result;
    }


}

