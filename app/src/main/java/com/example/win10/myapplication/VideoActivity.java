package com.example.win10.myapplication;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.MediaController;
import android.widget.Switch;
import android.widget.Toast;
import android.widget.VideoView;

import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

public class VideoActivity extends Activity {

    SimpleExoPlayerView playerView;
    SimpleExoPlayer player;
    ProgressDialog progDailog;
    String pos;
    int actpos=0;
    String path;
    VideoView simpleVideoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
     //   playerView=(SimpleExoPlayerView)findViewById(R.id.video_view) ;
       // playerView = findViewById(R.id.video_view);

        Intent i=getIntent();
        pos=i.getStringExtra("Posi");
        actpos= Integer.parseInt(pos);

       // Toast.makeText(this,"Position"+pos,Toast.LENGTH_SHORT).show();

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

         simpleVideoView = (VideoView) findViewById(R.id.simpleVideoView);

      //  setContentView(R.layout.activity_main);

      //  https://sample-videos.com/video123/mp4/720/big_buck_bunny_720p_1mb.mp4

        MediaController mediaController = new MediaController(this);
// initiate a video view



        mediaController.setPrevNextListeners(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Handle next click here
                actpos=actpos+1;
                InitvideoUrl();

              //  Toast.makeText(VideoActivity.this,"NEXT",Toast.LENGTH_SHORT).show();
            }
        }, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Handle previous click here
                actpos=actpos-1;
                InitvideoUrl();
             //   Toast.makeText(VideoActivity.this,"Prev",Toast.LENGTH_SHORT).show();
            }
        });

// set media controller object for a video view
        simpleVideoView.setMediaController(mediaController) ;// initiate a video view
        InitvideoUrl();


        simpleVideoView.start();



        simpleVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {

            public void onPrepared(MediaPlayer mp) {
                // TODO Auto-generated method stub
                progDailog.dismiss();
            }
        });

    }

    void InitvideoUrl()
    {
        switch(actpos){
            case 0:
                //whatever
                path = "http://akshaydemo.000webhostapp.com/Trailers/Videos/Rustom.mp4";
                break;

            case 1:
                //whatever
                path = "http://akshaydemo.000webhostapp.com/Trailers/Videos/EkVillain.mp4";
                break;

            case 2:
                //whatever
                path = "http://akshaydemo.000webhostapp.com/Trailers/Videos/Manikarnika.mp4";
                break;

            case 3:
                //whatever
                path = "http://akshaydemo.000webhostapp.com/Trailers/Videos/Raid.mp4";
                break;

            case 4:
                //whatever
                path = "http://akshaydemo.000webhostapp.com/Trailers/Videos/Sarkari.mp4";
                break;

            default:
                 finish();
                //whatever
                break;
        }
        progDailog = ProgressDialog.show(this, "Please wait ...", "Retrieving data ...", true);

        simpleVideoView.setVideoURI(Uri.parse(path));
        simpleVideoView.start();
    }

    private void initializePlayer() {
        BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
        TrackSelection.Factory videoTrackSelectionFactory =
                new AdaptiveTrackSelection.Factory(bandwidthMeter);
        TrackSelector trackSelector =
                new DefaultTrackSelector(videoTrackSelectionFactory);

        //Initialize the player
        player = ExoPlayerFactory.newSimpleInstance(this, trackSelector);
        playerView.setPlayer(player);
        //Initialize simpleExoPlayerView



        // Produces DataSource instances through which media data is loaded.
        DataSource.Factory dataSourceFactory =
                new DefaultDataSourceFactory(this, Util.getUserAgent(this, "CloudinaryExoplayer"));

        // Produces Extractor instances for parsing the media data.
        ExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();

        // This is the MediaSource representing the media to be played.
        Uri videoUri = Uri.parse("https://www.youtube.com/watch?v=npT3Jndia5Q&t=5s");
        MediaSource videoSource = new ExtractorMediaSource(videoUri,
                dataSourceFactory, extractorsFactory, null, null);

        // Prepare the player with the source.
        player.prepare(videoSource);


    }

    @Override
    protected void onStart() {
        super.onStart();
        if (Util.SDK_INT > 23) {
         //   initializePlayer();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
     //   hideSystemUi();
        if ((Util.SDK_INT <= 23 || player == null)) {
         //   initializePlayer();
        }
    }

    @SuppressLint("InlinedApi")
    private void hideSystemUi() {
        playerView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }


}
