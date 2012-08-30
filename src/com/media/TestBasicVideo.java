package com.media;
import java.io.IOException;  

import android.app.Activity;  
import android.content.pm.ActivityInfo;  
import android.graphics.PixelFormat;  
import android.media.MediaRecorder;  
import android.os.Bundle;  
import android.view.SurfaceHolder;  
import android.view.SurfaceView;  
import android.view.View;  
import android.view.View.OnClickListener;  
import android.view.Window;  
import android.view.WindowManager;  
import android.widget.Button;  
  
/** 
 * class name��TestBasicVideo<BR> 
 * class description��һ���򵥵�¼����Ƶ����<BR> 
 * PS��ʵ�ֻ�����¼�Ʊ����ļ� <BR> 
 *  
 */  
public class TestBasicVideo extends Activity implements SurfaceHolder.Callback {  
    private Button start;// ��ʼ¼�ư�ť  
    private Button stop;// ֹͣ¼�ư�ť  
    private MediaRecorder mediarecorder;// ¼����Ƶ����  
    private SurfaceView surfaceview;// ��ʾ��Ƶ�Ŀؼ�  
    // ������ʾ��Ƶ��һ���ӿڣ��ҿ����û����У�Ҳ����˵��mediarecorder¼����Ƶ���ø������濴  
    // ��͵͵¼��Ƶ��ͬѧ���Կ��Ǳ�İ취��������Ҫʵ������ӿڵ�Callback�ӿ�  
    private SurfaceHolder surfaceHolder;  
  
    public void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);  
        requestWindowFeature(Window.FEATURE_NO_TITLE);// ȥ��������  
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,  
                WindowManager.LayoutParams.FLAG_FULLSCREEN);// ����ȫ��  
        // ���ú�����ʾ  
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);  
        // ѡ��֧�ְ�͸��ģʽ,����surfaceview��activity��ʹ�á�  
        getWindow().setFormat(PixelFormat.TRANSLUCENT);  
        setContentView(R.layout.main);  
        init();  
    }  
  
    private void init() {  
        start = (Button) this.findViewById(R.id.start);  
        stop = (Button) this.findViewById(R.id.stop);  
        start.setOnClickListener(new TestVideoListener());  
        stop.setOnClickListener(new TestVideoListener());  
        surfaceview = (SurfaceView) this.findViewById(R.id.surfaceview);  
        SurfaceHolder holder = surfaceview.getHolder();// ȡ��holder  
        holder.addCallback(this); // holder����ص��ӿ�  
        // setType�������ã�Ҫ������.  
        holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);  
    }  
  
    class TestVideoListener implements OnClickListener {  
  
        @Override  
        public void onClick(View v) {  
            if (v == start) {  
                mediarecorder = new MediaRecorder();// ����mediarecorder����  
                // ����¼����ƵԴΪCamera(���)  
                mediarecorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);  
                // ����¼����ɺ���Ƶ�ķ�װ��ʽTHREE_GPPΪ3gp.MPEG_4Ϊmp4  
                mediarecorder  
                        .setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);  
                // ����¼�Ƶ���Ƶ����h263 h264  
                mediarecorder.setVideoEncoder(MediaRecorder.VideoEncoder.H264);  
                // ������Ƶ¼�Ƶķֱ��ʡ�����������ñ���͸�ʽ�ĺ��棬���򱨴�  
                mediarecorder.setVideoSize(176, 144);  
                // ����¼�Ƶ���Ƶ֡�ʡ�����������ñ���͸�ʽ�ĺ��棬���򱨴�  
                mediarecorder.setVideoFrameRate(20);  
                mediarecorder.setPreviewDisplay(surfaceHolder.getSurface());  
                // ������Ƶ�ļ������·��  
                mediarecorder.setOutputFile("/sdcard/love.3gp");  
                try {  
                    // ׼��¼��  
                    mediarecorder.prepare();  
                    // ��ʼ¼��  
                    mediarecorder.start();  
                } catch (IllegalStateException e) {  
                    // TODO Auto-generated catch block  
                    e.printStackTrace();  
                } catch (IOException e) {  
                    // TODO Auto-generated catch block  
                    e.printStackTrace();  
                }  
            }  
            if (v == stop) {  
                if (mediarecorder != null) {  
                    // ֹͣ¼��  
                    mediarecorder.stop();  
                    // �ͷ���Դ  
                    mediarecorder.release();  
                    mediarecorder = null;  
                }  
            }  
  
        }  
  
    }  
  
    @Override  
    public void surfaceChanged(SurfaceHolder holder, int format, int width,  
            int height) {  
        // ��holder�����holderΪ��ʼ��oncreat����ȡ�õ�holder����������surfaceHolder  
        surfaceHolder = holder;  
    }  
  
    @Override  
    public void surfaceCreated(SurfaceHolder holder) {  
        // ��holder�����holderΪ��ʼ��oncreat����ȡ�õ�holder����������surfaceHolder  
        surfaceHolder = holder;  
    }  
  
    @Override  
    public void surfaceDestroyed(SurfaceHolder holder) {  
        // surfaceDestroyed��ʱ��ͬʱ��������Ϊnull  
        surfaceview = null;  
        surfaceHolder = null;  
        mediarecorder = null;  
    }  
}  