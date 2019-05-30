package SampleApplication.utils;

/**
        * Created by yun on 2018/12/3.
        */

import android.content.res.AssetManager;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.Buffer;


public class Banana extends MeshObject
{

    private static final String LOGTAG = "Banana";

    private Buffer mVertBuff;
    private Buffer mTexCoordBuff;
    private Buffer mNormBuff;
    private Buffer mIndBuff;

    private int indicesNumber = 0;
    private int verticesNumber = 0;

    private AssetManager assetManager;

    public Banana(AssetManager inputassetManager)
    {
        this.assetManager = inputassetManager;
        setVerts();
        setTexCoords();
        setNorms();

    }


    double[] banana_VERTS;
    double[] banana_TEX_COORDS;
    double[] banana_NORMS;

    InputStream inputFile = null;


    private int loadVertsFromModel(String fileName)
            throws IOException
    {
        try
        {
            inputFile = assetManager.open(fileName);

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(inputFile));

            String line = reader.readLine();

            //ex.24168 3 floatsdata in 1 line
//            int floatsToRead = Integer.parseInt(line);
            int floatsToRead = 24168;

            banana_VERTS = new double[3*floatsToRead];


            for (int i = 0; i < floatsToRead; i++)
            {

                String curline = reader.readLine();
                if( curline.indexOf('/') >= 0 ){
                    i--;
                    continue;
                }

                //split 1 line to 3 data
                String floatStrs[] = curline.split(",");

                banana_VERTS[3*i] = Float.parseFloat(floatStrs[0]);
                banana_VERTS[3*i+1] = Float.parseFloat(floatStrs[1]);
                banana_VERTS[3*i+2] = Float.parseFloat(floatStrs[2]);
                Log.d(LOGTAG, ""+i);
            }

            return floatsToRead;


        } finally
        {
            if (inputFile != null)
                inputFile.close();
        }
    }
    private int loadTexCoordsFromModel(String fileName)
            throws IOException
    {
        try
        {
            inputFile = assetManager.open(fileName);

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(inputFile));

            String line = reader.readLine();

            //ex.24168 3 floatsdata in 1 line
//            int floatsToRead = Integer.parseInt(line);
            int floatsToRead = 24168;

            banana_TEX_COORDS = new double[2*floatsToRead];


            for (int i = 0; i < floatsToRead; i++)
            {

                String curline = reader.readLine();
                if( curline.indexOf('/') >= 0 ){
                    i--;
                    continue;
                }

                //split 1 line to 2 data
                String floatStrs[] = curline.split(",");

                banana_TEX_COORDS[2*i] = Float.parseFloat(floatStrs[0]);
                banana_TEX_COORDS[2*i+1] = Float.parseFloat(floatStrs[1]);
                //banana_TEX_COORDS[3*i+2] = Float.parseFloat(floatStrs[2]);
            }

            return floatsToRead;


        } finally
        {
            if (inputFile != null)
                inputFile.close();
        }
    }

    private int loadNormsFromModel(String fileName)
            throws IOException
    {
        try
        {
            inputFile = assetManager.open(fileName);

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(inputFile));

            String line = reader.readLine();

            //ex.24168 3 floatsdata in 1 line
//            int floatsToRead = Integer.parseInt(line);
            int floatsToRead = 24168;

            banana_NORMS = new double[3*floatsToRead];


            for (int i = 0; i < floatsToRead; i++)
            {

                String curline = reader.readLine();
                if( curline.indexOf('/') >= 0 ){
                    i--;
                    continue;
                }

                //split 1 line to 3 data
                String floatStrs[] = curline.split(",");

                banana_NORMS[3*i] = Float.parseFloat(floatStrs[0]);
                banana_NORMS[3*i+1] = Float.parseFloat(floatStrs[1]);
                banana_NORMS[3*i+2] = Float.parseFloat(floatStrs[2]);
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
            num = loadVertsFromModel("ImageTargets/banana/verts.txt");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        mVertBuff = fillBuffer(banana_VERTS);
        verticesNumber = num ;
    }


    private void setTexCoords()
    {
        int num = 0;
        try {
            num = loadTexCoordsFromModel("ImageTargets/banana/texcoords.txt");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        mTexCoordBuff = fillBuffer(banana_TEX_COORDS);

    }


    private void setNorms()
    {
        int num = 0;
        try {
            num = loadNormsFromModel("ImageTargets/banana/normals.txt");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        mNormBuff = fillBuffer(banana_NORMS);
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

